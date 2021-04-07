package com.kbldemo.service;

import com.kbldemo.entity.SysMenu;
import com.baomidou.mybatisplus.extension.service.IService;
import com.kbldemo.entity.SysRole;

import java.util.List;

/**
 * <p>
 * 权限表 服务类
 * </p>
 *
 * @author kbl
 * @since 2021-03-22
 */
public interface SysMenuService extends IService<SysMenu> {

    /**
     * 根据角色查询用户权限
     * @Author Sans
     * @CreateTime 2019/6/19 10:14
     * @Param  roleId 角色ID
     * @Return List<SysMenuEntity> 权限集合
     */
    List<SysMenu> selectSysMenuByRoleId(Long roleId);
}
