package shiroAR;

import com.baizhi.entity.Admin;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

public class TestShiroAuthorization {
    /**
     * java方式
     * 使用shiro框架对普通管理员和超级管理员身份认证和授权  最终不同用户(超级管理员和普通管理员)基于角色的展示不同的主页面或基于资源的展示资源不同的相同主页面
     */
    @Test
    public void testSecurityManager() {
        //1初始化安全管理器工厂
        //2初始化安全管理器
        //3初始化工具类(将安全管理器设置到工具类中，从工具类中获取主体)
        //4初始化主体
        //5这样主体使用令牌(身份信息)时，会使用安全管理器进行认证


        //无参：默认只会读取resources目录下的配置文件 String参：可以读取指定配置文件的位置
        IniSecurityManagerFactory iniSecurityManagerFactory = new IniSecurityManagerFactory("classpath:shiroATR/shiro.ini");

        SecurityManager manager = iniSecurityManagerFactory.getInstance();

        SecurityUtils.setSecurityManager(manager);
        Subject subject = SecurityUtils.getSubject();

        //参数
        Admin user = new Admin(1L, "zhangsan", "123456");
        //用户令牌(前台传来的表单信息)
        AuthenticationToken authenticationToken = new UsernamePasswordToken(user.getPhone(), user.getPassword());
        //认证(1查询数据，2比对)
        //AuthenticatingRealm 查询(默认去配置文件，可以实现此类的方法自定义到数据库查询)和比对(属性 凭证匹配器)
        //1、SimpleAccountRealm是AuthenticatingRealm的实现类，有查询方法doGetAuthenticationInfo默认是读取配置文件的用户信息，不操作数据库，但可以实现此类自定义操作数据库。
        //2、CredentialsMatcher是AuthenticatingRealm的属性 是一个凭证匹配器类，实现类SimpleCredentialsMatcher有比对方法doCredentialsMatch

        try {

            subject.login(authenticationToken);
        } catch (UnknownAccountException e) {
            System.out.println("用户不存在");
        } catch (IncorrectCredentialsException e) {
            System.out.println("密码错误");
        }


        //判断用户认证是否成功
        boolean authenticated = subject.isAuthenticated();


        //认证成功后则判断用户是否拥有此角色(数据库先查询出的用户所拥有的角色，这边判断是否拥有相关角色,有则做相应操作）
        if (authenticated) {

            //判断用户是否拥有所有角色
            //boolean role = subject.hasAllRoles(Arrays.asList(new String[]{"super,admin"}));
            //判断用户是否拥有super角色
            boolean role = subject.hasRole("super");
            if (role) {
                //则用户具有super角色，跳转到具有super角色权限的页面
            } else {
                //则用户不具有super角色，跳转到具有admin角色的页面
            }
        }

        //认证成功后判断用户是否拥有此权限(资源的分配)
        if (authenticated) {

            //boolean permitted = subject.isPermitted("admin:add");
            String[] s = {"admin:add", "admin:delete"};
            boolean[] permitted = subject.isPermitted(s);
            //针对不同用户具有哪些资源的权限，java代码中不好控制，需要web中jsp标签的细粒度的控制  详见web开发

        }

    }

}
