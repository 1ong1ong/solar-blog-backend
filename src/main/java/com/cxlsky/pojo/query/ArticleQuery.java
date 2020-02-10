package com.cxlsky.pojo.query;

import lombok.Data;

@Data
public class ArticleQuery extends PageQuery {

    private Long categoryId;

    private Long tagId;
}
