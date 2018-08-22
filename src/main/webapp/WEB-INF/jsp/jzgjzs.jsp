<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.atg.com/taglibs/json" prefix="json" %>
<!doctype html>
<html>
<head>
	<meta charset="UTF-8">
	<title>基站轨迹展示</title>
	<link rel="stylesheet" type="text/css" href="/css/easyui-new.css">
	<link rel="stylesheet" type="text/css" href="/css/font-awesome.min.css">
    <link rel="stylesheet" type="text/css" href="/css/bjzycx.css">
</head>
<body>
<div id="nipic_search">
	<div id="header">		
		<h5 class="h_title2"></h5>
	</div>	
	<!--内容-->
	<div id="content_box0" class="content_box">
		<!--结果 table-->
		<div class="result" style="padding-bottom:30px;">
			<table class="easyui-datagrid" id="gjzsTable" style="border-top:1px dashed #ccc;" width="100%">
                <tbody>

                </tbody>
			</table>
		</div>
	</div>
</div>
<script type="text/javascript" src="/js/jquery-1.11.3.js"></script>
<script type="text/javascript" src="/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/js/easyui-lang-zh_CN.js"></script>
<script>
$(function(){
    var table_title = [
        {title:"IMSI",field:"objectvalue",align:'center',width:'20%'},
        {title:"经度",field:"j",align:'center',width:'15%'},
        {title:"纬度",field:"w",align:'center',width:'15%'},
        {title:"基站地址",field:"address",align:'center',width:'30%'},
        {title:"抓取时间",field:"timestr",align:'center',width:'20%'}
    ];
    var  param= {
        "objValue":"${objValue}",
        "kssj":"${kssj}",
        "jssj":"${jssj}"
    };
    console.log(JSON.stringify(param));
	$("#gjzsTable").datagrid({
        url: "/queryTrackList",
        columns : [table_title],
        queryParams:param,
		striped: true,
	    fitColumns: true,
	    singleSelect: false,
	    pagination: true,
	    nowrap: false, //设置是否换行  false换行 true表示不换行
	    pageSize: 20,
	    pageList: [10, 20, 50, 100, 150, 200],
	    showFooter: true,
		pageNumber:1,
        loadFilter: function(data){
            console.log(JSON.stringify(data));
            return data;
        }
    });
});
</script>
</body>
</html>