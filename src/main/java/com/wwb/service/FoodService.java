package com.wwb.service;

import com.wwb.mapper.FoodMapper;
import com.wwb.mod.Food;
import com.wwb.mod.Page;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class FoodService {
    @Resource
    FoodMapper foodMapper;

    public Page findFood(Page page){
        int count = foodMapper.findCount(page);
        page.setCount(count);
        List<Food> food = foodMapper.findFood(page);
        page.setData(food);
        return page;
    }

    public int updateFood(Food food){
       return foodMapper.updateFood(food);
    }

    public int deleteFood(Food food){
        return foodMapper.deleteFood(food);
    }
    public int addFood(Food food){
        return foodMapper.addFood(food);

    }
}
