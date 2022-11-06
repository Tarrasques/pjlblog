package com.pengjl.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pengjl.entity.Tag;
import com.pengjl.mapper.TagMapper;
import com.pengjl.service.TagService;
import org.springframework.stereotype.Service;

/**
 * 标签(Tag)表服务实现类
 *
 * @author pengjianglin
 * @since 2022-11-05 17:03:27
 */
@Service("tagService")
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {

}

