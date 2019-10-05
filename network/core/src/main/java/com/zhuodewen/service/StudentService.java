package com.zhuodewen.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhuodewen.domain.Student;
import com.zhuodewen.facade.StudentFacade;
import com.zhuodewen.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class StudentService implements StudentFacade{

    @Autowired
    private StudentMapper studentMapper;

    public int deleteByPrimaryKey(Integer id) {
        return studentMapper.deleteByPrimaryKey(id);
    }

    public int insert(Student record) {
        return studentMapper.insert(record);
    }

    public Student selectByPrimaryKey(Integer id) {
        return studentMapper.selectByPrimaryKey(id);
    }

    public List<Student> selectAll() {
        return studentMapper.selectAll();
    }

    public int updateByPrimaryKey(Student record) {
        return studentMapper.updateByPrimaryKey(record);
    }

    /**
     * 分页查询
     * @param student
     * @return
     */
    public PageInfo<Student> query(Student student) {
        PageHelper.startPage(1,10);
        return new PageInfo<Student>(studentMapper.selectAll());
    }
}
