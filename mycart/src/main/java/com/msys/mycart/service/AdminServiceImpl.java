package com.msys.mycart.service;

import com.msys.mycart.model.Admin;
import com.msys.mycart.repo.AdminRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AdminServiceImpl implements AdminService{
    private final AdminRepository adminRepository;
    @Override
    public Admin login(final Admin admin) {
        return adminRepository.findByIdAndPassword(admin.getId(), admin.getPassword());
    }
}
