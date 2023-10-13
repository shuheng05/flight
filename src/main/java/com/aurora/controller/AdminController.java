package com.aurora.controller;

import com.aurora.common.Result;
import com.aurora.entity.Admin;
import com.aurora.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admins")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @PostMapping("/login")
    public Result<String> login(Admin admin) {
        boolean isLogin = adminService.login(admin);
        if (isLogin) return Result.success("登录成功");
        else return Result.error("账号或密码错误");
    }
}
