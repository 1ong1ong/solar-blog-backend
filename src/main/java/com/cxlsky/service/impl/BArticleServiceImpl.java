package com.cxlsky.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cxlsky.mapper.BArticleMapper;
import com.cxlsky.mapper.BArticleTagMapper;
import com.cxlsky.mapper.BCategoryMapper;
import com.cxlsky.mapper.BTagMapper;
import com.cxlsky.pojo.dto.CategoryDto;
import com.cxlsky.pojo.dto.TagDto;
import com.cxlsky.pojo.entity.BArticle;
import com.cxlsky.pojo.entity.BArticleTag;
import com.cxlsky.pojo.query.ArticleQuery;
import com.cxlsky.pojo.vo.ArticleVo;
import com.cxlsky.pojo.vo.TimeLineVo;
import com.cxlsky.service.IBArticleService;
import com.cxlsky.utils.DateUtil;
import com.cxlsky.utils.WordUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author cxl
 * @since 2020-02-07
 */
@Service
@CacheConfig(cacheNames = "cache1Hour")
public class BArticleServiceImpl extends ServiceImpl<BArticleMapper, BArticle> implements IBArticleService {

    @Autowired
    private BArticleMapper bArticleMapper;
    @Autowired
    private BArticleTagMapper bArticleTagMapper;
    @Autowired
    private BTagMapper bTagMapper;
    @Autowired
    private BCategoryMapper bCategoryMapper;

    @Override
    @Cacheable(key = "'getArticlePage:'+#articleQuery.categoryId+#articleQuery.tagId+#articleQuery.pageNum+articleQuery.pageSize")
    public IPage<ArticleVo> getArticlePage(ArticleQuery articleQuery) {
        IPage<BArticle> articleIPage = new Page<>(articleQuery.getPageNum(), articleQuery.getPageSize());
        IPage<ArticleVo> articleVoIPage = new Page<>(articleQuery.getPageNum(), articleQuery.getPageSize());
        QueryWrapper<BArticle> queryWrapper = articleQueryWrapper(articleQuery);
        if (queryWrapper == null) {
            return articleVoIPage;
        }
        bArticleMapper.selectPage(articleIPage, queryWrapper);

        BeanUtils.copyProperties(articleIPage, articleVoIPage);

        List<BArticle> articleIPageRecords = articleIPage.getRecords();
        if (!CollectionUtils.isEmpty(articleIPageRecords)) {
            List<TagDto> tagDtos = bTagMapper.selectArticleTags(articleIPage.getRecords());
            Map<Long, List<TagDto>> articleTagMap = tagDtos.stream().collect(Collectors.groupingBy(TagDto::getArticleId));

            List<CategoryDto> categoryDtos = bCategoryMapper.selectArticleCategory(articleIPage.getRecords());
            Map<Long, List<CategoryDto>> articleCategoryMap = categoryDtos.stream().collect(Collectors.groupingBy(CategoryDto::getArticleId));

            List<ArticleVo> collect = articleIPageRecords.stream().map(article -> {
                ArticleVo articleVo = new ArticleVo();
                BeanUtils.copyProperties(article, articleVo);
                List<TagDto> bTags = articleTagMap.get(article.getId());
                List<CategoryDto> categories = articleCategoryMap.get(article.getId());
                articleVo.setCategory(categories.get(0));
                articleVo.setTags(bTags);
                return articleVo;
            }).collect(Collectors.toList());
            articleVoIPage.setRecords(collect);
        }

        return articleVoIPage;
    }

    @Override
    @Cacheable(key = "'getArticleLatest:'")
    public List<ArticleVo> getArticleLatest() {
        ArticleQuery articleQuery = new ArticleQuery();
        IPage<ArticleVo> articlePage = getArticlePage(articleQuery);
        List<ArticleVo> records = articlePage.getRecords();

        List<ArticleVo> latestThree = new ArrayList<>();
        if (records.size() > 3) {
            latestThree.add(records.get(0));
            latestThree.add(records.get(1));
            latestThree.add(records.get(2));
        } else {
            latestThree.addAll(records);
        }
        return latestThree;
    }

    @Override
    @Cacheable(key = "'getArticleTimeLine:'")
    public List<TimeLineVo> getArticleTimeLine() {
        String dateFormat = "MM/yyyy";

        List<BArticle> bArticles = bArticleMapper.selectList(new QueryWrapper<BArticle>().lambda().eq(BArticle::getStatus, 1).orderByDesc(BArticle::getCreateTime));
        Map<String, List<BArticle>> monthDateMap = bArticles.stream().collect(Collectors.groupingBy(article -> DateTimeFormatter.ofPattern(dateFormat).format(article.getCreateTime())));

        List<TimeLineVo> timeLineVos = new ArrayList<>();
        monthDateMap.forEach((monthDateKey, articlesValue) -> {
            TimeLineVo timeLineVo = new TimeLineVo();
            timeLineVo.setMonthDate(monthDateKey);
            timeLineVo.setArticleVos(articlesValue);
            timeLineVos.add(timeLineVo);
        });

        return timeLineVos.stream().sorted((o1, o2) -> {
            Date date1 = DateUtil.parseDate(o1.getMonthDate(), dateFormat);
            Date date2 = DateUtil.parseDate(o2.getMonthDate(), dateFormat);
            return  date2.compareTo(date1);
        }).collect(Collectors.toList());
    }

    @Override
    @Cacheable(key = "'getArticleDetail:'+#id")
    public ArticleVo getArticleDetail(Long id) {
        BArticle bArticle = bArticleMapper.selectById(id);

        if (bArticle != null) {
            List<TagDto> bTags = bTagMapper.selectArticleTags(Collections.singletonList(bArticle));
            List<CategoryDto> categories = bCategoryMapper.selectArticleCategory(Collections.singletonList(bArticle));

            ArticleVo articleVo = new ArticleVo();
            BeanUtils.copyProperties(bArticle, articleVo);
            articleVo.setTags(bTags);
            articleVo.setCategory(categories.get(0));

            int wordCount = WordUtil.wordCount(articleVo.getMdContent());
            articleVo.setWordCount(wordCount);

            return articleVo;
        }

        return null;
    }

    @Override
    @CacheEvict(allEntries = true)
    public void articleCacheEvict() {
    }

    private QueryWrapper<BArticle> articleQueryWrapper(ArticleQuery articleQuery) {
        QueryWrapper<BArticle> queryWrapper = new QueryWrapper<>();
        if (articleQuery.getCategoryId() != null) {
            queryWrapper.lambda().eq(BArticle::getCategoryId, articleQuery.getCategoryId());
        }
        if (articleQuery.getTagId() != null) {
            QueryWrapper<BArticleTag> articleTagQueryWrapper = new QueryWrapper<>();
            List<BArticleTag> bArticleTags = bArticleTagMapper.selectList(articleTagQueryWrapper.lambda().eq(BArticleTag::getTagId, articleQuery.getTagId()));
            if (CollectionUtils.isEmpty(bArticleTags)) {
                return null;
            }
            queryWrapper.lambda().in(BArticle::getId, bArticleTags.stream().map(BArticleTag::getArticleId).distinct().collect(Collectors.toList()));
        }
        queryWrapper.lambda().eq(BArticle::getStatus, 1).orderByDesc(BArticle::getCreateTime);
        return queryWrapper;
    }
}
