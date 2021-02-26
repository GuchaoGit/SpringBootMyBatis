package com.guc.springboot.mybatis.ftp;

import org.apache.ftpserver.DataConnectionConfigurationFactory;
import org.apache.ftpserver.FtpServer;
import org.apache.ftpserver.FtpServerFactory;
import org.apache.ftpserver.ftplet.FtpException;
import org.apache.ftpserver.ftplet.Ftplet;
import org.apache.ftpserver.listener.Listener;
import org.apache.ftpserver.listener.ListenerFactory;
import org.apache.ftpserver.usermanager.ClearTextPasswordEncryptor;
import org.apache.ftpserver.usermanager.PropertiesUserManagerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: guc
 * @Description: ftp服务端
 * @Date: 2021/2/24 14:00
 * @Version: 1.0
 */
@Configuration
public class MyFtpServer {
    private static final Logger logger = LoggerFactory.getLogger(MyFtpServer.class);
    //springboot配置好数据源直接注入即可
    protected FtpServer server;
    public static final int PORT = 2221;
    /**
     * 是否保存文件到本地
     */
    public static final Boolean SAVE_FILE_TO_DISK = false;

    //我们这里利用spring加载@Configuration的特性来完成ftp server的初始化
    public MyFtpServer() {
        initFtp();
        logger.info("Apache ftp server is already instantiation complete!");
    }

    /**
     * ftp server init
     *
     * @throws IOException
     */
    public void initFtp() {
        FtpServerFactory serverFactory = new FtpServerFactory();
        ListenerFactory listenerFactory = new ListenerFactory();
        //1、设置服务端口
        listenerFactory.setPort(PORT);
        //2、设置被动模式数据上传的接口范围,云服务器需要开放对应区间的端口给客户端
        DataConnectionConfigurationFactory dataConnectionConfFactory = new DataConnectionConfigurationFactory();
        dataConnectionConfFactory.setPassivePorts("10000-10500");
        listenerFactory.setDataConnectionConfiguration(dataConnectionConfFactory.createDataConnectionConfiguration());
        //3、增加SSL安全配置
//        SslConfigurationFactory ssl = new SslConfigurationFactory();
//        ssl.setKeystoreFile(new File("src/main/resources/ftpserver.jks"));
//        ssl.setKeystorePassword("password");
        //ssl.setSslProtocol("SSL");
        // set the SSL configuration for the listener
//        listenerFactory.setSslConfiguration(ssl.createSslConfiguration());
//        listenerFactory.setImplicitSsl(true);
        //4、替换默认的监听器
        Listener listener = listenerFactory.createListener();
        serverFactory.addListener("default", listener);
        //5、配置自定义用户事件
        Map<String, Ftplet> ftpLets = new HashMap();
        ftpLets.put("ftpService", new MyFtplet());
        serverFactory.setFtplets(ftpLets);
        //6、读取用户的配置信息
        //注意：配置文件位于resources目录下，如果项目使用内置容器打成jar包发布，FTPServer无法直接直接读取Jar包中的配置文件。
        //解决办法：将文件复制到指定目录(本文指定到根目录)下然后FTPServer才能读取到。
        PropertiesUserManagerFactory userManagerFactory = new PropertiesUserManagerFactory();
        String tempPath = System.getProperty("java.io.tmpdir") + System.currentTimeMillis() + ".properties";
        File tempConfig = new File(tempPath);
        try {
            ClassPathResource resource = new ClassPathResource("users.properties");
            org.apache.commons.io.IOUtils.copy(resource.getInputStream(), new FileOutputStream(tempConfig));
        } catch (IOException e) {
            throw new RuntimeException("未获取到配置文件信息");
        }
        userManagerFactory.setFile(tempConfig);
        userManagerFactory.setPasswordEncryptor(new ClearTextPasswordEncryptor());  //密码以明文的方式
        serverFactory.setUserManager(userManagerFactory.createUserManager());
        //7、实例化FTP Server
        server = serverFactory.createServer();
    }


    /**
     * ftp server start
     */
    public void start() {
        try {
            server.start();
            logger.info("Apache Ftp server is starting! on port(s): {} (ftp)", MyFtpServer.PORT);
        } catch (FtpException e) {
            e.printStackTrace();
        }
    }


    /**
     * ftp server stop
     */
    public void stop() {
        server.stop();
        logger.info("Apache Ftp server is stoping!");
    }
}
