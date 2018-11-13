<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.atg.com/taglibs/json" prefix="json" %>
<!doctype html>
<html>
<head>
	<meta charset="UTF-8">
	<title>呈请审批日志展示</title>
	<link rel="stylesheet" type="text/css" href="/css/easyui-new.css">
	<link rel="stylesheet" type="text/css" href="/css/font-awesome.min.css">
    <link rel="stylesheet" type="text/css" href="/css/bjzycx.css">
</head>
<body>
<div id="nipic_search">
	<div id="header">		
		<h5 class="h_title5"></h5>
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

					<td class="td_r" style="width:10%;"> 标识号：</td>
					<td style="width:15%;"><input name="spbsh" id="spbsh"/></td>

					<td class="td_r" style="width:10%;"> 标识号类别：</td>
					<td style="width:15%;">
						<select id = "bshlxdm" name = "bshlxdm">
							<option value="">--请选择--</option>
							<option value="001">手机号码</option>
							<option value="002">QQ号码</option>
							<option value="004">身份证号</option>
							<option value="005">车牌号</option>
						</select>
					</td>
				</tr>
				<tr>
					<td class="td_r" style="width:10%;"> 姓名：</td>
					<td style="width:15%;"><input name="cxrXm" id="cxrXm"/></td>

					<td class="td_r" style="width:10%;"> 身份证号：</td>
					<td style="width:15%;"><input name="cxrSfzh" id="cxrSfzh"/></td>

					<td class="td_r" style="width:10%;"> 警号：</td>
					<td style="width:15%;"><input name="cxrJh" id="cxrJh"/></td>
				</tr>
				<tr>
					<td class="td_r" style="width:10%;"> 联系电话：</td>
					<td style="width:20%;"><input name="cxrLxdh" id="cxrLxdh"/></td>

					<td class="td_r" style="width:10%;"> 单位名称：</td>
					<td style="width:15%;"><input name="dldwmc" id="dldwmc"/></td>
				</tr>
				
				<tr>
					<td class="td_r">开始时间：</td>
					<td>
						<p class="datebox-h"><input id = "startTime" type="text" class="easyui-datebox" style="height:24px;"></p>
					</td>
					<td class="td_r">结束时间：</td>
					<td>
						<p class="datebox-h"><input id = "endTime" type="text" class="easyui-datebox" style="height:24px;"></p>
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
		<div class="result" style="padding-bottom:30px;">
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
    $("#spbsh").val('');
    $("#bshlxdm").select(0);
    $("#cxrXm").val('');
    $("#cxrSfzh").val('');
    $("#cxrJh").val('');
    $("#cxrLxdh").val('');
    $("#dldwmc").val('');
    $("#startTime").datebox("setValue","");
    $("#endTime").combo("setText","");
}

function queryQueryLog(){
    var  param= {
        "asjbh":$("#asjbh").val().trim(),
		"spbsh":$("#spbsh").val().trim(),
		"bshlxdm":$("#bshlxdm").val().trim(),
        "cqrXm":$("#cxrXm").val().trim(),
        "cqrSfzh":$("#cxrSfzh").val().trim(),
        "cqrJh":$("#cxrJh").val().trim(),
        "cqrLxdh":$("#cxrLxdh").val().trim(),
		"dldwmc":$("#dldwmc").val().trim(),
        "startTime":$("#startTime").datebox("getValue")==""?null:$("#startTime").datebox("getValue"),
        "endTime":$("#endTime").datebox("getValue")==""?null:$("#endTime").datebox("getValue")
    };
    var table_title = [
        {title:"申请标题",field:"spTitle",align:'center',width:'15%'},
        {title:"案事件编号",field:"asjbh",align:'center',width:'12%'},
        {title:"查询标识号",field:"spbsh",align:'center',width:'10%'},
        {title:"标识号类型名称",field:"bshlxmc",align:'center',width:'8%'},
        {title:"呈请人姓名",field:"cqrXm",align:'center',width:'8%'},
        {title:"呈请人身份证号",field:"cqrSfzh",align:'center',width:'12%'},
        {title:"呈请人警号",field:"cqrJh",align:'center',width:'8%'},
        {title:"呈请人联系电话",field:"cqrLxdh",align:'center',width:'7%'},
        {title:"呈请人单位",field:"dldwmc",align:'center',width:'12%'},
        {title:"登记时间",field:"djsj",align:'center',width:'7%'}
    ];
    $("#recordLogTable").datagrid({
        url: "/querySpLogsPage",
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
        pageNumber:0,
        loadFilter: function(data){
            return data;
        }
    });
}

</script>
</body>
</html>