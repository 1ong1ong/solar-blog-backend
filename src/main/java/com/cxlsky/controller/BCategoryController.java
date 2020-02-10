package com.cxlsky.controller;


import com.cxlsky.pojo.entity.BCategory;
import com.cxlsky.service.IBCategoryService;
import com.cxlsky.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 *  前端控制器
 * </p>
 *
 * @author cxl
 * @since 2020-02-07
 */
@RestController
@RequestMapping("/category")
public class BCategoryController {

    @Autowired
    private IBCategoryService ibCategoryService;

    @GetMapping("/all")
    public ResponseEntity<List<BCategory>> getAll() {
        List<BCategory> list = ibCategoryService.list();
        return ResultUtil.ok(list);
    }
}

