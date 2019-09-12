<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/9/7
  Time: 9:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" href="/layui/css/layui.css"  media="all">
<script type="text/javascript" src="/js/dateformat.js"></script>
<html>
<head>

    <div class="layui-input-inline">
        <form id="conditionForm">
        <input name="condition" class="layui-input" type="text" autocomplete="off" placeholder="请输入餐桌名">
        </form>
    </div>
    <button class="layui-btn layui-btn-warm" type="button" id="findTableBt">查找</button>
    <button class="layui-btn" type="button" id="addTable">添加餐桌</button>
    <table id="table1" lay-filter="test"></table>

    <!-- 这是一个根据tp数值来显示具体值的标签，通过templet标签引用-->
<script src="/layui/layui.js" charset="utf-8"></script>
    <script type="text/html" id="choseTy">
        {{#  if(d.tp == 0){ }}
        空闲
        {{#  } else { }}
        预定
        {{#  } }}
</script>

<script type="text/html" id="dateChange">
    <!-- 这是对时间类型的转换，数据库类型与页面类型不同，通过外置js下写的 dateformat.js中的方法进行转换-->
    {{#  if(d.orderTime || ''){ }}
        {{=new Date(d.orderTime).format('yyyy-MM-dd HH:mm:ss') }}
        {{#  } else { }}
        {{#  } }}
</script>
<script id="optionBar" type="text/html">
        <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="order">
            {{#  if(d.tp == 0){ }}
            预定
            {{#  } else { }}
            退订
            {{#  } }}
        </a>
        <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
        <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>
        <script>
            layui.use(['table','jquery','layer','laydate'],function () {
                var table=layui.table;
                var $ =layui.jquery;
                var layer = layui.layer;
                var laydate=layui.laydate;
                //将表格需要渲染的内容提出为一个对象
                var option={
                    elem:'#table1',
                    url:"/findTable.action",
                    parseData:function (res) {

                        return{
                            "code":0,
                            "msg":"",
                            "count":res.count,
                            "data" : res.data
                        };
                    },
                    cols:[[
                            {field:"tid",title:"编号"}
                            ,{field:"name",title:"餐桌名"}
                            ,{field:"tp",title:"餐桌状态",templet:'#choseTy'}
                            ,{field:"orderTime",title:"预定时间" ,templet:'#dateChange'}
                            ,{toolbar:'#optionBar',title:"操作"}
                        ]],
                    page:true
                };
                table.render(option);

                //监听查找按钮,进行模糊查询
                $("#findTableBt").click(function () {
                    var condition=$("#conditionForm input[name=condition]" ).val();
                    option.where={"condition['tableName']":condition}
                    console.log(condition);
                    table.render(option);
                })


                $("#addTable").click(function () {
                    layer.open({
                        type: 1
                        ,title: "添加餐桌"//不显示标题栏
                        ,closeBtn: false
                        ,area: '300px;'
                        ,shade: 0.8
                        ,id: 'changeTableName' //设定一个id，防止重复弹出
                        ,btn: ['添加', '取消']
                        ,btnAlign: 'c'
                        ,moveType: 1 //拖拽模式，0或者1
                        ,content: '<div style="padding: 50px; line-height: 22px; background-color: #393D49; color: #fff; font-weight: 300;"><form id="addForm"><input id="addName" name="addName" lay-verify="required" ></form></div>'
                        ,btn1:function () {
                            var name=$("#addForm input[name=addName]").val();
                            $.ajax({
                                url:"/addTable.action"
                                ,type:"POST"
                                ,contentType:"application/json;charset=utf-8"
                                ,data:JSON.stringify({"name":name})
                                ,success:function (res) {
                                    var returnNumber=parseInt(res);
                                    if(returnNumber!=0){
                                        layer.closeAll();
                                        table.render(option);
                                        layer.msg("添加成功");
                                    }else{
                                        layer.closeAll();
                                        layer.msg("添加失败");
                                    }
                                }
                            })
                        }
                    })
                })


                //监听表格中的操作按钮
                table.on('tool(test)',function (obj) {
                    var data = obj.data;
                    if(obj.event === "edit"){
                        layer.open({
                            type: 1
                            ,title: "修改餐桌名"//不显示标题栏
                            ,closeBtn: false
                            ,area: '300px;'
                            ,shade: 0.8
                            ,id: 'changeTableName' //设定一个id，防止重复弹出
                            ,btn: ['修改', '取消']
                            ,btnAlign: 'c'
                            ,moveType: 1 //拖拽模式，0或者1
                            ,content: '<div style="padding: 50px; line-height: 22px; background-color: #393D49; color: #fff; font-weight: 300;"><form id="changeForm"><input id="changeName" name="changName" value="'+data.name+'" lay-verify="required" ></form></div>'
                            ,btn1:function (index,layero) {//监听第一个按钮
                                var name=$("#changeForm input[name='changName']").val();
                                var tid=data.tid;
                                $.ajax({
                                    type:"POST"
                                    ,contentType:"application/json;charset=utf-8"
                                     ,url:'/updateTable.action'
                                    ,data:JSON.stringify({"name":name,"tid":tid})
                                    ,success:function (res) {
                                        var returnNumber=   parseInt(res);
                                            if (returnNumber!=0){
                                                layer.closeAll();
                                                table.render(option);
                                                layer.msg("修改成功")
                                            }else {
                                                layer.msg("修改失败")
                                            }

                                        }
                                })
                            }
                            /*,btn2:function (index,layero) {
                                console.log("按钮二")
                            }*/
                        });
                    }
                    if(obj.event=="del"){
                        console.log("进入删除")
                        layer.open({
                            type: 1
                            ,title: "确定删除该餐桌吗？"//不显示标题栏
                            ,closeBtn: false
                            ,area: '300px;'
                            ,shade: 0.8
                            ,id: 'changeTableName' //设定一个id，防止重复弹出
                            ,btn: ['确定', '取消']
                            ,btnAlign: 'c'
                            ,moveType: 1 //拖拽模式，0或者1
                            ,btn1:function (index,layero) {//监听第一个按钮
                                var tid=data.tid;
                                $.ajax({
                                    type:"POST"
                                    ,contentType:"application/json;charset=utf-8"
                                    ,url:'/deleteTable.action'
                                    ,data:JSON.stringify({"tid":tid})
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
                    if (obj.event=="order"){
                        if (data.tp==1){//原本为预定状态，点击之后将预定变为退订，并删除预定时间
                            var tid = data.tid;
                            var tp = data.tp;
                            $.ajax({
                                contentType:"application/json;charset=utf-8"
                                ,type:"POST"
                                ,url:"/updateTableTp.action"
                                ,data:JSON.stringify({"tid":tid,"tp":0,"orderTime":null})
                                ,success:function (res) {
                                    var returnNumber=   parseInt(res);
                                    if (returnNumber!=0){
                                        table.render(option);
                                        layer.msg("退订成功")
                                    }else {
                                        layer.msg("退订失败")
                                    }
                                }
                            })
                        }else {//原本为空闲状态，点击之后需要选择预定时间， 并将空闲状态改为预定。
                            var tid=data.tid;
                            var tp =data.tp;
                                layer.open({
                                    type: 1
                                    ,title: "请选择预定时间？"//不显示标题栏
                                    ,closeBtn: false
                                    ,area: '300px;'
                                    ,shade: 0.8
                                    ,id: 'changeTableName' //设定一个id，防止重复弹出
                                    ,btn: ['确定', '取消']
                                    ,btnAlign: 'c'
                                    ,moveType: 1
                                    ,content:"<div><input class='layui-input' type='text' placeholder='yyyy-MM-dd HH:mm:ss' id='timeChose'></div>"
                                    ,btn1:function () {
                                        var date=$("#timeChose").val();
                                        $.ajax({
                                            contentType:"application/json;charset=utf-8"
                                            ,url:'/updateTableTp.action'
                                            ,type:"POST"
                                            ,data:JSON.stringify({"tid":tid,"tp":1,"orderTime":parseDate(date)})
                                            ,success:function (res) {
                                                var returnNumber=  parseInt(res);
                                                if (returnNumber!=0){
                                                    table.render(option);
                                                    layer.closeAll();
                                                    layer.msg("预定成功")

                                                }else {
                                                    layer.closeAll();
                                                    layer.msg("预定失败")
                                                }
                                            }
                                        })
                                    }

                                });
                                laydate.render({
                                    elem:"#timeChose"
                                    ,type:'datetime'
                                    ,min:2
                                });

                        }
                    }
                })
            });
        </script>

</head>
<body>

</body>
</html>
