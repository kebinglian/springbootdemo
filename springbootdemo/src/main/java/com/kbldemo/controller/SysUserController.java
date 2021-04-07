package com.kbldemo.controller;


import com.kbldemo.config.aspect.SysAopLog;
import com.kbldemo.config.http.response.JsonResult;
import com.kbldemo.entity.SysUser;
import com.kbldemo.service.SysUserService;
import com.kbldemo.system.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 系统用户表 前端控制器
 * </p>
 *
 * @author kbl
 * @since 2021-03-22
 */
@RestController
@RequestMapping("/sys-user")
public class SysUserController {

    @Autowired
    SysUserService sysUserService;


    @GetMapping("/getUserInfo")
    @ResponseBody
    @SysAopLog(value = "获取用户详情信息")
    public Object getUserInfo() {
        SysUser user=sysUserService.getById("1");

        return user;

    }


    @GetMapping("/{id}")
    @ResponseBody
    @SysAopLog(value = "获取用户信息详情")
    public String getUser(@PathVariable("id") Integer id)
    {
        try {
            return new JsonResult<>().JsonSuccessResult(sysUserService.getById(id));
        } catch (Exception e) {
            Log4j.showLogInfo(e.getMessage(),e);
            return new JsonResult<>().JsonErrorResult("系统错误！");
        }
    }

    @PostMapping("/getUserByMode")
    @ResponseBody
    @SysAopLog(value = "获取用户信息详情")
    public String getUserByMode(SysUser user)
    {
        try {
            return new JsonResult<>().JsonSuccessResult(sysUserService.getById(user.getUserId()));
        } catch (Exception e) {
            Log4j.showLogInfo(e.getMessage(),e);
            return new JsonResult<>().JsonErrorResult("系统错误！");
        }
    }

    @GetMapping("/getError")
    @ResponseBody
    @SysAopLog(value = "获取用户详情信息")
    public String getError() {
        try {
            int i=5;
            int y=0;
            int z=i/y;


            return new JsonResult<>().JsonSuccessResult("成功");
        } catch (Exception e) {
            Log4j.showLogInfo(e.getMessage(),e);
            return new JsonResult<>().JsonErrorResult("系统错误！");
        }

    }

    @GetMapping("/getAllUser")
    @ResponseBody
    @SysAopLog(value = "获取用户总人数信息")
    public Object getAllUser(){
        int i = sysUserService.queryAllStudentCount();
        return new JsonResult<>().JsonSuccessResult("总人数为："+i);

    }
}
