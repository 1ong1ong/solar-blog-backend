package com.cxlsky.pojo.dto;

import com.cxlsky.pojo.entity.BTag;
import lombok.Data;

@Data
public class TagDto extends BTag {

    private Long articleId;
}
