package com.baizhi.service;

import com.baizhi.entity.User;
import com.baizhi.entity.UserCount;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public interface UserService {
    List<UserCount> queryProvince();

    List<Integer> queryUserCount();

    void addUser(User user);
}
