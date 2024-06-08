package com.noob.framework.service.impl;

import com.noob.framework.model.User;
import com.noob.framework.mapper.master.MasterUserMapper;
import com.noob.framework.mapper.salve.SalveUserMapper;
import com.noob.framework.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private MasterUserMapper masterUserMapper;

    @Autowired
    private SalveUserMapper salveUserMapper;

    @Override
    public List<User> getMasterUserList() {
        return masterUserMapper.getAllUser();
    }

    @Override
    public List<User> getSalveUserList() {
        return salveUserMapper.getAllUser();
    }
}
