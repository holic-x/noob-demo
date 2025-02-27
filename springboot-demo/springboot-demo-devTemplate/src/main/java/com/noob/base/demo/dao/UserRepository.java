package com.noob.base.demo.dao;

import com.noob.base.demo.model.entity.NoobUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<NoobUser, Long> {
}