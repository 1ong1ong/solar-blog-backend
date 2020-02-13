package com.cxlsky.pojo.vo;

import com.cxlsky.pojo.entity.BArticle;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class TimeLineVo implements Serializable {
    private static final long serialVersionUID=1L;

    private String monthDate;

    private List<BArticle> articleVos;
}
