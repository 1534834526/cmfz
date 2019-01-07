package shiroAR;

import com.baizhi.entity.Admin;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import java.util.Arrays;
import java.util.List;


public class MyRealm2 extends AuthorizingRealm {
    /**
     * 认证 自定义Realm继承AuthenticatingRealm自定义到数据库中查询,参数来自前台数据
     */

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //前台数据
        String principal = (String) authenticationToken.getPrincipal();
        //通过前台用户名principal查询出的用户对象密码为密文
        //Admin admin=null;
        Admin admin = new Admin(1L, "zhangsan", "3d53b73c485f523ef2fe45f2b8dd3c58");
        AuthenticationInfo authenticationInfo = null;
        if (admin != null) {
            authenticationInfo = new SimpleAuthenticationInfo(admin.getPhone(), admin.getPassword(), ByteSource.Util.bytes("ABCD"), this.getName());
        }
        //将数据库中查询的数据交给凭证匹配器
        return authenticationInfo;
    }

    /**
     * 授权  基于角色或资源
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String phone = (String) principalCollection.getPrimaryPrincipal();
        //通过用户名查询主体，主体查角色，角色查权限
        Admin admin = new Admin(1L, "zhangsan", "3d53b73c485f523ef2fe45f2b8dd3c58");
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        if (admin != null) {
            //基于角色  主体查角色  通过增加角色来代表数据库查出的用户拥有的角色
           /* List<String> list = Arrays.asList(new String[]{"super", "admin"});
            authorizationInfo.addRoles(list);*/

            //基于资源  主体查角色 角色查权限 权限查资源   通过增加权限来代表数据库中查出的用户所拥有的权限 操作不同的资源
            String[] strings = {"admin:add", "admin:delete", "admin:update", "admin:query"};
            List<String> list = Arrays.asList(strings);
            authorizationInfo.addStringPermissions(list);
        }
        return authorizationInfo;
    }
}
