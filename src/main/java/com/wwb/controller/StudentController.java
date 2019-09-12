package com.wwb.controller;

import com.wwb.mod.Student;
import com.wwb.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class StudentController {

    @Resource StudentService studentService;
    Student student = new Student();

@RequestMapping("/findStu.action")// RequestMapping只需要加注在controller 控制器的方法上，同时需要其访问路径
    public @ResponseBody Student findStu(){
    System.out.println("进入controller----------------------------------------");

       student =    studentService.findStu();

    System.out.println(student);
    return student;
    }
    @RequestMapping("/deleteStu.action")
    public @ResponseBody int deleteStu(int sid){
        System.out.println("进入删除学生方法------"+sid);
        return studentService.deleteStu(sid);
    }
    @RequestMapping("/updateStu.action")
    public  @ResponseBody int updateStu(Student student){
        return studentService.updateStu(student);
 }
 @RequestMapping("/addStu.action")
    public @ResponseBody int addStu(Student student){
     System.out.println("进入添加方法");
    return studentService.addStu(student);
    }

}
