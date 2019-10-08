package com.zhuodewen.realm;

import com.zhuodewen.domain.Student;
import com.zhuodewen.mapper.StudentMapper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import javax.annotation.Resource;

/**
 * 自定义realm
 */
public class MyRealm extends AuthorizingRealm {

    @Resource
    private StudentMapper studentMapper;

    /**
     * SHiro权限控制的方法
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    /**
     * Shiro认证的方法
     * @param authenticationToken
     * @return
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        // 根据 Token 获取用户名
        String username = (String) authenticationToken.getPrincipal();
        // 根据用户名从数据库中查询该用户
        Student student = studentMapper.selectByName(username);
        if(student != null) {
            // 把当前用户存到 Session 中
            SecurityUtils.getSubject().getSession().setAttribute("student", student);
            // 传入用户名和密码进行身份认证，并返回认证信息
            AuthenticationInfo authcInfo = new SimpleAuthenticationInfo(student.getName(), student.getPassword(), "myRealm");
            return authcInfo;
        } else {
            return null;
        }
    }
}
