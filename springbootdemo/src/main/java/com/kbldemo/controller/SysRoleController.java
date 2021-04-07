package com.kbldemo.controller;


import com.kbldemo.config.http.response.JsonResult;
import com.kbldemo.entity.SysMenu;
import com.kbldemo.entity.SysRole;
import com.kbldemo.entity.SysRoleMenu;
import com.kbldemo.entity.SysUser;
import com.kbldemo.service.SysMenuService;
import com.kbldemo.service.SysRoleMenuService;
import com.kbldemo.service.SysRoleService;
import com.kbldemo.service.SysUserService;
import com.kbldemo.system.Log4j;
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
 * 角色表 前端控制器
 * </p>
 *
 * @author kbl
 * @since 2021-03-22
 */
@RestController
@RequestMapping("/sys-role")
public class SysRoleController {

    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private SysMenuService sysMenuService;
    @Autowired
    private SysRoleMenuService sysRoleMenuService;

    /**
     * 获取用户信息集合
     *
     * @Return Map<String, Object> 返回结果
     */
    @RequestMapping("/getUserInfoList")
    @RequiresPermissions("sys:user:info")
    public String getUserInfoList() {
        try {
            List<SysUser> sysUserEntityList = sysUserService.list();
            return new JsonResult<>().JsonSuccessResult(sysUserEntityList);
        } catch (Exception e) {
            Log4j.showLogInfo(e.getMessage(), e);
            return new JsonResult<>().JsonErrorResult("系统错误！");
        }

    }

    /**
     * 获取角色信息集合
     *
     * @Return Map<String, Object> 返回结果
     */
    @RequestMapping("/getRoleInfoList")
    @RequiresPermissions("sys:role:info")
    public String getRoleInfoList() {

        try {
            List<SysRole> sysRoleEntityList = sysRoleService.list();
            return new JsonResult<>().JsonSuccessResult(sysRoleEntityList);
        } catch (Exception e) {
            Log4j.showLogInfo(e.getMessage(), e);
            return new JsonResult<>().JsonErrorResult("系统错误！");
        }
    }

    /**
     * 获取权限信息集合
     *
     * @Return Map<String, Object>
     */
    @RequestMapping("/getMenuInfoList")
    @RequiresPermissions("sys:menu:info")
    public String getMenuInfoList() {

        try {
            List<SysMenu> sysMenuEntityList = sysMenuService.list();
            return new JsonResult<>().JsonSuccessResult(sysMenuEntityList);
        } catch (Exception e) {
            Log4j.showLogInfo(e.getMessage(), e);
            return new JsonResult<>().JsonErrorResult("系统错误！");
        }
    }

    /**
     * 获取所有数据
     *
     * @Return Map<String, Object>
     */
    @RequestMapping("/getInfoAll")
    @RequiresPermissions("sys:info:all")
    public String getInfoAll() {


        try {
            Map<String, Object> map = new HashMap<>();
            List<SysUser> sysUserEntityList = sysUserService.list();
            map.put("sysUserEntityList", sysUserEntityList);
            List<SysRole> sysRoleEntityList = sysRoleService.list();
            map.put("sysRoleEntityList", sysRoleEntityList);
            List<SysMenu> sysMenuEntityList = sysMenuService.list();
            map.put("sysMenuEntityList", sysMenuEntityList);

            return new JsonResult<>().JsonSuccessResult(map);
        } catch (Exception e) {
            Log4j.showLogInfo(e.getMessage(), e);
            return new JsonResult<>().JsonErrorResult("系统错误！");
        }
    }

    /**
     * 添加管理员角色权限(测试动态权限更新)
     *
     * @Param username 用户ID
     * @Return Map<String, Object>
     */
    @RequestMapping("/addMenu")
    public String addMenu() {

        try {
            SysRoleMenu sysRoleMenuEntity = new SysRoleMenu();
            sysRoleMenuEntity.setMenuId(4L);
            sysRoleMenuEntity.setRoleId(1L);
            sysRoleMenuService.save(sysRoleMenuEntity);
            //清除缓存
            String username = "admin";
            ShiroUtils.deleteCache(username, false);
            Map<String, Object> map = new HashMap<>();
            map.put("code", 200);
            map.put("msg", "权限添加成功");
            return new JsonResult<>().JsonSuccessResult(map);
        } catch (Exception e) {
            Log4j.showLogInfo(e.getMessage(), e);
            return new JsonResult<>().JsonErrorResult("系统错误！");
        }
    }
}
