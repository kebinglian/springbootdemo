package com.kbldemo.service;


import com.kbldemo.entity.SysUser;

public interface LoginService {
    SysUser getUserByName(String userName);
}
