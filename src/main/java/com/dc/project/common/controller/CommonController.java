package com.dc.project.common.controller;

import com.dc.common.constant.CustomConstant;
import com.dc.common.exception.ServiceException;
import com.dc.framework.config.properties.FtpProperties;
import com.dc.common.utils.FTPUtil;
import com.dc.common.utils.FileUtil;
import com.dc.project.basis.entity.SysMaterielFile;
import com.dc.project.basis.service.ISysMaterielFileService;
import com.dc.project.sales.entity.SysAccessory;
import com.dc.project.sales.service.ISysAccessoryService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.net.ftp.FTPClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@Slf4j(topic = "sys-user")
public class CommonController {
    @Autowired
    private ISysMaterielFileService materielFileService;
    @Autowired
    private ISysAccessoryService accessoryService;
    @Autowired
    private FtpProperties ftpProperties;

    /**
     * 下载图纸
     */
    @GetMapping("/download/materiel/{pkId}")
    public void downloadMateriel(@PathVariable Integer pkId, HttpServletRequest request, HttpServletResponse response) throws Exception {
        SysMaterielFile file = materielFileService.getById(pkId);
        if (null != file) {
            download(request, response, file.getName(), file.getPath(), file.getFileName());
        } else {
            throw new ServiceException("下载失败");
        }
    }

    /**
     * 下载附件
     */
    @GetMapping("/download/accessory/{pkId}")
    public void download(@PathVariable Integer pkId, HttpServletRequest request, HttpServletResponse response) throws Exception {
        SysAccessory file = accessoryService.getById(pkId);
        if (null != file) {
            download(request, response, file.getName(), file.getPath(), file.getFileName());
        } else {
            throw new ServiceException("下载失败");
        }
    }

    public void download(HttpServletRequest request, HttpServletResponse response, String name, String path, String fileName) throws IOException {
        response.setCharacterEncoding(CustomConstant.UTF8);
        response.setContentType("application/force-download");
        response.setHeader("Content-Disposition", "attachment;fileName=" + FileUtil.setFileDownloadHeader(request, name));
        FTPClient ftpClient = FTPUtil.loginFTP(ftpProperties.getHost(), ftpProperties.getPort(), ftpProperties.getUsername(), ftpProperties.getPassword());
        if (null != ftpClient) {
            boolean success = FTPUtil.downloadFile(ftpClient, path, fileName, response.getOutputStream());
            if (!success) throw new ServiceException("下载失败");
        }
    }

    /**
     * 首页
     */
    @RequestMapping("/")
    public String index() {
        return "404.html";
    }

}