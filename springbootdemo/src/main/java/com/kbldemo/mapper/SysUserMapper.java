package com.kbldemo.mapper;

import com.kbldemo.entity.SysUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;

/**
 * <p>
 * 系统用户表 Mapper 接口
 * </p>
 *
 * @author kbl
 * @since 2021-03-22
 */
public interface SysUserMapper extends BaseMapper<SysUser> {

    SysUser SelectUserByName(@Param("username") String username);

    int selectAllStudentCount();
}
