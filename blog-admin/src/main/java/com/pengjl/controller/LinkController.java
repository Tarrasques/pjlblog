package com.pengjl.controller;

import com.alibaba.excel.util.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pengjl.entity.Link;
import com.pengjl.service.LinkService;
import com.pengjl.utils.MyRequestUtil;
import com.pengjl.utils.ResponseResult;
import com.pengjl.vo.PageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController
@RequestMapping("/content/link")
public class LinkController {
    
    @Autowired
    private LinkService linkService;

    @GetMapping("/list")
    public ResponseResult list(){
        Page<Link> tagPage = new Page<>(MyRequestUtil.getRequest().getPageNum(), MyRequestUtil.getRequest().getPageSize());
        String name = MyRequestUtil.getRequest().getValue("name");
        String status = MyRequestUtil.getRequest().getValue("status");

        linkService.page(tagPage,
                new LambdaQueryWrapper<Link>()
                        .like(StringUtils.isNotBlank(name),Link::getName,name)
                        .eq(StringUtils.isNotBlank(status),Link::getStatus,status)
        );
        return ResponseResult.okResult(new PageVo(tagPage.getRecords(), tagPage.getTotal()));
    }
    @GetMapping("/{id}")
    public ResponseResult getById(@PathVariable("id") Long id) {

        return ResponseResult.okResult(linkService.getById(id));
    }
    @DeleteMapping("/{id}")
    public ResponseResult deleteById(@PathVariable("id") Long... id) {
        Long[] ids = id;
        return ResponseResult.okResult(linkService.removeByIds(Arrays.asList(ids)));
    }
    @PutMapping()
    public ResponseResult update(@RequestBody Link Link) {
        return ResponseResult.okResult(linkService.updateById(Link));
    }
    @PostMapping()
    public ResponseResult add(@RequestBody Link Link) {
        return ResponseResult.okResult(linkService.save(Link));
    }
}
