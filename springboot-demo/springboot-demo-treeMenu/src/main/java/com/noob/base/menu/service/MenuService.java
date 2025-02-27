package com.noob.base.menu.service;

import com.noob.base.menu.dao.MenuRepository;
import com.noob.base.menu.model.dto.MenuDTO;
import com.noob.base.menu.model.entity.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MenuService {
    @Autowired
    private MenuRepository menuRepository;

    public List<MenuDTO> getTreeMenu() {
        // 获取所有菜单数据
        List<Menu> menus = menuRepository.findAll();
        // 构建树形结构
        return buildTree(menus);
    }

    private List<MenuDTO> buildTree(List<Menu> menus) {
        // 创建一个Map，用于存储所有菜单项
        Map<Long, MenuDTO> menuMap = new HashMap<>();
        // 创建一个列表，用于存储根节点
        List<MenuDTO> roots = new ArrayList<>();

        // 第一次遍历：将所有菜单项放入Map中
        for (Menu menu : menus) {
            MenuDTO menuDTO = new MenuDTO();
            menuDTO.setId(menu.getId());
            menuDTO.setName(menu.getName());
            menuDTO.setChildren(new ArrayList<>());
            menuMap.put(menu.getId(), menuDTO);
        }

        // 第二次遍历：构建树形结构
        for (Menu menu : menus) {
            MenuDTO menuDTO = menuMap.get(menu.getId());
            if (menu.getParent() == null) {
                // 如果当前菜单项没有父节点，则它是根节点
                roots.add(menuDTO);
            } else {
                // 如果当前菜单项有父节点，则将其添加到父节点的children中
                MenuDTO parentDTO = menuMap.get(menu.getParent().getId());
                parentDTO.getChildren().add(menuDTO);
            }
        }

        return roots;
    }
}