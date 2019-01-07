package com.baizhi.service;

import com.baizhi.entity.Album;
import com.baizhi.entity.Article;
import com.baizhi.entity.Chapter;
import com.baizhi.entity.User;
import com.baizhi.mapper.AlbumMapper;
import com.baizhi.mapper.ArticleMapper;
import com.baizhi.mapper.UserMapper;
import com.baizhi.util.Error;
import com.baizhi.util.MD5Util;
import com.baizhi.util.SendCode;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class TestPhoneInfoServiceImpl {
    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private AlbumMapper albumMapper;
    @Autowired
    private StringRedisTemplate redisTemplate;

    public Object test1(String uid, String type, String sub_type) {
        if (uid == null || type == null) {
            return new Error("参数不能为空");
        } else {
            if (type.equals("all")) {
                Map<String, Object> map = new HashMap<>();
                map.put("banner", "轮播图集合");
                map.put("album", "专辑集合");
                map.put("article", "文章集合");
                return map;
            } else if (type.equals("wen")) {
                Map<String, Object> map = new HashMap<>();
                map.put("album", "专辑集合");
                return map;
            } else {
                if (sub_type != null) {
                    if (sub_type.equals("ssyj")) {
                        Map<String, Object> map = new HashMap<>();
                        map.put("album", "上师的文章集合");
                        return map;
                    } else {
                        Map<String, Object> map = new HashMap<>();
                        map.put("album", "其他上师的文章集合");
                        return map;
                    }

                } else {
                    return new Error("思的类型不能为空");
                }
            }
        }
    }


    public Object test2(String uid, String id) {
        if (uid == null || id == null) {
            return new Error("参数不能为空");
        } else {
            Article article = articleMapper.selectByPrimaryKey(id);
            return article;
        }
    }


    public Object test3(Integer uid, Integer id) {
        if (uid == null || id == null) {
            return new Error("参数不能为空");
        } else {
            Map<Object, Object> map = new HashMap<>();
            Album album = albumMapper.selectByPrimaryKey(id);
            List<Chapter> children = album.getChildren();
            map.put("introduction", album);
            map.put("list", children);
            return map;
        }
    }


    //MD5密码方式登录
    public Object test4(String phone, String password) {
        if (phone == null || password == null) {
            return new Error("参数不能为空");
        } else {
            String md5Hex = DigestUtils.md5Hex(password);
            User u = new User();
            u.setPhone(phone);
            User user = userMapper.selectOne(u);
            if (user == null) {
                return new Error("用户不存在");
            } else if (!user.getPassword().equals(md5Hex)) {
                return new Error("-200", "密码错误");
            } else {
                return user;
            }
        }
    }

    //获取验证码接口(存储到redis)
    public boolean testSend(String phone) {
        boolean result = false;
        try {
            String sendCode = SendCode.sendCode(phone);
            final ValueOperations<String, String> operations = redisTemplate.opsForValue();
            operations.set("code", sendCode);
            redisTemplate.expire("code", 60, TimeUnit.SECONDS);
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    redisTemplate.delete("code");
                }
            }, 1000, 59000);

            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


    //code验证码方式登录
    public Object test5(String phone, String code) throws Exception {
        if (phone == null || code == null) {
            return new Error("参数不能为空");
        } else {
            User u = new User();
            u.setPhone(phone);
            User user = userMapper.selectOne(u);
            if (user == null) {
                return new Error("用户不存在");
            } else {
                ValueOperations<String, String> operations = redisTemplate.opsForValue();
                String sendCode2 = operations.get("code");
                if (sendCode2 != null) {
                    if (!code.equals(sendCode2)) {
                        return new Error("验证码输入错误");
                    } else {
                        return new Error("success");
                    }
                } else {
                    return new Error("请重新发送验证码");
                }

            }
        }
    }


    //注册插入到数据库，但是还未验证手机验证码（属于注册，未验证）

    public Object regist(String phone, String password) {
        if (phone == null || password == null) {
            return new Error("参数不能为空");
        } else {

            User user = new User();
            user.setPhone(phone);
            User user1 = userMapper.selectOne(user);
            if (user1 != null) {
                return new Error("-200", "手机号已经存在");
            } else {
                String salt = MD5Util.getSalt();
                user.setSalt(salt);
                String pwdHex = MD5Util.encryption(password + salt);
                user.setPassword(pwdHex);
                user.setStatus(0);
                userMapper.insert(user);
                return user;
            }


        }

    }


    /*表单只输入验证码，点击验证*/
    public Object validateCode(String code) throws Exception {
        ValueOperations<String, String> operations = redisTemplate.opsForValue();
        String sendCode2 = operations.get("code");
        if (sendCode2 != null) {
            if (!code.equals(sendCode2)) {
                return new Error("验证码输入错误");
            } else {
                User user = new User();
                User user1 = userMapper.selectOne(user);
                user1.setStatus(1);
                userMapper.updateByPrimaryKey(user1);
                return user1;
            }
        } else {
            return new Error("请重新发送验证码");
        }

    }


    //修改个人信息
    public Object updateUser(User user) {
        if (user.getId() == null) {
            return new Error("用户id不能为空");
        } else {
            userMapper.updateByPrimaryKey(user);
            return user;
        }
    }


    //获取金刚道友
    public List getMember(User user) {
        //表连接进行查询此id作为外键对应的其它用户
        List<User> users = userMapper.select(user);
        return users;
    }
}
