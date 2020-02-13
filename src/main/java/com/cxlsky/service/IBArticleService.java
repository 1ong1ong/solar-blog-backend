package com.cxlsky.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cxlsky.pojo.entity.BArticle;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cxlsky.pojo.query.ArticleQuery;
import com.cxlsky.pojo.vo.ArticleVo;
import com.cxlsky.pojo.vo.TimeLineVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author cxl
 * @since 2020-02-07
 */
public interface IBArticleService extends IService<BArticle> {

    /**
     * 分页列表
     *
     * @param articleQuery
     * @return
     */
    IPage<ArticleVo> getArticlePage(ArticleQuery articleQuery);

    /**
     * 文章详情
     *
     * @param id
     * @return
     */
    ArticleVo getArticleDetail(Long id);

    /**
     * 获取最近更新文章  3篇
     *
     * @return
     */
    List<ArticleVo> getArticleLatest();

    /**
     * 获取时间线
     *
     * @return
     */
    List<TimeLineVo> getArticleTimeLine();

    /**
     * 删除缓存
     */
    void articleCacheEvict();
}
