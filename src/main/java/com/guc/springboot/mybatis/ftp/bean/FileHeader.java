package com.guc.springboot.mybatis.ftp.bean;

import com.guc.springboot.mybatis.ftp.Constants;
import com.guc.springboot.mybatis.ftp.e.FileNameParseException;

/**
 * @Author: guc
 * @Description: 文件名解析bean
 * @Date: 2021/2/24 17:01
 * @Version: 1.0
 */
public class FileHeader {
    public String srcIp;//源IP
    public String flag; //固定标识
    public String cityCode;//城市代码
    public String deviceID;//设备ID
    public String createTime;//数据文件生成时间 yyyymmddhhmmss
    public String dataLength;//数据文件长度
    public String deviceType;//2G /5G

    private FileHeader(){
    }
    @Override
    public String toString() {
        return "FileHeader = {srcIp=" + srcIp +
                ",flag=" + flag +
                ",cityCode=" + cityCode +
                ",deviceID=" + deviceID +
                ",createTime=" + createTime +
                ",dataLength=" + dataLength +
                ",deviceType=" + deviceType +
                "}";
    }
    /**
     * 解析文件名
     * @param name 文件名
     * @return
     * @throws FileNameParseException
     */
    public static FileHeader createFromFileName(String name) throws FileNameParseException {
        if (name == null) throw new FileNameParseException();
        String[] names = name.split("\\.");
        name = names[0];
        if (name.startsWith(Constants.FILE_REPORT_FLAG_MAC)){
            FileHeader header = new FileHeader();
            String[] values = name.split("_");
            if (values.length <5){
                throw new FileNameParseException();
            }
            header.flag = values[0];
            header.cityCode = values[1];
            header.deviceID = values[2];
            header.createTime = values[3];
            header.dataLength = values[4];
            if (values.length>5){
                header.deviceType = values[5];
            }
            return header;
        }else {
            throw new FileNameParseException();
        }
    }

}
