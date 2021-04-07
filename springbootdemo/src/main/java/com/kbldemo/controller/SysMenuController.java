package com.kbldemo.controller;


import com.kbldemo.entity.SysMenu;
import com.kbldemo.entity.SysRole;
import com.kbldemo.entity.SysRoleMenu;
import com.kbldemo.entity.SysUser;
import com.kbldemo.service.SysMenuService;
import com.kbldemo.service.SysRoleMenuService;
import com.kbldemo.service.SysRoleService;
import com.kbldemo.service.SysUserService;
import com.kbldemo.system.ShiroUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 权限表 前端控制器
 * </p>
 *
 * @author kbl
 * @since 2021-03-22
 */
@RestController
@RequestMapping("/sys-menu")
public class SysMenuController {


}
