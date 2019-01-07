package shiroATR;

import com.baizhi.entity.Admin;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.realm.AuthenticatingRealm;

/**
 * （2）自定义Realm继承AuthenticatingRealm自定义到数据库中查询,参数来自前台数据
 */
public class MyRealm extends AuthenticatingRealm {
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //前台数据
        String principal = (String) authenticationToken.getPrincipal();
        //通过前台用户名principal查询出的用户对象
        //Admin admin=null;
        Admin admin = new Admin(1L, "zhangsan", "123456");
        AuthenticationInfo authenticationInfo = null;
        if (admin != null) {
            authenticationInfo = new SimpleAuthenticationInfo(admin.getPhone(), admin.getPassword(), this.getName());
        }
        //将数据库中查询的数据交给凭证匹配器
        return authenticationInfo;
    }
}
