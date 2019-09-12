package com.wwb.mapper;

import com.wwb.mod.Student;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface StudentMapper {
@Select("SELECT * FROM student.stu")
@Results(value ={
        @Result(id = true,property = "sid",column = "sid"),
        @Result(property = "sname",column = "sname"),
        @Result(property = "cid",column = "cid")
} )
    public Student findStu();

@Delete("DELETE FROM student.`stu` WHERE student.`stu`.`sid`=#{sid}")
    public int deleteStu(int sid);

@Update("UPDATE  student.`stu` SET student.`stu`.`sname`=#{sname},student.`stu`.`cid`=#{cid} WHERE stu.`sid`=#{sid}")
    public int updateStu (Student student);
@Update("INSERT INTO stu(sname,cid) VALUES(#{sname},#{cid})")
 public int addStu(Student student);
}
