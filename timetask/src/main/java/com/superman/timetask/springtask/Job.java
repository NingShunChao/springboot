package com.superman.timetask.springtask;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Auther: Ningsc
 * @Date: 2019/6/14 17:21
 * @Description: 定时任务类
 */
@Component
@EnableScheduling //声明定时任务，spring自带的scheduling定时调度任务相当于轻量级的Quartz,但是不支持分布式
@EnableAsync //开启表示异步执行定时任务，采用多线程的方式
public class Job {

//    1.cron：cron表达式，指定任务在特定时间执行；
//    2.fixedDelay：表示上一次任务执行完成后多久再次执行，参数类型为long，单位ms；
//    3.fixedDelayString：与fixedDelay含义一样，只是参数类型变为String；
//    4.fixedRate：表示按一定的频率执行任务，参数类型为long，单位ms；
//    5.fixedRateString: 与fixedRate的含义一样，只是将参数类型变为String；
//    6.initialDelay：表示延迟多久再第一次执行任务，参数类型为long，单位ms；
//    7.initialDelayString：与initialDelay的含义一样，只是将参数类型变为String；
//    8.zone：时区，默认为当前时区，一般没有用到

//    cron表达式：
//    * 第一位，表示秒，取值0-59?
//    * 第二位，表示分，取值0-59
//    * 第三位，表示小时，取值0-23
//    * 第四位，日期天/日，取值1-31
//    * 第五位，日期月份，取值1-12
//    * 第六位，星期，取值1-7，星期一，星期二...，注：不是第1周，第二周的意思
//    另外：1表示星期天，2表示星期一。
//    * 第7位，年份，可以留空，取值1970-2099


//        (*)星号：可以理解为每的意思，每秒，每分，每天，每月，每年...
//        (?)问号：问号只能出现在日期和星期这两个位置，表示这个位置的值不确定，每天3点执行，所以第六位星期的位置，我们是不需要关注的，就是不确定的值。同时：日期和星期是两个相互排斥的元素，通过问号来表明不指定值。比如，1月10日，比如是星期1，如果在星期的位置是另指定星期二，就前后冲突矛盾了。
//        (-)减号：表达一个范围，如在小时字段中使用“10-12”，则表示从10到12点，即10,11,12
//        (,)逗号：表达一个列表值，如在星期字段中使用“1,2,4”，则表示星期一，星期二，星期四
//        (/)斜杠：如：x/y，x是开始值，y是步长，比如在第一位（秒） 0/15就是，从0秒开始，每15秒，最后就是0，15，30，45，60    另：*/y，等同于0/y


   /* @Scheduled(fixedDelay=5000)
    public void fixedDelayJob(){
        System.out.println(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())+" >>fixedDelay执行....");
    }*/

   /* @Scheduled(fixedRate=5000)
    public void fixedRateJob(){
        System.out.println(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())+" >>fixedRate执行....");
    }*/

   /* @Scheduled(cron="5/10 * * * * ?")
    @Async
    public void cronJob(){
        System.out.println(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())+" >>cron执行....");
    }*/

    /*@Scheduled(cron="5/10 * * * * ?")
    @Async
    public void cronJob2(){
        System.out.println(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())+" >>cron2执行....");
    }*/




}
