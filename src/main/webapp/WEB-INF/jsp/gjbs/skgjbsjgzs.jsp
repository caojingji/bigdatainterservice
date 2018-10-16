<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.atg.com/taglibs/json" prefix="json" %>
<!doctype html>
<html>
<head>
	<meta charset="UTF-8">
	<title> 时空轨迹碰撞结果展示</title>
	<link rel="stylesheet" type="text/css" href="/css/easyui-new.css">
	<link rel="stylesheet" type="text/css" href="/css/font-awesome.min.css">
	<link rel="stylesheet" type="text/css" href="/css/skgj.css">
	<style>
		.lf-img>img {position: inherit;}
	</style>
</head>
<body>
<div id="nipic_search">
	<div id="header">		
		<h5 class="h_title_bs"></h5>
	</div>	
	<!--内容-->
	<div id="content_box0" class="content_box">
		<!--结果 table-->
		<div class="result" style="padding-bottom:30px;">
			<div class="float-div">
				<div class="xx-box">
					<div class="lf-img"><img src="images/img1.jpg"/></div>
					<div class="rg-xx">
						<div class="xx-line"><span class="xx-xm">赵熙媛</span></div>
						<div class="xx-line"><i class="fa fa-id-card orange" title="身份证号"></i><span>5201131981010550425</span></div>
						<div class="xx-line"><i class="fa fa-user yellow" title="出生日期"></i><span>1981-01-5</span></div>
						<div class="xx-line"><i class="fa fa-phone" title="电话号码"></i><span>13996026536</span></div>
						<div class="xx-line"><i class="fa fa-map-marker green" title="住址"></i><span>武陵路191号4单元4-3</span></div>
						<div class="xx-line over">
							<span class="label">前科人员</span>
							<span class="label">高危人员</span>
							<span class="label">刑侦关注人员</span>
						</div>
					</div>
				</div>
			</div>
			<div class="float-div">
				<div class="xx-box">
					<div class="lf-img"><img src="images/img2.jpg"/></div>
					<div class="rg-xx">
						<div class="xx-line"><span class="xx-xm">梁书元</span></div>
						<div class="xx-line"><i class="fa fa-id-card orange" title="身份证号"></i><span>513001195003150218</span></div>
						<div class="xx-line"><i class="fa fa-user yellow" title="出生日期"></i><span>1950-03-15</span></div>
						<div class="xx-line"><i class="fa fa-phone" title="电话号码"></i><span>13996406726</span></div>
						<div class="xx-line"><i class="fa fa-map-marker green" title="住址"></i><span>花卉园东路46号2栋1单元1-4</span></div>
						<div class="xx-line over">
							<span class="label">前科人员</span>
							<span class="label">高危人员</span>
							<span class="label">刑侦关注人员</span>
						</div>
					</div>
				</div>
			</div>
			<div class="float-div">
				<div class="xx-box">
					<div class="lf-img"><img src="images/img3.jpg"/></div>
					<div class="rg-xx">
						<div class="xx-line"><span class="xx-xm">蒋冶凤</span></div>
						<div class="xx-line"><i class="fa fa-id-card orange" title="身份证号"></i><span>532623198106221127</span></div>
						<div class="xx-line"><i class="fa fa-user yellow" title="出生日期"></i><span>1981-06-22</span></div>
						<div class="xx-line"><i class="fa fa-phone" title="电话号码"></i><span>15223495131</span></div>
						<div class="xx-line"><i class="fa fa-map-marker green" title="住址"></i><span>云南省文山壮族苗族自治州</span></div>
						<div class="xx-line over">
							<span class="label">前科人员</span>
							<span class="label">高危人员</span>
							<span class="label">刑侦关注人员</span>
						</div>
					</div>
				</div>
			</div>
			<div class="float-div">
				<div class="xx-box">
					<div class="lf-img"><img src="images/img4.jpg"/></div>
					<div class="rg-xx">
						<div class="xx-line"><span class="xx-xm">湛弥</span></div>
						<div class="xx-line"><i class="fa fa-id-card orange" title="身份证号"></i><span>6533214565111415521414558</span></div>
						<div class="xx-line"><i class="fa fa-user yellow" title="出生日期"></i><span>1998-08-13</span></div>
						<div class="xx-line"><i class="fa fa-phone" title="电话号码"></i><span>18197742393</span></div>
						<div class="xx-line"><i class="fa fa-map-marker green" title="住址"></i><span>黄杨路10号</span></div>
						<div class="xx-line over">
							<span class="label">前科人员</span>
							<span class="label">高危人员</span>
							<span class="label">刑侦关注人员</span>
						</div>
					</div>
				</div>
			</div>
			<div class="float-div">
				<div class="xx-box">
					<div class="lf-img"><img src="images/img5.jpg"/></div>
					<div class="rg-xx">
						<div class="xx-line"><span class="xx-xm">肉籽古丽汗▪图尔图尔图尔</span></div>
						<div class="xx-line"><i class="fa fa-id-card orange" title="身份证号"></i><span>6533214565111415521414558</span></div>
						<div class="xx-line"><i class="fa fa-user yellow" title="出生日期"></i><span>1998-08-13</span></div>
						<div class="xx-line"><i class="fa fa-phone" title="电话号码"></i><span>18197742393</span></div>
						<div class="xx-line"><i class="fa fa-map-marker green" title="住址"></i><span>新疆墨玉县奎雅乡喀克勒克村3组22号</span></div>
						<div class="xx-line over">
							<span class="label">前科人员</span>
							<span class="label">高危人员</span>
							<span class="label">刑侦关注人员</span>
						</div>
					</div>
				</div>
			</div>
			<div class="float-div">
				<div class="xx-box">
					<div class="lf-img"><img src="images/img6.jpg"/></div>
					<div class="rg-xx">
						<div class="xx-line"><span class="xx-xm">肉籽古丽汗▪图尔图尔图尔</span></div>
						<div class="xx-line"><i class="fa fa-id-card orange" title="身份证号"></i><span>6533214565111415521414558</span></div>
						<div class="xx-line"><i class="fa fa-user yellow" title="出生日期"></i><span>1998-08-13</span></div>
						<div class="xx-line"><i class="fa fa-phone" title="电话号码"></i><span>18197742393</span></div>
						<div class="xx-line"><i class="fa fa-map-marker green" title="住址"></i><span>新疆墨玉县奎雅乡喀克勒克村3组22号</span></div>
						<div class="xx-line over">
							<span class="label">前科人员</span>
							<span class="label">高危人员</span>
							<span class="label">刑侦关注人员</span>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript" src="js/jquery-1.11.3.js"></script>
<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="js/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="js/jquery.hover3d.min.js"></script>
<script>
$(function(){
	$(".float-div").hover3d({
		selector: ".xx-box",
		shine: true,
		sensitivity: 20,
	});
});
</script>
</body>
</html>