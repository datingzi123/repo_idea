package com.lagou.dao;

        import com.lagou.domain.PromotionAd;
        import com.lagou.domain.PromotionAdVO;

        import java.util.List;

public interface PromotionAdMapper {

    //分页查询广告信息
    public List<PromotionAd> findAllPromotionAdByPage();

    //新建广告
    public void savePromotionAd(PromotionAd promotionAd);

    //回显广告：根据id查询广告
    public PromotionAd findPromotionAdById(Integer id);

    //更新广告
    public void updatePromotionAd(PromotionAd promotionAd);

    //修改广告状态上下线  根据id修改status  PromotionAd接收参数id status
    public void updatePromotionAdStatus(PromotionAd promotionAd);

}
