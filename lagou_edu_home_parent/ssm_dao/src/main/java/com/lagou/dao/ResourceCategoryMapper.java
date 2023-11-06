package com.lagou.dao;

import com.lagou.domain.ResourceCategory;

import java.util.List;

public interface ResourceCategoryMapper {

    //资源分类列表查询
    public List<ResourceCategory> findAllResourceCategory();
}
