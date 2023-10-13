package com.aurora.service.impl;

import com.aurora.common.Result;
import com.aurora.entity.Admin;
import com.aurora.mapper.AdminMapper;
import com.aurora.service.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminMapper adminMapper;

    @Override
    public boolean login(Admin admin) {
        String name = admin.getName();
        log.info(name);
        String password = admin.getPassword();
        log.info(password);
        Admin adminOne = adminMapper.getByName(name);
        if (adminOne == null) return false;
        return name.equals(adminOne.getName()) && password.equals((adminOne.getPassword()));
    }
}
