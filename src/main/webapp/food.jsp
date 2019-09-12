<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/9/11
  Time: 13:42
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
<div class="layui-input-inline">
    <form id="conditionForm">
        <input name="condition" class="layui-input" type="text" autocomplete="off" placeholder="请输入菜品名称">
    </form>
</div>
<button class="layui-btn layui-btn-warm" type="button" id="findFoodBt">查找</button>
<button class="layui-btn" type="button" id="addFood">添加菜品</button>
<table id="foodTable" lay-filter="test"></table>
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
            elem:"#foodTable"
            ,url:"/findFood.action"
            ,cols:[[
                {field:"fid",title:"编号"}
                ,{field:"fname",title:"菜名"}
                ,{field:"pic",title:"图片"}
                ,{field:"price",title:"价钱"}
                ,{field:"foodList",title:"菜系",templet:'<div>{{d.foodList.flname}}<div>'}
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
        table.render(option);
//Page{count=1, limit=10, startIndex=0, pageCount=1, data=[Food{fid=1, fname='红烧孟兆祥', flid=1, price=100.0, pic='', foodList=FoodList{flid=1, flname='山菜'}}], condition=null, page=1}
//Page{count=1, limit=10, startIndex=0, pageCount=1, data=[Table{tid=7, name='竹园村', tp=0, orderTime=null}], condition={tableName=园}, page=1}


                                                                     //添加的方法的监听
        $("#addFood").click(function () {
            layer.open({
                type: 1
                ,title: "添加菜品"//不显示标题栏
                ,closeBtn: false
                ,area: '300px;'
                ,shade: 0.8
                ,id: 'changeTableName' //设定一个id，防止重复弹出
                ,btn: ['修改', '取消']
                ,btnAlign: 'c'
                ,moveType: 1 //拖拽模式，0或者1
                ,content: '<div style="padding: 50px; line-height: 22px; background-color: #393D49; color: #fff; font-weight: 300;">' +
                    '<form id="addFoodForm">' +
                    '<input id="addFoodName"  placeholder="请输入菜品名称" name="addFoodName">' +
                    '<input id="addFoodPic" placeholder="请上传菜品图片" name="addFoodPic">' +
                    '<input id="addFoodPrice" placeholder="请输入菜品价格" name="addFoodPrice">' +
                    '<input id="addFoodType" placeholder="请选择菜系" name="addFoodType">' +
                    '</form></div>'
                ,btn1:function (index,layero) {//监听第一个按钮
                    var fname=$("#addFoodForm input[name='addFoodName']").val();
                    var pic=$("#addFoodForm input[name='addFoodPic']").val();
                    var price=$("#addFoodForm input[name='addFoodPrice']").val();
                    var flid=$("#addFoodForm input[name='addFoodType']").val();
                    $.ajax({
                        type:"POST"
                        ,contentType:"application/json;charset=utf-8"
                        ,url:'/addFood.action'
                        ,data:JSON.stringify({"price":price,"pic":pic,"fname":fname,"flid":flid})
                        ,success:function (res) {
                            var returnNumber=  parseInt(res);
                            if (returnNumber!=0){
                                layer.closeAll();
                                table.render(option);}
                            layer.msg("添加成功")
                        }
                    })
                }
            })
        })

                            //条件查询的监听
        $("#findFoodBt").click(function () {
            var  condition = $("#conditionForm input[name='condition']").val();
            option.where={"condition['foodName']":condition};
            table.render(option);


        });
                                                 //删除跟修改的监听
        table.on('tool(test)',function (obj) {
            var data=obj.data;
            if (obj.event==="edit"){
                layer.open({
                    type: 1
                    ,title: "修改菜名"//不显示标题栏
                    ,closeBtn: false
                    ,area: '300px;'
                    ,shade: 0.8
                    ,id: 'changeTableName' //设定一个id，防止重复弹出
                    ,btn: ['修改', '取消']
                    ,btnAlign: 'c'
                    ,moveType: 1 //拖拽模式，0或者1
                    ,content: '<div style="padding: 50px; line-height: 22px; background-color: #393D49; color: #fff; font-weight: 300;">' +
                        '<form id="changeForm">' +
                        '<input id="changeName" name="changName" value="'+data.fname+'" lay-verify="required" >' +
                        '<input id="changpic" name="changpic" src="'+data.pic+'">' +
                        '<input id="changePrice" name="changePrice" value="'+data.price+'">' +
                        '<input id="changeFlid" name="changeFlid" value="'+data.foodList.flid+'">' +
                        '</form>' +
                        '</div>'
                    ,btn1:function (index,layero) {//监听第一个按钮
                        var fname=$("#changeForm input[name='changName']").val();
                        var pic=$("#changeForm input[name='changpic']").val();
                        var price=$("#changeForm input[name='changePrice']").val();
                        var flid=$("#changeForm input[name='changeFlid']").val();
                        var fid=data.fid;
                        $.ajax({
                            type:"POST"
                            ,contentType:"application/json;charset=utf-8"
                            ,url:'/updateFood.action'
                            ,data:JSON.stringify({"fid":fid,"price":price,"pic":pic,"fname":fname,"flid":flid})
                            ,success:function (res) {
                                var returnNumber=  parseInt(res);
                                if (returnNumber!=0){
                                    layer.closeAll();
                                    table.render(option);}
                                layer.msg("修改成功")
                            }
                        })
                    }
                })
            }
            if (obj.event==="del"){
                layer.open({
                    type: 1
                    ,title: "确定删除该菜品吗？"//不显示标题栏
                    ,closeBtn: false
                    ,area: '300px;'
                    ,shade: 0.8
                    ,id: 'changeTableName' //设定一个id，防止重复弹出
                    ,btn: ['确定', '取消']
                    ,btnAlign: 'c'
                    ,moveType: 1 //拖拽模式，0或者1
                    ,btn1:function (index,layero) {//监听第一个按钮
                        var fid=data.fid;
                        $.ajax({
                            type:"POST"
                            ,contentType:"application/json;charset=utf-8"
                            ,url:'/deleteFood.action'
                            ,data:JSON.stringify({"fid":fid})
                            ,success:function (res) {
                                var returnNumber=   parseInt(res);
                                if (returnNumber!=0){
                                    table.render(option);}
                                layer.closeAll();
                                layer.msg("删除成功")
                            }
                        })
                    }

                });
            }

        })

    })
</script>
</body>
</html>
