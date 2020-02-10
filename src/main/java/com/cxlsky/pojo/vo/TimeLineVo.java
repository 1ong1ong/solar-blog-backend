package com.cxlsky.pojo.vo;

import com.cxlsky.pojo.entity.BArticle;
import lombok.Data;

import java.util.List;

@Data
public class TimeLineVo {

    private String monthDate;

    private List<BArticle> articleVos;
}
