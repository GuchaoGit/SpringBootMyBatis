package com.guc.springboot.mybatis.controller;

import com.guc.springboot.mybatis.entity.MacInfo;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: guc
 * @Description: http上报接收
 * @Date: 2021/2/24 12:57
 * @Version: 1.0
 */
@RestController
@RequestMapping("receiveMessage")
public class ReceiveController {

    @GetMapping()
    public String receive(@Validated MacInfo info) {
        StringBuilder sb = new StringBuilder();
        if (info.getType() == null) return "非法请求";
        switch (info.getType()) {
            case "login"://上线
                sb.append("上线");
                break;
            case "logout"://下线
                sb.append("下线");
                break;
            case "clearAll"://清除所有
                sb.append("清除所有");
                break;
            default:
                sb.append("未知操作");
                break;
        }

        return sb.toString();
    }
}
