package com.wwb.mapper;

import com.wwb.mod.Food;
import com.wwb.mod.FoodList;
import com.wwb.mod.Page;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

public interface FoodListMapper {
//SELECT * FROM foodlist
    @Select("SELECT * FROM foodlist WHERE 1=1 AND foodlist.`flid`=#{flid}")
    @Results(value = {
            @Result(property = "flid",column = "flid"),
            @Result(property = "flname",column = "flname")
    })
    public FoodList findFoodListById();
    @Select("<script>select count(*) from foodList where 1=1" +
            "<if test='condition!=null'> AND foodlist.`flname` LIKE concat('%',#{condition.foodListName},'%')" +
            "</if></script>")
    public int findCount(Page page);
    @RequestMapping("/findAllFoodList.action")
    @Select("<script>SELECT foodlist.`flid`,foodList.`flname`FROM foodList WHERE 1=1" +
            "<if test='condition!=null'> " +
            "AND foodlist.`flname` LIKE concat('%',#{condition.foodListName},'%') " +
            "</if> " +
            "ORDER BY foodlist.`flid` DESC LIMIT #{startIndex},#{limit}</script>")
    @Results(value = {
            @Result(id = true,property = "flid",column = "flid"),
            @Result(property = "flname",column = "flname")
    })
    public List<FoodList> findAllFoodList(Page page);

        @Update("/updateFoodListName.action")
    public int updateFoodListName(Food food);
}
