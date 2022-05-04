package com.nexon.onestop.controller.view.admin;

import com.nexon.onestop.domain.entity.Role;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class GroupController {

    @GetMapping(value="/admin/groups")
    public String getGroups(Model model) throws Exception {
        return "admin/group/list";
    }

}
