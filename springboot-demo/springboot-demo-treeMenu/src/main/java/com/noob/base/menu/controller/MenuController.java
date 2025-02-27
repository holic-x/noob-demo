package com.noob.base.menu.controller;

import com.noob.base.menu.model.dto.MenuDTO;
import com.noob.base.menu.model.entity.Menu;
import com.noob.base.menu.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    private MenuService menuService;

    @GetMapping("/tree")
    public List<MenuDTO> getTreeMenu() {
        return menuService.getTreeMenu();
    }
}