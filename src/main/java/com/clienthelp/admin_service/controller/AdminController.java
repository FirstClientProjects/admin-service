package com.clienthelp.admin_service.controller;

import com.clienthelp.admin_service.dto.requestdto.AdminRequestDTO;
import com.clienthelp.admin_service.dto.requestdto.AuthRequestDTO;
import com.clienthelp.admin_service.model.Admin;
import com.clienthelp.admin_service.repo.AdminRepo;
import com.clienthelp.admin_service.service.impl.AdminServiceImpl;
import com.clienthelp.admin_service.utils.StandardResponse;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping(path = "/api/v1/admin")
public class AdminController {

    @Autowired
    private final AdminServiceImpl adminService;

    private static final Logger  logger = LoggerFactory.getLogger(AdminController.class);
    @Autowired
    private AdminRepo adminRepo;

    public AdminController(AdminServiceImpl adminService) {
        this.adminService = adminService;
    }

    @GetMapping(path = "/list", produces = "application/json")
    public CompletableFuture<ResponseEntity<StandardResponse>> getAllAdmins() {
        try {
            List<Admin> allAdmins = adminService.getAllAdmins();
            logger.info("Get All Admins query was successful");
            return CompletableFuture.completedFuture(new ResponseEntity<>(
                    new StandardResponse(
                            200,
                            "Get all admins query was successful",
                            allAdmins
                    ),
                    HttpStatus.OK
            ));
        }catch (Exception ex) {
            logger.info("Get All Admins query was failed");
            logger.info(ex.getMessage());
            return CompletableFuture.completedFuture(new ResponseEntity<>(
                    new StandardResponse(
                            500,
                            "Get all admins query was failed",
                            ex.getMessage()
                    ),
                    HttpStatus.INTERNAL_SERVER_ERROR
            ));
        }
    }

    @GetMapping(path = "/list/{adminId}")
    public CompletableFuture<ResponseEntity<StandardResponse>> getAdmin(@PathVariable(value = "adminId", required = true) String adminId) {
        try {
            Optional<Admin> admin = adminService.getAdmin(adminId);
            logger.info("Get admin query was successful");
            return CompletableFuture.completedFuture(new ResponseEntity<>(
                    new StandardResponse(
                            200,
                            "Get admin query was successful",
                            admin
                    ),
                    HttpStatus.OK
            ));
        }catch (Exception ex) {
            logger.info("Get admin query was failed");
            logger.info(ex.getMessage());
            return CompletableFuture.completedFuture(new ResponseEntity<>(
                    new StandardResponse(
                            500,
                            "Get admin query was failed",
                            ex.getMessage()
                    ),
                    HttpStatus.INTERNAL_SERVER_ERROR
            ));
        }
    }

    @PostMapping(path = "/create/admin")
    public CompletableFuture<ResponseEntity<StandardResponse>> createAdmin(@RequestBody AdminRequestDTO adminRequestDTO) {
        try {
            Admin admin = adminService.registerAdmin(adminRequestDTO);
            logger.info("Register admin query was successful");
            return CompletableFuture.completedFuture(new ResponseEntity<>(
                    new StandardResponse(
                            201,
                            "Register admin query was successful",
                            admin
                    ),
                    HttpStatus.CREATED
            ));
        }catch (Exception ex) {
            logger.info("Register admin query was failed");
            logger.info(ex.getMessage());
            return CompletableFuture.completedFuture(new ResponseEntity<>(
                    new StandardResponse(
                            500,
                            "Register admin query was failed",
                            ex.getMessage()
                    ),
                    HttpStatus.INTERNAL_SERVER_ERROR
            ));
        }
    }

    @PostMapping(path = "/create/superadmin")
    public CompletableFuture<ResponseEntity<StandardResponse>> createSuperAdmin(@RequestBody AdminRequestDTO adminRequestDTO) {
        try {
            Admin admin = adminService.registerSuperAdmin(adminRequestDTO);
            logger.info("Register super admin query was successful");
            return CompletableFuture.completedFuture(new ResponseEntity<>(
                    new StandardResponse(
                            201,
                            "Register super admin query was successful",
                            admin
                    ),
                    HttpStatus.CREATED
            ));
        }catch (Exception ex) {
            logger.info("Register super admin query was failed");
            logger.info(ex.getMessage());
            return CompletableFuture.completedFuture(new ResponseEntity<>(
                    new StandardResponse(
                            500,
                            "Register super admin query was failed",
                            ex.getMessage()
                    ),
                    HttpStatus.INTERNAL_SERVER_ERROR
            ));
        }
    }

    @PutMapping(path = "/update/{adminId}")
    public CompletableFuture<ResponseEntity<StandardResponse>> updateAdmin(@RequestBody AdminRequestDTO adminRequestDTO, @PathVariable(value = "adminId", required = true) String adminId) {
        try {
            Optional<Object> updatedAdmin = adminService.updateAdmin(adminRequestDTO, adminId);
            logger.info("Update admin query was successful");
            return CompletableFuture.completedFuture(new ResponseEntity<>(
                    new StandardResponse(
                            201,
                            "Update admin query was successful",
                            updatedAdmin
                    ),
                    HttpStatus.OK
            ));
        }catch (Exception ex) {
            logger.info("Update admin query was failed");
            logger.info(ex.getMessage());
            return CompletableFuture.completedFuture(new ResponseEntity<>(
                    new StandardResponse(
                            500,
                            "Update admin query was failed",
                            ex.getMessage()
                    ),
                    HttpStatus.INTERNAL_SERVER_ERROR
            ));
        }
    }

    @PutMapping(path = "/update/superadmin/{adminId}")
    public CompletableFuture<ResponseEntity<StandardResponse>> updateSuperAdmin(@RequestBody AdminRequestDTO adminRequestDTO, @PathVariable(value = "adminId", required = true) String adminId) {
        try {
            Optional<Object> updatedAdmin = adminService.updateSuperAdmin(adminRequestDTO, adminId);
            logger.info("Update super admin query was successful");
            return CompletableFuture.completedFuture(new ResponseEntity<>(
                    new StandardResponse(
                            201,
                            "Update super admin query was successful",
                            updatedAdmin
                    ),
                    HttpStatus.OK
            ));
        }catch (Exception ex) {
            logger.info("Update super admin query was failed");
            logger.info(ex.getMessage());
            return CompletableFuture.completedFuture(new ResponseEntity<>(
                    new StandardResponse(
                            500,
                            "Update super admin query was failed",
                            ex.getMessage()
                    ),
                    HttpStatus.INTERNAL_SERVER_ERROR
            ));
        }
    }

    @DeleteMapping(path = "/delete/{adminId}")
    public CompletableFuture<ResponseEntity<StandardResponse>> deleteAdmin(@PathVariable(value = "adminId", required = true) String adminId) {
        try {
            adminService.deleteAdmin(adminId);
            logger.info("Delete admin query was successful");
            return CompletableFuture.completedFuture(new ResponseEntity<>(
                    new StandardResponse(
                            204,
                            "Delete admin query was successful",
                            "Delete admin query was successful"
                    ),
                    HttpStatus.NO_CONTENT
            ));
        }catch (Exception ex) {
            logger.info("Delete admin query was failed");
            logger.info(ex.getMessage());
            return CompletableFuture.completedFuture(new ResponseEntity<>(
                    new StandardResponse(
                            500,
                            "Delete admin query was failed",
                            ex.getMessage()
                    ),
                    HttpStatus.INTERNAL_SERVER_ERROR
            ));
        }
    }

    @PostMapping(path = "/login")
    public CompletableFuture<ResponseEntity<StandardResponse>> loginAdmin(@RequestBody AuthRequestDTO authRequestDTO) {
        try {
            Admin admin = adminRepo.findByEmail(authRequestDTO.getEmail());
            String token = adminService.loginAdmin(authRequestDTO);
            HashMap<String, Object> responseData = new HashMap<>();
            responseData.put("user", admin);
            responseData.put("token", token);
            logger.info("Login admin query was successful");
            return CompletableFuture.completedFuture(new ResponseEntity<>(
                    new StandardResponse(
                            200,
                            "Login admin query was successful",
                            responseData
                    ),
                    HttpStatus.OK
            ));
        }catch (Exception ex) {
            logger.info("Login admin query was failed");
            logger.info(ex.getMessage());
            return CompletableFuture.completedFuture(new ResponseEntity<>(
                    new StandardResponse(
                            500,
                            "Login admin query was failed",
                            ex.getMessage()
                    ),
                    HttpStatus.INTERNAL_SERVER_ERROR
            ));
        }
    }

    @PostMapping(path = "/change-password")
    public CompletableFuture<ResponseEntity<StandardResponse>> changePassword(@RequestBody AuthRequestDTO authRequestDTO) {
        try {
            Optional<Admin> updatedAdmin = adminService.changePasswordAdmin(authRequestDTO);
            logger.info("Change password admin query was successful");
            return CompletableFuture.completedFuture(new ResponseEntity<>(
                    new StandardResponse(
                            200,
                            "Change password admin query was successful",
                            "Password changed successfully"
                    ),
                    HttpStatus.OK
            ));
        }catch (Exception ex) {
            logger.info("Change password admin query was failed");
            logger.info(ex.getMessage());
            return CompletableFuture.completedFuture(new ResponseEntity<>(
                    new StandardResponse(
                            500,
                            "Change password admin query was failed",
                            ex.getMessage()
                    ),
                    HttpStatus.INTERNAL_SERVER_ERROR
            ));
        }
    }
}
