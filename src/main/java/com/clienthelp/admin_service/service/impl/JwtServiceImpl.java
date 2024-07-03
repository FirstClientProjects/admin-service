package com.clienthelp.admin_service.service.impl;

import com.clienthelp.admin_service.model.Admin;
import com.clienthelp.admin_service.service.JwtService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtServiceImpl implements JwtService {
    @Override
    public String generateToken(Object user, String email) {
        Map<String, Object> claims = new HashMap<>();
        Admin admin = (Admin) user;
        claims.put("adminId", admin.getId());
        claims.put("email", admin.getEmail());
        claims.put("role", admin.getRole());
        return createToken(claims, email);
    }

    private String createToken(Map<String, Object> claims, String email) {

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(email)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000*60*30))
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode("462ef8b9e205775f789b24a7645026008f33aec3439b00f02ed77921728dc989");
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
