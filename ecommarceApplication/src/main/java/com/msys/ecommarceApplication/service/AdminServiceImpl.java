package com.msys.ecommarceApplication.service;

import com.msys.ecommarceApplication.dao.AdminDao;
import com.msys.ecommarceApplication.model.Admin;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AdminServiceImpl implements AdminService{
    private final AdminDao adminDao;
    @Override
    public Admin login(final Admin admin) {
        return adminDao.login(admin);
    }
}
