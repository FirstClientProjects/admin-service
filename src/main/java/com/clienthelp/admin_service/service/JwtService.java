package com.clienthelp.admin_service.service;

public interface JwtService {
    public String generateToken(Object user, String username);
}
