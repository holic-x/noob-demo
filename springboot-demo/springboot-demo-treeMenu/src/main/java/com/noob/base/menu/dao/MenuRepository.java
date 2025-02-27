package com.noob.base.menu.dao;

import com.noob.base.menu.model.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MenuRepository extends JpaRepository<Menu, Long> {
    List<Menu> findByParentIsNull();
}