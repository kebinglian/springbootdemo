package com.kbldemo.service.impl;

import com.kbldemo.entity.SysUser;
import com.kbldemo.mapper.SysUserMapper;
import com.kbldemo.service.RedisService;
import com.kbldemo.service.SysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统用户表 服务实现类
 * </p>
 *
 * @author kbl
 * @since 2021-03-22
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;
    /**
     * 根据用户名查询实体
     * @Author Sans
     * @CreateTime 2019/6/14 16:30
     * @Param  username 用户名
     * @Return SysUserEntity 用户实体
     */
    @Override
    public SysUser selectUserByName(String username) {
        /*QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(SysUser::getUsername,username);
        return this.baseMapper.selectOne(queryWrapper);*/
       return sysUserMapper.SelectUserByName(username);
    }

    @Autowired
    private RedisService redisService;


    @Override
    public int queryAllStudentCount() {
        // 设置redisTemplate对象key的序列化方式
        String obj = redisService.get("allStudentCount");
        int allStudentCount=0;
        if(obj == null || obj.isEmpty()){

            allStudentCount = sysUserMapper.selectAllStudentCount();
            /*allStudentCount = userMapper.selectCount(null);*/
            // 并且把查询到的数据放入大redis中
            redisService.set("allStudentCount",allStudentCount);
        }else {
            allStudentCount =Integer.parseInt(obj);

        }
        return allStudentCount;
    }
}
