package com.cxlsky.mapper;

import com.cxlsky.pojo.entity.BComment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cxlsky.pojo.vo.CommentVo;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author cxl
 * @since 2020-02-07
 */
public interface BCommentMapper extends BaseMapper<BComment> {

    /**
     * 根据文章id获取评论
     *
     * @param articleId
     * @return
     */
    List<CommentVo> getByArticle(Long articleId);


    List<CommentVo> getByPid(Long pid);
}
