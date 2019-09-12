package com.wwb.mapper;

import com.wwb.mod.Food;
import com.wwb.mod.Page;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface FoodMapper {

@Select("<script>SELECT count(*)  FROM restaurant.`food` where 1=1" +
        " <if test='condition!=null'> AND food.`fname` LIKE concat('%',#{condition.foodName},'%')</if>" +
        "</script>")
    public int findCount(Page page);
//"<script>SELECT * FROM restaurant.`tb` where 1=1<if test='condition!=null'> AND tb.`name` LIKE concat('%',#{condition.tableName},'%')</if> ORDER BY tb.`tid` DESC LIMIT #{startIndex},#{limit}</script>"
@Select("<script>SELECT food.`fid`,food.`fname`,food.`price`,food.flid,food.`pic`,foodlist.flname FROM food,foodlist " +
        "WHERE food.`flid`=foodlist.`flid`" +
        "<if test='condition!=null'> AND food.`fname` LIKE concat('%',#{condition.foodName},'%')" +
        "</if> " +
        "ORDER BY food.`fid` DESC LIMIT #{startIndex},#{limit}</script>")
@Results(value = {
        @Result(id = true,property = "fid",column = "fid"),
        @Result(property = "fname",column = "fname"),
        @Result(property = "price",column = "price"),
        @Result(property = "pic",column = "pic"),
        @Result(property = "flid",column = "flid"),
        @Result(property = "foodList",one = @One(select = "com.wwb.mapper.FoodListMapper.findFoodListById"),column = "flid")
})
    public List<Food> findFood(Page page);

@Update("UPDATE food SET food.`flid`=#{flid},food.`fname`=#{fname},food.`pic`=#{pic},food.`price`=#{price}  WHERE food.`fid`=#{fid}")
    public int updateFood(Food food);

@Delete("DELETE FROM `restaurant`.`food` WHERE `fid` =#{fid}; ")
    public int deleteFood(Food food);

@Insert("INSERT INTO food (fname,pic,price,flid) VALUES (#{fname},#{pic},#{price},#{flid})")
    public int addFood(Food food);
}
