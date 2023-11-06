package com.lagou.controller;

import com.lagou.domain.ResponseResult;
import com.lagou.domain.Tmenu;
import com.lagou.service.TmenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tmenu")
public class TmenuController {

    @Autowired
    private TmenuService tmenuService;

    // get请求  pid=0
    @RequestMapping("/findTmenuByPid")
    public ResponseResult findTmenuByPid(Integer pid){

        List<Tmenu> tmenuList = tmenuService.findTmenuByPid(pid);

        return new ResponseResult(true,200,"响应成功",tmenuList);
    }
}
