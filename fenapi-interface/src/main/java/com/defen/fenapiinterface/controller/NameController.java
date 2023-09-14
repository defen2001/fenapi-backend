package com.defen.fenapiinterface.controller;

import com.defen.fenapiclientsdk.model.User;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 名称 API
 *
 * @author defen
 */
@RestController
public class NameController {

    @PostMapping("/api/name/user")
    public String getUserNameByPost(@RequestBody User user, HttpServletRequest request) {
        return "你的用户名字是：" + user.getUsername();
    }
}
