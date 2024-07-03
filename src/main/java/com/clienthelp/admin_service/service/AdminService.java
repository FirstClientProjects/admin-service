package com.clienthelp.admin_service.service;

import com.clienthelp.admin_service.dto.requestdto.AdminRequestDTO;
import com.clienthelp.admin_service.dto.requestdto.AuthRequestDTO;
import com.clienthelp.admin_service.model.Admin;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

public interface AdminService {

    public Admin registerAdmin(@RequestBody AdminRequestDTO adminRequestDTO);
    public Admin registerSuperAdmin(@RequestBody AdminRequestDTO adminRequestDTO);
    public String loginAdmin(@RequestBody AuthRequestDTO authRequestDTO);
    public List<Admin> getAllAdmins();
    public Optional<Admin> getAdmin(String adminId);
    public Optional<Object> updateAdmin(@RequestBody AdminRequestDTO adminRequestDTO, String adminId);
    public Optional<Object> updateSuperAdmin(@RequestBody AdminRequestDTO adminRequestDTO, String adminId);
    public void deleteAdmin(String adminId);
    public Optional<Admin> changePasswordAdmin(@RequestBody AuthRequestDTO authRequestDTO);
}
