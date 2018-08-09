package com.founder.interservice.util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.HttpParams;

import java.io.*;
import java.util.*;
import java.util.Map.Entry;

public class HttpUtil {
	
	public static String doGet(String uri) {
		HttpClient httpclient = new DefaultHttpClient();
		
		// Prepare a request object
		HttpGet httpget = new HttpGet(uri);

		// Execute the request
		HttpResponse response = null;
		try {
			response = httpclient.execute(httpget);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		StringBuffer sb = new StringBuffer();
//		System.out.println(response.getStatusLine());
		HttpEntity entity = response.getEntity();
		if (entity != null) {
			InputStream instream = null;
			try {
				instream = entity.getContent();
				BufferedReader reader = new BufferedReader(new InputStreamReader(instream,"UTF-8"));
				String temp = null;
				while((temp = reader.readLine())!=null){
					sb.append(temp);
				}
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally{
//				httpget.abort();
//				httpclient.getConnectionManager().shutdown();
			}
		}
		httpclient.getConnectionManager().shutdown(); 
		return sb.toString();
	}
	
	public static String doGetEsb(String uri, String id) {
		HttpClient httpclient = new DefaultHttpClient();

		// Prepare a request object
		HttpGet httpget = new HttpGet(uri);

		// Execute the request
		HttpResponse response = null;
		StringBuffer sb = new StringBuffer();
		try {
			HttpParams params = httpclient.getParams();//new BasicHttpParams();
			Integer CONNECTION_TIMEOUT = 15 * 1000;
			Integer SO_TIMEOUT = 15 * 1000;
			Long CONN_MANAGER_TIMEOUT = 500L;
			params.setIntParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, CONNECTION_TIMEOUT);
			params.setIntParameter(CoreConnectionPNames.SO_TIMEOUT, SO_TIMEOUT);
			params.setLongParameter(ClientPNames.CONN_MANAGER_TIMEOUT, CONN_MANAGER_TIMEOUT);
			params.setBooleanParameter(CoreConnectionPNames.STALE_CONNECTION_CHECK, true);
			PoolingClientConnectionManager conMgr = new PoolingClientConnectionManager();
			conMgr.setMaxTotal(200);
			conMgr.setDefaultMaxPerRoute(conMgr.getMaxTotal());
			response = httpclient.execute(httpget);
//			System.out.println(response.getStatusLine());
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				InputStream instream = null;
				instream = entity.getContent();
				BufferedReader reader = new BufferedReader(new InputStreamReader(instream,"UTF-8"));
				String temp = null;
				while((temp = reader.readLine())!=null){
					sb.append(temp);
				}
			}
		} /*catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} */catch (Exception e) {
			//e.printStackTrace();
			sb.append("\"<root><resourceType id=\""+id+"\" count=\"0\"><timeAnalysisField></timeAnalysisField><data><record></record></data></resourceType></root>\"");
			//throw new BaseException("查询出错！uri = " + uri,e);
			System.err.println("查询出错:"+e.toString());
		}
		httpclient.getConnectionManager().shutdown(); 

		return sb.toString();
	}
	
	
	public static String doPost(String uri,Map<String,String> params) {
		HttpClient httpclient = new DefaultHttpClient();

		// Prepare a request object
		HttpPost httppost = new HttpPost(uri);
		httppost.setHeader("connection", "Keep-Alive");
		httppost.setHeader("accept", "*/*");
		if(params!=null&&params.size() > 0){
//			HttpParams hp = new BasicHttpParams();
			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
			Set<Entry<String, String>> set = params.entrySet();
			Iterator<Entry<String, String>> iterator = set.iterator();
			while(iterator.hasNext()){
				Entry<String, String> entry = iterator.next();
				NameValuePair nvp = new BasicNameValuePair(entry.getKey(), entry.getValue());
				nvps.add(nvp);
			}
			try {
				if(nvps.size() > 0)
				httppost.setEntity(new UrlEncodedFormEntity(nvps,"utf-8"));
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		// Execute the request
		HttpResponse response = null;
		try {
			response = httpclient.execute(httppost);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		StringBuffer sb = new StringBuffer();
//		System.out.println(response.getStatusLine());
		HttpEntity entity = response.getEntity();
		if (entity != null) {
			InputStream instream = null;
			try {
				instream = entity.getContent();
				BufferedReader reader = new BufferedReader(new InputStreamReader(instream,"UTF-8"));
				String temp = null;
				while((temp = reader.readLine())!=null){
					sb.append(temp);
				}
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		httpclient.getConnectionManager().shutdown(); 
		return sb.toString();
	}
	
	public static String doPost(String uri,String params) {
		HttpClient httpclient = new DefaultHttpClient();
		// Prepare a request object
		HttpPost httppost = new HttpPost(uri);
		httppost.setHeader("connection", "Keep-Alive");
		httppost.setHeader("accept", "*/*");
		if(params!=null&&!params.equals("")){
//				ContentType.create(ContentType.TEXT_XML, "utf-8");
//			HttpEntity entity = new StringEntity(params,ContentType.APPLICATION_FORM_URLENCODED);
			InputStream is = null;
			try {
				is = new ByteArrayInputStream(params.getBytes("utf-8"));
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			InputStreamEntity entity = new InputStreamEntity(is,-1);
			entity.setContentEncoding("utf-8");
			entity.setContentType("application/x-www-form-urlencoded");
			httppost.setEntity(entity);
		}
		
//		HttpConnectionParams.setConnectionTimeout(httpclient.getParams(), 1000);
//		HttpConnectionParams.setSoTimeout(httpclient.getParams(), 10000);
		// Execute the request
		HttpResponse response = null;
		try {
			response = httpclient.execute(httppost);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		StringBuffer sb = new StringBuffer();
		//System.out.println(response.getStatusLine());
		HttpEntity entity = response.getEntity();
		if (entity != null) {
			InputStream instream = null;
			try {
				instream = entity.getContent();
				BufferedReader reader = new BufferedReader(new InputStreamReader(instream,"UTF-8"));
				String temp = null;
				while((temp = reader.readLine())!=null){
					sb.append(temp);
				}
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} 
		}
		httpclient.getConnectionManager().shutdown(); 
		return sb.toString();
	}
	
	public static void main(String[] args) {
		try {
			Date date1=new Date();
			//号牌号码和号牌种类做条件，查询机动车信息
			//String  xml ="{\"user_name\":\"restful\",\"encrypt_flag\":1,\"notify_url\":\"notify89\",\"resource_id\":\"xxxx\",\"request_type\":\"type002\",\"service_code\":\"RestDataQueryRequestHandler\",\"service_name\":\"接口测试使用\",\"system_code\":\"DM\",\"user_password\":\"21218cca77804d2ba1922c33e0151105\",\"version\":\"1.0\",\"request_parameters\":{\"resourceList\":[{\"KSKFW\":0,\"timePlaceAnalysis\":\"\",\"resultColumns\":[\"HPHM\",\"SYR\",\"CSYS\",\"CLXH\",\"CLPP1\",\"CCRQ\",\"CCDJRQ\",\"ZZL\",\"ZQYZL\",\"RLZL\",\"PL\",\"ZT\",\"LXDH\",\"HPZL\",\"HDZZL\",\"HDZK\",\"FDJXH\",\"FDJH\",\"SFZMHM\",\"ZZXXDZ\"],\"HDCS\":0,\"havingConditionList\":\"\",\"whereCondition\":{\"compareCons\":[{\"compare\":\"=\",\"columnName\":\"HPHM\",\"columnValue\":\"A6E667\"},{\"compare\":\"=\",\"columnName\":\"HPZL\",\"columnValue\":\"02\"}],\"parentheseCons\":[],\"compare\":\"and\",\"conditionAlias\":\"\"},\"resourceCode\":\"130000312170212\",\"groupByNames\":[]}]}}";
			//所有人身份证号做条件，查询机动车信息
			//String xml ="{\"user_name\":\"restful\",\"encrypt_flag\":1,\"notify_url\":\"notify89\",\"resource_id\":\"xxxx\",\"request_type\":\"type002\",\"service_code\":\"RestDataQueryRequestHandler\",\"service_name\":\"接口测试使用\",\"system_code\":\"DM\",\"user_password\":\"21218cca77804d2ba1922c33e0151105\",\"version\":\"1.0\",\"request_parameters\":{\"resourceList\":[{\"KSKFW\":0,\"timePlaceAnalysis\":\"\",\"resultColumns\":[\"SYR\",\"HPHM\",\"SFZMHM\",\"HPZL\",\"CLPP1\",\"CLXH\",\"CLPP2\",\"GCJK\",\"ZZG\",\"ZZCMC\",\"CLSBDH\",\"FDJH\",\"CLLX\",\"CSYS\",\"SYXZ\",\"SFZMMC\",\"SYQ\",\"CCDJRQ\",\"DJRQ\",\"YXQZ\",\"QZBFQZ\",\"FZJG\",\"GLBM\",\"FPRQ\",\"FZRQ\",\"FDJRQ\",\"FHGZRQ\",\"BXZZRQ\",\"BPCS\",\"BZCS\",\"BDJCS\",\"DJZSBH\",\"ZDJZSHS\",\"DABH\",\"XZQH\",\"ZT\",\"DYBJ\",\"JBR\",\"CLLY\",\"LSH\",\"FDJXH\",\"RLZL\",\"PL\",\"GL\",\"ZXXS\",\"CWKC\",\"CWKK\",\"CWKG\",\"HXNBCD\",\"HXNBKD\",\"HXNBGD\",\"GBTHPS\",\"ZS\",\"ZJ\",\"QLJ\",\"HLJ\",\"LTGG\",\"LTS\",\"ZZL\",\"ZBZL\",\"HDZZL\",\"HDZK\",\"ZQYZL\",\"QPZK\",\"HPZK\",\"HBDBQK\",\"CCRQ\",\"HDFS\",\"LLPZ1\",\"PZBH1\",\"LLPZ2\",\"PZBH2\",\"XSDW\",\"XSJG\",\"XSRQ\",\"JKPZ\",\"JKPZHM\",\"HGZBH\",\"NSZM\",\"NSZMBH\",\"GXRQ\",\"XGZL\",\"QMBH\",\"HMBH\",\"BZ\",\"ZSXZQH\",\"ZSXXDZ\",\"YZBM1\",\"LXDH\",\"ZZZ\",\"ZZXZQH\",\"ZZXXDZ\",\"YZBM2\",\"ZDYZT\",\"YXH\",\"CYRY\",\"DPHGZBH\",\"SQDM\",\"CLYT\",\"YTSX\",\"DZYX\",\"XSZBH\",\"SJHM\",\"JYHGBZBH\",\"DWBH\",\"SYQSRQ\",\"YQJYQZBFQZ\",\"YQJYQZ2\",\"FDJGS\",\"SFYZHGN\",\"ZZJGLX\",\"WXMBC\",\"NCDQSY\",\"GAY_GXSJ\",\"WPBH\",\"XH\"],\"HDCS\":0,\"havingConditionList\":\"\",\"whereCondition\":{\"compareCons\":[{\"compare\":\"=\",\"columnName\":\"SFZMHM\",\"columnValue\":\"130106197105210624\"}],\"parentheseCons\":[],\"compare\":\"and\",\"conditionAlias\":\"\"},\"resourceCode\":\"130000312170212\",\"groupByNames\":[]}]}}";
			//以“车牌号”做条件，河北省MTC车辆通行记录表（唐山除外）
			String xml="{\"user_name\":\"restful\",\"encrypt_flag\":1,\"notify_url\":\"notify89\",\"resource_id\":\"xxxx\",\"request_type\":\"type002\",\"service_code\":\"RestDataQueryRequestHandler\",\"service_name\":\"接口测试使用\",\"system_code\":\"DM\",\"user_password\":\"21218cca77804d2ba1922c33e0151105\",\"version\":\"1.0\",\"request_parameters\":{\"resourceList\":[{\"KSKFW\":0,\"timePlaceAnalysis\":\"\",\"resultColumns\":[\"CAR_PLATE\",\"NET_NO\",\"EDTIME\"],\"HDCS\":0,\"havingConditionList\":\"\",\"whereCondition\":{\"compareCons\":[{\"compare\":\"=\",\"columnName\":\"CAR_PLATE\",\"columnValue\":\"冀A6E667\"}],\"parentheseCons\":[],\"compare\":\"and\",\"conditionAlias\":\"\"},\"resourceCode\":\"130000348010104\",\"groupByNames\":[]}]}}";
			
			//String xml="{\"user_name\":\"restful\",\"encrypt_flag\":1,\"notify_url\":\"notify89\",\"resource_id\":\"xxxx\",\"request_type\":\"type002\",\"service_code\":\"RestDataQueryRequestHandler\",\"service_name\":\"接口测试使用\",\"system_code\":\"DM\",\"user_password\":\"21218cca77804d2ba1922c33e0151105\",\"version\":\"1.0\",\"request_parameters\":{\"resourceList\":[{\"KSKFW\":0,\"timePlaceAnalysis\":\"\",\"resultColumns\":[\"NAME\",\"SEX\",\"ID\",\"BIRTHDAY\",\"M_CASE\",\"NATION\",\"CASECLASS\",\"NATIVE_PLACE\",\"RESIDADDR\",\"RESIDADDRALL\",\"ENDDATE\",\"DETAINDATE\",\"INDATE\",\"DETAINTYPE\",\"SENDER\",\"LINKMAN\",\"INLAWTEXT\",\"PZH\",\"CZDH\",\"M_DETAINTIME\",\"LOOKUPTYPE\",\"M_DBTIME\",\"M_COURTTIME\",\"M_JUDGETIME\",\"HANDLEDATE\",\"HANDLERSLT\",\"HANDLEBEGIN\",\"HANDLEEND\",\"RSLTTIME\",\"APPRSLT\",\"M_OUTWHERE\",\"OUTDATE\",\"M_OUTREASON\",\"ROOM_NO\",\"FREENUMBER\",\"M_OLDJAIL\",\"P_NO\",\"FINGER\",\"STAYREASON\",\"CONTROL\",\"M_PUNISH\",\"DANGERLEVEL\",\"M_CASEDEPT\",\"M_CASEMAN\",\"M_CASEPHONE\",\"M_DEPTTYPE\",\"M_CAREFUL\",\"FEEMONEY\",\"UNDERTAKER\",\"UNDERTAK_DATE\",\"REMARK\",\"RESUME\",\"OPERATOR\",\"OPTIME\",\"DRJSR\",\"DRJS_TIME\",\"HZ_NO\",\"HZ_OPER\",\"HZ_DATE\",\"VERSION\",\"YFH\",\"BED_NO\",\"WRITID\",\"AJBH\",\"RYBH1\",\"SYS_UNIT\",\"IDCHECK\",\"FWBH\",\"EXEC_DATE\",\"NAME_PY\",\"SLRY\",\"T_STATUS\",\"CERTTYPE\",\"P_KEY\",\"ADDRESSALL\",\"BYNAME\",\"ADDRESS\",\"NATIONALITY\",\"EDUCATION\",\"POLITICAL_AFF\",\"PROFESSION\",\"M_UNIT\",\"DUTY\",\"SPECIALTY\",\"PROSTYLE\",\"DIGNITY\",\"HEALTH\",\"HEIGHT\",\"WEIGHT\",\"FEATURE\",\"FOOTLEN\",\"SAMENUM\",\"SAMENO\",\"MEMBERTYPE\",\"PRECRIME\",\"M_HEAVY\",\"BQLX\",\"PICFILE\",\"DMQ1001\",\"DM_ADDRESSALL\",\"DM_ID\",\"GAY_GXSJ\",\"GAY_RKSJ\",\"IS_PROBLEM\",\"M_MARRIGE\",\"PY_BYNAME\",\"PY_NAME\",\"RYBH\"],\"HDCS\":0,\"havingConditionList\":\"\",\"whereCondition\":{\"compareCons\":[{\"compare\":\"=\",\"columnName\":\"ID\",\"columnValue\":\"130102197804172131\"}],\"parentheseCons\":[],\"compare\":\"and\",\"conditionAlias\":\"\"},\"resourceCode\":\"130000312130280\",\"groupByNames\":[]}]}}";
			String res =HttpUtil.doPost("http://10.24.101.218:8090/dsp/rest/ResourceQueryService/request", xml);
			Date date2=new Date();
			System.out.println(date2.getTime()-date1.getTime());
			//String res = HttpUtil.doGet("http://172.18.70.80:7001/EzSearchService/SearchTheme?page=1&maxrow=10&theme=GA000&keyword="+URLEncoder.encode("��", "utf-8"));

//			String res = HttpUtil.doGet("http://172.18.70.80:7001/EzSearchService/SearchMessage?theme=GA000&keyword="+URLEncoder.encode("��", "utf-8"));
			System.out.println(res);
			UrlEncodedFormEntity uef = null;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
