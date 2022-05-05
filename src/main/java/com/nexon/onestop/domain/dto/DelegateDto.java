package com.nexon.onestop.domain.dto;

import lombok.Data;
import java.util.List;

@Data
public class DelegateDto {
    private String id;
    private String groupname;
    private List<String> delegateUsers;

}


