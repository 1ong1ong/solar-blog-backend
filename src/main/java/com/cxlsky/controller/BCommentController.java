package com.cxlsky.controller;


import com.cxlsky.pojo.dto.CommentDto;
import com.cxlsky.pojo.entity.BComment;
import com.cxlsky.pojo.vo.CommentVo;
import com.cxlsky.service.IBCommentService;
import com.cxlsky.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author cxl
 * @since 2020-02-07
 */
@RestController
@RequestMapping("/comment")
public class BCommentController {

    @Autowired
    private IBCommentService ibCommentService;

    @PostMapping("/reply")
    public ResponseEntity<Boolean> reply(@RequestBody CommentDto commentDto) {
        ibCommentService.reply(commentDto);
        return ResultUtil.ok(true);
    }

    @GetMapping("/{articleId}")
    public ResponseEntity<List<CommentVo>> getByArticle(@PathVariable("articleId") Long articleId) {
        List<CommentVo> commentVos = ibCommentService.getByArticle(articleId);
        return ResultUtil.ok(commentVos);
    }
}

