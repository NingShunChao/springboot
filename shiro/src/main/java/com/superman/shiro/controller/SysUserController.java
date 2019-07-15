package com.superman.shiro.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Auther: Ningsc
 * @Date: 2019/5/19 11:58
 * @Description:
 */
@Controller
@RequestMapping("/userInfo")
public class SysUserController {
    /**
    * @Author: Ningsc
    * @Date: 2019/7/15
    * @Description:   用户查询.
    * @Param:
    * @return:
    */
    @RequestMapping("/userList")
    @RequiresPermissions("userInfo:view")
    public String userInfo(){
        return "userInfo";
    }

    /**
     * 用户添加;
     * @return
     */
    @RequestMapping("/userAdd")
    @RequiresPermissions("userInfo:add")
    public String userInfoAdd(){
        return "userInfoAdd";
    }

    /**
     * 用户删除;
     * @return
     */
    @RequestMapping("/userDel")
    @RequiresPermissions("userInfo:del")
    public String userDel(){
        return "userInfoDel";
    }

}
