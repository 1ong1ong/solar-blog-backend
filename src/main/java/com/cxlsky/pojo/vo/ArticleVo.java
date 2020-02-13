package com.cxlsky.pojo.vo;

import com.cxlsky.pojo.dto.CategoryDto;
import com.cxlsky.pojo.dto.TagDto;
import com.cxlsky.pojo.entity.BArticle;
import lombok.Data;

import java.util.List;

@Data
public class ArticleVo extends BArticle {

    private List<TagDto> tags;

    private CategoryDto category;
}
