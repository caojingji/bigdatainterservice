package com.founder.interservice.util;

import java.util.Random;

public class KeyUtil {
    /**
     *
     * @Description: 生成唯一的主键 格式: 时间+随机数
     * @Param:
     * @return: java.lang.String
     * @Author: cao peng
     * @date: 2018/8/12 0012-23:14
     */
    public static synchronized String getUniqueKey(String str) {
        Random random = new Random();
        Integer number = random.nextInt(900000) + 100000;
        return str + System.currentTimeMillis()+String.valueOf(number);
    }


}
