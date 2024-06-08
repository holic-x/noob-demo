package com.noob.framework.transaction.mapper;

import com.noob.framework.transaction.entity.TableEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

@Mapper
public interface TableMapper {

    @Insert("INSERT INTO tablea(id, name) VALUES(#{id}, #{name})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insertTableA(TableEntity tableEntity);

    @Insert("INSERT INTO tableb(id, name) VALUES(#{id}, #{name})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insertTableB(TableEntity tableEntity);

}
