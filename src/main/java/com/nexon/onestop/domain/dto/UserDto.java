package com.nexon.onestop.domain.dto;

import lombok.Data;

import java.util.List;

@Data
public class UserDto {
    private String id;
    private String username;
    private String email;
    private String password;
    private List<String> roles;
}
