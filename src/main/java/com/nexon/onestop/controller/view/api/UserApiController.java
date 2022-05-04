package com.nexon.onestop.controller.view.api;

import com.nexon.onestop.domain.dto.ResponseDto;
import com.nexon.onestop.domain.entity.Account;
import com.nexon.onestop.service.impl.UserServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserApiController {

    private UserServiceImpl userServiceImpl;

    public UserApiController(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @PostMapping("/auth/join")
    public ResponseDto<Integer> save(@RequestBody Account account) {
        userServiceImpl.createUser(account);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

}
