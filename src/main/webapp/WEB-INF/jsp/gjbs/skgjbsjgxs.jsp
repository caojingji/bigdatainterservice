<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.atg.com/taglibs/json" prefix="json" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <title>时空轨迹伴随结果展示</title>
    <link rel="stylesheet" type="text/css" href="/css/easyui-new.css">
    <link rel="stylesheet" type="text/css" href="/css/font-awesome.min.css">
    <link rel="stylesheet" type="text/css" href="/css/bjzycx.css">
    <link rel="stylesheet" type="text/css" href="/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="/css/base_initialize.css">
    <link rel="stylesheet" type="text/css" href="/css/style.css">
    <link rel="stylesheet" type="text/css" href="/common/layer/skin/layer.css">
    <!--- 时间插件-->
    <link rel="stylesheet" href="/common/datepicker/skin/WdatePicker.css">
    <link rel="stylesheet" href="/common/datepicker/skin/default/datepicker.css">
</head>
<style>
    .float-div{float: left;margin: 15px 0 25px 0;width:100%;}
    .xx-box{width:100%;height:240px;margin:0 auto;background: #fff;position: relative;}
    .result{display: table;width:100%;}
    .lf-img{display: inline-block;width:40%;height:100%;float: left;}
    .lf-img>img{width:98px;margin:0 10px;position: absolute;top:50%;margin-top:-60px;margin-left:44px;}
    .rg-xx{display: inline-block;width:59%;position:relative;z-index:1000000;border: 1px solid #ececec;border-radius: 5px;padding-left:10px;}
    .xx-line>.xx-xm{font-weight:600;float:left;color:#2145ca;width:130px;overflow: hidden;white-space: nowrap;text-overflow: ellipsis;}
    .xx-line{width:100%;line-height: 35px;}
    .xx-line>span{margin:0 3px;color:#848080;}
    .xx-line>i{margin:0 3px;color:#848080;cursor: pointer;}
    .xx-line>i{color:#848080;line-height: 35px;}
    .xx-line>.c125{float: right;color:#f11f05;}
    .xx-line{overflow: hidden;white-space: nowrap;text-overflow: ellipsis;border-bottom: 1px dashed #ccc;}
    .xx-line>.green{color:#40ea0a;}
    .xx-line>.yellow{color:#eac710;}
    .xx-line>.orange{color:#ce4b22;}
    .xx-line>.label{display: inline-block;margin:0 5px;padding:2px 5px;background: #e23e35;height: 20px;
        line-height: 20px;
        border-radius: 11px;
        font-size: 12px;color:#fff;}
    .over{    overflow: inherit;white-space: inherit;}
</style>
<body>
<header class="heade-box">
    <img src="images/bs-banner.png" class="header-title-img" alt="">
</header>
<div class="cxtj box-s">
    <!--任务信息-->
    <fieldset>
        <legend>任务信息</legend>
        <table>
            <tbody>
            <tr>
                <td colspan="6" style="height:20px;"></td>
            </tr>
            <tr>
                <td class="ar" width="13.3%">案件编号：</td>
                <td width="20%">
                      <span class="">
                          ${taskCaseId}
                      </span>
                </td>
                <td class="ar" >任务名称：</td>
                <td width="20%">
                      <span class="">
                          ${taskName}
                      </span>
                </td>
                <td class="ar" width="13.3%">状态：</td>
                <td>
                  <span class="">
                      ${state}
                  </span>
                </td>
            </tr>
            <tr>
                <td class="ar" width="13.3%">开始时间：</td>
                <td>
                      <span class="">
                          ${kssj}
                      </span>
                </td>
                <td class="ar" width="13.3%">结束时间：</td>
                <td>
                      <span class="">
                          ${jssj}
                      </span>
                </td>
                <td class="ar">进度：</td>
                <td>
                  <span class="">
                      ${progress}
                  </span>
                </td>

            </tr>
            </tbody>
        </table>
    </fieldset>
</div>
<div class="result box-s" style="overflow: hidden;">
    <!--查询条件-->
    <div class="cxjg">
        <form name="skgjbsjgzsForm"style="margin-top:15px;">
            <fieldset>
                <legend class="cursor" onclick="show()" flag="false">
                    查询条件<i class="fa fa-angle-double-down down"></i><i class="fa fa-angle-double-up up" style="display: none"></i>
                </legend>
                <table style="display: none" class="cxtj-table">
                    <tbody>
                    <input type="hidden" name="taskId" value="${taskId}">
                    <tr>
                        <td colspan="6" style="height:20px;"></td>
                    </tr>
                    <tr>
                        <td class="ar" width="13.3%">类别参数：</td>
                        <td style="width:20%;">
                                <span class="textbox">
                                    <input name="objectValue" type="text" id="objectValue"/>
                                </span>
                        </td>
                    </tr>
                    <tr>
                        <td class="ar" width="13.3%">登记时间：</td>
                        <td width="20%">
                                <span class="textbox">
                                  <input id="djsjStart" name="djsjStart" class="Wdate" type="text" onkeypress="return checkDateFmt();" style="width:100%;" onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'djsjEnd\')}',dateFmt:'yyyy-MM-dd'})"/>
                                </span>
                        </td>
                        <td class="ar" width="13.3%">至：</td>
                        <td width="20%">
                                 <span class="textbox">
                                     <input id="djsjEnd" name="djsjEnd" class="Wdate" type="text" onkeypress="return checkDateFmt();" style="width:100%;" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'djsjStart\')}',dateFmt:'yyyy-MM-dd'})"/>
                                 </span>
                        </td>
                        <td style="width:13.3%;"></td>
                        <td style="width:20%;"></td>
                    </tr>
                    <tr>
                        <td colspan="6" style="text-align:center;">
                            <a class="btn_a btn_search" onclick="doQuery()">查询</a>
                            <a class="btn_a btn_empty" onclick="doClear()">清空</a>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </fieldset>
        </form>
        <legend>查询结果<span class="st-span total">0</span></legend>
    </div>
    <table class="easyui-datagrid" id="skgjbsjgzsTable" style="border-top:1px dashed #ccc;" width="100%">
    </table>
</div>
<!--表格设置面板-->
<div id="ryjgzs" class="easyui-dialog" title="人员" data-options="modal:true,closed:true,collapsible:false,minimizable:false,maximizable:false" style="width:520px;height:auto;padding:10px;display:none;">
    <div class='float-div'>
        <div class='xx-box'>
            <div class='lf-img' id="ryzp"></div>
            <div class='rg-xx'>
                <div class='xx-line'><span>姓名：<span class='xx-xm' id="ryName"></span></span><span style="margin-left:10px;">年龄：<span id="ryAge"></span></span><span class='c125'></span></div>
                <div class='xx-line'><i class='fa fa-id-card orange' title='身份证号'></i><span id="rySfzh"></span></div>
                <div class='xx-line'><i class='fa fa-user yellow' title='出生日期'></i><span id="ryCsrq"></span></div>
                <div class='xx-line'><i class='fa fa-phone' title='电话号码'></i><span id="ryDhhm"></span></div>
                <div class='xx-line'><i class='fa fa-commenting-o' title='IMSI'></i><span id="imsi"></span></div>
                <div class='xx-line'><i class='fa fa-map-marker green' title='现住址'></i><span id="ryAddress"></span></div>
                <div class='xx-line over'><span class='label'>前科人员</span><span class='label'>高危人员</span><span class='label'>刑侦关注人员</span></div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript" src="/js/jquery-1.11.3.js"></script>
<script type="text/javascript" src="/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/js/easyui-lang-zh_CN.js"></script>
<!--- 日期时间-->
<script type="text/javascript" src="/common/datepicker/WdatePicker.js"></script>
<script type="text/javascript" src="/common/datepicker/calendar.js"></script>
<script src="/common/layer/layer.js" type="text/javascript"></script>
<script type="text/javascript" src="/js/skgjbasjgzs.js"></script>
</body>
</html>