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
	<style>
		.lf-img>img {position: inherit;}
	</style>
</head>
<body>
<div id="nipic_search">
	<div id="header">		
		<h5 class="h_title_bs"></h5>
	</div>	
	<!--内容-->
	<div id="content_box0" class="content_box">
		<!--结果 table-->
		<div class="result" style="padding-bottom:30px;">
			<div class="float-div">
				<div class="xx-box">
					<div class="lf-img"><img src="images/img1.jpg"/></div>
					<div class="rg-xx">
						<div class="xx-line"><span class="xx-xm">赵熙媛</span></div>
						<div class="xx-line"><i class="fa fa-id-card orange" title="身份证号"></i><span>5201131981010550425</span></div>
						<div class="xx-line"><i class="fa fa-user yellow" title="出生日期"></i><span>1981-01-5</span></div>
						<div class="xx-line"><i class="fa fa-phone" title="电话号码"></i><span>13996026536</span></div>
						<div class="xx-line"><i class="fa fa-map-marker green" title="住址"></i><span>武陵路191号4单元4-3</span></div>
						<div class="xx-line over">
							<span class="label">前科人员</span>
							<span class="label">高危人员</span>
							<span class="label">刑侦关注人员</span>
						</div>
					</div>
				</div>
			</div>
			<ul class="list" style="display: none;"><i class="fa fa-refresh fa-spin fa-fw"></i>数据加载中，请稍后...</ul>
			<div class="more" style="display: none;"><a href="javascript:void(0);" onClick="jq22.loadMore('${taskId}');">加载更多</a></div>
		</div>
	</div>
</div>
<script type="text/javascript" src="js/jquery-1.11.3.js"></script>
<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="js/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="js/jquery.hover3d.min.js"></script>
<script type="text/javascript" src="/js/skgjbsjgzs.js"></script>
<script>
var taskId = "${taskId}"; //任务编号
$(function(){
    jq22.init(taskId);
});
</script>
</body>
</html>