$("#content_box1").hide();
$("#menu li").click(function(){
    if(!$(this).hasClass("act")){
        if($(this).index()==1){
            if($(this).attr("flag") == "false"){
                jq22.init(taskId,"02");
                $(this).attr("flag","true");
            }
        }
        $("#menu li").removeClass( );
        $("#menu li span").removeClass("bot_triangle");
        $(this).addClass("act");
        $(this).find("span").addClass("bot_triangle");
        var ind=$(this).index();
        $(".content_box").each(function(){
            $(this).hide();
        });
        $("#content_box"+ind).show();
    }
});
$(".content_box").css("minHeight",$(window).height()-174);
$(window).resize(function() {
    $(".content_box").css("minHeight",$(window).height()-174);
})
var jq22 = {
    _default:6, //默认显示个数
    _loading:6, //每次点击按钮后加载的个数
    page_sj:0, //页码
    page_cph:0,
    init:function(taskId,type){
        var param = {
            "page":0,
            "size":6,
            "taskId":taskId,
            "objectType":type
        }
        $.ajax({
            type:"POST",
            data:param,
            url:"/getTaskResults",
            beforeSend:beforeSendFn(type,true),
            success:function(data) {
                if("01" == type){
                    $("#content_box0 div.more").show();
                    $("#content_box0 ul.list").hide();
                }else if("02" == type){
                    $("#content_box1 div.more").show();
                    $("#content_box1 ul.list").hide();
                }
                if (null != data && data.length > 0) {
                    for (var i = 0; i < data.length; i++) {
                        var ryzpStr = "";
                        if (null != data[i].ryzp && "" != data[i].ryzp) {
                            ryzpStr = "<img src=\"data:image/gif;base64," + data[i].ryzp + "\"/>";
                        } else {
                            ryzpStr = "<img src=\"/images/timg.jpg\" style = \"height:121px;width:100px;\"/>";
                        }
                        if ("01" == type) {
                            $("#content_box0 div[class='result']").append("<div class='float-div'>" +
                                "<div class='xx-box'>" +
                                "<div class='lf-img'>" + ryzpStr + "</div>" +
                                "<div class='rg-xx'>" +
                                "<div class='xx-line'><span class='xx-xm'>" + data[i].name + "</span><span>" + data[i].age + "</span><i class='fa fa-external-link'></i><i class='fa fa-folder-open-o'></i><span class='c125'></span></div>" +
                                "<div class='xx-line'><i class='fa fa-id-card orange' title='身份证号'></i><span>" + data[i].zjhm + "</span></div>" +
                                "<div class='xx-line'><i class='fa fa-user yellow' title='出生日期'></i><span>" + data[i].csrq + "</span></div>" +
                                "<div class='xx-line'><i class='fa fa-phone' title='电话号码'></i><span>" + data[i].sjhm + "</span></div>" +
                                "<div class='xx-line'><i class='fa fa-map-marker green' title='现住址'></i><span>" + data[i].xzzDzmc + "</span></div>" +
                                "<div class='xx-line over'><span class='label'>前科人员</span><span class='label'>高危人员</span><span class='label'>刑侦关注人员</span></div>" +
                                "</div>" +
                                "</div>" +
                                "</div>");
                        } else if ("02" == type) {
                            $("#content_box1 div[class='result']").append("<div class=\"float-div\">\n" +
                                "                            <div class=\"xx-box\" style=\"height:168px;\">\n" +
                                "                                        <div class=\"lf-img\">" + ryzpStr + "</div>\n" +
                                "                                <div class=\"rg-xx\">\n" +
                                "                                    <div class=\"top-div\" style=\"padding-top:16px;\">\n" +
                                "                                        <div class=\"xx-line\"><i class=\"orange\">车牌号：</i><span>" + data[i].objectValue + "</span></div>\n" +
                                "                                        <div class=\"xx-line\"><i class=\"orange\">车辆类型：</i><span>" + data[i].objectTypeName + "</span></div>\n" +
                                "                                    </div>\n" +
                                "                                    <div class=\"bottom-div\">\n" +
                                "                                        <div class=\"xx-line\"><i class=\"green\">证件类型：</i><span>" + data[i].zjlx + "</span></div>\n" +
                                "                                        <div class=\"xx-line\"><i class=\"green\">证件号码：</i><span>" + data[i].zjhm + "</span></div>\n" +
                                "                                    </div>\n" +
                                "                                </div>\n" +
                                "                            </div>\n" +
                                "                        </div>");
                        }
                    }
                    if ("01" == type) {
                        jq22.page_sj = jq22.page_sj + 1;
                    } else if ("02" == type) {
                        jq22.page_cph = jq22.page_cph + 1;
                    }
                } else {
                    if ("01" == type) {
                        $("#content_box0 div.result").append("<div style = \"text-align: center;clear: both;color: #f11f05;height:100%;\"><img src='/images/nodata.png' class='nodata'/></div>");
                        $("#content_box0 div.more").hide();
                    } else {
                        $("#content_box1 div.result").append("<div style = \"text-align: center;clear: both;color: #f11f05;height:100%;\"><img src='/images/nodata.png' class='nodata'/></div>");
                        $("#content_box1 div.more").hide();
                    }
                }
            }
        });
    },
    loadMore:function(taskId,type){
        var page;
        if("01" == type){
            page = jq22.page_sj;
        }else if("02" == type){
            page = jq22.page_cph;
        }
        var param = {
            "page":page,
            "size":6,
            "taskId":taskId,
            "objectType":type
        }
        $.ajax({
            type:"POST",
            data:param,
            url:"/getTaskResults",
            beforeSend:beforeSendFn(type,false),
            success:function(data) {
                if("01" == type){
                    $("#content_box0 div.more").show();
                    $("#content_box0 ul.list").hide();
                }else if("02" == type){
                    $("#content_box1 div.more").show();
                    $("#content_box1 ul.list").hide();
                }
                if (null != data && data.length > 0) {
                    for (var i = 0; i < data.length; i++) {
                        var ryzpStr = "";
                        if (null != data[i].ryzp && "" != data[i].ryzp) {
                            ryzpStr = "<img src=\"data:image/gif;base64," + data[i].ryzp + "\"/>";
                        } else {
                            ryzpStr = "<img src=\"/images/timg.jpg\" style = \"height:121px;width:100px;\"/>";
                        }
                        if ("01" == type) {
                            $("#content_box0 div[class='result']").append("<div class='float-div'>" +
                                "<div class='xx-box'>" +
                                "<div class='lf-img'>" + ryzpStr + "</div>" +
                                "<div class='rg-xx'>" +
                                "<div class='xx-line'><span class='xx-xm'>" + data[i].name + "</span><span>" + data[i].age + "</span><i class='fa fa-external-link'></i><i class='fa fa-folder-open-o'></i><span class='c125'></span></div>" +
                                "<div class='xx-line'><i class='fa fa-id-card orange' title='身份证号'></i><span>" + data[i].zjhm + "</span></div>" +
                                "<div class='xx-line'><i class='fa fa-user yellow' title='出生日期'></i><span>" + data[i].csrq + "</span></div>" +
                                "<div class='xx-line'><i class='fa fa-phone' title='电话号码'></i><span>" + data[i].sjhm + "</span></div>" +
                                "<div class='xx-line'><i class='fa fa-map-marker green' title='现住址'></i><span>" + data[i].xzzDzmc + "</span></div>" +
                                "<div class='xx-line over'><span class='label'>前科人员</span><span class='label'>高危人员</span><span class='label'>刑侦关注人员</span></div>" +
                                "</div>" +
                                "</div>" +
                                "</div>");
                        } else if ("02" == type) {
                            $("#content_box1 div[class='result']").append("<div class=\"float-div\">\n" +
                                "                            <div class=\"xx-box\" style=\"height:168px;\">\n" +
                                "                                        <div class=\"lf-img\">" + ryzpStr + "</div>\n" +
                                "                                <div class=\"rg-xx\">\n" +
                                "                                    <div class=\"top-div\" style=\"padding-top:16px;\">\n" +
                                "                                        <div class=\"xx-line\"><i class=\"orange\">车牌号：</i><span>" + data[i].objectValue + "</span></div>\n" +
                                "                                        <div class=\"xx-line\"><i class=\"orange\">车辆类型：</i><span>" + data[i].objectTypeName + "</span></div>\n" +
                                "                                    </div>\n" +
                                "                                    <div class=\"bottom-div\">\n" +
                                "                                        <div class=\"xx-line\"><i class=\"green\">证件类型：</i><span>" + data[i].zjlx + "</span></div>\n" +
                                "                                        <div class=\"xx-line\"><i class=\"green\">证件号码：</i><span>" + data[i].zjhm + "</span></div>\n" +
                                "                                    </div>\n" +
                                "                                </div>\n" +
                                "                            </div>\n" +
                                "                        </div>");
                        }
                    }
                    if ("01" == type) {
                        jq22.page_sj = jq22.page_sj + 1;
                    } else if ("02" == type) {
                        jq22.page_cph = jq22.page_cph + 1;
                    }
                } else {
                    if ("01" == type) {
                        $("#content_box0 div[class='result']").append("<div style = \"margin-left: 48%;clear: both;color: #f11f05;\">已加载全部数据</div>");
                        $("#content_box0 div.more").hide();
                    } else {
                        $("#content_box1 div[class='result']").append("<div style = \"margin-left: 48%;clear: both;color: #f11f05;\">已加载全部数据</div>");
                        $("#content_box1 div.more").hide();
                    }
                }
            }
        });
    }
}
function beforeSendFn(type,ifInit){
    if(ifInit){
        if("01" == type){
            $("#content_box0 ul.list").show();
        }else{
            $("#content_box1 ul.list").show();
        }
    }else{
        if("01" == type){
            $("#content_box0 div.more").hide();
            $("#content_box0 ul.list").show();

        }else{
            $("#content_box1 div.more").hide();
            $("#content_box1 ul.list").show();
        }
    }
}
