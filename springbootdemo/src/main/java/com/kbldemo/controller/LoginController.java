package com.kbldemo.controller;
import com.alibaba.druid.util.StringUtils;
import com.kbldemo.config.aspect.SysAopLog;
import com.kbldemo.config.http.response.JsonResult;
import com.kbldemo.config.jwt.JwtUtil;
import com.kbldemo.entity.SysUser;
import com.kbldemo.entity.SysUserRole;
import com.kbldemo.service.SysUserRoleService;
import com.kbldemo.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.subject.Subject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
public class LoginController {

    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysUserRoleService sysUserRoleService;


    @RequestMapping("/login")
    @SysAopLog(value = "用户登陆")
    public String login(@RequestBody SysUser user) {
        if (StringUtils.isEmpty(user.getUsername()) || StringUtils.isEmpty(user.getPassword())) {
            return "请输入用户名和密码！";
        }
        //用户认证信息
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(
                user.getUsername(),
                user.getPassword()
        );
        try {
            //进行验证，这里可以捕获异常，然后返回对应信息
            subject.login(usernamePasswordToken);
        } catch (UnknownAccountException e) {
            log.error("用户名不存在！", e);
            return "用户名不存在！";
        } catch (AuthenticationException e) {
            log.error("账号或密码错误！", e);
            return "账号或密码错误！";
        } catch (AuthorizationException e) {
            log.error("没有权限！", e);
            return "没有权限";
        }
        JwtUtil jwtUtil = new JwtUtil();
        Map<String, Object> chaim = new HashMap<>();
        chaim.put("username", user.getUsername());
        String jwtToken = jwtUtil.encode(user.getUsername(), 5 * 60 * 1000, chaim);
        Map<String, String> map = new HashMap<>();
        map.put("msg", user.getUsername()+"欢迎你登录成功");
        map.put("token", jwtToken);
        return new JsonResult<>().JsonSuccessResult(map);
    }

    /**
     * 未登录
     * @Author Sans
     * @CreateTime 2019/6/20 9:22
     */
    @RequestMapping("/unauth")
    public Map<String,Object> unauth(){
        Map<String,Object> map = new HashMap<>();
        map.put("code",500);
        map.put("msg","未登录");
        return map;
    }


    /**
     * 添加一个用户演示接口
     * 这里仅作为演示不加任何权限和重复查询校验
     * @Author Sans
     * @CreateTime 2020/1/6 9:22
     */
    @RequestMapping("/testAddUser")
    public Map<String,Object> testAddUser(){
        // 设置基础参数
        SysUser sysUser = new SysUser();
        sysUser.setUsername("user");
        sysUser.setState("NORMAL");
        // 随机生成盐值
        String salt = RandomStringUtils.randomAlphanumeric(20);
        sysUser.setSalt(salt);
        // 进行加密
        String password ="123456";
        sysUser.setPassword(com.kbldemo.system.SHA256Util.sha256(password, sysUser.getSalt()));
        // 保存用户
        sysUserService.save(sysUser);
        // 保存角色
        SysUserRole sysUserRoleEntity = new SysUserRole();
        sysUserRoleEntity.setUserId(sysUser.getUserId()); // 保存用户完之后会把ID返回给用户实体
        sysUserRoleService.save(sysUserRoleEntity);
        // 返回结果
        Map<String,Object> map = new HashMap<>();
        map.put("code",0);
        map.put("msg","添加成功");
        return map;
    }


    @RequestMapping("/testlogin")
    public ResponseEntity<Map<String, String>> login(String username, String password) {
        log.info("username:{},password:{}",username,password);
        Map<String, String> map = new HashMap<>();
        if (!"tom".equals(username) || !"123".equals(password)) {
            map.put("msg", "用户名密码错误");
            return ResponseEntity.ok(map);
        }
        JwtUtil jwtUtil = new JwtUtil();
        Map<String, Object> chaim = new HashMap<>();
        chaim.put("username", username);
        String jwtToken = jwtUtil.encode(username, 5 * 60 * 1000, chaim);
        map.put("msg", "登录成功");
        map.put("token", jwtToken);
        return ResponseEntity.ok(map);
    }
    @RequestMapping("/testdemo")
    public ResponseEntity<String> testdemo() {
        return ResponseEntity.ok("我爱蛋炒饭");
    }
}
