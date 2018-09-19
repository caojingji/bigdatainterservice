<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.atg.com/taglibs/json" prefix="json" %>
<!doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <title>区域碰撞参数发送</title>
    <link rel="stylesheet" type="text/css" href="/css/easyui-new.css">
    <link rel="stylesheet" type="text/css" href="/css/font-awesome.min.css">
    <link rel="stylesheet" type="text/css" href="/css/bjzycx.css">
</head>
<body>
<div id="nipic_search">
    <!--内容-->
    <div id="content_box0" class="content_box">
        <form id="sendParamForm" action="/sendRegionalAnalysisTask" method="post">
            <!--查询条件-->
            <div class="condition">
                <h6 class="cx_t">查询条件</h6>
                <table style="width:100%;border-collapse: collapse;background-color: rgba(255,255,255,0.75);text-align:left;" class="cx_table_tj">
                    <tr>
                        <td class="td_r" style="width:10%;"> 参数值：</td>
                        <td style="width:20%;"><input name="paramStr" id="paramStr"/></td>
                    </tr>

                    <tr>
                        <td colspan="8" style="text-align:center;">
                            <a href="javascript:queryJzgj();" class="btn_cx">查询</a>
                        </td>
                    </tr>
                </table>
            </div>
        </form>
    </div>
</div>
<script type="text/javascript" src="/js/jquery-1.11.3.js"></script>
<script type="text/javascript" src="/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/js/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">
    function queryJzgj(){
        $("#sendParamForm").submit();
    }
</script>
</body>
</html>