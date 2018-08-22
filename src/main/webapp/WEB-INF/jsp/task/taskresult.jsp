<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.atg.com/taglibs/json" prefix="json" %>
<!doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <title>任务发送结果展示界面</title>
    <link rel="stylesheet" type="text/css" href="/css/bjzycx.css">
</head>
<body>
<div id="nipic_search">
        <div class="result" style="padding-bottom:30px;">
			<span class="">
				结果展示：
			</span>
            <pre id = "resultView" style = "width:100%;border-collapse: collapse;background-color: rgba(255,255,255,0.75);text-align:left;">
                ${resultVo }
            </pre>
        </div>
    </div>
</div>
<script type="text/javascript" src="/js/jquery-1.11.3.js"></script>
</body>
</html>