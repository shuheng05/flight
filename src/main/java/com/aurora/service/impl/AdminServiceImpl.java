package com.aurora.service.impl;

import com.aurora.common.Result;
import com.aurora.constant.MessageConstant;
import com.aurora.entity.Admin;
import com.aurora.exception.AccountNotFoundException;
import com.aurora.exception.PasswordErrorException;
import com.aurora.mapper.AdminMapper;
import com.aurora.service.AdminService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

@Service
@Slf4j
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminMapper adminMapper;

    @Override
    public void login(Admin admin) {
        String name = admin.getName();
        log.info(name);
        String password = admin.getPassword();
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        log.info(password);
        Admin adminOne = adminMapper.getByName(name);
        if (adminOne == null) {
            throw new AccountNotFoundException(MessageConstant.ACCOUNT_NOT_FOUND);
        }
        if (!password.equals(adminOne.getPassword())) {
            throw new PasswordErrorException(MessageConstant.PASSWORD_ERROR);
        }
    }

    @Override
    public boolean add(Admin admin) {
        String name = admin.getName();
        Admin adminOne = adminMapper.getByName(name);
        if (adminOne != null) return false;
        admin.setPassword(DigestUtils.md5DigestAsHex(admin.getPassword().getBytes()));
        adminMapper.insert(admin);
        return true;
    }

    @Override
    public Result<String> delete(Integer id) {
        Admin admin = adminMapper.selectById(id);
        if (admin == null) return Result.error("用户不存在");
        else {
            adminMapper.deleteById(id);
            return Result.success("删除成功");
        }


    }

    @Override
    public Result<String> update(Admin admin) {
        String name = admin.getName();
        String password = admin.getPassword();
        Admin oldAdmin = adminMapper.getByName(name);
        if (password.equals(oldAdmin.getPassword())) {
            return Result.error("密码与原密码相同");
        }
        adminMapper.update(name, password);
        return Result.success("修改成功");
    }

}
