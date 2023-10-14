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

    @PostMapping("/login")
    public Result<AdminVo> login(Admin admin) {
        adminService.login(admin);
        //if (isLogin == false) return Result
        //登录成功后，生成jwt令牌
        Map<String, Object> claims = new HashMap<>();
        log.info(admin.getId().toString());
        claims.put("empId", admin.getId());
        String token = JwtUtil.createJWT(
                "Aurora",
                7200000,
                claims);
        AdminVo adminVo = AdminVo.builder().token(token).build();

        return Result.success(adminVo);

    }

    @PostMapping("/add")
    public Result<String> add(Admin admin) {
        boolean isAdded = adminService.add(admin);
        if (isAdded) return Result.success("注册成功");
        else return Result.error("用户名已存在");
    }

    @DeleteMapping("/delete/{id}")
    public Result<String> delete(@PathVariable Integer id){
        return adminService.delete(id);
    }


}
