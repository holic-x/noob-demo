package com.noob.base.qrcode.dao;

import com.noob.base.qrcode.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}