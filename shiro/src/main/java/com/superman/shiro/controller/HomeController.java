package com.superman.shiro.controller;


import com.superman.shiro.common.util.ShiroUtil;
import com.superman.shiro.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;

import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * @Auther: Ningsc
 * @Date: 2019/5/19 11:57
 * @Description:
 */
@Slf4j
@Controller
public class HomeController {

    @Autowired
    SysUserService sysUserService;


    @RequestMapping("/doLogin")
    public String doLogin(@RequestParam("username") String username,
                          @RequestParam("password") String password) {
        // 将用户名及密码封装到UsernamePasswordToken
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        try {
            ShiroUtil.login(token);
            // 判断当前用户是否登录
            if (ShiroUtil.getSubjct().isAuthenticated() == true) {
                return "/index.html";
            }
        } catch (AuthenticationException e) {
            e.printStackTrace();
            System.out.println("登录失败");
        }
        return "/loginPage.html";
    }

    @RequestMapping("/doRegister")
    public String doRegister(@RequestParam("username") String username,
                             @RequestParam("password") String password) {

        boolean result = sysUserService.registerData(username,password);
        if(result){
            return "redirect:/login";
        }
        return "redirect:/register";
    }

    @RequestMapping(value = "/login")
    public String login() {
        log.info("login() 方法被调用");
        return "loginPage.html";
    }

    @RequestMapping(value = "/register")
    public String register() {
        log.info("register() 方法被调用");
        return "registerPage.html";
    }

    @RequestMapping(value = "/403")
    public String unknow() {
        return "/error/403.html";
    }

}
