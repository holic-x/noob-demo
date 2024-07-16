package com.noob.base.mapper;

import com.noob.base.model.Student;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface StudentMapper {

    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    @Insert("INSERT INTO t_student (`name`,`create_time`,`grade_code`) VALUES (#{student.name},#{student.createTime},#{student.gradeCode})")
    int insert(@Param("student") Student student);
}
