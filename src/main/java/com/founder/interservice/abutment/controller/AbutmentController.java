package com.founder.interservice.abutment.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.founder.interservice.VO.ResultVO;
import com.founder.interservice.enums.ResultEnum;
import com.founder.interservice.exception.InterServiceException;
import com.founder.interservice.model.AutoTbStRy;
import com.founder.interservice.splog.model.SpLog;
import com.founder.interservice.splog.service.SpLogService;
import com.founder.interservice.util.*;
import org.apache.http.HttpResponse;
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
import unifiedservice.*;

import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBElement;
import java.io.IOException;
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
                    AutoTbStRy tbStRy = new Qgckzp().getQgckAllxxXml(sfzhStr);//通过身份证获取二代证信息
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
    public void toSpJsp(String asjbh, String sfzh, String bsh,String bshlxdm,String bshlxmc, HttpServletResponse response){
        try{

            LinkedHashMap<String, Object> params = new LinkedHashMap<>();
            params.put("sysname", "HCZZ-SQSP"); //参数
            JSONObject jsonObject = new JSONObject();
            String sqbt = "查看网安数据申请：案事件编号为"+asjbh+";查看对象为"+bsh+";申请人身份证号为"+sfzh;
            jsonObject.put("ajbh", asjbh);
            jsonObject.put("sfzh", sfzh);
            jsonObject.put("sqdx", bsh);
            jsonObject.put("sqbt",sqbt);
            params.put("sysparam", jsonObject.toJSONString()); //参数
            String url = UnifiedServiceUtil.sendRequest(bizCode,cjServiceId,params);
            if(!StringUtil.ckeckEmpty(url)){
                //添加审批日志
                SpLog spLog = new SpLog();
                spLog.setXxzjbh(KeyUtil.getUniqueKey("SPL"));
                spLog.setAsjbh(asjbh);
                spLog.setCqrSfzh(sfzh);
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
}
