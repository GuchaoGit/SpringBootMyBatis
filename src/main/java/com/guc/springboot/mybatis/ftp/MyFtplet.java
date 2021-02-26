package com.guc.springboot.mybatis.ftp;

import org.apache.ftpserver.ftplet.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableAsync;

import java.io.IOException;
import java.net.InetSocketAddress;

/**
 * @Author: guc
 * @Description:
 * @Date: 2021/2/24 13:55
 * @Version: 1.0
 */
public class MyFtplet extends DefaultFtplet {
    private static final Logger logger = LoggerFactory.getLogger(MyFtplet.class);
    @Override
    public FtpletResult onUploadStart(FtpSession session, FtpRequest request) throws FtpException, IOException {
        return super.onUploadStart(session, request);
    }

    @Override
    public FtpletResult onUploadEnd(FtpSession session, FtpRequest request) throws FtpException, IOException {
        //获取上传文件的上传路径
        String path = session.getUser().getHomeDirectory();
        //获取上传用户
        String name = session.getUser().getName();
        //获取上传文件名
        String filename = request.getArgument();
        InetSocketAddress clientAddress = session.getClientAddress();
        FtpFile file = session.getFileSystemView().getFile(filename);
        String pathName = file.getAbsolutePath();
        logger.info("用户ip:" + clientAddress);
        logger.info("用户:'{}'，上传文件到目录：'{}'，文件名称为：'{}，状态：成功！'", name, path, filename);
        //解析mac地址
        Utils.parseMacFromFile(clientAddress.getHostName(), path, filename);
        return super.onUploadEnd(session, request);
    }
}
