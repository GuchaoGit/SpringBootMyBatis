package com.guc.springboot.mybatis.ftp.e;

/**
 * @Author: guc
 * @Description: 文件名解析异常
 * @Date: 2021/2/24 16:48
 * @Version: 1.0
 */
public class FileNameParseException extends Exception {
    @Override
    public String toString() {
        return "文件名不符合解析规则："+getMessage();
    }
}
