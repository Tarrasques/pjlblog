package com.pengjl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pengjl.mapper.LinkMapper;
import com.pengjl.entity.Link;
import com.pengjl.service.LinkService;
import com.pengjl.utils.BeanCopyUtils;
import com.pengjl.utils.ResponseResult;
import com.pengjl.utils.SystemConstants;
import com.pengjl.vo.LinkVo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 友链(Link)表服务实现类
 *
 * @author pengjianglin
 * @since 2022-10-30 19:56:42
 */
@Service("linkService")
public class LinkServiceImpl extends ServiceImpl<LinkMapper, Link> implements LinkService {

    @Override
    public ResponseResult getAllLink() {
        LambdaQueryWrapper<Link> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Link::getStatus, SystemConstants.STATUS_NORMAL);
        List<Link> list = list(wrapper);
        List<LinkVo> linkVos = BeanCopyUtils.copyBeanList(list, LinkVo.class);

        return ResponseResult.okResult(linkVos);
    }
}

