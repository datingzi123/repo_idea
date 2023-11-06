package com.lagou.dao;

import com.lagou.domain.PromotionSpace;

import java.util.List;

public interface PromotionSpaceMapper {

    //获取所有的广告位
    public List<PromotionSpace> findAllPromotionSpace();

    //添加广告位
    public void savePromotionSpace(PromotionSpace promotionSpace);

    //回显广告位：根据id查询广告位  参数：隐藏携带id
    public PromotionSpace findPromotionById(Integer id);

    //修改广告位
    public void updatePromotionSpace(PromotionSpace promotionSpace);
}
