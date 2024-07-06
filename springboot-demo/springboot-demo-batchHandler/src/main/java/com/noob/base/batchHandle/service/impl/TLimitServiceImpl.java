package com.noob.base.batchHandle.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.noob.base.batchHandle.entity.model.TLimit;
import com.noob.base.batchHandle.service.TLimitService;
import com.noob.base.batchHandle.mapper.TLimitMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author hahabibu
* @description 针对表【t_limit】的数据库操作Service实现
* @createDate 2024-07-06 10:35:39
*/
@Service
public class TLimitServiceImpl extends ServiceImpl<TLimitMapper, TLimit>
    implements TLimitService{

    @Override
    public boolean batchInsert(List<TLimit> limits) {
        // java.lang.StackOverflowError: null   错用 this.batchInsert(limits)  嵌套太深，因为用错方法，误认为这是mybatis 的批量操作
        return this.saveBatch(limits);
    }
}




