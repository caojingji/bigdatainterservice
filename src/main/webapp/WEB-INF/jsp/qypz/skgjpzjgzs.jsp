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
        .jq22 ul.list{overflow: hidden;}
        .jq22 ul.list li{width: 150px;height: 150px;margin:5px;float: left;overflow: hidden;}
        .jq22 ul.list li img{width: 100%;height: 100%;}
        .jq22 ul.list p{text-align: center;padding: 10px;}
        .jq22 .more{overflow: hidden;padding:10px;text-align: center;}
        .jq22 .more a{display: block;width: 80px;padding:8px 0;color:#fff;margin:0 auto;background:#333;text-align:center;border-radius:3px;}
        .jq22 .more a:hover{text-decoration: none;background: red;color: #fff;}
    </style>
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
		<!--人员结果 table-->
		<div class="result" style="padding-bottom:30px;">
		</div>
        <ul class="list" style="display: none;">数据加载中，请稍后...</ul>
        <div class="more" style="display: none;"><a href="javascript:;" onClick="jq22.loadMore('01');">加载更多</a></div>
	</div>
	<div id="content_box1" class="content_box">
		<!--车辆结果 table-->
        <div class="result" style="padding-bottom:30px;">
        </div>
        <ul class="list" style="display: none;">数据加载中，请稍后...</ul>
        <div class="more" style="display: none;"><a href="javascript:;" onClick="jq22.loadMore('02');">加载更多</a></div>
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
        $("#menu li").removeClass( );
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

var taskId = "${taskId}"; //任务编号
var jq22 = {
    _default:6, //默认显示图片个数
    _loading:6, //每次点击按钮后加载的个数
    page_sj:0, //页码
    page_cph:0,
    init:function(taskId,type){
        var param = {
            "page":0,
            "size":6,
            "taskId":taskId,
            "objectType":type
        }
        $.ajax({
            type:"POST",
            data:param,
            url:"/getTaskResults",
			beforeSend:beforeSendFn(type,true),
            complete:completeFn(type),
			success:function(data) {
                console.log("data ===== " + JSON.stringify(data));
                if (null != data && data.length > 0) {
                    for (var i = 0; i < data.length; i++) {
                        var ryzpStr = "";
                        if (null != data[i].ryzp && "" != data[i].ryzp) {
                            ryzpStr = "<img src=\"data:image/gif;base64," + data[i].ryzp + "\"/>";
                        } else {
                            ryzpStr = "<img src=\"/images/timg.jpg\" style = \"height:121px;width:100px;\"/>";
                        }
                        if ("01" == type) {
                            $("#content_box0 div[class='result']").append("<div class='float-div'>" +
                                "<div class='xx-box'>" +
                                "<div class='lf-img'>" + ryzpStr + "</div>" +
                                "<div class='rg-xx'>" +
                                "<div class='xx-line'><span class='xx-xm'>" + data[i].name + "</span><span>" + data[i].age + "</span><i class='fa fa-external-link'></i><i class='fa fa-folder-open-o'></i><span class='c125'></span></div>" +
                                "<div class='xx-line'><i class='fa fa-id-card orange' title='身份证号'></i><span>" + data[i].zjhm + "</span></div>" +
                                "<div class='xx-line'><i class='fa fa-user yellow' title='出生日期'></i><span>" + data[i].csrq + "</span></div>" +
                                "<div class='xx-line'><i class='fa fa-phone' title='电话号码'></i><span>" + data[i].sjhm + "</span></div>" +
                                "<div class='xx-line'><i class='fa fa-map-marker green' title='现住址'></i><span>" + data[i].xzzDzmc + "</span></div>" +
                                "<div class='xx-line over'><span class='label'>前科人员</span><span class='label'>高危人员</span><span class='label'>刑侦关注人员</span></div>" +
                                "</div>" +
                                "</div>" +
                                "</div>");
                        } else if ("02" == type) {
                            $("#content_box1 div[class='result']").append("<div class=\"float-div\">\n" +
                                "                            <div class=\"xx-box\" style=\"height:168px;\">\n" +
                                "                                        <div class=\"lf-img\">" + ryzpStr + "</div>\n" +
                                "                                <div class=\"rg-xx\">\n" +
                                "                                    <div class=\"top-div\" style=\"padding-top:16px;\">\n" +
                                "                                        <div class=\"xx-line\"><i class=\"orange\">车牌号：</i><span>" + data[i].objectValue + "</span></div>\n" +
                                "                                        <div class=\"xx-line\"><i class=\"orange\">车辆类型：</i><span>" + data[i].objectTypeName + "</span></div>\n" +
                                "                                    </div>\n" +
                                "                                    <div class=\"bottom-div\">\n" +
                                "                                        <div class=\"xx-line\"><i class=\"green\">证件类型：</i><span>" + data[i].zjlx + "</span></div>\n" +
                                "                                        <div class=\"xx-line\"><i class=\"green\">证件号码：</i><span>" + data[i].zjhm + "</span></div>\n" +
                                "                                    </div>\n" +
                                "                                </div>\n" +
                                "                            </div>\n" +
                                "                        </div>");
                        }
                    }
                    if ("01" == type) {
                        jq22.page_cph = jq22.page_cph + 1;
                    } else if ("02" == type) {
                        jq22.page_sj = jq22.page_sj + 1;
                    }
                } else {
                    if ("01" == type) {
                        $("#content_box0 div.result").append("<font color=\"red\" style = \"margin-left: 48%;\">暂无数据</font>");
                    } else {
                        $("#content_box1 div.result").append("<font color=\"red\" style = \"margin-left: 48%;\">暂无数据</font>");
                    }
                }
            }
        });
    },
    loadMore:function(taskId,type){
        var page = 0;
        if("01" == type){
            page = jq22.page_sj;
        }else if("02" == type){
            page = jq22.page_cph;
        }
        var param = {
            "page":page,
            "size":6,
            "taskId":taskId,
            "objectType":type
        }
        $.post({
            type:"POST",
            data:param,
            url:"/getTaskResults",
			beforeSend:beforeSendFn(type,false),
			complete:completeFn(type),
			success:function(data) {
                console.log("data ===== " + JSON.stringify(data));
                if (null != data && data.length > 0) {
                    for (var i = 0; i < data.length; i++) {
                        var ryzpStr = "";
                        if (null != data[i].ryzp && "" != data[i].ryzp) {
                            ryzpStr = "<img src=\"data:image/gif;base64," + data[i].ryzp + "\"/>";
                        } else {
                            ryzpStr = "<img src=\"/images/timg.jpg\" style = \"height:121px;width:100px;\"/>";
                        }
                        if ("01" == type) {
                            $("#content_box0 div[class='result']").append("<div class='float-div'>" +
                                "<div class='xx-box'>" +
                                "<div class='lf-img'>" + ryzpStr + "</div>" +
                                "<div class='rg-xx'>" +
                                "<div class='xx-line'><span class='xx-xm'>" + data[i].name + "</span><span>" + data[i].age + "</span><i class='fa fa-external-link'></i><i class='fa fa-folder-open-o'></i><span class='c125'></span></div>" +
                                "<div class='xx-line'><i class='fa fa-id-card orange' title='身份证号'></i><span>" + data[i].zjhm + "</span></div>" +
                                "<div class='xx-line'><i class='fa fa-user yellow' title='出生日期'></i><span>" + data[i].csrq + "</span></div>" +
                                "<div class='xx-line'><i class='fa fa-phone' title='电话号码'></i><span>" + data[i].sjhm + "</span></div>" +
                                "<div class='xx-line'><i class='fa fa-map-marker green' title='现住址'></i><span>" + data[i].xzzDzmc + "</span></div>" +
                                "<div class='xx-line over'><span class='label'>前科人员</span><span class='label'>高危人员</span><span class='label'>刑侦关注人员</span></div>" +
                                "</div>" +
                                "</div>" +
                                "</div>");
                        } else if ("02" == type) {
                            $("#content_box1 div[class='result']").append("<div class=\"float-div\">\n" +
                                "                            <div class=\"xx-box\" style=\"height:168px;\">\n" +
                                "                                        <div class=\"lf-img\">" + ryzpStr + "</div>\n" +
                                "                                <div class=\"rg-xx\">\n" +
                                "                                    <div class=\"top-div\" style=\"padding-top:16px;\">\n" +
                                "                                        <div class=\"xx-line\"><i class=\"orange\">车牌号：</i><span>" + data[i].objectValue + "</span></div>\n" +
                                "                                        <div class=\"xx-line\"><i class=\"orange\">车辆类型：</i><span>" + data[i].objectTypeName + "</span></div>\n" +
                                "                                    </div>\n" +
                                "                                    <div class=\"bottom-div\">\n" +
                                "                                        <div class=\"xx-line\"><i class=\"green\">证件类型：</i><span>" + data[i].zjlx + "</span></div>\n" +
                                "                                        <div class=\"xx-line\"><i class=\"green\">证件号码：</i><span>" + data[i].zjhm + "</span></div>\n" +
                                "                                    </div>\n" +
                                "                                </div>\n" +
                                "                            </div>\n" +
                                "                        </div>");
                        }
                    }
                    if ("01" == type) {
                        jq22.page_cph = jq22.page_cph + 1;
                    } else if ("02" == type) {
                        jq22.page_sj = jq22.page_sj + 1;
                    }
                } else {
                    if ("01" == type) {
                        $("#content_box0 div[class='result']").append("<font color=\"red\" style = \"margin-left: 48%;\">暂无数据</font>");
                    } else {
                        $("#content_box1 div[class='result']").append("<font color=\"red\" style = \"margin-left: 48%;\">暂无数据</font>");
                    }
                }
            }
        });
    }
}
jq22.init();
function beforeSendFn(type,ifInit){
    if(ifInit){
        if("01" == type){
            $("#content_box0 div.list").show();
        }else{
            $("#content_box0 div.list").show();
        }
	}else{
        if("01" == type){
            $("#content_box0 div.more").hide();
            $("#content_box0 div.list").show();
        }else{
            $("#content_box1 div.more").hide();
            $("#content_box1 div.list").show();
        }
	}
}
function completeFn(type){
	if("01" == type){
		$("#content_box0 div.more").show();
		$("#content_box0 div.list").hide();
	}else if("02" == type){
		$("#content_box1 div.more").show();
		$("#content_box1 div.list").hide();
	}
}

</script>
</body>
</html>