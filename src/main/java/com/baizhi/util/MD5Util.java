package com.baizhi.util;

import org.apache.commons.codec.digest.DigestUtils;

import java.util.Random;

public class MD5Util {
    public static String getSalt() {
        //使用Random随机生成一个String(salt) --- 6位字符串(UUID获取,验证码工具类获取(底层Random)，或者纯Random获取)
        char[] array = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789".toCharArray();

        StringBuilder sbu = new StringBuilder("");
        Random random = new Random();

        for (int i = 1; i <= 6; i++) {
            int num = random.nextInt(array.length);
            sbu.append(array[num]);
        }

        return sbu.toString();
    }

    public static String encryption(String src) {
        return DigestUtils.md5Hex(src);
    }
}
