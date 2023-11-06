package com.lagou.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lagou.dao.PromotionAdMapper;
import com.lagou.domain.PromotionAd;
import com.lagou.domain.PromotionAdVO;
import com.lagou.service.PromotionAdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class PromotionAdServiceImpl implements PromotionAdService {

    @Autowired
    private PromotionAdMapper promotionAdMapper;

    //分页查询广告信息（广告信息+广告位信息）
    @Override
    public PageInfo<PromotionAd> findAllPromotionAdByPage(PromotionAdVO promotionAdVO) {

        //参数1：当前页  参数2：每页显示的条数
        PageHelper.startPage(promotionAdVO.getCurrentPage(),promotionAdVO.getPageSize());

        List<PromotionAd> promotionAdList = promotionAdMapper.findAllPromotionAdByPage();

        PageInfo<PromotionAd> pageInfo = new PageInfo<>(promotionAdList);

        return pageInfo;//返回总条数等页数信息
    }

    //新建广告
    @Override
    public void savePromotionAd(PromotionAd promotionAd) {

        Date date = new Date();
        promotionAd.setCreateTime(date);
        promotionAd.setUpdateTime(date);

        promotionAdMapper.savePromotionAd(promotionAd);

    }

    //回显广告：根据id查询广告
    @Override
    public PromotionAd findPromotionAdById(Integer id) {
        PromotionAd promotionAd = promotionAdMapper.findPromotionAdById(id);
        return promotionAd;
    }

    //更新广告
    @Override
    public void updatePromotionAd(PromotionAd promotionAd) {

        //接收前端传递的参数：开始日期和结束日期  需要格式转化 String转date

       /*SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
       promotionAd.setUpdateTime(date);*/

        promotionAd.setUpdateTime(new Date());
        promotionAdMapper.updatePromotionAd(promotionAd);
    }

    //修改广告状态上下线
    @Override
    public void updatePromotionAdStatus(Integer id, Integer status) {

        //封装数据：参数封装到实体类中
        PromotionAd promotionAd = new PromotionAd();
        promotionAd.setId(id);
        promotionAd.setStatus(status);
        promotionAd.setUpdateTime(new Date());

        promotionAdMapper.updatePromotionAdStatus(promotionAd);
    }
}
