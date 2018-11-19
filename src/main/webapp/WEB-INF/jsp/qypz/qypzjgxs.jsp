<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.atg.com/taglibs/json" prefix="json" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <title>区域碰撞结果展示</title>
    <link rel="stylesheet" type="text/css" href="/css/easyui-new.css">
    <link rel="stylesheet" type="text/css" href="/css/font-awesome.min.css">
    <link rel="stylesheet" type="text/css" href="/css/bjzycx.css">
    <link rel="stylesheet" type="text/css" href="/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="/css/base_initialize.css">
    <link rel="stylesheet" type="text/css" href="/css/style.css">
    <!--- 时间插件-->
    <link rel="stylesheet" href="/common/datepicker/skin/WdatePicker.css">
    <link rel="stylesheet" href="/common/datepicker/skin/default/datepicker.css">
</head>
<style>
    .float-div{float: left;margin:15px 0;width:100%;}
    .xx-box{width:89%;height:240px;margin:0 auto;background: #fff;position: relative;}
    .result{display: table;width:100%;}
    .lf-img{display: inline-block;width:30%;}
    .lf-img>img{width:98px;margin:0 10px;position: absolute;top:50%;margin-top:-60px;}
    .rg-xx{display: inline-block;width:64%;position:relative;z-index:1000000;}
    .xx-line>.xx-xm{font-weight:600;float:left;color:#2145ca;width:130px;overflow: hidden;white-space: nowrap;text-overflow: ellipsis;}
    .xx-line{width:100%;line-height: 35px;}
    .xx-line>span,.xx-line>i{margin:0 3px;color:#848080;cursor: pointer;}
    .xx-line>i{color:#848080;line-height: 35px;}
    .xx-line>.c125{float: right;color:#f11f05;}
    .xx-line{overflow: hidden;white-space: nowrap;text-overflow: ellipsis;}
    .xx-line>.green{color:#40ea0a;}
    .xx-line>.yellow{color:#eac710;}
    .xx-line>.orange{color:#ce4b22;}
    .xx-box:hover{box-shadow: 0 0 20px 0 rgba(0, 0, 0, 0.7);}
    .xx-line>.label{display: inline-block;margin:0 5px;padding:2px 5px;background: #e23e35;height: 20px;
        line-height: 20px;
        border-radius: 11px;
        font-size: 12px;color:#fff;}
    .over{    overflow: inherit;white-space: inherit;}
</style>
<body>
<header class="heade-box">
    <img src="images/title_qypzjgzs.png" class="header-title-img" alt="">
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
                <td class="ar" width="13.3%">任务名称：</td>
                <td width="20%">
                      <span class="">
                          ${taskName}
                      </span>
                </td>
                <td class="ar" width="13.3%">表达式：</td>
                <td width="20%">
                      <span class="">
                          ${expression}
                      </span>
                </td>
            </tr>
            <tr>
                <td class="ar">发起时间：</td>
                <td>
                      <span class="">
                          ${djsj}
                      </span>
                </td>
                <td class="ar">状态：</td>
                <td>
                  <span class="">
                      ${state}
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
    <!--查询结果-->
</div>
    <%--<div class="statistics">查询结果<span class="st-span total">0</span>
    </div>--%>
    <div class="result box-s" style="overflow: hidden;">
        <!--查询条件-->
        <div class="cxjg">
            <form name="qypzjgxsForm"style="margin-top:15px;">
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
                                    <input name="objValue" type="text" id="objValue"/>
                                </span>
                            </td>
                        </tr>
                        <tr>
                            <td class="ar" width="13.3%">登记日期：</td>
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
                            <td class="ar">类别分类：</td>
                            <td colspan="5" class="radil_td">
                                <input type="checkbox"  name="objType" value="558"  class="radio_inp"/>QQ
                                <input type="checkbox" name="objType" value="4615" class="radio_inp"/>微信ID
                                <input type="checkbox" name="objType" value="4314"  class="radio_inp"/>IMSI
                                <input type="checkbox" name="objType" value="4725"  class="radio_inp"/>热点MAC
                                <input type="checkbox" name="objType" value="4329"  class="radio_inp"/>MAC地址
                                <input type="checkbox" name="objType" value="6424"  class="radio_inp"/>汽车蓝色号牌
                                <input type="checkbox" name="objType" value="6423"  class="radio_inp"/>汽车黄色号牌
                                <input type="checkbox" name="objType" value="6422"  class="radio_inp"/>汽车白色号牌
                                <input type="checkbox" name="objType" value="7888"  class="radio_inp"/>摩托车黄色号码
                            </td>
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
        <table class="easyui-datagrid" id="qypzjgxsTable" style="border-top:1px dashed #ccc;" width="100%">
        </table>
    </div>
<!--表格设置面板-->
<div id="ryjgzs" class="easyui-dialog" title="人员" data-options="modal:true,closed:true,collapsible:false,minimizable:false,maximizable:false" style="width:700px;height:auto;padding:10px;display:none;">
    <div class='float-div'>
        <div class='xx-box'>
            <div class='lf-img' id="ryzp"></div>
            <div class='rg-xx'>
                <div class='xx-line'><span class='xx-xm' id="ryName"></span><span id="ryAge"></span><span class='c125'></span></div>
                <div class='xx-line'><i class='fa fa-id-card orange' title='身份证号'></i><span id="rySfzh"></span></div>
                <div class='xx-line'><i class='fa fa-user yellow' title='出生日期'></i><span id="ryCsrq"></span></div>
                <div class='xx-line'><i class='fa fa-phone' title='电话号码'></i><span id="ryDhhm"></span></div>
                <div class='xx-line'><i class='fa fa-phone' title='IMSI'></i><span id="imsi"></span></div>
                <div class='xx-line'><i class='fa fa-map-marker green' title='现住址'></i><span id="ryAddress"></span></div>
                <div class='xx-line over'><span class='label'>前科人员</span><span class='label'>高危人员</span><span class='label'>刑侦关注人员</span></div>
            </div>
        </div>
    </div>
</div>
<div id="clxxzs" class="easyui-dialog" title="车辆" data-options="modal:true,closed:true,collapsible:false,minimizable:false,maximizable:false" style="width:700px;height:auto;padding:10px;display:none;">
    <div class='float-div'>
        <div class="xx-box" style="height:168px;">
                       <div class="lf-img" id="clRyzp"></div>
                <div class="rg-xx">
                        <div class="top-div" style="padding-top:16px;">
                                <div class="xx-line"><i class="orange">车牌号：</i><span id="cph"></span></div>
                                <div class="xx-line"><i class="orange">车辆类型：</i><span id="cllx"></span></div>
                            </div>
                       <div class="bottom-div">
                               <div class="xx-line"><i class="green">证件类型：</i><span id="clZjlx"></span></div>
                                <div class="xx-line"><i class="green">证件号码：</i><span id="clZjhm"></span></div>
                            </div>
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
<script type="text/javascript" src="/js/qypzjgxs.js"></script>
</body>
</html>