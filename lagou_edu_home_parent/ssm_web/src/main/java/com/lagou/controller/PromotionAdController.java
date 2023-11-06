package com.lagou.controller;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.PromotionAd;
import com.lagou.domain.PromotionAdVO;
import com.lagou.domain.ResponseResult;
import com.lagou.service.PromotionAdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/promotionAd")
public class PromotionAdController {

    @Autowired
    protected PromotionAdService promotionAdService;

    //广告分页查询
    @RequestMapping("/findAllPromotionAdByPage")
    public ResponseResult findAllPromotionAdByPage(PromotionAdVO promotionAdVO){

        PageInfo<PromotionAd> pageInfo = promotionAdService.findAllPromotionAdByPage(promotionAdVO);

        return new ResponseResult(true,200,"广告分页查询成功",pageInfo);
    }

    /*
        图片上传代码步骤：
            1.判断上传文件是否为空 参数file
            2.获取项目部署路径并截取 request
            3.获取原始文件名
            4.生成新文件名
            5.声明上传路径：截取路径+文件上传目录
            6.响应数据：文件名+文件路径 map
     */
    @RequestMapping("/promotionAdFileUpload")
    public ResponseResult promotionAdFileUpload(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws IOException {

        if(file.isEmpty()){
            throw new RuntimeException();
        }

        String realPath = request.getServletContext().getRealPath("/");
        String webappPath = realPath.substring(0, realPath.indexOf("ssm_web"));

        String originalFilename = file.getOriginalFilename();
        String newFileName = System.currentTimeMillis() + originalFilename.substring(originalFilename.lastIndexOf("."));

        String uploadPath = webappPath + "upload//";
        File filePath = new File(uploadPath+newFileName); //这句不懂

        if(!filePath.getParentFile().exists()){
            filePath.getParentFile().mkdirs();
            System.out.println("创建目录：" + filePath);
        }

        file.transferTo(filePath);

        Map<String, String> map = new HashMap<>();
        map.put("fileName",newFileName);
        map.put("filePath","http://localhost:8080/upload/ + newFileName");

        return new ResponseResult(true,200,"上传图片成功",map);
    }

    //新建&更新广告
    @RequestMapping("/saveOrUpdatePromotionAd")
    public ResponseResult saveOrUpdatePromotionAd(@RequestBody PromotionAd promotionAd) {

        if(promotionAd.getId() == null){
            promotionAdService.savePromotionAd(promotionAd);
            return new ResponseResult(true,200,"新建广告成功",null);
        }else {
            try {
                promotionAdService.updatePromotionAd(promotionAd);
                return new ResponseResult(true,200,"修改广告成功",null);
            } catch (ParseException e) {
                e.printStackTrace();
                return null;
            }
        }
    }

    //回显广告：根据id查询广告
    @RequestMapping("/findPromotionAdById")
    public ResponseResult findPromotionAdById(Integer id){

        PromotionAd promotionAd = promotionAdService.findPromotionAdById(id);
        return new ResponseResult(true,200,"回显广告成功",promotionAd);
    }


    //修改广告状态上下线
    @RequestMapping("/updatePromotionAdStatus")
    public ResponseResult updatePromotionAdStatus(Integer id,Integer status){

        if(status == 1){
            promotionAdService.updatePromotionAdStatus(id,status);// 1 上线
        }else {
            promotionAdService.updatePromotionAdStatus(id,0);// 0下线
        }

        /*promotionAdService.updatePromotionAdStatus(id,status);*/

        Map<String, Integer> map = new HashMap<>();
        map.put("status",status);

        return new ResponseResult(true,200,"修改广告状态成功",map);
    }
}




















