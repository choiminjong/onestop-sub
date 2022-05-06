package com.nexon.onestop.service;

import com.nexon.onestop.domain.dto.AccountDto;
import com.nexon.onestop.domain.entity.Account;

import java.util.List;

public interface UserService {

    void createUser(Account account);
    void modifyUser(AccountDto accountDto);
    List<Account> getUsers();
    AccountDto getUser(Long id);
    void deleteUser(Long idx);
}