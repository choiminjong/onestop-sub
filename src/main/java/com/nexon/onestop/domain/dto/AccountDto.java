package com.nexon.onestop.domain.dto;

import com.nexon.onestop.domain.audit.BaseTimeEntity;
import com.nexon.onestop.domain.entity.Role;
import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
public class AccountDto {
    private String id;
    private String username;
    private String password;
    private String email;
    private String oauthType;
    private List<String> roles;
}