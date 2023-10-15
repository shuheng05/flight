package com.aurora.controller;

import com.aurora.common.Result;
import com.aurora.constant.JwtClaimsConstant;
import com.aurora.entity.Admin;
import com.aurora.properties.JwtProperties;
import com.aurora.service.AdminService;
import com.aurora.utils.JwtUtil;
import com.aurora.vo.AdminVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/admins")
@Slf4j
public class AdminController {
    @Autowired
    private AdminService adminService;

    @Autowired
    private JwtProperties jwtProperties;

    //管理员登录
    @PostMapping("/login")
    public Result<AdminVo> login(Admin admin) {
        adminService.login(admin);
        //登录成功后，生成jwt令牌
        Map<String, Object> claims = new HashMap<>();
        claims.put(JwtClaimsConstant.ADMIN_ID, admin.getId());

        String token = JwtUtil.createJWT(
                jwtProperties.getAdminSignKey(),
                jwtProperties.getAdminTime(),
                claims);
        AdminVo adminVo = AdminVo.builder()
                .id(admin.getId())
                .name(admin.getName())
                .token(token)
                .build();

        return Result.success(adminVo);

    }

    //增加管理员
    @PostMapping("/add")
    public Result<String> add(Admin admin) {
        boolean isAdded = adminService.add(admin);
        if (isAdded) return Result.success("注册成功");
        else return Result.error("用户名已存在");
    }

    //删除管理员
    @DeleteMapping("/delete/{id}")
    public Result<String> delete(@PathVariable Integer id) {
        return adminService.delete(id);
    }

    //修改管理员密码
    @PutMapping("/updata")
    public Result<String> updata(Admin admin) {
        return adminService.update(admin);
    }

}
