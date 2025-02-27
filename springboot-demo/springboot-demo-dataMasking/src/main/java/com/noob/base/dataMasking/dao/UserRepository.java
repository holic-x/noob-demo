package com.noob.base.dataMasking.dao;

import com.noob.base.dataMasking.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}