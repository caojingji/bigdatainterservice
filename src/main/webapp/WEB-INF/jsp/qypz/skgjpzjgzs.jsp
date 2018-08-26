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
</head>
<body>
<div id="nipic_search">
	<div id="header">		
		<h5 class="h_title3"></h5>
	</div>	
	<!--内容-->
	<div id="content_box0" class="content_box">
		<!--结果 table-->
		<div class="result" style="padding-bottom:30px;">
			<c:if test="${!empty taskResultVOS}">
				<c:forEach items="${taskResultVOS}" var="task">
					<div class="float-div">
						<div class="xx-box">
							<div class="lf-img"><img src="data:image/gif;base64,${task.ryzp}"/></div>
							<div class="rg-xx">
								<div class="xx-line"><span class="xx-xm">${task.name}</span><span>${task.age}</span><i class="fa fa-external-link"></i><i class="fa fa-folder-open-o"></i><span class="c125"></span></div>
								<div class="xx-line"><i class="fa fa-id-card orange" title="身份证号"></i><span>${task.zjhm}</span></div>
								<div class="xx-line"><i class="fa fa-user yellow" title="出生日期"></i><span>${task.csrq}</span></div>
								<div class="xx-line"><i class="fa fa-phone" title="电话号码"></i><span>${task.sjhm}</span></div>
								<div class="xx-line"><i class="fa fa-map-marker green" title="住址"></i><span>${task.address}</span></div>
							</div>
						</div>
					</div>
				</c:forEach>
			</c:if>
		</div>
	</div>
</div>
<script type="text/javascript" src="/js/jquery-1.11.3.js"></script>
<script type="text/javascript" src="/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/js/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="/js/jquery.hover3d.min.js"></script>
<script>
$(function(){
	$(".float-div").hover3d({
				selector: ".xx-box",
				shine: true,
				sensitivity: 20,
			});
});
</script>
</body>
</html>