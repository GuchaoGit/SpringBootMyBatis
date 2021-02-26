package com.guc.springboot.mybatis.ftp.e;

/**
 * @Author: guc
 * @Description: 内容解析异常
 * @Date: 2021/2/24 16:48
 * @Version: 1.0
 */
public class ContentParseException extends Exception {
    public ContentParseException(){
        super();
    }
    public ContentParseException(String msg){
        super(msg);
    }
    @Override
    public String toString() {
        return "文件内容不符合解析规则：" + getMessage();
    }
}
