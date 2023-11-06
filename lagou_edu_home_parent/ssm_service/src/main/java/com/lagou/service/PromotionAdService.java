package com.lagou.service;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.PromotionAd;
import com.lagou.domain.PromotionAdVO;

import java.text.ParseException;
import java.util.List;

public interface PromotionAdService {

    //分页查询广告信息（广告信息+广告位信息） 返回值：PageInfo 响应数据包括：总条数等页面信息
    public PageInfo<PromotionAd> findAllPromotionAdByPage(PromotionAdVO promotionAdVO);

    //新建广告
    public void savePromotionAd(PromotionAd promotionAd);

    //回显广告：根据id查询广告
    public PromotionAd findPromotionAdById(Integer id);

    //更新广告
    public void updatePromotionAd(PromotionAd promotionAd) throws ParseException;

    //修改广告状态上下线   参数
    public void updatePromotionAdStatus(Integer id,Integer status);
}
