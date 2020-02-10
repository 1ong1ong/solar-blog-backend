package com.cxlsky.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author cxl
 * @since 2020-02-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class BComment implements Serializable {

private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

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
     * 评论的状态
     */
    private Boolean status;

    /**
     * 评论的内容
     */
    private String content;

    /**
     * 评论文章id， 0：关于我    -1：时间线   >0：文章id
     */
    private Long articleId;

    /**
     * 添加时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime modifyTime;


}
