package com.noob.base.batchHandle.service;

import com.noob.base.batchHandle.entity.model.TLimit;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author hahabibu
* @description 针对表【t_limit】的数据库操作Service
* @createDate 2024-07-06 10:35:39
*/
public interface TLimitService extends IService<TLimit> {

    public boolean batchInsert(List<TLimit> limits);

    public boolean exportInsertSQL(List<TLimit> limits,String filePath) throws Exception;

}
