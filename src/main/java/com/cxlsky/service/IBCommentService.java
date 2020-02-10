package com.cxlsky.service;

import com.cxlsky.pojo.dto.CommentDto;
import com.cxlsky.pojo.entity.BComment;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cxlsky.pojo.vo.CommentVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author cxl
 * @since 2020-02-07
 */
public interface IBCommentService extends IService<BComment> {

    /**
     * 回复评论
     * @param commentDto
     */
    void reply(CommentDto commentDto);

    /**
     * 获取评论列表
     *
     * @param articleId
     * @return
     */
    List<CommentVo> getByArticle(Long articleId);
}
