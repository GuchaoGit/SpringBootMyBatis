package com.guc.springboot.mybatis.ftp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
/**
 * @Author: guc
 * @Description:FTP-SERVER
 * @Date: 2021/2/24 13:55
 * @Version: 1.0
 */
@WebListener
@Component
public class FtpServerListener implements ServletContextListener {

    private static final Logger logger = LoggerFactory.getLogger(MyFtpServer.class);
    private static final String SERVER_NAME="FTP-SERVER";

    @Autowired
    private MyFtpServer server;

    //容器关闭时调用方法stop ftpServer
    public void contextDestroyed(ServletContextEvent sce) {
//        WebApplicationContext ctx= WebApplicationContextUtils.getWebApplicationContext(sce.getServletContext());
//        MyFtpServer server=(MyFtpServer)ctx.getServletContext().getAttribute(SERVER_NAME);
        server.stop();
        sce.getServletContext().removeAttribute(SERVER_NAME);
        logger.info("Apache Ftp server is stoped!");
    }

    //容器初始化调用方法start ftpServer
    public void contextInitialized(ServletContextEvent sce) {
//        WebApplicationContext ctx= WebApplicationContextUtils.getWebApplicationContext(sce.getServletContext());
//        MyFtpServer server=(MyFtpServer) ctx.getBean("MyFtp");
        sce.getServletContext().setAttribute(SERVER_NAME,server);
        try {
            //项目启动时已经加载好了
            server.start();
            logger.info("Apache Ftp server is started!");
        } catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("Apache Ftp server start failed!", e);
        }
    }

}