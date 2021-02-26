package com.guc.springboot.mybatis.ftp;

import com.guc.springboot.mybatis.ftp.bean.FileHeader;
import com.guc.springboot.mybatis.ftp.bean.MacInfo;
import com.guc.springboot.mybatis.ftp.e.ContentParseException;
import com.guc.springboot.mybatis.ftp.e.FileNameParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: guc
 * @Description: 工具
 * @Date: 2021/2/24 16:19
 * @Version: 1.0
 */
public class Utils {
    private static Logger log = LoggerFactory.getLogger(Utils.class);

    /**
     * 解析mac地址
     *
     * @param srcIp    mac上报的 设备ip
     * @param filePath 文件路径
     * @param fileName 文件名称
     */
    public static void parseMacFromFile(String srcIp, String filePath, String fileName) {
        File file = new File(filePath, fileName);
        boolean isCorrectFile = true;
        try {
            FileHeader header = FileHeader.createFromFileName(fileName);
            header.srcIp = srcIp;
            log.info(header.toString());
            List<MacInfo> macInfos = parseMacInfoFromFile(file);
        } catch (FileNameParseException e) {
            e.printStackTrace();
            isCorrectFile = false;
        } catch (ContentParseException e) {
            e.printStackTrace();
            isCorrectFile = false;
        }
        if (isCorrectFile && !MyFtpServer.SAVE_FILE_TO_DISK) {  //不保存 ，正确的文件，解析后删除文件
            if (file.exists()) {
                log.info("文件已删除");
                file.delete();
            }
        }

    }

    /**
     * 文件读取上报信息
     * @param file 文件
     * @return List<MacInfo>
     * @throws ContentParseException
     */
    private static List<MacInfo> parseMacInfoFromFile(File file) throws ContentParseException {
        List<MacInfo> macInfos = new ArrayList<>();
        MacInfo info;
        FileInputStream inputStream = null;
        BufferedReader br = null;
        try {
            inputStream = new FileInputStream(file);
            br = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = br.readLine()) != null) {
                info = new MacInfo(line);
                log.info(info.toString());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) br.close();
                if (inputStream != null) inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return macInfos;
    }

}
