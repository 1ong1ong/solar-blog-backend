package com.cxlsky.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cxlsky.pojo.entity.BConfig;
import com.cxlsky.service.IBConfigService;
import com.cxlsky.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author cxl
 * @since 2020-02-07
 */
@RestController
@RequestMapping("/config")
public class BConfigController {

    @Autowired
    private IBConfigService ibConfigService;

    @GetMapping("")
    public ResponseEntity<BConfig> getConfig() {
        BConfig one = ibConfigService.getOne(new QueryWrapper<>());
        return ResultUtil.ok(one);
    }
}

