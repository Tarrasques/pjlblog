package com.pengjl.controller;

import com.alibaba.excel.util.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pengjl.entity.Category;
import com.pengjl.entity.Category;
import com.pengjl.service.CategoryService;
import com.pengjl.utils.MyRequestUtil;
import com.pengjl.utils.ResponseResult;
import com.pengjl.vo.PageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController
@RequestMapping("/content/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/list")
    public ResponseResult list(){
        Page<Category> tagPage = new Page<>(MyRequestUtil.getRequest().getPageNum(), MyRequestUtil.getRequest().getPageSize());
        String name = MyRequestUtil.getRequest().getValue("name");
        String status = MyRequestUtil.getRequest().getValue("status");

        categoryService.page(tagPage,
                new LambdaQueryWrapper<Category>()
                        .like(StringUtils.isNotBlank(name),Category::getName,name)
                        .eq(StringUtils.isNotBlank(status),Category::getStatus,status)
        );
        return ResponseResult.okResult(new PageVo(tagPage.getRecords(), tagPage.getTotal()));
    }
    @GetMapping("/{id}")
    public ResponseResult getById(@PathVariable("id") Long id) {

        return ResponseResult.okResult(categoryService.getById(id));
    }
    @DeleteMapping("/{id}")
    public ResponseResult deleteById(@PathVariable("id") Long... id) {
        Long[] ids = id;
        return ResponseResult.okResult(categoryService.removeByIds(Arrays.asList(ids)));
    }
    @PutMapping()
    public ResponseResult update(@RequestBody Category Category) {
        return ResponseResult.okResult(categoryService.updateById(Category));
    }
    @PostMapping()
    public ResponseResult add(@RequestBody Category Category) {
        return ResponseResult.okResult(categoryService.save(Category));
    }
}
