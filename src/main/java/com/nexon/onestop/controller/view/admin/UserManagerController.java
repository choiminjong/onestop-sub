package com.nexon.onestop.controller.view.admin;

import com.nexon.onestop.domain.dto.AccountDto;
import com.nexon.onestop.domain.entity.Account;
import com.nexon.onestop.domain.entity.Role;
import com.nexon.onestop.service.impl.RoleServiceImpl;
import com.nexon.onestop.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class UserManagerController {
    @Autowired
    private UserServiceImpl userServiceImpl;

    @Autowired
    private RoleServiceImpl roleServiceImpl;

    @GetMapping(value="/admin/accounts")
    public String getUsers(Model model) throws Exception {

        List<Account> accounts = userServiceImpl.getUsers();
        model.addAttribute("accounts", accounts);

        return "admin/user/list";
    }

    @PostMapping(value="/admin/accounts")
    public String modifyUser(AccountDto accountDto) throws Exception {

        userServiceImpl.modifyUser(accountDto);

        return "redirect:/admin/accounts";
    }

    @GetMapping(value = "/admin/accounts/{id}")
    public String getUser(@PathVariable(value = "id") Long id, Model model) {

        AccountDto accountDto = userServiceImpl.getUser(id);
        List<Role> roleList = roleServiceImpl.getRoles();

        System.out.println("AccountDto E@@@ = " + accountDto);
        System.out.println("roleList E@@@ = " + roleList);

        model.addAttribute("account", accountDto);
        model.addAttribute("roleList", roleList);

        return "admin/user/detail";
    }

    @GetMapping(value = "/admin/accounts/delete/{id}")
    public String removeUser(@PathVariable(value = "id") Long id, Model model) {

        userServiceImpl.deleteUser(id);

        return "redirect:/admin/users";
    }
}
