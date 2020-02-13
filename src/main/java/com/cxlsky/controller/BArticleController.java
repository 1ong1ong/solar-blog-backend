package com.cxlsky.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cxlsky.pojo.entity.BArticle;
import com.cxlsky.pojo.query.ArticleQuery;
import com.cxlsky.pojo.vo.ArticleVo;
import com.cxlsky.pojo.vo.TimeLineVo;
import com.cxlsky.service.IBArticleService;
import com.cxlsky.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author cxl
 * @since 2020-02-07
 */
@RestController
@RequestMapping("/article")
public class BArticleController {

    @Autowired
    private IBArticleService ibArticleService;

    @GetMapping("/page")
    public ResponseEntity<IPage<ArticleVo>> getArticlePage(ArticleQuery articleQuery) {
        IPage<ArticleVo> articlePage = ibArticleService.getArticlePage(articleQuery);
        return ResultUtil.ok(articlePage);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArticleVo> getArticleDetail(@PathVariable("id") Long id) {
        ArticleVo articleVo = ibArticleService.getArticleDetail(id);
        return ResultUtil.ok(articleVo);
    }

    @GetMapping("/latest")
    public ResponseEntity<List<ArticleVo>> getArticleLatest() {
        List<ArticleVo> articleVos = ibArticleService.getArticleLatest();
        return ResultUtil.ok(articleVos);
    }

    @GetMapping("/timeline")
    public ResponseEntity<List<TimeLineVo>> getArticleTimeLine() {
        List<TimeLineVo> timeLineVos = ibArticleService.getArticleTimeLine();
        return ResultUtil.ok(timeLineVos);
    }

    @DeleteMapping("/cache/all")
    public ResponseEntity<Boolean> articleCacheEvict() {
        ibArticleService.articleCacheEvict();
        return ResultUtil.ok(Boolean.TRUE);
    }
}

