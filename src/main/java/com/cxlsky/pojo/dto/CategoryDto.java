package com.cxlsky.pojo.dto;

import com.cxlsky.pojo.entity.BCategory;
import lombok.Data;

@Data
public class CategoryDto extends BCategory {

    private Long articleId;

}
