package com.wwb.controller;

import com.wwb.mod.Food;
import com.wwb.mod.FoodList;
import com.wwb.mod.Page;
import com.wwb.service.FoodListService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
public class FoodListController {
    @Resource
    FoodListService foodListService;
    @RequestMapping("/findFoodList.action")
    public FoodList findFoodList(){
       return foodListService.findFoodListById();
    }

    @RequestMapping("/findAllFoodList.action")
    public @ResponseBody Page findAllFoodList(Page page){
       return foodListService.findAllFoodList(page);
    }

    @RequestMapping("/updateFoodListName.action")
    public@ResponseBody int updateFoodListName(@RequestBody Food food){
       return foodListService.updateFoodListName(food);
    }
}
