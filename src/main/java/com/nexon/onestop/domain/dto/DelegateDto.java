package com.nexon.onestop.domain.dto;

import lombok.Data;
import java.util.List;

@Data
public class DelegateDto {
    private Long id;
    private String groupname;
    private String username;
    private List<String> delegateUsers;

}


