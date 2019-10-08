package com.zhuodewen.mapper;

import com.zhuodewen.domain.Student;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface StudentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Student record);

    Student selectByPrimaryKey(Integer id);

    List<Student> selectAll();

    int updateByPrimaryKey(Student record);

    Student selectByName(String name);
}