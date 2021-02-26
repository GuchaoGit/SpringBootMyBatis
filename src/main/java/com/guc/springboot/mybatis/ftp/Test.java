package com.guc.springboot.mybatis.ftp;

/**
 * @Author: guc
 * @Description:
 * @Date: 2021/2/25 10:07
 * @Version: 1.0
 */
public class Test {
    public static void main(String[] args) {
        String info = "f8:34:41:c7:12:08,,,,,1614159442,44:6a:2e:ae:a5:60,1,11975,,,,,,,,,,,,";
        String[] parms = info.split(",",-1);
        for (int i=0;i<parms.length;i++){
            System.out.println("下标："+i+", 值："+parms[i]);
        }
        System.out.println(parms.length);

    }
}
