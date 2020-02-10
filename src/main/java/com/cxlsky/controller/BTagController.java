package com.cxlsky.controller;


import com.cxlsky.pojo.entity.BTag;
import com.cxlsky.service.IBTagService;
import com.cxlsky.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping("/tags")
public class BTagController {

    @Autowired
    private IBTagService ibTagService;

    @GetMapping("/all")
    public ResponseEntity<List<BTag>> all() {
        List<BTag> list = ibTagService.list();
        return ResultUtil.ok(list);
    }
}

