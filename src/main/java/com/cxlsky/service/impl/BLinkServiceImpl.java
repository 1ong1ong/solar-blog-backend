package com.cxlsky.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cxlsky.pojo.entity.BLink;
import com.cxlsky.mapper.BLinkMapper;
import com.cxlsky.service.IBLinkService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class BLinkServiceImpl extends ServiceImpl<BLinkMapper, BLink> implements IBLinkService {

    @Autowired
    private BLinkMapper bLinkMapper;

    @Override
    public List<BLink> getByType(Integer type) {
        return bLinkMapper.selectList(new QueryWrapper<BLink>().lambda().eq(BLink::getType, type));
    }
}
