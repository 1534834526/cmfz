package shiroATMD5;

import org.apache.shiro.crypto.hash.Md5Hash;

public class TestMD5Hash {
    public static void main(String[] args) {
        //明文 盐 散列次数  得到密文
        Md5Hash password = new Md5Hash("123456", "ABCD", 1024);
        String s = password.toHex();
        System.out.println(s);


    }
}
