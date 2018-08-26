package com.founder.interservice.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.founder.interservice.model.TbStRy;

import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class Qgckzp {
	private static final JSONArray esbMap = null;
	/**
	 * 根据证件号码 查取人员照片信息
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
			e.printStackTrace();
		}
		return zp64bite;
	}
	
	private String getConn(){
		String url="http://10.100.17.115:8080/NmgEsbQueryService/QueryLocalPersonForOther?conditions=sfzh=";
		return url;
	}
	/**
	 * 根据证件号码查取人员的二代证基本信息
	 * @param zjhm
	 * @return
	 */
	public TbStRy getQgckAllxxXml(String zjhm){
		TbStRy ryxx=new TbStRy();
		String esbServiceReturn="";
		String url=getConn()+"'"+zjhm+"'";
		HttpUtil hu=new HttpUtil();
		try {
			esbServiceReturn=hu.doGet(url);
		} catch (Exception e) {
			e.printStackTrace();
		}
		ryxx=getryJbxxxml(esbServiceReturn);
		return ryxx;
	}
	protected String getJsonString(String urlPath) throws Exception {
		URL url = new URL(urlPath);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.connect();
		InputStream inputStream = connection.getInputStream();
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
			    List<Element> element=dataElement.get(2).elements();
			    for(int i=0;i<element.size();i++){
				    resultStr=element.get(3).getTextTrim();
			    }
			}
	} catch (Exception e) {
		e.printStackTrace();
	}
	return resultStr;
}
	
  public static TbStRy getryJbxxxml(String esb_result){
	TbStRy resulryxx=new TbStRy();
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
					  if(dataElement.size() > 2){
						  List<Element> element =dataElement.get(2).elements();//�õ���Ա��Ӧֵ
						  String JGSSX=element.get(1).getTextTrim();
						  String XP=element.get(3).getTextTrim();
						  String SFZH=element.get(6).getTextTrim();
						  String XM=element.get(7).getTextTrim();
						  String MZ=element.get(8).getTextTrim();
						  String XB=element.get(9).getTextTrim();
						  String HKSZD=element.get(10).getTextTrim();
						  String CSDXZ=element.get(11).getTextTrim();
						  String CSRQ=element.get(13).getTextTrim();
						  String ZZXZ=element.get(14).getTextTrim();
						  resulryxx.setJgssxdm(JGSSX);
						  resulryxx.setEdzzplj(XP);
						  resulryxx.setXm(XM);
						  resulryxx.setXbdm(XB);
						  resulryxx.setMzdm(MZ);
						  resulryxx.setHjdzXzqhdm(HKSZD);
						  resulryxx.setCsdDzmc(CSDXZ);
						  resulryxx.setZjhm(SFZH);
						  if(CSRQ != null && !CSRQ.trim().isEmpty()){
							  resulryxx.setCsrqQsrq(fmt.parse(CSRQ));
						  }else{
							  resulryxx.setCsrqQsrq(null);
						  }
						  resulryxx.setXzzDzmc(ZZXZ);
					  }
			}

	} catch (Exception e) {
		e.printStackTrace();
	}
	return resulryxx;
}
}
