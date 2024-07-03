package com.clienthelp.admin_service.service.impl;

import com.clienthelp.admin_service.dto.requestdto.AdminRequestDTO;
import com.clienthelp.admin_service.dto.requestdto.AuthRequestDTO;
import com.clienthelp.admin_service.exceptions.NotFoundException;
import com.clienthelp.admin_service.model.Admin;
import com.clienthelp.admin_service.repo.AdminRepo;
import com.clienthelp.admin_service.service.AdminService;
import com.clienthelp.admin_service.service.JwtService;
import com.clienthelp.admin_service.utils.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private final AdminRepo adminRepo;
    @Autowired
    private final JwtService jwtService;

    public AdminServiceImpl(AdminRepo adminRepo, JwtServiceImpl jwtService) {
        this.adminRepo = adminRepo;
        this.jwtService = jwtService;
    }

    @Override
    public Admin registerAdmin(AdminRequestDTO adminRequestDTO) {
        Admin admin = new Admin();
        admin.setFirstName(adminRequestDTO.getFirstName());
        admin.setLastName(adminRequestDTO.getLastName());
        admin.setEmail(adminRequestDTO.getEmail());
        admin.setPassword(adminRequestDTO.getPassword());
        admin.setCreatedAt(LocalDateTime.now());
        admin.setUpdatedAt(LocalDateTime.now());
        admin.setRole(Role.ADMIN);
        Admin savedAdmin = adminRepo.save(admin);
        return savedAdmin;
    }

    @Override
    public Admin registerSuperAdmin(AdminRequestDTO adminRequestDTO) {
        Admin admin = new Admin();
        admin.setFirstName(adminRequestDTO.getFirstName());
        admin.setLastName(adminRequestDTO.getLastName());
        admin.setEmail(adminRequestDTO.getEmail());
        admin.setPassword(adminRequestDTO.getPassword());
        admin.setRole(Role.SUPER_ADMIN);
        admin.setCreatedAt(LocalDateTime.now());
        admin.setUpdatedAt(LocalDateTime.now());
        Admin savedAdmin = adminRepo.save(admin);
        return savedAdmin;
    }

    @Override
    public String loginAdmin(AuthRequestDTO authRequestDTO) {
        Admin existingAdmin = adminRepo.findByEmail(authRequestDTO.getEmail());
        if (existingAdmin != null) {
            if (existingAdmin.getPassword().equals(authRequestDTO.getPassword())) {
                String token = jwtService.generateToken(existingAdmin, authRequestDTO.getEmail());
                return token;
            }else {
                throw new NotFoundException("Password is incorrect");
            }
        }else {
            throw new NotFoundException("Email doesn't exists");
        }
    }

    @Override
    public List<Admin> getAllAdmins() {
        List<Admin> adminList = adminRepo.findAll();
        return adminList;
    }

    @Override
    public Optional<Admin> getAdmin(String adminId) {
        Optional<Admin> adminResponseDTO = adminRepo.findById(adminId);
        return adminResponseDTO;
    }

    @Override
    public Optional<Object> updateAdmin(AdminRequestDTO adminRequestDTO, String adminId) {
        Optional<Admin> optionalAdmin = adminRepo.findById(adminId);
        if (optionalAdmin.isPresent()) {
            Admin existingAdmin = optionalAdmin.get();
            existingAdmin.setFirstName(adminRequestDTO.getFirstName());
            existingAdmin.setLastName(adminRequestDTO.getLastName());
            existingAdmin.setEmail(adminRequestDTO.getEmail());
            existingAdmin.setPassword(adminRequestDTO.getPassword());
            existingAdmin.setRole(Role.ADMIN);
            existingAdmin.setCreatedAt(existingAdmin.getCreatedAt());
            existingAdmin.setUpdatedAt(LocalDateTime.now());

            adminRepo.save(existingAdmin);

            return Optional.of(existingAdmin);
        } else {
            System.out.println("Admin not found");
            return Optional.empty();
        }
    }

    @Override
    public Optional<Object> updateSuperAdmin(AdminRequestDTO adminRequestDTO, String adminId) {
        Optional<Admin> optionalAdmin = adminRepo.findById(adminId);
        if (optionalAdmin.isPresent()) {
            Admin existingAdmin = optionalAdmin.get();
            existingAdmin.setFirstName(adminRequestDTO.getFirstName());
            existingAdmin.setLastName(adminRequestDTO.getLastName());
            existingAdmin.setEmail(adminRequestDTO.getEmail());
            existingAdmin.setPassword(adminRequestDTO.getPassword());
            existingAdmin.setRole(Role.SUPER_ADMIN);
            existingAdmin.setCreatedAt(existingAdmin.getCreatedAt());
            existingAdmin.setUpdatedAt(LocalDateTime.now());

            adminRepo.save(existingAdmin);

            return Optional.of(existingAdmin);
        } else {
            System.out.println("Admin not found");
            return Optional.empty();
        }
    }

    @Override
    public void deleteAdmin(String adminId) {
        adminRepo.deleteById(adminId);
    }

    @Override
    public Optional<Admin> changePasswordAdmin(AuthRequestDTO authRequestDTO) {
        Admin existingAdmin = adminRepo.findByEmail(authRequestDTO.getEmail());
        if (existingAdmin != null) {
            existingAdmin.setPassword(authRequestDTO.getPassword());
            adminRepo.save(existingAdmin);

            return Optional.of(existingAdmin);
        }else {
            throw new NotFoundException("Admin email not found");
        }
    }
}
