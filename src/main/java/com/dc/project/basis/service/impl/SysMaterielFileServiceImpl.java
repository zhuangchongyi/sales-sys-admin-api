package com.dc.project.basis.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dc.common.exception.ServiceException;
import com.dc.common.utils.CodeUtil;
import com.dc.common.utils.DateUtil;
import com.dc.common.utils.FTPUtil;
import com.dc.framework.config.properties.FtpProperties;
import com.dc.project.basis.dao.SysMaterielFileDao;
import com.dc.project.basis.entity.SysMaterielFile;
import com.dc.project.basis.service.ISysMaterielFileService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.net.ftp.FTPClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.Objects;

/**
 * 产品文件表 服务实现类
 *
 * @author zhuangchongyi
 * @since 2020-09-05
 */
@Service
@Slf4j
public class SysMaterielFileServiceImpl extends ServiceImpl<SysMaterielFileDao, SysMaterielFile> implements ISysMaterielFileService {
    @Autowired
    private FtpProperties ftpProperties;


    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean upload(Integer materielId, MultipartFile file) {
        if (null != materielId && null != file) {
            FTPClient ftpClient = FTPUtil.loginFTP(ftpProperties.getHost(), ftpProperties.getPort(), ftpProperties.getUsername(), ftpProperties.getPassword());
            if (null != ftpClient) {
                String name = file.getOriginalFilename();
                String fileName = CodeUtil.randomUUIDNotRail() + Objects.requireNonNull(name).substring(name.indexOf('.'));
                String fileDir = DateUtil.getYYYYMMPathString(new Date());
                boolean success = false;
                String url = ftpProperties.getUrlPrefix() + fileDir + fileName;
                String path = ftpProperties.getPath() + fileDir;
                try {
                    success = FTPUtil.uploadFile(ftpClient, path, fileName, file.getInputStream());
                } catch (IOException e) {
                    throw new ServiceException();
                }
                if (success) {
                    SysMaterielFile materielFile = new SysMaterielFile();
                    materielFile.setMaterielId(materielId)
                            .setFileName(fileName)
                            .setName(name)
                            .setPath(path)
                            .setUrl(url);
                    if (this.save(materielFile)) {
                        return true;
                    }
                }
            }
        }
        throw new ServiceException("上传失败");
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean delete(Long pkId) {
//        SysMaterielFile materielFile = baseMapper.selectById(pkId);
        if (!this.removeById(pkId))
            throw new ServiceException("已删除");
        //删除文件
//        FTPClient ftpClient = FTPUtil.loginFTP(ftpProperties.getHost(), ftpProperties.getPort(), ftpProperties.getUsername(), ftpProperties.getPassword());
//        FTPUtil.deleteFile(ftpClient, materielFile.getPath(), materielFile.getFileName());
        return true;
    }

}
