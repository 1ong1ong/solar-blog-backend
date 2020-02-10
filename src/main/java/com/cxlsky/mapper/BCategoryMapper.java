package com.cxlsky.mapper;

import com.cxlsky.pojo.dto.CategoryDto;
import com.cxlsky.pojo.entity.BArticle;
import com.cxlsky.pojo.entity.BCategory;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author cxl
 * @since 2020-02-07
 */
public interface BCategoryMapper extends BaseMapper<BCategory> {

    /**
     * 文章分类
     *
     * @param records
     * @return
     */
    List<CategoryDto> selectArticleCategory(List<BArticle> records);
}
