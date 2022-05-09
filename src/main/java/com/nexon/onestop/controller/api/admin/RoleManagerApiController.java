package com.nexon.onestop.controller.api.admin;

import com.nexon.onestop.domain.dto.AccountDto;
import com.nexon.onestop.domain.dto.ResponseDto;
import com.nexon.onestop.domain.dto.RoleDto;
import com.nexon.onestop.domain.entity.Role;
import com.nexon.onestop.service.impl.RoleServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
public class RoleManagerApiController {

    @Autowired
    private RoleServiceImpl roleServiceImpl;

    @PostMapping(value="/admin/roles")
    public ResponseDto<Integer> createRole(@RequestBody RoleDto roleDto) throws Exception {

        ModelMapper modelMapper = new ModelMapper();
        Role role = modelMapper.map(roleDto, Role.class);
        roleServiceImpl.createRole(role);

        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

    @PostMapping(value="/admin/roles/{id}")
    public ResponseDto<Integer> modifyRole(@RequestBody RoleDto roleDto ,
                                           @PathVariable(value = "id") Long id) throws Exception {

        ModelMapper modelMapper = new ModelMapper();
        Role role = modelMapper.map(roleDto, Role.class);
        role.setId(id);
        roleServiceImpl.createRole(role);

        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

//    @GetMapping(value="/admin/roles/delete/{id}")
//    public String removeResources(@PathVariable String id, Model model) throws Exception {
//
//        Role role = roleServiceImpl.getRole(Long.valueOf(id));
//        roleServiceImpl.deleteRole(Long.valueOf(id));
//
//        return "redirect:/admin/resources";
//    }
//

}
