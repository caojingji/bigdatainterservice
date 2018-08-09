/**   
 * @Title: StringUtil.java   
 * @Package com.highland.core.utils     
 */
package com.founder.interservice.util;

import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.Properties;
import java.util.Set;

public class StringUtil {
	public static Set keyset;
	public static Properties prop;
	static {
		prop = new Properties();
		try {
			prop.load(StringUtil.class.getClassLoader().getResourceAsStream("conf.properties"));
			keyset = (Set) prop.keySet();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * @param  code_name 代码表名称
	 * @param  code_value 代码表值
	 * @return 返回根据配置文件配置截取的大类兼容小类的新的字符串
	 */
	public static String getSqlUnderBig(String code_name,String code_value){
		String resultVal = null;
		String pattern = null;
		if(code_name != null && !"".equals(code_name)){
			if(keyset.contains(code_name.toUpperCase())){
				pattern = prop.getProperty(code_name);
			}
		}
		if (pattern != null && code_value != null && !"".equals(code_value)) {
			//pattern存在两种类型：（1）一种类型一种形式代码（2）一种类型多种形式代码，使用#分割
			code_value = code_value.trim();
			pattern = pattern.trim();
			if(pattern.indexOf("#") > 0){
				String array[] = pattern.split("#");
				for(int i = 0;i < array.length;i++){
					pattern = array[i];
					if(pattern != null){
						resultVal = TrimCodeZero(code_value,pattern);
						if(resultVal == null){
							continue;
						}else{
							break;
						}
					}
				}
			}else{
				resultVal = TrimCodeZero(code_value,pattern);
			}
		}else{
			resultVal = code_value;
		}
		return resultVal;
	}
	/**
	 * 函数功能： 去各类代码尾部的0
	 * @param code_value
	 * @param pattern。 pattern为代码的级别分组  比如单位代码 61，05，25，010，000，其pattern就是22233
	 * @return String  null表示模式的长度与code的长度不符
	 */
	static final public String TrimCodeZero(String code_value,String pattern) {
		int codeLength = code_value.length();
		int length = 0;
		int[] intArray = new int[pattern.length()];
		boolean flag = false;//不满足规则的，根据最后面的0确定截取方式
		String codetrimzero = "";

		for (int i = 0; i < pattern.length(); i++) {
			intArray[i] = Character.getNumericValue((int)pattern.charAt(i));
			if(intArray[i]==0){
				flag = true;
			}
			length += intArray[i];
		}
		if (length == codeLength){//正确的参数值
			String zeroStr = "000000000000";
			int lasthead = codeLength;
			int thishead = 0;
			for (int i = intArray.length - 1; i >= 0; i--) {
				thishead = lasthead - intArray[i];
				String tempStr = zeroStr.substring(0, intArray[i]);
				if (!code_value.substring(thishead, lasthead).equals(tempStr)) {
					codetrimzero = code_value.substring(0, lasthead);
					break;
				}
				lasthead = thishead;
			}
			if(codeLength == codetrimzero.length() && flag){//如果字符串未截取，且匹配字符串中有0，则右截0
				codetrimzero = trimRigthZero(code_value);
			}
		}else{
			codetrimzero = null;
		}
		return codetrimzero;
	}
	/**
	 * 函数功能：代码右截0
	 *
	 * @param code
	 *            ：待处理字符串
	 * @return：参数值
	 */
	static final public String trimRigthZero(String code) {
		String rs = code;
		for (int i = 0; i < code.length() - 1; i++) {
			if (rs.charAt(rs.length() - 1) == '0') {
				rs = rs.substring(0, rs.length() - 1);
			} else {
				break;
			}
		}
		return rs;
	}
	static final public String TrimDwZero(String code) {
		if(!StringUtils.isEmpty(code)){
		if (code.substring(2).equals("0000000000")) {//省
			code = code.substring(0, 2);
		} else {
			if (code.substring(4).equals("00000000")) {//市
				code = code.substring(0, 4);
			} else {
				if (code.substring(6).equals("000000")) {//县
					code = code.substring(0, 6);
				} else {
					if (code.substring(8).equals("0000")) {//直接从具体单位
						code = code.substring(0, 8);
					}
				}
			}
		}
		}
		return code;
	}

}
