
package com.founder.interservice.util;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Properties;

import com.alibaba.fastjson.JSONArray;
import com.founder.interservice.model.AutoTbStRy;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;


public class Qgckzp {
	private static final JSONArray esbMap = null;





	/**
	 * 根据ajhm查询人员照片信息
	 * @param zjhm
	 * @return
	 */
	public String getQgckZpXml(String zjhm){
		String zp64bite="";
		String esbServiceReturn="";
		String url=getConn()+"'"+zjhm+"'";
		HttpUtil hu=new HttpUtil();
		try {
			esbServiceReturn=hu.doGet(url);
			if(StringUtils.isNotEmpty(esbServiceReturn)){
				zp64bite=getxml(esbServiceReturn);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return zp64bite;
	}

	private String getConn(){
		String url="http://10.100.17.115:8080/NmgEsbQueryService/QueryLocalPersonForOther?conditions=sfzh=";
		/*Properties properties = new Properties();
		java.io.InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("jdbc.properties");
		try {
			properties.load(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		url = properties.getProperty("service.qgczrk.url");*/
		return url;
	}
	/**
	 * 根据ajhm查询人员的基本信息
	 * @param zjhm
	 * @return
	 */
	public AutoTbStRy getQgckAllxxXml(String zjhm){
		AutoTbStRy ryxx=new AutoTbStRy();
		String esbServiceReturn="";
		//String url="http://10.100.17.115:8080/NmgEsbQueryService/QueryLocalPersonForOther?conditions=sfzh='"+zjhm+"'";
		String url=getConn()+"'"+zjhm+"'";
		HttpUtil hu=new HttpUtil();
		try {
			esbServiceReturn=hu.doGet(url);
		} catch (Exception e) {
			// TODO: handle exception
		}
		ryxx=getryJbxxxml(esbServiceReturn);
		return ryxx;
	}

	protected String getJsonString(String urlPath) throws Exception {
		URL url = new URL(urlPath);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.connect();
		InputStream inputStream = connection.getInputStream();
		// 对应的字符编码转换
		Reader reader = new InputStreamReader(inputStream, "UTF-8");
		BufferedReader bufferedReader = new BufferedReader(reader);
		String str = null;
		StringBuffer sb = new StringBuffer();
		while ((str = bufferedReader.readLine()) != null) {
			sb.append(str);
		}
		reader.close();
		connection.disconnect();
		return sb.toString();
	}

	public static String getxml(String esb_result){
		String resultStr="";
	 /* esb_result = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"+
  "<RBSPMessage>"+
    "<Version/>"+
    "<ServiceID>S10-00000298</ServiceID>"+
    "<TimeStamp/>"+
    "<Validity/>"+
    "<Security>"+
        "<Signature Algorithm=\"\"/>"+
        "<CheckCode Algorithm=\"\"/>"+
        "<Encrypt/>"+
    "</Security>"+
    "<Method>"+
        "<Name>Query</Name>"+
        "<Items>"+
            "<Item>"+
                "<Value Type=\"arrayOfArrayOf_string\">"+
                    "<Row>"+
                        "<Data>000</Data>"+
                        "<Data/>"+
                        "<Data/>"+
                        "<Data/>"+
                        "<Data/>"+
                        "<Data/>"+
                        "<Data/>"+
                        "<Data/>"+
                        "<Data/>"+
                        "<Data/>"+
                        "<Data/>"+
                        "<Data/>"+
                        "<Data/>"+
                        "<Data/>"+
                        "<Data/>"+
                        "<Data/>"+
                    "</Row>"+
                    "<Row>"+
                        "<Data>CYM</Data>"+
                        "<Data>JGSSX</Data>"+
                        "<Data>WHCD</Data>"+
                        "<Data>XP</Data>"+
                        "<Data>HYZK</Data>"+
                        "<Data>BYQK</Data>"+
                        "<Data>SFZH</Data>"+
                        "<Data>XM</Data>"+
                        "<Data>MZ</Data>"+
                        "<Data>XB</Data>"+
                        "<Data>HKSZD</Data>"+
                        "<Data>CSDXZ</Data>"+
                        "<Data>FWCS</Data>"+
                        "<Data>CSRQ</Data>"+
                        "<Data>ZZXZ</Data>"+
                        "<Data>SG</Data>"+
                    "</Row>"+
                    "<Row>"+
                        "<Data/>"+
                        "<Data>150929</Data>"+
                        "<Data/>"+
                        "<Data>/9j/4AAQSkZJRgABAQEBXgFeAAD/2</Data>"+
                        "<Data/>"+
                        "<Data/>"+
                        "<Data>152634199205290614</Data>"+
                        "<Data>肖飞</Data>"+
                        "<Data>01</Data>"+
                        "<Data>1</Data>"+
                        "<Data>150929</Data>"+
                        "<Data>哈彦忽洞自然村</Data>"+
                        "<Data/>"+
                        "<Data>19920529</Data>"+
                        "<Data>内蒙古乌兰察布市四子王旗东八号乡哈彦忽洞自然村</Data>"+
                        "<Data/>"+
                    "</Row>"+
                "</Value>"+
            "</Item>"+
        "</Items>"+
    "</Method>"+
"</RBSPMessage>";*/
		Document document = null;
		try {
			document = DocumentHelper.parseText(esb_result);
			Element root = document.getRootElement();
			Element rmethedType = root.element("Method");
			Element itemsType = rmethedType.element("Items");
			Element itemType = itemsType.element("Item");
			Element data = itemType.element("Value");
			if(data!=null){
				List<Element> dataElement = data.elements();
				//for(int i=0;i<dataElement.size();i++){
				List<Element> element=dataElement.get(2).elements();
				for(int i=0;i<element.size();i++){
					resultStr=element.get(3).getTextTrim();
				}
				//esbMap.add(map);
				//}
			}
			//JSONArray ja = JSONArray.fromObject(esbMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultStr;
	}

	public static AutoTbStRy getryJbxxxml(String esb_result){
		AutoTbStRy resulryxx=new AutoTbStRy();
	  /*esb_result = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"+
  "<RBSPMessage>"+
    "<Version/>"+
    "<ServiceID>S10-00000298</ServiceID>"+
    "<TimeStamp/>"+
    "<Validity/>"+
    "<Security>"+
        "<Signature Algorithm=\"\"/>"+
        "<CheckCode Algorithm=\"\"/>"+
        "<Encrypt/>"+
    "</Security>"+
    "<Method>"+
        "<Name>Query</Name>"+
        "<Items>"+
            "<Item>"+
                "<Value Type=\"arrayOfArrayOf_string\">"+
                    "<Row>"+
                        "<Data>000</Data>"+
                        "<Data/>"+
                        "<Data/>"+
                        "<Data/>"+
                        "<Data/>"+
                        "<Data/>"+
                        "<Data/>"+
                        "<Data/>"+
                        "<Data/>"+
                        "<Data/>"+
                        "<Data/>"+
                        "<Data/>"+
                        "<Data/>"+
                        "<Data/>"+
                        "<Data/>"+
                        "<Data/>"+
                    "</Row>"+
                    "<Row>"+
                        "<Data>CYM</Data>"+
                        "<Data>JGSSX</Data>"+
                        "<Data>WHCD</Data>"+
                        "<Data>XP</Data>"+
                        "<Data>HYZK</Data>"+
                        "<Data>BYQK</Data>"+
                        "<Data>SFZH</Data>"+
                        "<Data>XM</Data>"+
                        "<Data>MZ</Data>"+
                        "<Data>XB</Data>"+
                        "<Data>HKSZD</Data>"+
                        "<Data>CSDXZ</Data>"+
                        "<Data>FWCS</Data>"+
                        "<Data>CSRQ</Data>"+
                        "<Data>ZZXZ</Data>"+
                        "<Data>SG</Data>"+
                    "</Row>"+
                    "<Row>"+
                        "<Data/>"+
                        "<Data>150929</Data>"+
                        "<Data/>"+
                        "<Data>/9j/4AAQSkZJRgABAQEBXgFeAAD/2</Data>"+
                        "<Data/>"+
                        "<Data/>"+
                        "<Data>152634199205290614</Data>"+
                        "<Data>肖飞</Data>"+
                        "<Data>01</Data>"+
                        "<Data>1</Data>"+
                        "<Data>150929</Data>"+
                        "<Data>哈彦忽洞自然村</Data>"+
                        "<Data/>"+
                        "<Data>19920529</Data>"+
                        "<Data>内蒙古乌兰察布市四子王旗东八号乡哈彦忽洞自然村</Data>"+
                        "<Data/>"+
                    "</Row>"+
                "</Value>"+
            "</Item>"+
        "</Items>"+
    "</Method>"+
"</RBSPMessage>";*/
		Document document = null;
		DateFormat fmt =new SimpleDateFormat("yyyyMMdd");
		try {
			document = DocumentHelper.parseText(esb_result);
			Element root = document.getRootElement();
			Element rmethedType = root.element("Method");
			Element itemsType = rmethedType.element("Items");
			Element itemType = itemsType.element("Item");
			Element data = itemType.element("Value");
			if(data!=null){
				List<Element> dataElement = data.elements();
				//for(int i=1;i<dataElement.size();i++){
				if(dataElement.size() > 2){
					List<Element> element =dataElement.get(2).elements();//得到人员对应值
					String JGSSX=element.get(1).getTextTrim();
					String XP=element.get(3).getTextTrim();
					String SFZH=element.get(6).getTextTrim();
					String XM=element.get(7).getTextTrim();
					String MZ=element.get(8).getTextTrim();
					String XB=element.get(9).getTextTrim();
					//内蒙调用常口返回的性别代码01 02 做处理
					String BZXB="";
					if(XB.length()>=2){
						 BZXB=(XB).replace("0","");
					}else {
						 BZXB=XB;
					}
					String HKSZD=element.get(10).getTextTrim();
					String CSDXZ=element.get(11).getTextTrim();
					String CSRQ=element.get(13).getTextTrim();
					String ZZXZ=element.get(14).getTextTrim();
					resulryxx.setJgdm(JGSSX);
					resulryxx.setEdzzplj(XP);
					resulryxx.setXm(XM);
					resulryxx.setMzdm(MZ);
					resulryxx.setXbdm(BZXB);
					resulryxx.setHjdzXzqhdm(HKSZD);
					resulryxx.setCsdDzmc(CSDXZ);
					resulryxx.setCyzjZjhm(SFZH);
					resulryxx.setCyzjCyzjdm("111");
					if(CSRQ != null && !CSRQ.trim().isEmpty()){
						resulryxx.setCsrqRqgzxx(fmt.parse(CSRQ));
					}else{
						resulryxx.setCsrqRqgzxx(null);
					}
					resulryxx.setXzzDzmc(ZZXZ);
				}
				//}
			}
			//JSONArray ja = JSONArray.fromObject(esbMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resulryxx;
	}




	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//getryJbxxxml("123");
	}

}
