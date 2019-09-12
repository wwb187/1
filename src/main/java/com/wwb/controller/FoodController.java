package com.wwb.controller;

import com.wwb.mod.Food;
import com.wwb.mod.Page;
import com.wwb.service.FoodService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
public class FoodController {
@Resource
    FoodService foodService;
@RequestMapping("/findFood.action")
    public @ResponseBody Page findFood(Page page){
         page = foodService.findFood(page);
        return page;
    }

    @RequestMapping("/updateFood.action")
    public @ResponseBody int updateFood(@RequestBody Food food){
    return foodService.updateFood(food);
     }
     @RequestMapping("/deleteFood.action")
     public@ResponseBody int deleteFood(@RequestBody Food food){
    return foodService.deleteFood(food);
     }

     @RequestMapping("/addFood.action")
     public@ResponseBody int addFood(@RequestBody Food food){
         System.out.println("进入addFood方法");
    return foodService.addFood(food);
     }
}
