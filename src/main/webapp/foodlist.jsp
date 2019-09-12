<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/9/12
  Time: 11:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <link rel="stylesheet" href="/layui/css/layui.css"  media="all">
    <script type="text/javascript" src="/js/dateformat.js"></script>
</head>
<body>
    <script src="/layui/layui.js" charset="utf-8"></script>
    <table id="foodlistTable" lay-filter="test"></table>
    <!--这是表中操作行引用的格式，通过id引用 -->
    <script id="optionBar" type="text/html">
        <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
        <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
    </script>


<script>
    layui.use(['table','layer','jquery'],function () {
        var table=layui.table;
        var layer=layui.layer;
        var $=layui.jquery;
        var option={
            elem:"#foodlistTable"
            ,url:"/findAllFoodList.action"
            ,cols:[[
                {field:"flid",title:"编号"}
                ,{field:"flname",title:"菜名"}
                ,{toolbar:"#optionBar",title:"操作"}
            ]],
            page:true
            ,parseData:function (res) {
                return{
                    "code":0
                    ,"msg":""
                    ,"count":res.count
                    ,"data":res.data
                };
            }

        };
        //首次访问对表格进行渲染
        table.render(option);
        table.on('tool(test)',function (obj) {
            var data=obj.data;
            if (obj.event==='edit'){
                layer.open({
                    type: 1
                    ,title: "修改菜系"
                    ,closeBtn: false
                    ,area: '300px;'
                    ,shade: 0.8
                    ,id: 'changeTableName'
                    ,btn: ['修改', '取消']
                    ,btnAlign: 'c'
                    ,moveType: 1
                    ,content:'<div style="padding: 50px; line-height: 22px; background-color: #393D49; color: #fff; font-weight: 300;">' +
                        '<form id="changeFoodListForm">' +
                        '<input id="changeFoodListName" name="changeFoodListName" value="'+data.flname+'">' +
                        '</form></div>'
                    ,btn1:function (index,layero) {
                        var flname=$("#changeFoodListForm input[name='changeFoodListName']").val();
                        $.ajax({
                            type:"POST"
                            ,contentType:"application/json;charset=utf-8"
                            ,url:'/updateFood.action'
                            ,data:JSON.stringify({"flname":flname})
                            ,success:function (res) {
                                var returnNumber=  parseInt(res);
                                if (returnNumber!=0){
                                    layer.closeAll();
                                    table.render(option);
                                    layer.msg("修改成功")
                                }
                            }
                        })
                    }
                })
            }
            if (obj.event==='del'){}
        })
    })
</script>

</body>
</html>
