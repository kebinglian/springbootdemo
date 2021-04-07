package com.kbldemo.config.jwt;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @Author kbl
 * @Date 2021/3/27 14:11
 */
public class JwtToken implements AuthenticationToken {

    private String jwt;

    public JwtToken(String jwt) {
        this.jwt = jwt;
    }

    @Override//类似是用户名
    public Object getPrincipal() {
        return jwt;
    }

    @Override//类似密码
    public Object getCredentials() {
        return jwt;
    }
    //返回的都是jwt
}
