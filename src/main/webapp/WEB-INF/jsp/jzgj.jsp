<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.atg.com/taglibs/json" prefix="json" %>
<!doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <title>基站轨迹测试</title>
    <link rel="stylesheet" type="text/css" href="/css/easyui-new.css">
    <link rel="stylesheet" type="text/css" href="/css/font-awesome.min.css">
    <link rel="stylesheet" type="text/css" href="/css/bjzycx.css">
</head>
<body>
<div id="nipic_search">
    <div id="header">
        <h5 class="h_title"></h5>
    </div>
    <!--内容-->
    <div id="content_box0" class="content_box">
        <!--查询条件-->
        <div class="condition">
            <h6 class="cx_t">查询条件</h6>
            <table style="width:100%;border-collapse: collapse;background-color: rgba(255,255,255,0.75);text-align:left;" class="cx_table_tj">
                <tr>
                    <td class="td_r" style="width:10%;">接口名称：</td>
                    <td style="width:20%;">
                        <select name="type" id="interName">
                            <option value="2" selected>轨迹接口</option>
                        </select>
                    </td>
                    <td class="td_r" style="width:10%;">参数类型：</td>
                    <td style="width:20%;">
                        <select name="type" id="objType">
                            <option value="001" selected>手机号码</option>
                            <option value="002">QQ号码</option>
                            <option value="003">微信号码</option>
                        </select>
                    </td>
                    <td class="td_r" style="width:10%;"> 参数值：</td>
                    <td style="width:20%;"><input name="objValue" id="objValue"/></td>
                </tr>
                <tr>
                    <td class="td_r">轨迹开始时间：</td>
                    <td>
                        <p class="datebox-h"><input id = "kssj" type="text" class="easyui-datebox" style="height:24px;"></p>
                    </td>
                    <td class="td_r">轨迹结束时间：</td>
                    <td>
                        <p class="datebox-h"><input id = "jssj" type="text" class="easyui-datebox" style="height:24px;"></p>
                    </td>
                </tr>
                <tr>
                    <td colspan="8" style="text-align:center;">
                        <a href="javascript:queryJzgj();" class="btn_cx">查询</a>
                        <a href="javascript:clear();" class="btn_qkcxtj">重置</a>
                    </td>
                </tr>
            </table>
        </div>
        <!--查询结果 table-->
        <div class="result" style="padding-bottom:30px;">
			<span class="">
				结果展示：
			</span>
            <div id = "resultView" style = "width:100%;height:400px;border-collapse: collapse;background-color: rgba(255,255,255,0.75);text-align:left;">

            </div>
        </div>
    </div>
</div>
<script type="text/javascript" src="/js/jquery-1.11.3.js"></script>
<script type="text/javascript" src="/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/js/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="/js/gjcx.js"></script>
</body>
</html>