package com.pengjl.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pengjl.entity.Role;

import java.util.List;


/**
 * 角色信息表(Role)表服务接口
 *
 * @author pengjianglin
 * @since 2022-11-05 22:27:52
 */
public interface RoleService extends IService<Role> {

    List<String> selectRoleKeyByUserId(Long id);
}

