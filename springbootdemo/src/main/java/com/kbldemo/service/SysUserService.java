package com.kbldemo.service;

import com.kbldemo.entity.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 系统用户表 服务类
 * </p>
 *
 * @author kbl
 * @since 2021-03-22
 */
public interface SysUserService extends IService<SysUser> {

    /**
     * 根据用户名查询实体
     * @Author Sans
     * @CreateTime 2019/6/14 16:30
     * @Param  username 用户名
     * @Return SysUserEntity 用户实体
     */
    SysUser selectUserByName(String username);

    int queryAllStudentCount();
}
