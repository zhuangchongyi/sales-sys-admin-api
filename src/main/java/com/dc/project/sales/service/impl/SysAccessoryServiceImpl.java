package com.dc.project.sales.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dc.common.exception.ServiceException;
import com.dc.common.utils.CodeUtil;
import com.dc.common.utils.DateUtil;
import com.dc.common.utils.FTPUtil;
import com.dc.common.vo.FtpEntity;
import com.dc.project.sales.dao.SysAccessoryDao;
import com.dc.project.sales.entity.SysAccessory;
import com.dc.project.sales.service.ISysAccessoryService;
import org.apache.commons.net.ftp.FTPClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;

/**
 * 附件表 服务实现类
 *
 * @author zhuangcy
 * @since 2020-09-17
 */
@Service
public class SysAccessoryServiceImpl extends ServiceImpl<SysAccessoryDao, SysAccessory> implements ISysAccessoryService {

    @Autowired
    private FtpEntity ftpEntity;


    @Transactional
    @Override
    public boolean upload(Long id, MultipartFile file) {
        try {
            if (null == id || null == file) {
                throw new ServiceException("上传失败");
            }
            String name = file.getOriginalFilename();
            String fileName = CodeUtil.randomUUIDNotRail() + name.substring(name.indexOf('.'));
            String fileDir = DateUtil.getYYYYMMPathString(new Date());
            String url = ftpEntity.getUrlPrefix() + fileDir + fileName;
            String path = ftpEntity.getPath() + fileDir;
            FTPClient ftpClient = FTPUtil.loginFTP(ftpEntity.getHost(), ftpEntity.getPort(), ftpEntity.getUsername(), ftpEntity.getPassword());
            if (null != ftpClient) {
                boolean success = FTPUtil.uploadFile(ftpClient, path, fileName, file.getInputStream());
                if (success) {
                    SysAccessory accessory = new SysAccessory();
                    accessory.setSubId(id)
                            .setFileName(fileName)
                            .setName(name)
                            .setPath(path)
                            .setUrl(url);
                    if (!this.save(accessory)) {
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

    @Transactional
    @Override
    public boolean delete(Long pkId) {
        SysAccessory accessory = this.getById(pkId);
        boolean row = this.removeById(pkId);
        //删除文件
//        if (null == accessory.getMaterielId()) {
        FTPClient ftpClient = FTPUtil.loginFTP(ftpEntity.getHost(), ftpEntity.getPort(), ftpEntity.getUsername(), ftpEntity.getPassword());
        FTPUtil.deleteFile(ftpClient, accessory.getPath(), accessory.getFileName());
//        }
        return row;
    }
}
