package com.cxlsky.controller;


import com.cxlsky.pojo.entity.BLink;
import com.cxlsky.service.IBLinkService;
import com.cxlsky.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
@RequestMapping("/links")
public class BLinkController {

    @Autowired
    private IBLinkService ibLinkService;

    @GetMapping("/type/{type}")
        public ResponseEntity<List<BLink>> getByType(@PathVariable("type") Integer type) {
        List<BLink> bLinks = ibLinkService.getByType(type);
        return ResultUtil.ok(bLinks);
    }
}

