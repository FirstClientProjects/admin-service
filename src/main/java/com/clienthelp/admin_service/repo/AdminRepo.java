package com.clienthelp.admin_service.repo;

import com.clienthelp.admin_service.model.Admin;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepo extends MongoRepository<Admin, String> {
    Admin findByEmail(String email);
}
