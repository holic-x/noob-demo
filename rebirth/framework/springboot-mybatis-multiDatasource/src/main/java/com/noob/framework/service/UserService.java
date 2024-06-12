package com.noob.framework.service;


import com.noob.framework.model.User;

import java.util.List;

public interface UserService {

    public List<User> getMasterUserList();

    public List<User> getSalveUserList();

}
