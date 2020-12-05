package com.dc.common.utils;


import com.dc.common.constant.CustomConstant;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @Author zhuangcy
 * @Description ftp工具类
 * @Date 2020/9/12 12:03
 */
@Slf4j
public class FTPUtil {

    /**
     * 登陆FTP并获取FTPClient对象
     *
     * @param host     FTP主机地址
     * @param port     FTP端口
     * @param userName 登录用户名
     * @param password 登录密码
     * @return
     */
    public static FTPClient loginFTP(String host, int port, String userName, String password) {
        FTPClient ftpClient = null;
        try {
            ftpClient = new FTPClient();
            ftpClient.setConnectTimeout(1000 * 30);//设置连接超时时间
            ftpClient.connect(host, port);// 连接FTP服务器
            ftpClient.login(userName, password);// 登陆FTP服务器
            ftpClient.setControlEncoding(CustomConstant.UTF8);// 中文支持
            // 设置文件类型为二进制（如果从FTP下载或上传的文件是压缩文件的时候，不进行该设置可能会导致获取的压缩文件解压失败）
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
            ftpClient.enterLocalPassiveMode();//开启被动模式，否则文件上传不成功，也不报错
            if (!FTPReply.isPositiveCompletion(ftpClient.getReplyCode())) {
                log.info("连接FTP失败，用户名或密码错误。");
                ftpClient.disconnect();
            } else {
                log.info("FTP连接成功!");
            }
        } catch (Exception e) {
            log.info("登陆FTP失败，请检查FTP相关配置信息是否正确！" + e);
            return null;
        }
        return ftpClient;
    }

    /**
     * 从FTP下载文件到本地
     *
     * @param ftpClient     已经登陆成功的FTPClient
     * @param fileName      FTP上的目标文件路径+文件名称
     * @param localFilePath 下载到本地的文件路径
     * @param servicePath   服务器的上面文件的上层路径
     */
    public static String downloadFile(FTPClient ftpClient, String servicePath, String fileName, String localFilePath) {
        InputStream is = null;
        FileOutputStream fos = null;
        try {
            ftpClient.enterLocalPassiveMode();
            is = ftpClient.retrieveFileStream(servicePath + fileName);// 获取ftp上的文件
            fos = new FileOutputStream(new File(localFilePath + fileName));
            // 文件读取方式一
            int i;
            byte[] bytes = new byte[1024];
            while ((i = is.read(bytes)) != -1) {
                fos.write(bytes, 0, i);
            }
            // 文件读取方式二
            //ftpClient.retrieveFile(ftpFilePath, new FileOutputStream(new File(localFilePath)));
            ftpClient.completePendingCommand();
            log.info("FTP文件下载成功！");
        } catch (Exception e) {
            log.error("FTP文件下载失败！" + e);
        } finally {
            try {
                if (fos != null) fos.close();
                if (is != null) is.close();
            } catch (IOException e) {
                log.error("下载流关闭失败" + e);
                return null;
            }
        }
        return localFilePath + fileName;
    }

    /**
     * 请求下载文件
     *
     * @param ftpClient
     * @param servicePath
     * @param fileName
     * @param os
     * @return
     */
    public static boolean downloadFile(FTPClient ftpClient, String servicePath, String fileName, OutputStream os) {
        log.info("开始下载FTP文件");
        InputStream is = null;
        BufferedInputStream bis = null;
        try {
            log.info(servicePath + fileName);
            ftpClient.enterLocalPassiveMode();
            ftpClient.changeWorkingDirectory(servicePath);
            is = ftpClient.retrieveFileStream(fileName);
            bis = new BufferedInputStream(is);
            byte[] buf = new byte[1024];
            int len = 0;
            while ((len = bis.read(buf)) > 0) {
                os.write(buf, 0, len);
            }
            os.flush();
            ftpClient.completePendingCommand();
            log.info("FTP文件下载成功！");
            return true;
        } catch (Exception e) {
            log.error("FTP文件下载失败！" + e);
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    /**
     * 删除ftp文件
     *
     * @param ftpClient
     * @param servicePath
     * @param fileName
     * @return
     */
    public static String deleteFile(FTPClient ftpClient, String servicePath, String fileName) {
        try {
            log.info("开始删除FTP文件");
            ftpClient.changeWorkingDirectory(servicePath);
            ftpClient.deleteFile(servicePath + fileName);
            log.info("FTP文件删除成功！");
        } catch (Exception e) {
            log.error("FTP文件删除失败！" + e);
        }
        return fileName;
    }

    /**
     * 上传文件
     *
     * @param serviceDec     ftp服务保存地址
     * @param fileName       上传到ftp的文件名
     * @param originfilename 待上传文件的名称（绝对地址） *
     * @return
     */
    public static boolean uploadFile(FTPClient ftpClient, String serviceDec, String fileName, String originfilename) {
        log.info("开始上传文件");
        try (InputStream input = new FileInputStream(new File(originfilename))) {
            return uploadFile(ftpClient, serviceDec, fileName, input);
        } catch (FileNotFoundException e) {
            log.error("文件上传失败" + e);
        } catch (IOException e) {
            log.error("文件上传失败" + e);
        }
        return false;
    }

    /**
     * 上传文件
     *
     * @param path        ftp服务保存地址
     * @param fileName    上传到ftp的文件名
     * @param inputStream 输入文件流
     * @return
     */
    public static boolean uploadFile(FTPClient ftpClient, String path, String fileName, InputStream inputStream) {
        try {
            log.info("开始上传文件");
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
            createDirecroty(ftpClient, path);
            ftpClient.makeDirectory(path);
            ftpClient.changeWorkingDirectory(path);
            ftpClient.storeFile(fileName, inputStream);
            inputStream.close();
            ftpClient.logout();
            log.info("上传文件成功");
            return true;
        } catch (Exception e) {
            log.error("上传文件失败" + e);
        } finally {
            try {
                if (ftpClient.isConnected())
                    ftpClient.disconnect();
                if (null != inputStream)
                    inputStream.close();
            } catch (IOException e) {
                log.error("上传文件失败" + e);
            }
        }
        return false;
    }


    //改变目录路径
    private static boolean changeWorkingDirectory(FTPClient ftpClient, String directory) {
        boolean flag = true;
        try {
            flag = ftpClient.changeWorkingDirectory(directory);
            if (flag) {
                log.info("进入文件夹" + directory + " 成功！");

            } else {
                log.info("进入文件夹" + directory + " 失败！开始创建文件夹");
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return flag;
    }

    //创建多层目录文件，如果有ftp服务器已存在该文件，则不创建，如果无，则创建
    private static boolean createDirecroty(FTPClient ftpClient, String remote) throws IOException {
        boolean success = true;
        String directory = remote + "/";
        // 如果远程目录不存在，则递归创建远程服务器目录
        if (!directory.equalsIgnoreCase("/") && !changeWorkingDirectory(ftpClient, new String(directory))) {
            int start = 0;
            int end = 0;
            if (directory.startsWith("/")) {
                start = 1;
            } else {
                start = 0;
            }
            end = directory.indexOf("/", start);
            String path = "";
            String paths = "";
            while (true) {
                String subDirectory = new String(remote.substring(start, end).getBytes("GBK"), "iso-8859-1");
                path = path + "/" + subDirectory;
                if (!existFile(ftpClient, path)) {
                    if (makeDirectory(ftpClient, subDirectory)) {
                        changeWorkingDirectory(ftpClient, subDirectory);
                    } else {
                        log.info("创建目录[" + subDirectory + "]失败");
                        changeWorkingDirectory(ftpClient, subDirectory);
                    }
                } else {
                    changeWorkingDirectory(ftpClient, subDirectory);
                }

                paths = paths + "/" + subDirectory;
                start = end + 1;
                end = directory.indexOf("/", start);
                // 检查所有目录是否创建完毕
                if (end <= start) {
                    break;
                }
            }
        }
        return success;
    }

    //判断ftp服务器文件是否存在
    private static boolean existFile(FTPClient ftpClient, String path) throws IOException {
        boolean flag = false;
        FTPFile[] ftpFileArr = ftpClient.listFiles(path);
        if (ftpFileArr.length > 0) {
            flag = true;
        }
        return flag;
    }

    //创建目录
    private static boolean makeDirectory(FTPClient ftpClient, String dir) {
        boolean flag = true;
        try {
            flag = ftpClient.makeDirectory(dir);
            if (flag) {
                log.info("创建文件夹" + dir + " 成功！");

            } else {
                log.info("创建文件夹" + dir + " 失败！");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 获取FTP某一特定目录下的所有文件名称
     *
     * @param ftpClient  已经登陆成功的FTPClient
     * @param ftpDirPath FTP上的目标文件路径
     */
    public static List<String> getFileNameList(FTPClient ftpClient, String ftpDirPath) {
        List<String> list = new ArrayList();
        try {
            if (ftpDirPath.startsWith("/") && ftpDirPath.endsWith("/")) {
                // 通过提供的文件路径获取FTPFile对象列表
                FTPFile[] files = ftpClient.listFiles(ftpDirPath);
                // 遍历文件列表，打印出文件名称
                int length = files.length;
                for (int i = 0; i < length; i++) {
                    FTPFile ftpFile = files[i];
                    // 此处只打印文件，未遍历子目录（如果需要遍历，加上递归逻辑即可）
                    if (ftpFile.isFile()) {
//                        log.info(ftpDirPath + ftpFile.getName());
                        list.add(ftpFile.getName());
                    }
                }
                log.info("当前FTP路径可用");
            } else {
                log.info("当前FTP路径不可用");
            }
        } catch (IOException e) {
            log.error("错误" + e);
        }
        return list;
    }

    /**
     * 获取到服务器文件夹里面最新创建的文件名称
     *
     * @param ftpDirPath 文件路径
     * @param ftpClient  ftp的连接
     * @return fileName
     */
    public static String getNewFile(FTPClient ftpClient, String ftpDirPath) throws Exception {
        if (ftpDirPath.startsWith("/") && ftpDirPath.endsWith("/")) {
            // 通过提供的文件路径获取FTPFile对象列表
            FTPFile[] files = ftpClient.listFiles(ftpDirPath);
            if (files == null) throw new Exception("文件数组为空");
            Arrays.sort(files, new Comparator<FTPFile>() {
                public int compare(FTPFile f1, FTPFile f2) {
                    return f1.getTimestamp().compareTo(f2.getTimestamp());
                }

                public boolean equals(Object obj) {
                    return true;
                }
            });
            return ftpDirPath + "/" + files[files.length - 1].getName();
        } else throw new Exception("文件夹路径错误！");
    }
}



