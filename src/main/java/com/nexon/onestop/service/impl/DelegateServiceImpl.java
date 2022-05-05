package com.nexon.onestop.service.impl;

import com.nexon.onestop.domain.dto.DelegateDto;
import com.nexon.onestop.domain.entity.Delegate;
import com.nexon.onestop.repository.DelegateRepository;
import com.nexon.onestop.service.DelegateService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DelegateServiceImpl implements DelegateService {

    @Autowired
    private DelegateRepository delegateRepository;

    @Override
    @Transactional
    public List<Delegate> getDelegates() {
        return delegateRepository.findAll();
    }

    @Override
    @Transactional
    public DelegateDto getDelegate(Long id) {
        Delegate delegate = delegateRepository.findById(id)
                .orElseThrow(()->{
                    return new UsernameNotFoundException("해당 그룹을 찾을 수 없습니다.");
                });

        ModelMapper modelMapper = new ModelMapper();
        DelegateDto delegateDto = modelMapper.map(delegate, DelegateDto.class);

        List<String> deletes =delegate.getDelegateUsers()
                .stream()
                .map(DelegateUser -> DelegateUser.getUsername())
                .collect(Collectors.toList());

        delegateDto.setDelegateUsers(deletes);
        return delegateDto;
    }
}
