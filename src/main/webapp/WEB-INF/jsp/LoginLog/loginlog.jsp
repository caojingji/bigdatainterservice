<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.atg.com/taglibs/json" prefix="json" %>
<!doctype html>
<html>
<head>
	<meta charset="UTF-8">
	<title>登录日志展示</title>
	<link rel="stylesheet" type="text/css" href="/css/easyui-new.css">
	<link rel="stylesheet" type="text/css" href="/css/font-awesome.min.css">
	<link rel="stylesheet" type="text/css" href="/css/bjzycx.css">
</head>
<body>
<div id="nipic_search">
	<div id="header">
		<h5 class="h_title6"></h5>
	</div>
	<!--内容-->
	<div id="content_box0" class="content_box">
		<!--查询条件-->
		<div class="condition">
			<h6 class="cx_t">查询条件</h6>
			<table style="width:100%;border-collapse: collapse;background-color: rgba(255,255,255,0.75);text-align:left;" class="cx_table_tj">
				<tr>
					<td class="td_r" style="width:10%;"> 案事件编号：</td>
					<td style="width:20%;"><input name="asjbh" id="asjbh"/></td>
					<td class="td_r" style="width:10%;"> 登录人姓名：</td>
					<td style="width:20%;"><input name="cxrXm" id="cxrXm"/></td>
					<td class="td_r" style="width:15%;"> 登录人身份证号：</td>
					<td style="width:20%;"><input name="cxrSfzh" id="cxrSfzh"/></td>

				</tr>
				<tr>
					<td class="td_r" style="width:10%;"> 登录人警号：</td>
					<td style="width:20%;"><input name="cxrJh" id="cxrJh"/></td>
					<td class="td_r" style="width:14%;"> 登录人联系电话：</td>
					<td style="width:20%;"><input name="cxrLxdh" id="cxrLxdh"/></td>

					<td class="td_r" style="width:14%;"> 登录人单位：</td>
					<td style="width:20%;"><input name="dldwmc" id="dldwmc"/></td>
				</tr>

				<tr>

					<td class="td_r">开始时间：</td>
						<td>
							<p class="datebox-h"><input id = "cxrKssj" type="text" class="easyui-datebox" style="height:24px;"></p>
						</td>

					<td class="td_r">结束时间：</td>
					<td>
						<p class="datebox-h"><input id = "cxrJssj" type="text" class="easyui-datebox" style="height:24px;"></p>
					</td>
				</tr>
				<tr>
					<td colspan="8" style="text-align:center;">
						<a href="javascript:queryQueryLog();" class="btn_cx">查询</a>
						<a href="javascript:clear();" class="btn_qkcxtj">重置</a>
					</td>
				</tr>
			</table>
		</div>
		<!--结果 table-->
		<div class="result" style="padding-bottom:30px;width:100%;overflow:hidden;">
			<table class="easyui-datagrid" id="recordLogTable" style="border-top:1px dashed #ccc;" width="100%">
				<tbody>
				<pre id = "resultView" style = "width:100%;border-collapse: collapse;background-color: rgba(255,255,255,0.75);text-align:left;">

            		</pre>
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
        queryQueryLog();

    });

    function clear(){
        $("#asjbh").val('');
        $("#cxrXm").val('');
        $("#cxrSfzh").val('');
        $("#cxrJh").val('');
        $("#cxrLxdh").val('');
        $("#dldwmc").val('');
        $("#cxrKssj").datebox("setValue","");
        $("#cxrJssj").combo("setText","");
    }

    function queryQueryLog(){
        var  param= {
            "asjbh":$("#asjbh").val().trim(),
            "cxrXm":$("#cxrXm").val().trim(),
            "cxrSfzh":$("#cxrSfzh").val().trim(),
            "cxrJh":$("#cxrJh").val().trim(),
            "cxrLxdh":$("#cxrLxdh").val().trim(),
			"dldwmc":$("#dldwmc").val().trim(),
            "cxrKssj":$("#cxrKssj").datebox("getValue"),
            "cxrJssj":$("#cxrJssj").datebox("getValue")
        };
        var table_title = [
            {title:"案事件编号",field:"asjbh",align:'center',width:'15%'},
            {title:"登录人姓名",field:"cxrXm",align:'center',width:'15%'},
            {title:"登录人身份证号",field:"cxrSfzh",align:'center',width:'15%'},
            {title:"登录人警号",field:"cxrJh",align:'center',width:'10%'},
            {title:"登录人联系电话",field:"cxrLxdh",align:'center',width:'15%'},
            {title:"登录人单位",field:"dldwmc",align:'center',width:'15%'},
            {title:"登录时间",field:"cxrDlsj",align:'center',width:'15%'},
        ];
        $("#recordLogTable").datagrid({
            url: "/selectLoginLog",
            columns : [table_title],
            queryParams:param,
            striped: true,
            fitColumns: true,
            singleSelect: true,
            pagination: true,
            nowrap: false, //设置是否换行  false换行 true表示不换行
            pageSize: 20,
            pageList: [10, 20, 50, 100, 150, 200],
            showFooter: true,
            pageNumber:1,
            loadFilter: function(data){
                return data;
            }
        });

    }

</script>
</body>
</html>