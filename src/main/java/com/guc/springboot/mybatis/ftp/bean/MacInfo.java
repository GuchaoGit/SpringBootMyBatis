package com.guc.springboot.mybatis.ftp.bean;

import com.guc.springboot.mybatis.ftp.e.ContentParseException;

/**
 * @Author: guc
 * @Description: 上报的mac信息
 * @Date: 2021/2/25 9:57
 * @Version: 1.0
 */
public class MacInfo {
    public String mac; //用户mac
    public String timeStamp; //获取采集时间
    public String bissd;//用户关联BISSD 由17位字符组成，所有的字符小写，每两个字符用半角“：”，类似00：e0：4c：3b：7d：22
    public String onlineFlag;//在线标识 ０：用户上线；１：用户在线；2：用户下线
    public String onlineTimePeriod;//在线时长 单位：秒

    private MacInfo() {
    }

    public MacInfo(String info) throws ContentParseException {
        if (info == null || "".equals(info)) {
            throw new ContentParseException("待解析信息不能为空");
        }
        String[] params = info.split(",", -1);
        if (params == null || params.length != 21) throw new ContentParseException("数据格式不符合，需21个参数");
        mac = params[0];
        timeStamp = params[5];
        bissd = params[6];
        onlineFlag = params[7];
        onlineTimePeriod = params[8];
    }

    @Override
    public String toString() {
        return "MacInfo = {mac=" + mac +
                ",timeStamp=" + timeStamp +
                ",bissd=" + bissd +
                ",onlineFlag=" + onlineFlag +
                ",onlineTimePeriod=" + onlineTimePeriod +
                "}";
    }
}
