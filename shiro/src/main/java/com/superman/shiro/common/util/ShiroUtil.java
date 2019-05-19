package com.superman.shiro.common.util;

import com.superman.shiro.entity.SysUserEntity;
import netscape.security.Principal;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.List;


/**
 * @Auther: Ningsc
 * @Date: 2019/5/19 16:14
 * @Description: shiro工具类
 */
public class ShiroUtil {
    @Autowired
    private static SessionDAO sessionDAO;

    public static Subject getSubjct() {
        return SecurityUtils.getSubject();
    }
    public static SysUserEntity getUser() {
        Object object = getSubjct().getPrincipal();
        return (SysUserEntity)object;
    }
    public static Integer getUserId() {
        return getUser().getUid();
    }

    public static void login(UsernamePasswordToken token){
        getSubjct().login(token);
    }

    public static void logout() {
        getSubjct().logout();
    }

    public static List<Principal> getPrinciples() {
        List<Principal> principals = null;
        Collection<Session> sessions = sessionDAO.getActiveSessions();
        return principals;
    }
}
