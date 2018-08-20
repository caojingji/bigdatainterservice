<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.atg.com/taglibs/json" prefix="json" %>
<!doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <title>基站轨迹地图展示</title>
</head>
<body>
</div>
<script type="text/javascript" src="/js/jquery-1.11.3.js"></script>
<script type="text/javascript">
    $(function(){
        var paramStr = "${paramStr}";
        window.open("http://77.1.24.49:8081/lbs/?markers="+paramStr)
    });
</script>
</body>
</html>