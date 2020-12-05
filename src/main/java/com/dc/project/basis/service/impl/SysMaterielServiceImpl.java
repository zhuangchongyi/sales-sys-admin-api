package com.dc.project.basis.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dc.common.constant.CustomConstant;
import com.dc.common.exception.ServiceException;
import com.dc.common.vo.MaterielVo;
import com.dc.project.basis.dao.SysMaterielDao;
import com.dc.project.basis.entity.SysMateriel;
import com.dc.project.basis.entity.SysMaterielModel;
import com.dc.project.basis.service.ISysMaterielModelService;
import com.dc.project.basis.service.ISysMaterielService;
import com.dc.project.open.vo.ItemVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 产品档案表 服务实现类
 *
 * @author zhuangchongyi
 * @since 2020-09-03
 */
@Service
public class SysMaterielServiceImpl extends ServiceImpl<SysMaterielDao, SysMateriel> implements ISysMaterielService {
    @Autowired
    private ISysMaterielModelService materielModelService;

    @Override
    public IPage<SysMateriel> page(Page page, SysMateriel materiel) {
        return baseMapper.page(page, materiel);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean insert(SysMateriel materiel) {
        SysMateriel one = this.getOne(new QueryWrapper<SysMateriel>()
                .select("materiel_id").eq("materiel_num", materiel.getMaterielNum()), false);
        if (one != null) {
            throw new ServiceException(String.format("编码不允许重复,%s", materiel.getMaterielNum()));
        }
        if (!this.save(materiel)) {
            throw new ServiceException("添加失败");
        }
        insertModel(materiel);
        return true;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean update(SysMateriel materiel) {
        SysMateriel res = this.getOne(new QueryWrapper<SysMateriel>()
                .select("materiel_id").eq("materiel_num", materiel.getMaterielNum()), false);
        if (null != res && !res.getMaterielId().equals(materiel.getMaterielId())) {
            throw new ServiceException(String.format("编码不允许重复,%s", materiel.getMaterielNum()));
        }
        verifyModel(materiel);
        if (!this.updateById(materiel)) {
            throw new ServiceException("修改失败");
        }
        return true;
    }

    private void verifyModel(SysMateriel materiel) {
        // 查询所有型号
        LambdaQueryWrapper<SysMaterielModel> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysMaterielModel::getMId, materiel.getMaterielId());
        List<SysMaterielModel> allModels = materielModelService.list(queryWrapper);
        List<String> allModelNames = allModels.stream().map(SysMaterielModel::getModelName).collect(Collectors.toList());
        // 传入的产品型号
        List<String> nowModelNames = Arrays.asList(materiel.getModelNames());
        // 新加的部分
        String[] models = nowModelNames.stream().filter(name -> allModelNames.stream().noneMatch(name::equals)).toArray(String[]::new);
        if (0 != models.length) {
            materiel.setModelNames(models);
            insertModel(materiel);
        }
        // 删除的型号
        List<SysMaterielModel> delModels = allModels.stream().filter(model -> nowModelNames.stream().noneMatch(model.getModelName()::equals)).collect(Collectors.toList());
        if (!delModels.isEmpty()) {
            List<Long> idList = delModels.stream().map(SysMaterielModel::getPkId).collect(Collectors.toList());
            if (!materielModelService.removeByIds(idList)) {
                throw new ServiceException("删除型号失败");
            }
        }
    }

    @Override
    public SysMateriel get(Integer materielId) {
        return baseMapper.get(materielId);
    }

    @Override
    public IPage list(Page page, SysMateriel materiel) {
        return baseMapper.list(page, materiel);
    }

    @Override
    public MaterielVo findMateriel(MaterielVo materiel) {
        return baseMapper.findMateriel(materiel);
    }

    @Override
    public boolean verifyStatus(Integer id) {
        SysMateriel one = this.getById(id);
        if (null == one) return false;
        if (CustomConstant.STOP_STATUS.equals(one.getStatus()))
            throw new ServiceException(String.format("该产品已被停用(%s,%s)", one.getMaterielNum(), one.getMaterielName()));
        return true;
    }

    private void insertModel(SysMateriel materiel) {
        String[] modelNames = materiel.getModelNames();
        Integer materielId = materiel.getMaterielId();
        List<SysMaterielModel> list = Stream.of(modelNames)
                .filter(StringUtils::isNotEmpty)
                .map(model -> new SysMaterielModel().setMId(materielId).setPrice(materiel.getPrice()).setModelName(model.trim()))
                .collect(Collectors.toList());
        if (!materielModelService.saveBatch(list)) {
            throw new ServiceException("新增型号失败");
        }
    }


    @Override
    public List<ItemVo> listItem(Page page) {
        return baseMapper.listItem(page);
    }

    @Override
    public ItemVo detailItem(Integer id) {
        return baseMapper.detailItem(id);
    }

    @Override
    public List<SysMaterielModel> models(SysMateriel materiel) {
        QueryWrapper<SysMaterielModel> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .eq(SysMaterielModel::getMId, materiel.getMaterielId())
                .orderByDesc(SysMaterielModel::getPkId);
        return materielModelService.list(queryWrapper);
    }

    @Override
    public boolean models(List<SysMaterielModel> models) {
        return materielModelService.updateBatchById(models);
    }

    @Override
    public List<ItemVo> listItemByCategoryId(Page page, Integer categoryId) {
        return baseMapper.listItemByCategoryId(page, categoryId);
    }
}
