package com.cxlsky.mapper;

import com.cxlsky.pojo.dto.TagDto;
import com.cxlsky.pojo.entity.BArticle;
import com.cxlsky.pojo.entity.BTag;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author cxl
 * @since 2020-02-07
 */
public interface BTagMapper extends BaseMapper<BTag> {

    List<TagDto> selectArticleTags(List<BArticle> records);
}
