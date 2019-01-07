package com.baizhi.service;

import com.baizhi.entity.User;
import com.baizhi.entity.UserCount;
import com.baizhi.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    @Override
    public List<UserCount> queryProvince() {
        return userMapper.queryProvince();
    }

    @Override
    public List<Integer> queryUserCount() {

        List<Integer> list = new ArrayList<>();
        Integer[] integers = new Integer[]{7, 14, 21, 30, 180, 365};
        for (int i = 0; i < integers.length; i++) {
            Integer ii = userMapper.queryUserCount(integers[i]);
            list.add(ii);
        }

        return list;
    }

    @Override
    public void addUser(User user) {

        userMapper.insert(user);

    }


}
