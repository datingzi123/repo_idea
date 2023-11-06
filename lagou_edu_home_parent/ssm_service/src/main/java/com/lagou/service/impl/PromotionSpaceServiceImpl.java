package com.lagou.service.impl;

import com.lagou.dao.PromotionSpaceMapper;
import com.lagou.domain.PromotionSpace;
import com.lagou.service.PromotionSpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class PromotionSpaceServiceImpl implements PromotionSpaceService {

    @Autowired
    private PromotionSpaceMapper promotionSpaceMapper;

    //获取所有广告位
    @Override
    public List<PromotionSpace> findAllPromotionSpace() {

        List<PromotionSpace> allPromotionSpace = promotionSpaceMapper.findAllPromotionSpace();
        return allPromotionSpace;
    }

    //添加广告位
    @Override
    public void savePromotionSpace(PromotionSpace promotionSpace) {

        //1.封装数据  前端传递参数补全，需要手动补全
        promotionSpace.setSpaceKey(UUID.randomUUID().toString());//不能重复

        Date date = new Date();
        promotionSpace.setCreateTime(date);
        promotionSpace.setUpdateTime(date);
        promotionSpace.setIsDel(0);

        //2.调用dao
        promotionSpaceMapper.savePromotionSpace(promotionSpace);
    }

    //回显广告位：根据id查询广告位
    @Override
    public PromotionSpace findPromotionById(Integer id) {

        PromotionSpace promotionSpace = promotionSpaceMapper.findPromotionById(id);
        return promotionSpace;
    }

    //修改广告位
    @Override
    public void updatePromotionSpace(PromotionSpace promotionSpace) {

        //补全数据
        promotionSpace.setUpdateTime(new Date());

        promotionSpaceMapper.updatePromotionSpace(promotionSpace);
    }
}
