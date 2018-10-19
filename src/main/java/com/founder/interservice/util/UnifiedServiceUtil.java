package com.founder.interservice.util;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import unifiedservice.*;

import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBElement;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class UnifiedServiceUtil {
    /**
     * 调用新德汇接口工具类
     * @param bizCode
     * @param cjServiceId
     * @param params
     * @return
     */
    public static String sendRequest(String bizCode, String cjServiceId, LinkedHashMap<String, Object> params){
        String resultStr = null;
        try{
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
                return resultStr;
            }

            UnifiedService service = new UnifiedService();
            IUnifiedService iUnifiedService = service.getBasicHttpBindingIUnifiedService();
            ResponseMessage responseMessage = iUnifiedService.query(bizCode,cjServiceId,parameterList);

            Boolean status = responseMessage.isStatus();
            System.out.println("status ===== " + status);
            if(status){
                JAXBElement result = responseMessage.getResult();
                String valueXml = (String)result.getValue();
                //String valueXml = "<?xml version=\"1.0\" encoding=\"utf-8\"?><XZUWSResponse><BizCode>XDH002</BizCode><ServiceId>XDHDS0004</ServiceId><Status>True</Status><Message></Message><Result><Table Count=\"1\" Name=\"Table1\"><Others /><Columns><Column Type=\"string\">URL</Column></Columns><Rows><Row><Value>http://10.154.106.29:10081/Main/HomeNew?H5UqnlKXd2q4NdiHenzBUEw6egqekhISXhAhP9VAwkdpew8shn6TmuqYZOWyssGVnVZ0vqAcEzdEEemcuZXOOQiX1d%2b4OOrh</Value></Row></Rows></Table></Result></XZUWSResponse>";
                Document document = DocumentHelper.parseText(valueXml);
                Element rootEle = document.getRootElement();
                System.out.println(rootEle.getName());
                Element resultEle = rootEle.element("Result");
                Element tableEle = resultEle.element("Table");
                Element valueEle = tableEle.element("Rows").element("Row").element("Value");
                resultStr = valueEle.getTextTrim();
                System.out.println("resultStr ==================== " + resultStr);

            }
            return resultStr;
        }catch (Exception e){
            e.printStackTrace();
            return resultStr;
        }
    }

}
