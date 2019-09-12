package com.wwb.controller;

import com.wwb.mod.Page;
import com.wwb.mod.Table;
import com.wwb.service.TableService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class TableController {
    @Resource
    TableService tableService;
    @RequestMapping("/findTable.action")



    public @ResponseBody Page findTable( Page page){
        System.out.println("123123123123123123123");
        page=  tableService.findTable(page);
        return page;
    }



    @RequestMapping("/updateTable.action")
    public  @ResponseBody int updateTable(@RequestBody Table table){
        System.out.println("----------ssss-");
        System.out.println("table------"+table);
       return tableService.updateTable(table);
    }


    @RequestMapping("/deleteTable.action")
    public @ResponseBody int deleteTable(@RequestBody Table table){
       return tableService.deleteTable(table);
    }

    @RequestMapping("/updateTableTp.action")
    public @ResponseBody int updateTableTp(@RequestBody Table table){
       return tableService.updateTableTp(table);
    }
@RequestMapping("/addTable.action")
    public @ResponseBody int addTable(@RequestBody Table table){
        return tableService.addTable(table);
    }
}
