package com.lagou.dao;

import com.lagou.domain.Resource;
import com.lagou.domain.ResourceVO;

import java.util.List;

public interface ResourceMapper {

    //资源列表分页查询&多条件查询  查询条件就是前端传递参数有5个，用ResourceVO来接收
    public List<Resource> findAllResourceByPage(ResourceVO resourceVO);
}
