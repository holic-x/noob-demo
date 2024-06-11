package com.noob.framework.service;

import com.noob.framework.model.User;
import com.noob.framework.model.UserAddDTO;

public interface UserService {

    public void save(User user);

    public void saveByValidator(UserAddDTO userAddDTO);
}
