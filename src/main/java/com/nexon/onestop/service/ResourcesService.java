package com.nexon.onestop.service;

import com.nexon.onestop.domain.entity.Resources;

import java.util.List;

public interface ResourcesService {
    Resources getResources(long id);
    List<Resources> getResources();
    void createResources(Resources Resources);
    void deleteResources(long id);
}