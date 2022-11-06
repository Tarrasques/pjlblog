package com.pengjl.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pengjl.entity.Menu;

import java.util.List;


/**
 * 菜单权限表(Menu)表数据库访问层
 *
 * @author pengjianglin
 * @since 2022-11-05 22:20:54
 */
public interface MenuMapper extends BaseMapper<Menu> {

    List<String> selectPermsByUserId(Long id);

    List<Menu> selectAllRouterMenu();

    List<Menu> selectRouterMenuTreeByUserId(Long userId);
}

