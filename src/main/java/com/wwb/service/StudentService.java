package com.wwb.service;

import com.wwb.mapper.StudentMapper;
import com.wwb.mod.Student;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Component
public class StudentService {
@Resource
    StudentMapper studentMapper;
    // @Transactional 方法上加事务处理注解 事务处理只处理service层方法，且只处理更新方法，查询方法不需要
     public Student findStu(){
          return studentMapper.findStu();
     }
    public  int deleteStu(int sid){
        return studentMapper.deleteStu(sid);
}
    public int updateStu(Student student){
        return studentMapper.updateStu(student);
}
    public int addStu(Student student){
         return studentMapper.addStu(student);
    }
}
