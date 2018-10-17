<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.atg.com/taglibs/json" prefix="json" %>
<!doctype html>
<html>
<head>
	<meta charset="UTF-8">
	<title>时空轨迹伴随任务列表</title>
	<link rel="stylesheet" type="text/css" href="/css/easyui-new.css">
	<link rel="stylesheet" type="text/css" href="/css/font-awesome.min.css">
    <link rel="stylesheet" type="text/css" href="/css/bjzycx.css">
    <link rel="stylesheet" type="text/css" href="/css/skgj.css">
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
					<td class="td_r" style="width:25%;font-weight: 600;">案件编号：</td>
					<td style="width:20%;"><input name = "asjbh" id = "asjbh" type="text" value="${asjbh}"/></td>
					<td class="td_r" style="width:10%;font-weight: 600;">服务标识号：</td>
					<td style="width:45%;"><input name = "fwbsh" id = "fwbsh" type="text" value="${fwbsh}"/></td>
				</tr>
				<tr>
					<td style="text-align: center" colspan="4">
						<a href="javascript:queryTasks('02');" class="btn_cx">查询</a>
						<a href="javascript:$('#asjbh').val('');$('#fwbsh').val('');" class="btn_qkcxtj" style="margin-right:100px;">重置</a>
					</td>
				</tr>
			</table>
		</div>
		<!--查询结果 table-->
		<div class="result" style="padding-bottom:30px;">
			<table class="easyui-datagrid" id="taskTable" style="border-top:1px dashed #ccc;" width="100%">
                <tbody>
                </tbody>
			</table>
		</div>
	</div>
</div>
<script type="text/javascript" src="/js/jquery-1.11.3.js"></script>
<script type="text/javascript" src="/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/js/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">
    $(function(){
        queryTasks("01");
    });
function queryTasks(type){
        var  asjbh,fwbsh;
        if("01" == type){
            asjbh = "${asjbh}";
            fwbsh = "${fwbsh}";
        }else{
            asjbh = $("#asjbh").val();
            fwbsh = $("#fwbsh").val();
        }
        var table_title = [
            {title:"任务编号",field:"taskId",align:'center',width:'15%',formatter : function(val,row,index){
                    return "<a onclick=\"showTaskResults('"+row.taskId+"')\" title='点击查看任务推荐结果'>"+row.taskId+"</a>";
                }
			},
            {title:"案件编号",field:"taskCaseId",align:'center',width:'20%'},
            {title:"服务标识号",field:"objectValue",align:'center',width:'15%'},
            {title:"任务名称",field:"taskName",align:'center',width:'15%'},
            {title:"伴随开始时间",field:"startTime",align:'center',width:'10%'},
            {title:"伴随结束时间",field:"endTime",align:'center',width:'10%'},
            {title:"任务状态",field:"state",align:'center',width:'5%'},
            {title:"登记时间",field:"djsj",align:'center',width:'8%'}
        ];
        var  param= {
            "asjbh":asjbh,
			"fwbsh":fwbsh,
        };
        $("#taskTable").datagrid({
            url: "/queryTasksByAsjbhAndFwbsh",
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
                return data;
            }
        });
}
function showTaskResults(taskId){
    //var url="/toTaskResultJsp?taskId="+taskId;
    //window.open(url);
}
</script>
</body>
</html>