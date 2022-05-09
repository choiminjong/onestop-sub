package com.nexon.onestop.controller.view.admin;

import com.nexon.onestop.domain.dto.ResourcesDto;
import com.nexon.onestop.domain.entity.Resources;
import com.nexon.onestop.domain.entity.Role;
import com.nexon.onestop.repository.RoleRepository;
import com.nexon.onestop.service.ResourcesService;
import com.nexon.onestop.service.RoleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class ResourcesManagerController {

    @Autowired
    private ResourcesService resourcesService;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private RoleService roleService;

    @GetMapping(value="/admin/resources")
    public String getResources(Model model) throws Exception {

        List<Resources> resources = resourcesService.getResources();
        model.addAttribute("resources", resources);

        return "admin/resource/list";
    }

    @PostMapping(value="/admin/resources")
    public String createResources(ResourcesDto resourcesDto) throws Exception {

        Role role =  roleRepository.findByroleName(resourcesDto.getRoleName());
        Set<Role> roles = new HashSet<>();
        roles.add(role);

        ModelMapper modelMapper = new ModelMapper();
        Resources resources = modelMapper.map(resourcesDto, Resources.class);
        resources.setRoleSet(roles);

        resourcesService.createResources(resources);

        return "redirect:/admin/resources";
    }

    @GetMapping(value="/admin/resources/register")
    public String viewRoles(Model model) throws Exception {

        List<Role> roleList = roleService.getListRoles();

        ResourcesDto resources = new ResourcesDto();
        Set<Role> roleSet = new HashSet<>();
        roleSet.add(new Role());
        resources.setRoleSet(roleSet);

        model.addAttribute("roleList", roleList);
        model.addAttribute("resources", resources);

        return "admin/resource/detail";
    }

    @GetMapping(value="/admin/resources/{id}")
    public String getResources(@PathVariable String id, Model model) throws Exception {

        List<Role> roleList = roleService.getListRoles();

        Resources resources = resourcesService.getResources(Long.valueOf(id));
        ModelMapper modelMapper = new ModelMapper();
        ResourcesDto resourcesDto = modelMapper.map(resources, ResourcesDto.class);

        model.addAttribute("roleList", roleList);
        model.addAttribute("resources", resourcesDto);

        return "admin/resource/detail";
    }

    @GetMapping(value="/admin/resources/delete/{id}")
    public String removeResources(@PathVariable String id, Model model) throws Exception {

        Resources resources = resourcesService.getResources(Long.valueOf(id));
        resourcesService.deleteResources(Long.valueOf(id));

        return "redirect:/admin/resources";
    }

}