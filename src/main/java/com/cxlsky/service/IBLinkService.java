package com.cxlsky.service;

import com.cxlsky.pojo.entity.BLink;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author cxl
 * @since 2020-02-07
 */
public interface IBLinkService extends IService<BLink> {

    /**
     * 根据类型获取链接
     *
     * @param type
     * @return
     */
    List<BLink> getByType(Integer type);
}
