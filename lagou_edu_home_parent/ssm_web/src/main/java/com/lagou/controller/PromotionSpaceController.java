package com.lagou.controller;

import com.lagou.domain.PromotionSpace;
import com.lagou.domain.ResponseResult;
import com.lagou.service.PromotionSpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/promotionSpace")
public class PromotionSpaceController {

    @Autowired
    private PromotionSpaceService promotionSpaceService;

    //获取所有广告位
    @RequestMapping("/findAllPromotionSpace")
    public ResponseResult findALlPromotionSpace(){

        List<PromotionSpace> allPromotionSpace = promotionSpaceService.findAllPromotionSpace();

        ResponseResult responseResult = new ResponseResult(true, 200, "获取所有广告位成功", allPromotionSpace);
        return responseResult;
    }

    //添加&修改广告位  前端传递的是json串
    @RequestMapping("/saveOrUpdatePromotionSpace")
    public ResponseResult saveOrUpdatePromotionSpace(@RequestBody PromotionSpace promotionSpace){

        //未携带id，新增操作  表中id自增
        if(promotionSpace.getId() == null){

            promotionSpaceService.savePromotionSpace(promotionSpace);
            return new ResponseResult(true,200,"添加广告位成功",null);
        }else {

            //携带id，修改操作
            promotionSpaceService.updatePromotionSpace(promotionSpace);
            return new ResponseResult(true,200,"修改广告位成功",null);
        }
    }

    //回显广告位：根据id查询广告位
    @RequestMapping("/findPromotionSpaceById")
    public ResponseResult findPromotionById(Integer id){

        PromotionSpace promotionSpace = promotionSpaceService.findPromotionById(id);

        return new ResponseResult(true,200,"回显广告位成功",promotionSpace);
    }
}
