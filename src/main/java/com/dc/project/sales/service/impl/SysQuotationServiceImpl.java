package com.dc.project.sales.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dc.common.lang.annotation.DataScope;
import com.dc.common.constant.SalesConstant;
import com.dc.common.exception.ServiceException;
import com.dc.common.utils.*;
import com.dc.common.vo.MaterielVo;
import com.dc.project.basis.service.ISysClienteleService;
import com.dc.project.basis.service.ISysMaterielFileService;
import com.dc.project.basis.service.ISysMaterielService;
import com.dc.project.sales.dao.SysQuotationDao;
import com.dc.project.sales.entity.*;
import com.dc.project.sales.service.*;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 销售报价主表 服务实现类
 *
 * @author zhuangcy
 * @since 2020-09-12
 */
@Service
public class SysQuotationServiceImpl extends ServiceImpl<SysQuotationDao, SysQuotation> implements ISysQuotationService {
    @Autowired
    private ISysQuotationSubService quotationSubService;
    @Autowired
    private ISysAccessoryService accessoryService;
    @Autowired
    private ISysMaterielFileService materielFileService;
    @Autowired
    private ISysOrderService orderService;
    @Autowired
    private ISysOrderSubService orderSubService;
    @Autowired
    private ISysClienteleService clienteleService;
    @Autowired
    private ISysMaterielService materielService;

    @DataScope(userAlias = "sq", userColumn = "create_id")
    @Override
    public IPage<SysQuotation> page(Page page, SysQuotation quotation) {
        return baseMapper.page(page, quotation);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Map<String, Object> saveAndUpdate(Map formMap) throws Exception {
        Object clienteleForm = formMap.get("clientele");
        Object materielListForm = formMap.get("materielList");
        Object delSubIdsForm = formMap.get("delSubIds");
        if (null == clienteleForm || null == materielListForm)
            throw new ServiceException("保存失败");
        SysQuotation quotation = new SysQuotation();
        BeanUtil.register();
        BeanUtils.populate(quotation, ObjectMapperUtil.toObject(clienteleForm.toString(), Map.class));
        if (null == quotation.getQuotationId()) { //新增主表
            quotation.setQuotationNum(CodeUtil.getCode(SalesConstant.SALES_QUOTATION_NO));
            quotation.setStatus(SalesConstant.SAVE);
            if (!this.save(quotation))
                throw new ServiceException("保存失败");
        } else { // 修改主表
            updateQuotation(quotation);
        }
        List<Map> materielList = ObjectMapperUtil.toObject(materielListForm.toString(), List.class);
        //新增修改子表
        saveAndUpdateQuotationSub(quotation, materielList);
        // 删除子表
        if (null != delSubIdsForm) {
            List<Long> delSubIds = ObjectMapperUtil.toObject(delSubIdsForm.toString(), List.class);
            if (!delSubIds.isEmpty()) {
                quotationSubService.removeByIds(delSubIds);
                //删除子表图片
                deleteFile(delSubIds);
            }
        }
        Map<String, Object> result = new HashMap<>();
        result.put("quotationNum", quotation.getQuotationNum());
        result.put("quotationId", quotation.getQuotationId());
        return result;
    }


    private void saveAndUpdateQuotationSub(SysQuotation quotation, List<Map> materielList) throws InvocationTargetException, IllegalAccessException {
        if (null == materielList && materielList.isEmpty())
            throw new ServiceException("保存失败,未添加报价产品");
        List<SysQuotationSub> updateList = new ArrayList<>();
        List<SysQuotationSub> addList = new ArrayList<>();
        for (Map map : materielList) {
            SysQuotationSub quotationSub = new SysQuotationSub();
            BeanUtils.populate(quotationSub, map);
            quotationSub.setQuotationId(quotation.getQuotationId());
            if (null == quotationSub.getSubId())
                addList.add(quotationSub);
            else
                updateList.add(quotationSub);
        }
        if (!updateList.isEmpty())
            if (!quotationSubService.updateBatchById(updateList))
                throw new ServiceException("修改失败");

        if (!addList.isEmpty()) {
            if (!quotationSubService.saveBatch(addList))
                throw new ServiceException("保存失败");
            // 带出产品的图片另外做保存
//            for (SysQuotationSub sub : addList) {
//                if (null != sub.getMaterielId()) {
//                    List<SysMaterielFile> fileList = materielFileService.list(new QueryWrapper<SysMaterielFile>().eq("materiel_id", sub.getMaterielId()));
//                    if (null == fileList || fileList.isEmpty()) continue;
//                    for (SysMaterielFile materielFile : fileList) {
//                        SysAccessory sysAccessory = new SysAccessory();
//                        BeanUtil.copyBeanProp(sysAccessory, materielFile);
//                        sysAccessory.setPkId(null);
//                        sysAccessory.setSubId(sub.getSubId());
//                        accessoryService.save(sysAccessory);
//                    }
//                }
//            }
        }
    }


    private void updateQuotation(SysQuotation quotation) {
        if (!this.updateById(quotation))
            throw new ServiceException("修改失败");
    }

    @Override
    public SysQuotation get(Integer quotationId) {
        return baseMapper.get(quotationId);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean delete(Integer quotationId) {
        SysQuotation quotation = this.getById(quotationId);
        if (null == quotation) throw new ServiceException("已删除");
        SalesConstant.verifyDeleteStatus(quotation.getStatus());
        if (!this.removeById(quotationId))
            throw new ServiceException(String.format("%s,删除失败", quotation.getQuotationNum()));
        QueryWrapper<SysQuotationSub> queryWrapper = new QueryWrapper<SysQuotationSub>().eq("quotation_id", quotationId);
        List<SysQuotationSub> list = quotationSubService.list(queryWrapper);
        if (!quotationSubService.remove(queryWrapper))
            throw new ServiceException("删除失败");
        // 删除对应的文件图片
        deleteFile(list.stream().map(item -> item.getSubId()).collect(Collectors.toList()));
        return true;
    }

    private void deleteFile(List<Long> ids) {
        List<SysAccessory> accessoryList = accessoryService.list(new QueryWrapper<SysAccessory>().in("sub_id", ids));
        for (SysAccessory accessory : accessoryList) {
            accessoryService.delete(accessory.getPkId());
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean submit(Integer[] ids, String status) {
        if (ids.length == 0)
            throw new ServiceException("提交失败");
        List<SysQuotation> list = this.list(new QueryWrapper<SysQuotation>()
                .select("quotation_id,quotation_num,status").in("quotation_id", Arrays.asList(ids)));
        if (list == null || list.isEmpty())
            throw new ServiceException("操作失败");
        ArrayList<Integer> idList = new ArrayList<>();
        for (SysQuotation quotation : list) {
            SalesConstant.verifySubmitStatus(status, quotation.getQuotationNum(), quotation.getStatus());
            idList.add(quotation.getQuotationId());
        }
        UpdateWrapper<SysQuotation> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set("status", status).in("quotation_id", idList);
        return this.update(updateWrapper);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean audit(SysQuotation quotation) {
        SysQuotation sysQuotation = this.getOne(new QueryWrapper<SysQuotation>().select("quotation_id,status").eq("quotation_id", quotation.getQuotationId()));
        SalesConstant.verifyAuditStatus(quotation.getStatus(), sysQuotation.getStatus());
        quotation.setAuditTime(new Date());
        quotation.setAuditBy(UserSecurityUtil.getUsername());
        return this.updateById(quotation);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public String saveOrder(Map<String, Object> formMap) throws InvocationTargetException, IllegalAccessException {
        Object clienteleForm = formMap.get("clientele");
        Object materielListForm = formMap.get("materielList");
        if (null == clienteleForm || null == materielListForm)
            throw new ServiceException("生成订单失败");
        Map objMap = ObjectMapperUtil.toObject(clienteleForm.toString(), Map.class);
        Date effectiveTime = (Date) objMap.get("effectiveTime");
        if (null == effectiveTime || !DateUtil.compareToYYYYMMDD(effectiveTime, new Date())) {
            throw new ServiceException("已过期，无法生成订单");
        }
        SysOrder sysOrder = new SysOrder();
        BeanUtil.register();
        BeanUtils.populate(sysOrder, objMap);
        String clienteleNum = sysOrder.getClienteleNum();
        String clienteleName = sysOrder.getClienteleName();
        if (StringUtils.isEmpty(clienteleNum) || StringUtils.isEmpty(clienteleName)) {
            throw new ServiceException("客户编码或名称不能为空");
        } else {
//            SysClientele clientele = clienteleService.getOne(new QueryWrapper<SysClientele>()
//                    .eq("clientele_num", clienteleNum).eq("clientele_name", clienteleName));
//            if (null == clientele) {
//                throw new ServiceException(String.format("客户档案不存在该客户(编码%s,名称%s)", clienteleNum, clienteleName));
//            }
//           sysOrder.setClienteleId(clientele.getClienteleId());
            sysOrder.setOrderTime(new Date());
            sysOrder.setAuditBy(null);
            sysOrder.setAuditTime(null);
            sysOrder.setStatus(SalesConstant.SAVE);
        }
        String code = CodeUtil.getCode(SalesConstant.SALES_ORDER_NO);
        sysOrder.setOrderNum(code);
        if (!orderService.save(sysOrder)) {
            throw new ServiceException("生成订单失败");
        }
        List<Map<String, Object>> subList = ObjectMapperUtil.toObject(materielListForm.toString(), List.class);
        for (Map<String, Object> map : subList) {
            SysOrderSub orderSub = new SysOrderSub();
            BeanUtils.populate(orderSub, map);
            String materielNum = orderSub.getMaterielNum();
            String materielName = orderSub.getMaterielName();
            String modelName = orderSub.getModelName();
            if (StringUtils.isEmpty(materielNum) || StringUtils.isEmpty(modelName)) {
                throw new ServiceException(String.format("产品档案不存在该产品(编码:%s,名称:%s,型号:%s)", materielNum, materielName, modelName));
            } else {
                MaterielVo materielVo = new MaterielVo();
                BeanUtil.copyBeanProp(materielVo, orderSub);
                MaterielVo materiel = materielService.findMateriel(materielVo);
                if (null == materiel) {
                    throw new ServiceException(String.format("产品档案不存在该产品(编码:%s,名称:%s,型号:%s)", materielNum, materielName, modelName));
                } else {
                    orderSub.setOrderId(sysOrder.getOrderId());
                    orderSub.setMaterielId(materiel.getMaterielId());
                    if (!orderSubService.save(orderSub))
                        throw new ServiceException("生成订单失败");
                }
            }
        }
        return code;
    }
}
