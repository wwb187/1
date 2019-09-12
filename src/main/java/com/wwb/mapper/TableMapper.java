package com.wwb.mapper;

import com.wwb.mod.Page;
import com.wwb.mod.Table;
import javafx.scene.control.Tab;
import org.apache.ibatis.annotations.*;

import java.util.List;
//@Select({"<script>",
//    "SELECT * FROM tbl_order",
//    "WHERE 1=1",
//    "<when test='title!=null'>",
//    "AND mydate = #{mydate}",
//    "</when>",  sql的顺序   SELECT  * FROM restaurant.tb  WHERE 1=1 AND tb.`name` LIKE '%2%' ORDER BY tb.`tid` DESC LIMIT 0,2
//    "</script>"})      {"SELECT * FROM restaurant.`tb`"}
public interface TableMapper {
    @Select("<script>SELECT count(*)  FROM restaurant.`tb` where 1=1<if test='condition!=null'> AND tb.`name` LIKE concat('%',#{condition.tableName},'%')</if></script>")
    public int findCount(Page page);

    @Select("<script>SELECT * FROM restaurant.`tb` where 1=1<if test='condition!=null'> AND tb.`name` LIKE concat('%',#{condition.tableName},'%')</if> ORDER BY tb.`tid` DESC LIMIT #{startIndex},#{limit}</script>")
    @Results(value = {
            @Result(id = true,property = "tid",column = "tid"),
            @Result(property = "tname",column = "tname"),
            @Result(property = "tp",column = "tp"),
            @Result(property = "orderTime",column = "orderTime")
    })
     public List<Table> findTable(Page page);

    @Update("UPDATE tb SET tb.name=#{name} WHERE tid=#{tid}")
     public int updateTable(Table table);

    @Delete("DELETE FROM restaurant.tb WHERE tid=#{tid}")
     public int deleteTable(Table table);
@Update("UPDATE tb SET tb.`tp`=#{tp},tb.`orderTime`=#{orderTime} WHERE tid=#{tid}")
    public int updateTableTp(Table  table);

@Insert("INSERT INTO `tb`(`name`) VALUES (#{name})")
     public int addTable(Table table);
}
