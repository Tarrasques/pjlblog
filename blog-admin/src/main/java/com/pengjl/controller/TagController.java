package com.pengjl.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pengjl.entity.Tag;
import com.pengjl.service.TagService;
import com.pengjl.utils.MyRequestUtil;
import com.pengjl.utils.ResponseResult;
import com.pengjl.vo.PageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;


@RestController
@RequestMapping("/content/tag")
public class TagController {


    @Autowired
    private TagService tagService;

    @GetMapping("/list")
    public ResponseResult list(){
        Page<Tag> tagPage = new Page<>(MyRequestUtil.getRequest().getPageNum(), MyRequestUtil.getRequest().getPageSize());
        tagService.page(tagPage);
        return ResponseResult.okResult(new PageVo(tagPage.getRecords(), tagPage.getTotal()));
    }
    @GetMapping("/{id}")
    public ResponseResult getById(@PathVariable("id") Long id) {

        return ResponseResult.okResult(tagService.getById(id));
    }
    @DeleteMapping("/{id}")
    public ResponseResult deleteById(@PathVariable("id") Long... id) {
        Long[] ids = id;
        return ResponseResult.okResult(tagService.removeByIds(Arrays.asList(ids)));
    }
    @PutMapping()
    public ResponseResult update(@RequestBody Tag tag) {
        return ResponseResult.okResult(tagService.updateById(tag));
    }
    @PostMapping()
    public ResponseResult add(@RequestBody Tag tag) {
        return ResponseResult.okResult(tagService.save(tag));
    }
}
