package com.superman.timetask;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
//整合quartz动态配置定时任务
@MapperScan("com.superman.*.dao")
public class TimetaskApplication {

    public static void main(String[] args) {
        SpringApplication.run(TimetaskApplication.class, args);
        System.out.println("ヾ(◍°∇°◍)ﾉﾞ   TIME_TASK启动成功      ヾ(◍°∇°◍)ﾉﾞ\n");
    }

}
