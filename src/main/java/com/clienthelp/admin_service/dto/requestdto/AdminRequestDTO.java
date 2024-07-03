package com.clienthelp.admin_service.dto.requestdto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AdminRequestDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}