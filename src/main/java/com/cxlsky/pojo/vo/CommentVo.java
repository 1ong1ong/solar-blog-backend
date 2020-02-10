package com.cxlsky.pojo.vo;

import com.cxlsky.pojo.entity.BComment;
import lombok.Data;

import java.util.List;

@Data
public class CommentVo extends BComment {

    private List<CommentVo> children;
}
