package com.noob.scene.impl;

import com.noob.scene.api.SearchService;
import com.noob.scene.otherService.DBService;
import com.noob.scene.otherService.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("SearchService")
public class SearchServiceImpl implements SearchService {

    @Autowired
    private DBService dbService;

    @Autowired
    private FileService fileService;

    @Override
    public String search(String searchType, String keyword) {
        if("skip".equals(searchType)){
            return "跳过检索";
        }

        // 调用文件服务
        String file = fileService.getFile();

        // 调用数据库服务
        int dbRes = dbService.update();

        return "检索信息";
    }

}
