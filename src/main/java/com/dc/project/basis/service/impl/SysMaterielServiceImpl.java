package com.dc.project.basis.service.impl;

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
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        if (one != null) throw new ServiceException(String.format("编码不允许重复,%s", materiel.getMaterielNum()));
        if (!this.save(materiel)) throw new ServiceException("添加失败");
        insertModel(materiel, false);
        return true;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean update(SysMateriel materiel) {
        SysMateriel res = this.getOne(new QueryWrapper<SysMateriel>()
                .select("materiel_id").eq("materiel_num", materiel.getMaterielNum()), false);
        if (null != res && !res.getMaterielId().equals(materiel.getMaterielId()))
            throw new ServiceException(String.format("编码不允许重复,%s", materiel.getMaterielNum()));
        if (!this.updateById(materiel)) throw new ServiceException("修改失败");
        insertModel(materiel, true);
        return true;
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

    private void insertModel(SysMateriel materiel, boolean delFlag) {
        String[] modelNames = materiel.getModelNames();
        int length = modelNames.length;
        if (length != 0) {
            Integer materielId = materiel.getMaterielId();
            if (delFlag) {
                materielModelService.remove(new QueryWrapper<SysMaterielModel>().eq("m_id", materielId));
            }
            List<SysMaterielModel> list = Stream.of(modelNames)
                    .filter(model -> StringUtils.isNotEmpty(model))
                    .map(model -> new SysMaterielModel().setMId(materielId).setModelName(model))
                    .collect(Collectors.toList());
            if (!materielModelService.saveBatch(list))
                throw new ServiceException("操作失败");
        } else {
            throw new ServiceException("产品未添加型号");
        }
    }
}
