package com.sz.demo.controller;

import org.flowable.common.engine.api.management.TableMetaData;
import org.flowable.common.engine.api.management.TablePage;
import org.flowable.idm.api.IdmIdentityService;
import org.flowable.idm.api.IdmManagementService;
import org.flowable.idm.engine.impl.persistence.entity.GroupEntityImpl;
import org.flowable.idm.engine.impl.persistence.entity.UserEntityImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Set;
import java.util.UUID;

/**
 * @ClassName IdmController
 * @Description TODO
 * @Author
 * @Date 2020/6/16 0:14
 * @Version
 **/

@RestController
@RequestMapping("/idm")
public class IdmController {

    @Autowired
    private IdmIdentityService idmIdentityService;

    @Autowired
    private IdmManagementService idmManagementService;


    /**
     * 添加用户
     **/
    @RequestMapping("addUser")
    public void addUser() {
        UserEntityImpl user = new UserEntityImpl();
        // 设定版本号为0,指定添加用户操作
        user.setRevision(0);
//        user.setId(UUID.randomUUID().toString().replaceAll("-",""));
        user.setId("haha");
        user.setFirstName("张");
        user.setLastName("小白");
        user.setEmail("hhh@126.com");
        idmIdentityService.saveUser(user);
    }

    /**
     * 添加用户组
     **/
    @RequestMapping("addGroup")
    public void addGroup() {
        GroupEntityImpl group = new GroupEntityImpl();
        // 设定版本号为0,指定添加用户操作
        group.setRevision(0);
        group.setId("admin");
        group.setName("管理员组");
//        group.setType("");
        idmIdentityService.saveGroup(group);
    }

    /**
     * 绑定用户与组关联关系
     **/
    @RequestMapping("bindMemberShip")
    public void bindMemberShip() {
        String userId = "haha";
        String groupId = "admin";
        idmIdentityService.createMembership(userId, groupId);
    }


    /**
     * 创建查询
     **/
    @RequestMapping("createQuery")
    public void createQuery() {
        idmIdentityService.createUserQuery().list();
        idmIdentityService.createGroupQuery().list();
        idmIdentityService.createPrivilegeQuery().list();
        idmIdentityService.createTokenQuery().list();
    }

    /**
     * 查询指定数据表信息
     **/
    @RequestMapping("getTableMetaData")
    public void getTableMetaData() {
        // 获取指定数据表信息
        TableMetaData table = idmManagementService.getTableMetaData("ACT_ID_USER");
        System.out.println(table.getTableName());
        System.out.println(table.getColumnNames());
        System.out.println(table.getColumnTypes());
    }


    /**
     * 查询指定数据表信息
     **/
    @RequestMapping("getProperties")
    public void getProperties() {
        Map<String, String> properties = idmManagementService.getProperties();
        Set<Map.Entry<String, String>> entries = properties.entrySet();
        for (Map.Entry<String, String> entry : entries) {
            System.out.println("key:" + entry.getKey());
            System.out.println("value:" + entry.getValue());
        }
    }

    /**
     * 查询指定数据表信息
     **/
    @RequestMapping("createTablePageQuery")
    public void createTablePageQuery() {
        TablePage tablePage = idmManagementService.createTablePageQuery().tableName("ACT_ID_GROUP").listPage(0, 10);
        System.out.println("rows:" + tablePage.getRows());
        System.out.println("size:" + tablePage.getSize());
        System.out.println("tableName:" + tablePage.getTableName());
    }

}
