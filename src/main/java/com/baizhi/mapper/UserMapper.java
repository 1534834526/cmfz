package com.baizhi.mapper;

import com.baizhi.entity.User;
import com.baizhi.entity.UserCount;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface UserMapper extends Mapper<User> {
    public List<UserCount> queryProvince();

    Integer queryUserCount(Integer integer);
}
