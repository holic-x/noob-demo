package com.noob.base.largeData.dao;

import com.noob.base.largeData.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // 分页查询方法
    List<User> findAllByOrderByIdAsc();
}