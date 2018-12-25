package com.baizhi.controller;

import com.baizhi.entity.UserCount;
import com.baizhi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

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
