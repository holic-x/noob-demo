package com.noob.student.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.noob.student.domain.Student;
import com.noob.student.service.StudentService;
import com.noob.student.mapper.StudentMapper;
import org.springframework.stereotype.Service;

/**
* @author hahabibu
* @description 针对表【student】的数据库操作Service实现
* @createDate 2024-04-05 17:42:31
*/
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student>
    implements StudentService{

}




