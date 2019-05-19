package com.superman.shiro;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@ServletComponentScan
public class ShiroApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShiroApplication.class, args);
        System.out.println("ヾ(◍°∇°◍)ﾉﾞ   shiro权限框架启动成功      ヾ(◍°∇°◍)ﾉﾞ\n");
    }

}
