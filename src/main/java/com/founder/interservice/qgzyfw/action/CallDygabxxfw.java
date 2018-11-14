package com.founder.interservice.qgzyfw.action;

import java.io.*;
import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.founder.interservice.qgzyfw.domain.*;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;


/**
 *<P>Copyright：Founder Copyright(c) 2018</P>
 *<P>Descrip：调用全国资源查询请求服务</P>
 *<P>Company：Founder</P>
 *<P>Author：zhouxiao</P>
 *<P>E-mail：zhou_xiao@founder.com.cn</P>
 *<P>Carate：2018-07-29 下午12:10:22</P>
 *<P>Version：1.0</P>
 */

public class CallDygabxxfw{
	public Map DataMap;

	//根据条件调取部级资源-主方法
	public Map getGabZyInfoByJyaq(String zylx,Object[] os,GabConfig gabConfig){//zylx:查询资源类型,os:参数
		String responseId = "";//联动服务方id
		String bizParamXml = "";//发送查询所需的xml字符串
		if("asj".equals(zylx)){
			TbStAsj tbst = new TbStAsj();
			responseId = gabConfig.getReceiverAsjId();
			bizParamXml = getParamXml(tbst,"GAB-BJXZ_XZASJCX",os);
		}else if("fzxyr".equals(zylx)){
			TbStFzxyr fzxyr = new TbStFzxyr();
			responseId = gabConfig.getReceiverFzxyrId();
			bizParamXml = getParamXml(fzxyr,"GAB-BJXZ_XZASJFZXYRCX",os);
		}else if("xzgzry".equals(zylx)){
			TbStXzgzry xzgzry = new TbStXzgzry();
			responseId = gabConfig.getReceiverXzgzryId();
			bizParamXml = getParamXml(xzgzry,"GAB-BJXZ_XZGWRYCX",os);
		}
		String resultStr = getResoursesData(gabConfig.getIp(),gabConfig.getSenderId(),responseId,bizParamXml);
		System.out.println("查询类别=="+zylx+"获取的resultStr="+resultStr);
		if(resultStr != null){
			resultStr = resultStr.substring(resultStr.indexOf("<Data>")+6,resultStr.indexOf("</Data>"));
			System.out.println("查询类别=="+zylx+"裁剪后的resultStr="+resultStr);
			DataMap = returnXmlMap(xmlStr2Document(resultStr),zylx);
			System.out.println("查询类别=="+zylx+"裁剪后的resultStr生成的DataMap="+DataMap);
		}
    	//String resultStr = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><RESULT><AMOUNT>12</AMOUNT><DATA><DATATYPE ID=\"BJXZ_XZASJCX\"><COLUMNS><COLUMN>ASJBH</COLUMN><COLUMN>ZCJDDM</COLUMN><COLUMN>AJLBDM</COLUMN><COLUMN>AJMC</COLUMN><COLUMN>ASJFSSJ_ASJFSKSSJ</COLUMN><COLUMN>LARQ</COLUMN><COLUMN>LADW_GAJGMC</COLUMN><COLUMN>JYAQ</COLUMN><COLUMN>SLDW_GAJGMC</COLUMN><COLUMN>SFSQ_PDBZ</COLUMN></COLUMNS><ROWS><ROW><ITEM>A12345601</ITEM><ITEM>0500</ITEM><ITEM>05000</ITEM><ITEM>张三被诈骗案</ITEM><ITEM>20170101</ITEM><ITEM>简要案情简要案情简要案情简要案情</ITEM><ITEM>陕西省公安厅</ITEM><ITEM>20170101</ITEM><ITEM>简要案情</ITEM><ITEM>简要案情</ITEM></ROW><ROW><ITEM>A12345665756867</ITEM><ITEM>0500</ITEM><ITEM>05000</ITEM><ITEM>李四被盗窃案</ITEM><ITEM>20170101</ITEM><ITEM>20170101</ITEM><ITEM>陕西省公安厅</ITEM><ITEM>20170101</ITEM><ITEM>陕西省公安厅</ITEM><ITEM>陕西省公安厅</ITEM></ROW><ROW><ITEM>A12345601</ITEM><ITEM>0500</ITEM><ITEM>05000</ITEM><ITEM>张三被诈骗案</ITEM><ITEM>20170101</ITEM><ITEM>简要案情简要案情简要案情简要案情</ITEM><ITEM>陕西省公安厅</ITEM><ITEM>20170101</ITEM><ITEM>简要案情</ITEM><ITEM>简要案情</ITEM></ROW><ROW><ITEM>A1234566575686704</ITEM><ITEM>0500</ITEM><ITEM>05000</ITEM><ITEM>李四被盗窃案</ITEM><ITEM>20170101</ITEM><ITEM>20170101</ITEM><ITEM>陕西省公安厅</ITEM><ITEM>20170101</ITEM><ITEM>陕西省公安厅</ITEM><ITEM>陕西省公安厅</ITEM></ROW><ROW><ITEM>A1234560102</ITEM><ITEM>0500</ITEM><ITEM>05000</ITEM><ITEM>张三被诈骗案</ITEM><ITEM>20170101</ITEM><ITEM>简要案情简要案情简要案情简要案情</ITEM><ITEM>陕西省公安厅</ITEM><ITEM>20170101</ITEM><ITEM>简要案情</ITEM><ITEM>简要案情</ITEM></ROW><ROW><ITEM>A1234566575686703</ITEM><ITEM>0500</ITEM><ITEM>05000</ITEM><ITEM>李四被盗窃案</ITEM><ITEM>20170101</ITEM><ITEM>20170101</ITEM><ITEM>陕西省公安厅</ITEM><ITEM>20170101</ITEM><ITEM>陕西省公安厅</ITEM><ITEM>陕西省公安厅</ITEM></ROW><ROW><ITEM>A1234560105</ITEM><ITEM>0500</ITEM><ITEM>05000</ITEM><ITEM>张三被诈骗案</ITEM><ITEM>20170101</ITEM><ITEM>简要案情简要案情简要案情简要案情</ITEM><ITEM>陕西省公安厅</ITEM><ITEM>20170101</ITEM><ITEM>简要案情</ITEM><ITEM>简要案情</ITEM></ROW><ROW><ITEM>A1234566575686706</ITEM><ITEM>0500</ITEM><ITEM>05000</ITEM><ITEM>李四被盗窃案</ITEM><ITEM>20170101</ITEM><ITEM>20170101</ITEM><ITEM>陕西省公安厅</ITEM><ITEM>20170101</ITEM><ITEM>陕西省公安厅</ITEM><ITEM>陕西省公安厅</ITEM></ROW><ROW><ITEM>A1234560107</ITEM><ITEM>0500</ITEM><ITEM>05000</ITEM><ITEM>张三被诈骗案</ITEM><ITEM>20170101</ITEM><ITEM>简要案情简要案情简要案情简要案情</ITEM><ITEM>陕西省公安厅</ITEM><ITEM>20170101</ITEM><ITEM>简要案情</ITEM><ITEM>简要案情</ITEM></ROW><ROW><ITEM>A1234566575686708</ITEM><ITEM>0500</ITEM><ITEM>05000</ITEM><ITEM>李四被盗窃案</ITEM><ITEM>20170101</ITEM><ITEM>20170101</ITEM><ITEM>陕西省公安厅</ITEM><ITEM>20170101</ITEM><ITEM>陕西省公安厅</ITEM><ITEM>陕西省公安厅</ITEM></ROW><ROW><ITEM>A12345601</ITEM><ITEM>0500</ITEM><ITEM>05000</ITEM><ITEM>张三被诈骗案</ITEM><ITEM>20170101</ITEM><ITEM>简要案情简要案情简要案情简要案情</ITEM><ITEM>陕西省公安厅</ITEM><ITEM>20170101</ITEM><ITEM>简要案情</ITEM><ITEM>简要案情</ITEM></ROW><ROW><ITEM>A12345665756867</ITEM><ITEM>0500</ITEM><ITEM>05000</ITEM><ITEM>李四被盗窃案</ITEM><ITEM>20170101</ITEM><ITEM>20170101</ITEM><ITEM>陕西省公安厅</ITEM><ITEM>20170101</ITEM><ITEM>陕西省公安厅</ITEM><ITEM>陕西省公安厅</ITEM></ROW></ROWS></DATATYPE></DATA></RESULT>";
    	DataMap = returnXmlMap(xmlStr2Document(resultStr),zylx);
    	System.out.println("查询类别=="+zylx+"未裁剪后的resultStr生成的DataMap=="+DataMap);
		return DataMap;
	}
	/**
	 * 调取全国的28个资源服务
	 * @param ip
	 * @param requestId
	 * @param responseId
	 * @param bizParamXml
	 * @return
	 */
	public static String getResoursesData(String ip,String requestId,String responseId,String bizParamXml){
		String res = "";
		String endpoint = "http://"+ip+"/axis/services/DYGABXXFW";//IP和端口号根据调用不同省份修改
		String sendId = requestId;//请求方ID
		String serviceId = responseId;//服务方ID
		try{
			Service service = new Service();
			Call call = (Call) service.createCall();
			call.setTargetEndpointAddress(new java.net.URL(endpoint));
			call.setOperationName("syncBizProcess");
			res = (String) call.invoke(new Object[]{sendId,serviceId,bizParamXml});
			System.out.println(res);
		}catch(Exception e){
			e.printStackTrace();
		}
		return res;
	}
	/**
	 * 通过反射机制生成xml字符串--全国案事件、全国犯罪人员、全国刑侦关注人员、在逃人员、撤销在逃人员等服务资源
	 * @param obj
	 * @param fwfbs
	 * @param os
	 * @author zhouxiao
	 * @return
	 */
	public static String getParamXml(Object obj,String fwfbs,Object[] os){
		DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		String bizParamXml = "";
		StringWriter sw = new StringWriter();//xml字符串
		Document document = DocumentHelper.createDocument();//创建document对象
		Element root = document.addElement("QUERY");//根节点
		Element fwfElement = root.addElement("FWFXZQH");//服务方行政区划
		fwfElement.setText("010000");
		Element fwfbzElement = root.addElement("FWFFWBZ");//服务方标识
		fwfbzElement.setText(fwfbs);
		Element packageid = root.addElement("TERM");// 服务方内容
		Field[] fields = obj.getClass().getFields();
		//<query>
		// <fwfxzqh>010000</fwfxzqh>
		//<fwffwbz>GAB-BJXZ_XZASJFZXYRCX</fwffwbz>
		//<term></term>
		// </query>
		String optType = "";//操作符说明
		for(int j=0;j<fields.length;j++){
			String sname = (String)fields[j].getName();//拿到属性名称
			String stype = fields[j].getType().toString();//拿到属性的类型
			//以下是为了拿到属性名对应的属性值
			for(int i=0;i<os.length;i++){
				String o = os[i].toString();
				JSONObject jsonobj = JSONObject.fromObject(o);
				if("class java.util.Date".equals(stype) || "class java.lang.Number".equals(stype)){
					Object objt1 = jsonobj.get(sname+"1");
					Object objt2 = jsonobj.get(sname+"2");
					if(objt1 != null &&  !"".equals(objt1)){
						Element commonElement = packageid.addElement("ITEM");
						commonElement.addAttribute("ID", sname);
						commonElement.addAttribute("OPT", "2");//2表示大于等于
						try {
							if("class java.util.Date".equals(stype)){
								commonElement.setText(formatter.format(format1.parse(objt1.toString()).getTime()));
							}else{
								commonElement.setText(objt1.toString());
							}
						} catch (ParseException e) {
							e.printStackTrace();
						}
					}
					if(objt2 != null  &&  !"".equals(objt2)){
						Element commonElement = packageid.addElement("ITEM");
						commonElement.addAttribute("ID", sname);
						commonElement.addAttribute("OPT", "3");//3表示小于等于
						try {
							if("class java.util.Date".equals(stype)){
								commonElement.setText(formatter.format(format1.parse(objt2.toString()).getTime()));
							}else{
								commonElement.setText(objt2.toString());
							}
						} catch (ParseException e) {
							e.printStackTrace();
						}
					}
				}else{
					Object svalue =jsonobj.get(sname);
					if(svalue != null  &&  !"".equals(svalue)){
						if("interface java.sql.Blob".equals(stype)){
							optType = "4";//4表示支持模糊查询
						}else if("class java.lang.String".equals(stype)){
							optType = "1";//1表示等于查询
						}else{
							optType = "5";//5表示多值查询
						}
						Element commonElement = packageid.addElement("ITEM");
						commonElement.addAttribute("ID", sname);
						commonElement.addAttribute("OPT", optType);
						commonElement.setText(svalue.toString());
					}
				}
			}
		}
		OutputFormat format = OutputFormat.createCompactFormat();// 缩进格式
		format.setEncoding("UTF-8");// 编码
		format.setTrimText(true);
		XMLWriter xmlwriter = new XMLWriter(sw);
		try {
			xmlwriter.write(document);
			xmlwriter.close();
			sw.close();
			bizParamXml = sw.toString();// 生成的xml字符串
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(bizParamXml);
		return bizParamXml;
	}
	//以下方法处理的是部级反馈的结果
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
	 * 解析部级反馈的xml报文，并将结果展示到界面
	 * @author zhouxiao
	 * @param doc
	 * @return
	 */
	public static Map returnXmlMap(Document doc,String zylxtype){
		Map datamap = new HashMap();//存放需要返回到前台的数据
		List pkgList = new ArrayList();//存表头
		List datalist = new ArrayList();//存数据
		Element rootElement = doc.getRootElement();
		Element accountElement = rootElement.element("AMOUNT");
		int account = Integer.parseInt(accountElement.getText());//返回数据的条数
		System.out.println("total==="+account);
		datamap.put("account", String.valueOf(account));//存放的是返回结果数量
		String id = "";//服务类型
		List pkgs = new ArrayList();
		List rowlist = new ArrayList();
		if(account > 0){
			Element dataElement = rootElement.element("DATA");
			Element datatypeElement  = dataElement.element("DATATYPE");
			id = datatypeElement.attributeValue("ID");//获取资源类型
			Element columelement = datatypeElement.element("COLUMNS");
			pkgs = columelement.elements("COLUMN");
			pkgList = dealDataTitle(pkgs,zylxtype);//处理表头
			Element rows = datatypeElement.element("ROWS");
			rowlist = rows.elements("ROW");
			datalist = dealDataMatchTitle(rowlist,pkgs,zylxtype);//处理跟表头匹配的数据
		}
		datamap.put("zylx", id);//存放资源类型
		datamap.put("dataResult", datalist);
		datamap.put("title", pkgList);
		return datamap;
	}
	/**
	 * 处理界面需要展示的表头
	 * @author zhouxiao
	 * @param pkgs
	 * @param xxtype
	 * @return
	 */
	public static List<Map<String,Object>> dealDataTitle(List<Map<String,String>> pkgs,String xxtype){
		List<Map<String,Object>> pkgList = new ArrayList<Map<String,Object>>();
		for(int t=0;t<pkgs.size();t++){
			Element pkg = (Element) pkgs.get(t);
			String dataname = pkg.getText();
			Map<String, Object> commpkgmap = new HashMap<String, Object>();
			commpkgmap.put("field", dataname);
			commpkgmap.put("title", dataname);//title的名字也是对应的字段属性，如果有特殊需要，可参照if中的写法
			commpkgmap.put("align", "center");
			pkgList.add(commpkgmap);

		}
		return pkgList;
	}
	/**
	 * 处理跟界面表头匹配的数据展示问题
	 * @author zhouxiao
	 * @param pkgs
	 * @param xxtype
	 * @return
	 */
	public static List dealDataMatchTitle(List rowlist,List<Map<String,String>> pkgs,String xxtype){
		List datalist = new ArrayList();
		for(int m=0;m<rowlist.size();m++){
			Element e = (Element) rowlist.get(m);
			List nodes = e.elements("ITEM");
			Map<String,String> map = new HashMap<String,String>();
			for(int j=0;j<pkgs.size();j++){
				Element pkg = (Element) pkgs.get(j);
				String dataname = pkg.getText();
				for(int i=0;i<j+1;i++){
					Element basicrow = (Element) nodes.get(i);
					String dataBasic = basicrow.getText();

					map.put(dataname, dataBasic);
				}
			}
			datalist.add(map);
		}
		return datalist;
	}


	public Map<String, String> getDataMap() {
		return DataMap;
	}
	public void setDataMap(Map<String, String> dataMap) {
		DataMap = dataMap;
	}
//	@Override
	public boolean hasPermission() {
		// TODO Auto-generated method stub
		return false;
	}
}
