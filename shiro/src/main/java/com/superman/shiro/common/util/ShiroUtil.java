package com.superman.shiro.common.util;

import com.superman.shiro.entity.SysUserEntity;
import netscape.security.Principal;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.ArrayList;
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

    /**
    * @Author: Ningsc
    * @Date: 2019/7/12
    * @Description:  获取subject对象
    * @Param:
    * @return:
    */
    public static Subject getSubjct() {
        return SecurityUtils.getSubject();
    }

    /**
    * @Author: Ningsc
    * @Date: 2019/7/12
    * @Description:  当前会话ID
    * @Param:
    * @return:
    */
    public static Serializable getSessionId() {
        return getSubjct().getSession().getId();
    }

    /**
    * @Author: Ningsc
    * @Date: 2019/7/12
    * @Description:  当前登陆用户的信息
    * @Param:
    * @return:
    */
    public static SysUserEntity getUser() {
        Object object = getSubjct().getPrincipal();
        return (SysUserEntity)object;
    }
    public static Integer getUserId() {
        return getUser().getUid();
    }

    /**
    * @Author: Ningsc
    * @Date: 2019/7/12
    * @Description:  登陆
    * @Param:
    * @return:
    */
    public static void login(UsernamePasswordToken token){
        getSubjct().login(token);
    }

    /**
    * @Author: Ningsc
    * @Date: 2019/7/12
    * @Description:  退出登陆
    * @Param:
    * @return:
    */
    public static void logout() {
        getSubjct().logout();
    }

//    public static List<Principal> getPrinciples() {
//        List<Principal> principals = null;
//        Collection<Session> sessions = sessionDAO.getActiveSessions();
//        return principals;
//    }

    /**
    * @Author: Ningsc
    * @Date: 2019/7/12
    * @Description:  剔除当前账户在其他地方登陆
    * @Param:
    * @return:
    */
    public static  void removeSession(){
        // 系统已经登陆的session集合
        Collection<Session> activeSessions = sessionDAO.getActiveSessions();
        // 当前登陆的用户
        SysUserEntity sysCurrentUserEntity = getUser();
        // 用于存储当前用户在其他地方登陆的session
        List<Session> loginSession = new ArrayList<>();
        // 循环遍历session
        for (Session session : activeSessions) {
            Subject subject = new Subject.Builder().session(session).buildSubject();
            if(subject.isAuthenticated()){
                SysUserEntity sysUserEntity = (SysUserEntity)subject.getPrincipal();
                if (sysUserEntity.getUid().equals(sysCurrentUserEntity.getUid())) {
                    if (!session.getId().equals(getSessionId())) {
                        loginSession.add(session);
                    }
                }
            }
        }

        // 剔除已经登陆的session
        for (Session session:loginSession) {
            session.stop();
        }

    }
}
