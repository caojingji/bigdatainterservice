<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2018/11/13
  Time: 15:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>日志统计</title>
    <link rel="stylesheet" href="/logcss/bootstrap.min.css">
    <link rel="stylesheet" href="/logcss/WdatePicker.css">
    <link rel="stylesheet" href="/logcss/font-awesome.min.css">
    <link rel="stylesheet" href="/logcss/base_initialize.css">
    <link rel="stylesheet" href="/logcss/style.css">
    <style type="text/css" media="screen">
        .cxtj table input{background:none;}
        .left-list-ftitle span{top:0;}
    </style>
</head>
<body>
    <header class="heade-box">
        <img src="/images/logtj.png" class="header-title-img" alt="">
    </header>
    <aside class="left-menu-box">
        <ul class="left-list">
            <li id="B0501">
                <ul>
                    <span class="bot"></span>
                    <li class="left-list-ttitle"  id="B050101">
                        <a href="toLoginLog" class="menu" target="mainFrame">登录日志统计</a>
                    </li>
                    <li class="left-list-ttitle" id="B050102">
                        <a href="toRecordLog" class="menu" target="mainFrame">查询日志统计</a>
                    </li>
                    <li class="left-list-ttitle" id="B050103">
                        <a href="toSplog" class="menu" target="mainFrame">审批日志统计</a>
                    </li>

                </ul>
            </li>
        </ul>
    </aside>
</body>
</html>
