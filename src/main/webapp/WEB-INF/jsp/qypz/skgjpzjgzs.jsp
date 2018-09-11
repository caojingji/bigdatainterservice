<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.atg.com/taglibs/json" prefix="json" %>
<!doctype html>
<html>
<head>
	<meta charset="UTF-8">
	<title> 时空轨迹碰撞结果展示</title>
	<link rel="stylesheet" type="text/css" href="/css/easyui-new.css">
	<link rel="stylesheet" type="text/css" href="/css/font-awesome.min.css">
    <link rel="stylesheet" type="text/css" href="/css/skgj.css">
    <style type="text/css">
        ul.list{overflow: hidden;text-align:center;height:40px;font-size: 14px;font-weight: 600;}
        ul.list li{width: 150px;height: 150px;margin:5px;float: left;overflow: hidden;}
        ul.list li img{width: 100%;height: 100%;}
        ul.list p{text-align: center;padding: 10px;}
        .more{overflow: hidden;padding:10px;text-align: center;}
        .more a{display: block;width: 80px;padding:8px 0;color:#fff;margin:0 auto;background:#333;text-align:center;border-radius:3px;}
        .more a:hover{text-decoration: none;background: red;color: #fff;}
		.nodata{height:342px;position:absolute;top:50%;margin-top:-171px;left: 50%;margin-left: -176px;}
    </style>
</head>
<body>
<div>
	<div id="header">		
		<h5 class="h_title3"></h5>
	</div>
	<div id="menu">
		<ul class="menu_list">
			<li class="act"><a>人&nbsp;&nbsp;员<span class="bot_triangle"></span></a></li>
			<li flag="false"><a>车&nbsp;&nbsp;辆<span></span></a></li>
		</ul>
	</div>
	<!--内容-->
	<div id="content_box0" class="content_box" style="position:relative;">
		<!--人员结果 table-->
		<div class="result" style="padding-bottom:30px;">
		</div>
        <ul class="list" style="display: none;"><i class="fa fa-refresh fa-spin fa-fw"></i>数据加载中，请稍后...</ul>
        <div class="more" style="display: none;"><a href="javascript:void(0);" onClick="jq22.loadMore('${taskId}','01');">加载更多</a></div>
	</div>
	<div id="content_box1" class="content_box" style="position:relative;">
		<!--车辆结果 table-->
        <div class="result" style="padding-bottom:30px;">
        </div>
        <ul class="list" style="display: none;"><i class="fa fa-refresh fa-spin fa-fw"></i>数据加载中，请稍后...</ul>
        <div class="more" style="display: none;"><a href="javascript:void(0);" onClick="jq22.loadMore('${taskId}','02');">加载更多</a></div>
	</div>
</div>
<script type="text/javascript" src="/js/jquery-1.11.3.js"></script>
<script type="text/javascript" src="/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/js/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="/js/jquery.hover3d.min.js"></script>
<script type="text/javascript" src="/js/skgjpzjgzs.js"></script>
<script>
var taskId = "${taskId}"; //任务编号
$(function(){
	$(".float-div").hover3d({
        selector: ".xx-box",
        shine: true,
        sensitivity: 20,
    });
    jq22.init(taskId,"01");
});
</script>
</body>
</html>