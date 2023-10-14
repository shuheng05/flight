package com.aurora.service;

import com.aurora.common.Result;
import com.aurora.entity.Admin;

public interface AdminService {
    public void login(Admin admin);

    public boolean add(Admin admin);

    public Result<String> delete(Integer id);
}
