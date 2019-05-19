package com.superman.shiro.controller;

import com.superman.shiro.common.util.MD5Utils;
import com.superman.shiro.common.util.ShiroUtil;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @Auther: Ningsc
 * @Date: 2019/5/19 11:57
 * @Description:
 */
@Controller
public class HomeController {
    @RequestMapping({"/","/index"})
    public String index(){
        return"/index";
    }

    // 这里如果不写method参数的话，默认支持所有请求，如果想缩小请求范围，还是要添加method来支持get, post等等某个请求。
    @RequestMapping("/login")
    public String login(HttpServletRequest request,Map<String, Object> map) throws Exception {
        String username=request.getParameter("username");
    	String password=request.getParameter("password");
        password = MD5Utils.encrypt(username, password);
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        ShiroUtil.login(token);
        if(ShiroUtil.getUser() != null){
            return "redirect:/userInfo/userList";
        }else {
            // 登录失败从request中获取shiro处理的异常信息。
            // shiroLoginFailure:就是shiro异常类的全类名.
            Object exception = request.getAttribute("shiroLoginFailure");
            String msg = "";
            if (exception != null) {
                if (UnknownAccountException.class.isInstance(exception)) {
                    System.out.println("账户不存在");
                    msg = "账户不存在或密码不正确";
                } else if (IncorrectCredentialsException.class.isInstance(exception)) {
                    System.out.println("密码不正确");
                    msg = "账户不存在或密码不正确";
                } else {
                    System.out.println("其他异常");
                    msg = "其他异常";
                }
            }
            map.put("msg", msg);
            return "/login";
        }
    }

    @RequestMapping("/403")
    public String unauthorizedRole(){
        System.out.println("------没有权限-------");
        return "403";
    }
}
