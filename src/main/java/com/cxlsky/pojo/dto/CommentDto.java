package com.cxlsky.pojo.dto;

import lombok.Data;

@Data
public class CommentDto {

    /**
     * 父级评论的id
     */
    private Long pid;
    /**
     * 评论层级
     */
    private Integer level;
    /**
     * 评论人的昵称（未登录用户）
     */
    private String replyNickname;
    /**
     * 评论人的邮箱地址（未登录用户）
     */
    private String replyEmail;
    /**
     * 评论人的网站地址（未登录用户）
     */
    private String replyUrl;
    /**
     * 评论的内容
     */
    private String content;
    /**
     * 评论文章id， 0：关于我    -1：时间线   >0：文章id
     */
    private Long articleId;
}
