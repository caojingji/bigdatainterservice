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
	<div id="menu">
		<ul class="menu_list">
			<li class="act"><a>人&nbsp;&nbsp;员<span class="bot_triangle"></span></a></li>
			<li><a>车&nbsp;&nbsp;辆<span></span></a></li>
		</ul>
	</div>
	<!--内容-->
	<div id="content_box0" class="content_box">
		<!--结果 table-->
		<div class="result" style="padding-bottom:30px;">
			<c:choose>
				<c:when test="${!empty taskResultVOS}">
					<c:forEach items="${taskResultVOS}" var="task">
						<div class="float-div">
							<div class="xx-box">
                                <c:choose>
                                    <c:when test="${!empty task.ryzp}">
                                        <div class="lf-img"><img src="data:image/gif;base64,${task.ryzp}"/></div>
                                    </c:when>
                                    <c:otherwise>
                                        <div class="lf-img"><img src="/images/timg.jpg" style = "height:121px;width:100px;"/></div>
                                    </c:otherwise>
                                </c:choose>
								<div class="rg-xx">
									<div class="xx-line"><span class="xx-xm">${task.name}</span><span>${task.age}</span><i class="fa fa-external-link"></i><i class="fa fa-folder-open-o"></i><span class="c125"></span></div>
									<div class="xx-line"><i class="fa fa-id-card orange" title="身份证号"></i><span>${task.zjhm}</span></div>
									<div class="xx-line"><i class="fa fa-user yellow" title="出生日期"></i><span>${task.csrq}</span></div>
									<div class="xx-line"><i class="fa fa-phone" title="电话号码"></i><span>${task.sjhm}</span></div>
									<div class="xx-line"><i class="fa fa-map-marker green" title="现住址"></i><span>${task.xzzDzmc}</span></div>
									<div class="xx-line over"><span class="label">前科人员</span><span class="label">高危人员</span><span class="label">刑侦关注人员</span></div>
								</div>
							</div>
						</div>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<font color="red" style = "margin-left: 48%;">暂无数据</font>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
	<div id="content_box1" class="content_box">
		<!--结果 table-->
        <div class="result" style="padding-bottom:30px;">
            <c:choose>
                <c:when test="${!empty taskResultVOSCph}">
                    <c:forEach items="${taskResultVOSCph}" var="cph">
                        <div class="float-div">
                            <div class="xx-box" style="height:168px;">
                                <c:choose>
                                    <c:when test="${!empty cph.ryzp}">
                                        <div class="lf-img"><img src="data:image/gif;base64,${cph.ryzp}"/></div>
                                    </c:when>
                                    <c:otherwise>
                                        <div class="lf-img"><img src="/images/timg.jpg" style = "height:121px;width:100px;"/></div>
                                    </c:otherwise>
                                </c:choose>
                                <div class="rg-xx">
                                    <div class="top-div" style="padding-top:16px;">
                                        <div class="xx-line"><i class="orange">车牌号：</i><span>${cph.objectValue}</span></div>
                                        <div class="xx-line"><i class="orange">车辆类型：</i><span>${cph.objectTypeName}</span></div>
                                    </div>
                                    <div class="bottom-div">
                                        <div class="xx-line"><i class="green">证件类型：</i><span>${cph.zjlx}</span></div>
                                        <div class="xx-line"><i class="green">证件号码：</i><span>${cph.zjhm}</span></div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <font color="red" style = "margin-left: 48%;">暂无数据</font>
                </c:otherwise>
            </c:choose>
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
$("#content_box1").hide();
$("#menu li").click(function(){
    if(!$(this).hasClass("act")){
        $("#menu li").removeClass("act");
        $("#menu li span").removeClass("bot_triangle");
        $(this).addClass("act");
        $(this).find("span").addClass("bot_triangle");
        var ind=$(this).index();
        $(".content_box").each(function(){
            $(this).hide();
        });
        $("#content_box"+ind).show();
        if(ind==1){
            if(flag_){
                /*queryDnabzList();*/
                flag_=false;
            }
        }
    }
});
</script>
</body>
</html>