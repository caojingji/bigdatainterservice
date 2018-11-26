$(function () {
	doQuery();
});

function show(){
    if($(".cursor").attr("flag")=="false"){
        $(".cxtj-table").slideDown('fast');
        $(".down").hide();
        $(".up").show();
        $(".cursor").attr("flag","true");
    }else{
        $(".cxtj-table").slideUp('fast');
        $(".down").show();
        $(".up").hide();
        $(".cursor").attr("flag","false");
    }
}
/**
 * 清除检索条件
 */
function doClear(){
    $("form input[type=text]:visible,form input[class='textbox-value']").val("");//将所有可见的input和class是textbox-value的input框 即代码选择框 的值清空
    $("input[name='objectType']").each(function() {
        this.checked = false;
    });
}
var table_title =[
    {title:"类别参数",field:"objectValue",align:'center',width:'25%'},
    {title:"类别代码",field:"objectType",align:'center',width:'15%'},
    {title:"类别名称",field:"objectTypeName",align:'center',width:'15%'},
    {title:"数量",field:"count",align:'center',width:'10%'},
    {title:"登记时间",field:"djsj",align:'center',width:'25%'},
    {title:"操作",field:"cz",align:'center',width:'10%',formatter:function(val,row,index){
        return getCzColumn(row);
    }},
];
function doQuery(){
    $('#skgjbsjgzsTable').datagrid({
        url: "/getTogetherTaskResultList",
        columns : [table_title],
        queryParams:serializeObject($("form[name='skgjbsjgzsForm']")),
        striped: true,
        fitColumns: true,
        singleSelect: true,
        pagination: true,
        nowrap: false, //设置是否换行  false换行 true表示不换行
        pageSize: 20,
        pageList: [10, 20, 50, 100, 150, 200],
        showFooter: true,
        selectOnCheck : false,
        checkOnSelect : false,
        pageNumber:1,
        loadFilter: function(data){
            $(".total").html(data.total);
            return data;
        }
    });
};

/**
 * 将表单序列化
 */
function serializeObject(form){
    var o={};
    $.each(form.serializeArray(),function(index){
        if(o[this['name'] ]){
            o[this['name'] ] = o[this['name'] ] + "," + this['value'];
        }else{
            o[this['name'] ]=this['value'];
        }
    })
    return o;
}

function getCzColumn(row){
    var str="<a href=\"javascript:;\" onclick=\"toSkgjbsjgzsDetail('"+row.taskId+"','"+row.objectType+"','"+row.objectValue+"');\" class=\"cz\"><span>结果显示</span></a>";
    return str;
}

function toSkgjbsjgzsDetail(taskId,objType,objValue){
    var param = {
        "imsi":objValue,
        "objType":objType
    }
    var index = layer.load(1, {
        content: '加载中',
        shade: [0.35,'#fff'],
        success: function(layero) {
            layero.find('.layui-layer-content').css({
                'padding-top': '40px',
                'width': '70px',
                'background-position-x': '2px'
            });
        }
    });
    $.ajax({
        type:"POST",
        data:param,
        url:"/queryTogetherTaskDetail",
        success:function(result) {
            layer.close(index);
            if(result){
                var data = result.data;
                var ryzpStr = "";
                if(data.ryzp){
                        ryzpStr = "<img src=\"data:image/gif;base64," + data.ryzp + "\"/>";
                }else {
                    ryzpStr = "<img src=\"/images/timg.jpg\" style = \"height:121px;width:100px;\"/>";
                }
                    var name = data.name==null?"":data.name;
                    var age =  data.age==null?"":data.age;
                    var zjhm = data.zjhm==null?"":data.zjhm;
                    var csrq = data.csrq==null?"":data.csrq;
                    var sjhm = data.sjhm==null?"":data.sjhm;
                    var xzzDzmc = data.xzzDzmc==null?"":data.xzzDzmc;
                    $("#ryzp").html(ryzpStr);
                    $("#ryName").text(name);
                    $("#ryAge").text(age);
                    $("#rySfzh").text(zjhm);
                    $("#ryCsrq").text(csrq);
                    $("#ryDhhm").text(sjhm);
                    $("#imsi").text(objValue);
                    $("#ryAddress").text(xzzDzmc);
                    openRyxx();
                }
        },
        error:function () {
        }
    });
}
function openRyxx(){
    $('#ryjgzs').dialog({
        buttons:[{
            //确定生成表头配置信息,并重新加载表格数据
            text:'确定',
            handler:function(){
                $('#ryjgzs').dialog('close');
                $(document).unbind("scroll");
            }
        },{
            text:'取消',
            handler:function(){
                $('#ryjgzs').dialog('close');
                $(document).unbind("scroll");
            }
        }],
        onClose: function () {
            $(document).unbind("scroll");
        }
    });
    $('#ryjgzs').show().dialog('open');
    $("#ryjgzs").window('center');
    var tops = $(document).scrollTop();//当页面滚动时，把当前距离赋值给页面，这样保持页面滚动条不动
    $(document).bind("scroll",function (){$(document).scrollTop(tops); });
}



