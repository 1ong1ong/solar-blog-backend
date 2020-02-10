package com.cxlsky.service.impl;

import com.cxlsky.pojo.dto.CommentDto;
import com.cxlsky.pojo.entity.BComment;
import com.cxlsky.mapper.BCommentMapper;
import com.cxlsky.pojo.vo.CommentVo;
import com.cxlsky.service.IBCommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author cxl
 * @since 2020-02-07
 */
@Service
public class BCommentServiceImpl extends ServiceImpl<BCommentMapper, BComment> implements IBCommentService {

    @Autowired
    private BCommentMapper bCommentMapper;

    @Override
    public void reply(CommentDto commentDto) {
        BComment bComment = new BComment();
        BeanUtils.copyProperties(commentDto, bComment);

        if (commentDto.getLevel() == 2) {
            BComment parentComment = bCommentMapper.selectById(commentDto.getPid());

            if (parentComment.getPid() != null) {
                bComment.setPid(parentComment.getPid());
            } else {
                bComment.setPid(parentComment.getId());
            }
        }

        bCommentMapper.insert(bComment);
    }

    @Override
    public List<CommentVo> getByArticle(Long articleId) {
        List<CommentVo> commentVos = bCommentMapper.getByArticle(articleId);
        return commentVos;
    }
}
