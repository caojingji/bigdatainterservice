package com.founder.interservice.abutment.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.founder.interservice.exception.InterServiceException;
import com.founder.interservice.util.StringUtil;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import unifiedservice.*;

import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBElement;
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
    private String getCjDateServiceId;
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
            System.out.println("params ============= " + params.toString());

            ObjectFactory objectFactory = new ObjectFactory();
            ArrayOfUnifiedServiceParameter parameterList = objectFactory.createArrayOfUnifiedServiceParameter();
            List<UnifiedServiceParameter> parameters = parameterList.getUnifiedServiceParameter();

            UnifiedServiceParameter  parameter = null;
            if(null != params && !params.isEmpty()){
                for (Map.Entry<String,Object> entry:params.entrySet()) {
                    parameter = objectFactory.createUnifiedServiceParameter();
                    parameter.setName(objectFactory.createUnifiedServiceParameterName(entry.getKey()));
                    parameter.setValue(objectFactory.createUnifiedServiceParameterValue(entry.getValue()));
                    parameters.add(parameter);
                }
            }else{
                return new JSONArray();
            }

            UnifiedService service = new UnifiedService();
            IUnifiedService iUnifiedService = service.getBasicHttpBindingIUnifiedService();
            ResponseMessage responseMessage = iUnifiedService.query(bizCode,getCjDateServiceId,parameterList);
            Boolean status = responseMessage.isStatus();
            if(status){
                JAXBElement result = responseMessage.getResult();
                String valueXml = (String)result.getValue();
                //String valueXml = "<?xml version=\"1.0\" encoding=\"utf-8\"?><XZUWSResponse><BizCode>XDH002</BizCode><ServiceId>XDHDS0004</ServiceId><Status>True</Status><Message></Message><Result><Table Count=\"1\" Name=\"Table1\"><Others /><Columns><Column Type=\"string\">URL</Column></Columns><Rows><Row><Value>http://10.154.106.29:10081/Main/HomeNew?H5UqnlKXd2q4NdiHenzBUEw6egqekhISXhAhP9VAwkdpew8shn6TmuqYZOWyssGVnVZ0vqAcEzdEEemcuZXOOQiX1d%2b4OOrh</Value></Row></Rows></Table></Result></XZUWSResponse>";
                System.out.println("valueXml ====================" + valueXml);
                Document document = DocumentHelper.parseText(valueXml);
                Element rootEle = document.getRootElement();
                System.out.println(rootEle.getName());
                Element resultEle = rootEle.element("Result");
                Element tableEle = resultEle.element("Table");
                Element valueEle = tableEle.element("Rows").element("Row").element("Value");
                String resultJson = valueEle.getTextTrim();
                if(!StringUtil.ckeckEmpty(resultJson)){
                    jsonArray = JSONArray.parseArray(resultJson);
                }else{
                    return new JSONArray();
                }
            }else{
                return new JSONArray();
            }
            return jsonArray;
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

            System.out.println("params ============= " + params.toString());

            ObjectFactory objectFactory = new ObjectFactory();
            ArrayOfUnifiedServiceParameter parameterList = objectFactory.createArrayOfUnifiedServiceParameter();
            List<UnifiedServiceParameter> parameters = parameterList.getUnifiedServiceParameter();

            UnifiedServiceParameter  parameter = null;
            if(null != params && !params.isEmpty()){
                for (Map.Entry<String,Object> entry:params.entrySet()) {
                    parameter = objectFactory.createUnifiedServiceParameter();
                    parameter.setName(objectFactory.createUnifiedServiceParameterName(entry.getKey()));
                    parameter.setValue(objectFactory.createUnifiedServiceParameterValue(entry.getValue()));
                    parameters.add(parameter);
                }
            }

            UnifiedService service = new UnifiedService();
            IUnifiedService iUnifiedService = service.getBasicHttpBindingIUnifiedService();
            ResponseMessage responseMessage = iUnifiedService.query(bizCode,cjServiceId,parameterList);

            Boolean status = responseMessage.isStatus();
            if(status){
                JAXBElement result = responseMessage.getResult();
                String valueXml = (String)result.getValue();
                //String valueXml = "<?xml version=\"1.0\" encoding=\"utf-8\"?><XZUWSResponse><BizCode>XDH002</BizCode><ServiceId>XDHDS0004</ServiceId><Status>True</Status><Message></Message><Result><Table Count=\"1\" Name=\"Table1\"><Others /><Columns><Column Type=\"string\">URL</Column></Columns><Rows><Row><Value>http://10.154.106.29:10081/Main/HomeNew?H5UqnlKXd2q4NdiHenzBUEw6egqekhISXhAhP9VAwkdpew8shn6TmuqYZOWyssGVnVZ0vqAcEzdEEemcuZXOOQiX1d%2b4OOrh</Value></Row></Rows></Table></Result></XZUWSResponse>";
                System.out.println("valueXml ====================" + valueXml);
                Document document = DocumentHelper.parseText(valueXml);
                Element rootEle = document.getRootElement();
                System.out.println(rootEle.getName());
                Element resultEle = rootEle.element("Result");
                Element tableEle = resultEle.element("Table");
                Element valueEle = tableEle.element("Rows").element("Row").element("Value");
                String url = valueEle.getTextTrim();
                System.out.println("url ==================== " + url);
                response.sendRedirect(url);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new InterServiceException(55,"发生未知错误！");
        }
    }
}
