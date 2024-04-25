package cn.noob.mapper;

import cn.noob.pojo.Emp;
import java.util.List;

public interface EmpMapper {
    int deleteByPrimaryKey(Integer empid);

    int insert(Emp record);

    Emp selectByPrimaryKey(Integer empid);

    List<Emp> selectAll();

    int updateByPrimaryKey(Emp record);
}