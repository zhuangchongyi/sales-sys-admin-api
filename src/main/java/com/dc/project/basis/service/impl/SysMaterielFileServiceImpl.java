package com.dc.project.basis.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dc.common.exception.ServiceException;
import com.dc.common.utils.CodeUtil;
import com.dc.common.utils.DateUtil;
import com.dc.common.utils.FTPUtil;
import com.dc.framework.config.properties.FtpConfig;
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
    private FtpConfig ftpConfig;


    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean upload(Integer materielId, MultipartFile file) {
        try {
            if (null == materielId || null == file) {
                throw new ServiceException("上传失败");
            }
            String name = file.getOriginalFilename();
            String fileName = CodeUtil.randomUUIDNotRail() + name.substring(name.indexOf('.'));
            String fileDir = DateUtil.getYYYYMMPathString(new Date());
            String url = ftpConfig.getUrlPrefix() + fileDir + fileName;
            String path = ftpConfig.getPath() + fileDir;
            FTPClient ftpClient = FTPUtil.loginFTP(ftpConfig.getHost(), ftpConfig.getPort(), ftpConfig.getUsername(), ftpConfig.getPassword());
            if (null != ftpClient) {
                boolean success = FTPUtil.uploadFile(ftpClient, path, fileName, file.getInputStream());
                if (success) {
                    SysMaterielFile materielFile = new SysMaterielFile();
                    materielFile.setMaterielId(materielId)
                            .setFileName(fileName)
                            .setName(name)
                            .setPath(path)
                            .setUrl(url);
                    if (!this.save(materielFile)) {
                        throw new ServiceException("上传失败");
                    }
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new ServiceException("上传失败");
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean delete(Long pkId) {
        SysMaterielFile materielFile = baseMapper.selectById(pkId);
        int row = baseMapper.deleteById(pkId);
        //删除文件
        FTPClient ftpClient = FTPUtil.loginFTP(ftpConfig.getHost(), ftpConfig.getPort(), ftpConfig.getUsername(), ftpConfig.getPassword());
        FTPUtil.deleteFile(ftpClient, materielFile.getPath(), materielFile.getFileName());
        return row > 0;
    }

}
