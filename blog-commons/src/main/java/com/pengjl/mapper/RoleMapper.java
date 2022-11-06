package com.pengjl.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pengjl.entity.Role;

import java.util.List;


/**
 * 角色信息表(Role)表数据库访问层
 *
 * @author pengjianglin
 * @since 2022-11-05 22:27:52
 */
public interface RoleMapper extends BaseMapper<Role> {


    List<String> selectRoleKeyByUserId(Long id);
}

