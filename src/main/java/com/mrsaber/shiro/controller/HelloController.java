package com.mrsaber.shiro.controller;

import com.mrsaber.shiro.Mapper.UserMapper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HelloController {


    @RequestMapping("login.do")
    public String sayHi(String username,String password)
    {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username,password);
        try {
            subject.login(token);
        } catch (AuthenticationException e) {
            //登录失败处理
            e.printStackTrace();
            return "Sorry....";
        }
        return "Hi,Boy!";
    }

    @RequiresRoles("ADMIN")
    @RequestMapping("admin.do")
    public String  admin()
    {
       return "管理员可以访问";
    }

    @RequestMapping("guest.do")
    public String  guest()
    {
       return "陌生人可以访问";
    }

    @RequestMapping("logout.do")
    public void  logout()
    {
        System.out.println(SecurityUtils.getSubject());
        SecurityUtils.getSubject().logout();
    }
}
