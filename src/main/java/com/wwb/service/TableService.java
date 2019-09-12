package com.wwb.service;

import com.wwb.mapper.TableMapper;
import com.wwb.mod.Page;
import com.wwb.mod.Table;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TableService {
    @Resource
    TableMapper tableMapper;

    //查看餐桌方法
    public Page findTable(Page page){
         int count = tableMapper.findCount(page);
         page.setCount(count);
          int pageCount = page.getPageCount();
       List<Table> table =  tableMapper.findTable(page);
        System.out.println("table++++++++"+table);
       page.setCount(count);
       page.setData(table);
       page.setPageCount(pageCount);
       return page;
    }
    //修改餐桌名称方法
    public  int updateTable(Table table){
         return tableMapper.updateTable(table);
    }
    //删除餐桌方法
    public int deleteTable(Table table){
       return tableMapper.deleteTable(table);
    }
    //更改预定状态的方法
    public  int updateTableTp(Table table){
        return tableMapper.updateTableTp(table);
    }

    //添加餐桌的方法
    public int addTable(Table table){
       return tableMapper.addTable(table);
    }
}
