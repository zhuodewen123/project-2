package com.zhuodewen.facade;

import com.github.pagehelper.PageInfo;
import com.zhuodewen.domain.Student;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentFacade {

    int deleteByPrimaryKey(Integer id);

    int insert(Student record);

    Student selectByPrimaryKey(Integer id);

    List<Student> selectAll();

    int updateByPrimaryKey(Student record);

    PageInfo<Student> query(Student student);

    Student selectByName(String name);
}
