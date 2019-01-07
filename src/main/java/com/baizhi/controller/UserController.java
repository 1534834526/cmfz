package com.baizhi.controller;

import com.alibaba.fastjson.JSONObject;
import com.baizhi.entity.User;
import com.baizhi.entity.UserCount;
import com.baizhi.service.UserService;
import io.goeasy.GoEasy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;


    /*注册用户功能.
    //解释：当管理员查看用户分布图或柱状图时，有人注册了信息(注册功能)，会进行实时推送注册后的数据库的所有数据，更新前台页面数据，管理员看到最新数据
    */
    @RequestMapping("/registUser")
    public void regist() {
        User user = new User(43, "13200000002", "777777", null, null, null, null, null, null, "河南", "多对多", 1, new Date());
        userService.addUser(user);
        //1
        List<UserCount> list = userService.queryProvince();
        Map<String, List> map = new HashMap<>();
        map.put("data", list);
        String jsonString = JSONObject.toJSONString(map);

        //2
        List<Integer> list2 = userService.queryUserCount();
        Map<String, List> map2 = new HashMap<>();
        map2.put("data2", list2);
        String jsonString2 = JSONObject.toJSONString(map2);

        //3
        GoEasy goEasy = new GoEasy("http://rest-hangzhou.goeasy.io", "BC-317c21a7de5e43a79646b9c6255fd12b");

        goEasy.publish("my_channel", jsonString);

        goEasy.publish("my_channel2", jsonString2);

    }



    @RequestMapping("/queryProvinceUserCount")
    public Map queryProvince() {
        List<UserCount> list = userService.queryProvince();
        Map<String, List> map = new HashMap<>();
        map.put("data", list);
        return map;
    }

    @RequestMapping("/queryUserCount")
    public Map queryUserCount() {
        List<Integer> list = userService.queryUserCount();
        Map<String, List> map = new HashMap<>();
        map.put("data", list);
        return map;
    }


}
