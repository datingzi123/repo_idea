package com.lagou.service;

import com.lagou.domain.ResourceCategory;

import java.util.List;

public interface ResourceCategoryService {

    //资源分类列表查询
    public List<ResourceCategory> findAllResourceCategory();
}
