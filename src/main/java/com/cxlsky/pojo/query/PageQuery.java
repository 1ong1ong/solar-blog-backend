package com.cxlsky.pojo.query;

import lombok.Data;

@Data
public class PageQuery {
    private int pageNum = 1;
    private int pageSize = 12;
}