/**   
 * @Title: StringUtil.java   
 * @Package com.highland.core.utils     
 */
package com.founder.interservice.util;

import com.founder.interservice.enums.ResultEnum;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {
	public static Set keyset;
	public static Properties prop;

	/**
	 * 函数功能：代码右截0
	 *
	 * @param code ：待处理字符串
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
		if (!StringUtils.isEmpty(code)) {
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

	/**
	 * 判断字符串是否为null或者空
	 *
	 * @param string 目标字符串
	 * @return
	 */
	public static boolean ckeckEmpty(String string) {
		return (string == null) || (string.length() == 0);
	}

	/**
	 * 验证手机号是否正确
	 *
	 * @param phone
	 * @return
	 */
	public static Map<String,Object> isPhone(String phone) {
		String regex = "^((13[0-9])|(14[5,7,9])|(15([0-3]|[5-9]))|(166)|(17[0,1,3,5,6,7,8])|(18[0-9])|(19[8|9]))\\d{8}$";
		Map<String,Object> rs = new HashMap<String,Object>();
		if (phone.length() != 11) {
			rs.put("code",ResultEnum.IPHONE_PARAM_ERROE.getCode());
			rs.put("msg",ResultEnum.IPHONE_PARAM_ERROE.getMessage());
		} else {
			Pattern pattern = Pattern.compile(regex);
			Matcher m = pattern.matcher(phone);
			boolean isMatch = m.matches();
			if (!isMatch) {
				rs.put("code",ResultEnum.IPHONE_PARAM_ERROR1.getCode());
				rs.put("msg",ResultEnum.IPHONE_PARAM_ERROR1.getMessage());
			} else {
				rs.put("code",ResultEnum.SUCCESS.getCode());
				rs.put("msg",ResultEnum.SUCCESS.getMessage());
			}
		}
		return rs;
	}

	/**
	 * @param qqStr QQ号码
	 * @Description: 验证QQ号码格式是否正确
	 * @Param:
	 * @return: java.lang.String
	 * @Author: cao peng
	 * @date: 2018/8/13 0013-11:35
	 */
	public static Map<String,Object> isQQ(String qqStr) {
		String regex = "^[1-9][0-9]{4,14}&";// 5到15位qq验证 第一位1-9之间的数字，第二位0-9之间的数字
		Map<String,Object> rs = new HashMap<String,Object>();
		boolean bol = qqStr.matches(regex);
		if (bol) {
			rs.put("code",ResultEnum.SUCCESS.getCode());
			rs.put("msg",ResultEnum.SUCCESS.getMessage());
		} else {
			rs.put("code",ResultEnum.QQ_PARAM_ERROR.getCode());
			rs.put("msg",ResultEnum.QQ_PARAM_ERROR.getMessage());
		}
		return rs;
	}
}
