
/**
* 
* @Description: 清空值
* @Param:
    * @param null
* @return:
* @Author: cao peng
* @date: 2018/8/14 0014-18:56
*/
function clear(){
	$("#interName").get(0).selectedIndex = 0;
	$("#objType").get(0).selectedIndex = 0;
	$("#objValue").val('');
    $("#kssj").datebox("setValue","");
    $("#jssj").combo("setText","");
}

function queryJzgj(){
	var objType = $("#objType").val();
	var objValue = $("#objValue").val();
	var kssj = $("#kssj").val();
	var jssj = $("#jssj").val();
	var yhCate = "00";
	//访问controller调取数据
	var param = {"yhCate":yhCate,"objType":objType,"objValue":objValue,"kssj":kssj,"jssj":jssj};
	console.log("param ====" + JSON.stringify(param));
	$.post("/iphoneTrackForSjhm",param,function(result){
		$("#resultView").text(JSON.stringify(result,null,2));
	});

}

