package com.baizhi.service;

import com.baizhi.entity.Admin;
import com.baizhi.mapper.AdminMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminMapper adminMapper;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    @Override
    public String login(Admin admin) {
        Admin ad = adminMapper.selectOne(admin);
        if (ad == null) {
            return "loginError";
        }
        return "loginOk";
    }
}
