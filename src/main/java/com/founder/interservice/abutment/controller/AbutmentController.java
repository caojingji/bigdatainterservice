package com.founder.interservice.abutment.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dragonsoft.node.adapter.comm.RbspCall;
import com.dragonsoft.node.adapter.comm.RbspConsts;
import com.dragonsoft.node.adapter.comm.RbspService;
import com.founder.interservice.VO.ResultVO;
import com.founder.interservice.enums.ResultEnum;
import com.founder.interservice.exception.InterServiceException;
import com.founder.interservice.model.AutoTbStRy;
import com.founder.interservice.qgzyfw.domain.AbutmentConfig;
import com.founder.interservice.qgzyfw.domain.GabConfig;
import com.founder.interservice.querymodel.RelationLocalFilter;
import com.founder.interservice.service.IphoneTrackService;
import com.founder.interservice.splog.model.SpLog;
import com.founder.interservice.splog.service.SpLogService;
import com.founder.interservice.util.*;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * @ClassName： AbutmentController
 * @Auther： 曹鹏
 * @Description: java类作用描述
 * @CreateDate： 2018-09-13 10:07
 * @Version: 1.0
 */
@Controller
@CrossOrigin //跨域访问
public class AbutmentController {

    @Value(value = "${xdhbigdata.xdhserviceParam.bizCode}")
    private String bizCode;
    @Value(value = "${xdhbigdata.xdhserviceParam.cjServiceId}")
    private String cjServiceId;
    @Value(value = "${xdhbigdata.xdhserviceParam.getCjDataServiceId}")
    private String getCjDataServiceId;
    @Autowired
    private SpLogService spLogService;
    @Autowired
    private IphoneTrackService iphoneTrackService;
    @Autowired
    private AbutmentConfig abutmentConfig;
    /**
     * 调取四川机主信息
     * @return
     */
    @RequestMapping(value = "/getJwzhJzxx")
    @ResponseBody
    public JSONObject getJwzhJzxx(String sfzh,String yddh){
        JSONObject resultObj = new JSONObject();
        List<Map<String,Object>> resultList = new ArrayList<Map<String, Object>>();
        try{
            RbspService rbspService = new RbspService(abutmentConfig.getSenderId(),abutmentConfig.getServiceId());
            rbspService.setVersion("02");
            rbspService.setTimeOut("100");
            rbspService.setUserCardId(abutmentConfig.getUserCardId());
            rbspService.setUserDept(abutmentConfig.getUserDept());
            rbspService.setUserName("何沙");
            RbspCall call = rbspService.createCall();
            call.setUrl(abutmentConfig.getUrl());
            call.setMethod(RbspConsts.METHOD_PAGEQUERY);
            Map<String,Object> params = new HashMap<String,Object>();
            params.put("DataObjectCode", "A607_510000");
            params.put("InfoCodeMode", "0");
            if(!StringUtil.ckeckEmpty(sfzh)){
                params.put("Condition", "ZJHM = '"+ sfzh +"'");
            }
            if(!StringUtil.ckeckEmpty(yddh)){
                params.put("Condition", "YDDH = '"+ yddh +"'");
            }
            params.put("RequiredItems", new String[]{"XM","ZJHM","YDDH","RWSJ"});
            params.put("PageNum",1);
            params.put("RowsPerPage", 4);
            params.put("SortItems", new String[]{});
            String result = call.invoke(params);
            System.out.println("未处理前的查询结果====="+result);
            Map<String,Object> m = new HashMap<String, Object>();
            m = returnXmlMap(xmlStr2Document(result));
            resultList = (List<Map<String,Object>>)m.get("dataResult");
            System.out.println("处理后的查询结果====="+resultList);
            if(!resultList.isEmpty() && resultList != null){
                resultObj.put("code", ResultEnum.SUCCESS.getCode());
                resultObj.put("message",ResultEnum.SUCCESS.getMessage());
            }else {
                resultObj.put("code", ResultEnum.SUCCESS.getCode());
                resultObj.put("message","无数据");
            }
            resultObj.put("dataList",resultList);
         }catch (Exception e){
            resultObj.put("code", ResultEnum.RESULT_ERROR.getCode());
            resultObj.put("message",ResultEnum.RESULT_ERROR.getMessage());
            e.printStackTrace();
        }
        System.out.println("封装后返回前台的======"+ resultObj);
        return resultObj;
    }
    /**
     *
     * @Description: 获取新德惠采集的数据
     * @Param:
     * @param asjbh 案事件编号
     * @param sfzh 身份证号
     * @param type tb_xw_wffzkyry，tb_xw_fxzasyfwbzh，tb_xw_fxzasyjdc
     * @return: org.springframework.web.servlet.ModelAndView
     * @Author: cao peng
     * @date: 2018/9/13 0013-18:38
     */
    @RequestMapping(value = "/getCjData",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public JSONArray getCjData(String asjbh, String sfzh, String type){
        JSONArray jsonArray = null;
        try{
            LinkedHashMap<String, Object> params = new LinkedHashMap<>();
            params.put("asjbh", asjbh);
            params.put("sfzh", sfzh);
            params.put("type", type);
            String resultJson = UnifiedServiceUtil.sendRequest(bizCode,getCjDataServiceId,params);
            if(!StringUtil.ckeckEmpty(resultJson)){
                jsonArray = JSONArray.parseArray(resultJson);
                JSONArray jsonArrayNew = new JSONArray();//通过身份证号查找二代证信息后的JSONArray
                if (!"tb_xw_wffzkyry".equals(type)){
                    return jsonArray;
                }
                for (int i = 0 ;i<jsonArray.size();i++){
                    JSONObject json = jsonArray.getJSONObject(i);
                    JSONObject jsonNew = new JSONObject();
                    String sfzhStr = json.get("WFFZKYRY_CYZJ_ZJHM").toString();//获取身份证号
                    AutoTbStRy tbStRy = new Qgckzp().getQgckAllxxXml(sfzhStr);//通过身份证号码获取二代证信息
                    jsonNew.put("WFFZKYRY_HJDZ_DZMC",StringUtil.ckeckEmpty(json.getString("WFFZKYRY_HJDZ_DZMC"))?tbStRy.getCsdDzmc():json.get("WFFZKYRY_HJDZ_DZMC"));//户籍地址名称（出生地）
                    jsonNew.put("WFFZKYRY_CSRQ",StringUtil.ckeckEmpty(json.getString("WFFZKYRY_CSRQ"))?tbStRy.getCsrqRqgzxx():json.get("WFFZKYRY_CSRQ"));//出生日期
                    jsonNew.put("WFFZKYRY_XM",StringUtil.ckeckEmpty(json.getString("WFFZKYRY_XM"))?tbStRy.getXm():json.get("WFFZKYRY_XM"));//姓名
                    jsonNew.put("WFFZKYRY_CYZJ_ZJHM",StringUtil.ckeckEmpty(json.getString("WFFZKYRY_CYZJ_ZJHM"))?tbStRy.getCyzjZjhm():json.get("WFFZKYRY_CYZJ_ZJHM"));
                    jsonNew.put("WFFZKYRY_XZZ_DZMC",StringUtil.ckeckEmpty(json.getString("WFFZKYRY_XZZ_DZMC"))?tbStRy.getXzzDzmc():json.get("WFFZKYRY_XZZ_DZMC"));
                    String sex = "";
                    String imgJson = StringUtil.ckeckEmpty(tbStRy.getEdzzplj())?"":tbStRy.getEdzzplj();
                    String sexStr = StringUtil.ckeckEmpty(tbStRy.getXbdm())?"":tbStRy.getXbdm();
                    switch(sexStr){
                        default:
                            sex = "未知的性别";break;
                        case "0":
                            sex = "未知的性别";break;
                        case "1":
                            sex = "男";break;
                        case "2":
                            sex = "女";break;
                        case "9":
                            sex = "未说明的性别";break;
                    }
                    jsonNew.put("WFFZKYRY_XBDM",sex);
                    jsonNew.put("EDZZPLJ",imgJson);
                    jsonArrayNew.add(jsonNew);
                }
                return jsonArrayNew;
            }else{
                return new JSONArray();
            }
        }catch (Exception e){
            e.printStackTrace();
            return new JSONArray();
        }
    }


    /**
     *
     * @Description: 跳转到新德惠的采集界面
     * @Param:
     * @param asjbh 案事件编号
     * @param sfzh 身份证号
     * @param type
     * @param response
     * @return: void
     * @Author: cao peng
     * @date: 2018/9/13 0013-18:36
     */
    @RequestMapping(value = "toKyrycj",method = {RequestMethod.GET,RequestMethod.POST})
    public void toKyrycj(String asjbh, String sfzh, String type, HttpServletResponse response){
        try {
            LinkedHashMap<String, Object> params = new LinkedHashMap<>();
            params.put("sysname", type); //参数
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("AJBH", asjbh);
            jsonObject.put("SFZH", sfzh);
            jsonObject.put("VIEWTYPE", "3");
            params.put("sysparam", jsonObject.toJSONString()); //参数

            String url = UnifiedServiceUtil.sendRequest(bizCode,cjServiceId,params);
            if(!StringUtil.ckeckEmpty(url)){
                response.sendRedirect(url);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new InterServiceException(55,"发生未知错误！");
        }
    }

    /**
     * 跳转到新德汇审批界面
     * @param asjbh 案事件编号
     * @param sfzh 身份证号
     * @param bsh 标识号
     * @param response
     */
    @RequestMapping(value = "/toSpJsp",method = {RequestMethod.GET,RequestMethod.POST})
    public void toSpJsp(String asjbh, String sfzh,String bsh,String bshlxdm,String cxrXm,String cxrJh,String cxrLxdh,String dldwdm,String dldwmc,HttpServletResponse response){
        try{
            String bshlxmc = null;
            LinkedHashMap<String, Object> params = new LinkedHashMap<>();
            params.put("sysname", "HCZZ-SQSP"); //参数
            JSONObject jsonObject = new JSONObject();
            String sqbt = "查看网安数据申请：案事件编号为"+asjbh+";查看对象为"+bsh+";申请人身份证号为"+sfzh;
            jsonObject.put("ajbh", asjbh);
            jsonObject.put("sfzh", sfzh);
            jsonObject.put("sqdx", bsh);
            jsonObject.put("sqbt",sqbt);
            String level = null;
            RelationLocalFilter filter = new RelationLocalFilter();
            filter.setAsjbh(asjbh);
            filter.setSfzh(sfzh);
            filter.setJh(cxrJh);
            if(bshlxdm != null){
                switch (bshlxdm){
                    case "001":
                        bshlxmc = "手机号码";
                        filter.setPhone(bsh); break;
                    case "002":
                        bshlxmc = "QQ号码";
                        filter.setQq(bsh); break;
                    case "003":
                        bshlxmc = "微信ID";
                        filter.setWechat(bsh); break;
                    case "004":
                        bshlxmc = "身份证号";
                        filter.setIdcard(bsh); break;
                    case "005":
                        bshlxmc = "车牌号码";
                        filter.setCar(bsh); break;
                }
                boolean bol = iphoneTrackService.queryObjectRelationLocal(filter);
                if (bol){
                    level = "1";
                }else{
                    level = "2";
                }
            }else{
                level = "2";
            }
            jsonObject.put("level",level);
            jsonObject.put("sqly","智慧侦查脑图");
            params.put("sysparam", jsonObject.toJSONString()); //参数
            String url = UnifiedServiceUtil.sendRequest(bizCode,cjServiceId,params);
            if(!StringUtil.ckeckEmpty(url)){
                //添加审批日志
                SpLog spLog = new SpLog();
                spLog.setXxzjbh(KeyUtil.getUniqueKey("SPL"));
                spLog.setAsjbh(asjbh);
                spLog.setCqrSfzh(sfzh);
                spLog.setCqrXm(cxrXm);
                spLog.setCqrJh(cxrJh);
                spLog.setCqrLxdh(cxrLxdh);
                spLog.setDldwdm(dldwdm);
                spLog.setDldwmc(dldwmc);
                spLog.setSpbsh(bsh);
                spLog.setBshlxdm(bshlxdm);
                spLog.setBshlxmc(bshlxmc);
                spLog.setSpTitle(sqbt);
                spLog.setSpzt("2"); //正在审批
                spLogService.saveSpLog(spLog);
                //跳转
                response.sendRedirect(url);
            }
        }catch (Exception e){
            e.printStackTrace();
            throw new InterServiceException(55,"发生未知错误！");
        }
    }

    /**
     * 调用新德汇接口获取审批结果
     * @param asjbh
     * @param sfzh
     * @param bsh
     * @return
     */
    @RequestMapping(value = "/getSpResult",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public JSONObject getSpResult(String asjbh,String sfzh,String bsh){
        JSONObject resultObject = null;
        try{
            LinkedHashMap<String, Object> params = new LinkedHashMap<>();
            params.put("sysname", "HCZZ-SPJG"); //参数
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("ajbh", asjbh);
            jsonObject.put("sfzh", sfzh);
            jsonObject.put("sqdx", bsh);
            jsonObject.put("sqly","智慧侦查脑图");
            params.put("sysparam", jsonObject.toJSONString()); //参数
            String url = UnifiedServiceUtil.sendRequest(bizCode,cjServiceId,params);
            if(!StringUtil.ckeckEmpty(url)){
                String spjgStr = HttpUtil.doGet(url);
                resultObject = JSONObject.parseObject(spjgStr);
            }
            return resultObject;
        }catch(Exception e){
            e.printStackTrace();
            return resultObject;
        }
    }

    /**
     *
     * @param sfzh 调用者身份证号
     * @param phone 接受电话号码
     * @return
     */
    @RequestMapping(value = "/sendMessage",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public ResultVO sendMessage(String sfzh,String phone){
        ResultVO resultVO = null;
        try {
            //生成短信验证码
            String verifyCode = String
                    .valueOf(new Random().nextInt(899999) + 100000);
            String content = "您的验证码为："+ verifyCode+",该验证码有效时长为2分钟，请在一分钟内输入，且勿透漏给他人！【重庆刑专-智慧侦查脑图】";
            LinkedHashMap<String, Object> params = new LinkedHashMap<>();
            params.put("type", "SendMessage"); //参数
            params.put("sfzh", sfzh);
            params.put("SendMessage_phone", phone);
            params.put("SendMessage_content", content);
            params.put("SendMessage_sender", "重庆刑专-智慧侦查脑图");
            String url = UnifiedServiceUtil.sendRequest(bizCode,getCjDataServiceId,params);
            System.out.println("url=============" + url);
            JSONObject result = new JSONObject();
            if(!StringUtil.ckeckEmpty(url)){
                if("短信发送成功!".equals(url)){
                    result.put("code",verifyCode);
                    result.put("status",ResultEnum.SUCCESS.getCode());
                    result.put("message","短信发送成功！");
                }else{
                    result.put("code",null);
                    result.put("status",ResultEnum.TASK_SEND_ERROR.getCode());
                    result.put("message","短信发送失败！");
                }
            }else{
                result.put("code",null);
                result.put("status",ResultEnum.TASK_SEND_ERROR.getCode());
                result.put("message","短信发送失败！");
            }
            resultVO = ResultVOUtil.success(result);
        }catch (InterServiceException e){
            e.printStackTrace();
            resultVO = ResultVOUtil.error(ResultEnum.TASK_SEND_ERROR.getCode(),ResultEnum.TASK_SEND_ERROR.getMessage());
        }
        return resultVO;
    }

    /**
     * 将xml字符串转换为Document对象
     * @author zhouxiao
     * @param xmlStr
     * @return
     */
    public static Document xmlStr2Document(String xmlStr){
        Document document = null;
        try{
            document = DocumentHelper.parseText(xmlStr);
        }catch(Exception e){
            e.printStackTrace();
        }
        return document;
    }

    /**
     * 解析xml报文，并将结果展示到界面
     * @param doc
     * @return
     */
    public static Map returnXmlMap(Document doc){
        Map datamap = new HashMap();
        List pkgList = new ArrayList();//存表头
        List datalist = new ArrayList();//存数据
        Element rootElement = doc.getRootElement();
        Element methodElement = rootElement.element("Method");
        Element itemsElement = methodElement.element("Items");
        Element itemElement = itemsElement.element("Item");
        Element valueElement = itemElement.element("Value");
        List rowList  = valueElement.elements("Row");
        System.out.println("所有row信息====="+rowList);
        int account = rowList.size();//返回数据的条数(包含两个非所需结果的row节点)
        System.out.println("所有row信息总数====="+account);
        if(account > 2){
            System.out.println("查询是由数据的");
            datamap.put("account", String.valueOf((account-2)));//存放的是返回结果数量
            Element pkg = (Element)rowList.get(1);
            pkgList = dealDataTitle(pkg);//处理表头
            System.out.println("表头信息========"+pkgList);
            datalist = dealDataMatchTitle(rowList,pkg);//处理跟表头匹配的数据
            System.out.println("数据信息======="+datalist);
        }
        datamap.put("dataResult", datalist);
        datamap.put("title", pkgList);
        return datamap;
    }

    /**
     * 处理界面需要展示的表头
     * @param pkgs
     * @return
     */
    public static List<Map<String,Object>> dealDataTitle(Element pkgs){
        List<Map<String,Object>> dataList = pkgs.elements("Data");
        List<Map<String,Object>> pkgList = new ArrayList<Map<String,Object>>();
        for(int t=0;t<dataList.size();t++){
            Element pkg = (Element) dataList.get(t);
            String dataname = pkg.getText();
            Map<String, Object> commpkgmap = new HashMap<String, Object>();
            commpkgmap.put("field", dataname);
            commpkgmap.put("title", dataname);
            commpkgmap.put("align", "center");
            pkgList.add(commpkgmap);
        }
        return pkgList;
    }
    /**
     * 处理跟界面表头匹配的数据展示问题
     * @author zhouxiao
     * @param pkg
     * @return
     */
    public static List dealDataMatchTitle(List rowlist,Element pkg){
        List datalist = new ArrayList();
        for(int m=2;m<rowlist.size();m++){
            Element e = (Element) rowlist.get(m);
            List dataLists = e.elements("Data");
            Map<String,String> map = new HashMap<String,String>();
            List<Map<String,Object>> pkgLists = pkg.elements("Data");
            for(int j=0;j<pkgLists.size();j++){
                Element pkgElement = (Element) pkgLists.get(j);
                String pkgname = pkgElement.getText();
                for(int i=0;i<j+1;i++){
                    Element basicrow = (Element) dataLists.get(i);
                    String dataBasic = basicrow.getText();
                    map.put(pkgname, dataBasic);
                }
            }
            datalist.add(map);
        }
        return datalist;
    }

}
