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
public class BArticle implements Serializable {

private static final long serialVersionUID=1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 文章标题
     */
    private String title;

    /**
     * 文章简介，最多200字
     */
    private String description;

    /**
     * 类型id， bl_category
     */
    private Integer categoryId;

    /**
     * 文章封面图片路径
     */
    private String coverImgUrl;

    /**
     * markdown内容
     */
    private String mdContent;

    /**
     * 字数
     */
    private Integer wordCount;

    /**
     * 文章状态， 1：发布  0：未发布
     */
    private Integer status;

    /**
     * 添加时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime modifyTime;

}
