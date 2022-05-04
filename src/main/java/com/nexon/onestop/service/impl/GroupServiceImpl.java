package com.nexon.onestop.service.impl;

import com.nexon.onestop.domain.entity.Delegate;
import com.nexon.onestop.domain.entity.Role;
import com.nexon.onestop.repository.DelegateRepository;
import com.nexon.onestop.repository.RoleRepository;
import com.nexon.onestop.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class GroupServiceImpl implements GroupService {

    @Autowired
    private DelegateRepository delegateRepository;

    @Override
    @Transactional
    public List<Delegate> getGroups() {
        return delegateRepository.findAll();
    }
}
