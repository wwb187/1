package com.wwb.service;

import com.wwb.mapper.FoodListMapper;
import com.wwb.mod.Food;
import com.wwb.mod.FoodList;
import com.wwb.mod.Page;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class FoodListService {
    @Resource
    FoodListMapper  foodListMapper;
    //这个方法是food查询是需要的方法
    public FoodList findFoodListById(){
       return foodListMapper.findFoodListById();
    }
    public Page findAllFoodList(Page page){
         int count = foodListMapper.findCount(page);
         page.setCount(count);
         List<FoodList> foodLists= foodListMapper.findAllFoodList(page);
        page.setData(foodLists);
        return page;
    }
    public int updateFoodListName(Food food){
        return foodListMapper.updateFoodListName(food);
    }
}
