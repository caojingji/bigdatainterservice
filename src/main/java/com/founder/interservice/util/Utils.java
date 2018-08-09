package com.founder.interservice.util;

import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Utils {

    /**
     * @param unitCode 用户单位代码
     * @return 根据用户单位代码，返回截取的单位长度
     */
    public static String getDwlikeByUnitCode(String unitCode) {
        if (!StringUtils.isEmpty(unitCode)) {
            if (unitCode.endsWith("0000000000")) {
                unitCode = unitCode.substring(0, 2);
            } else if (unitCode.endsWith("00000000")) {
                unitCode = unitCode.substring(0, 4);
            } else if (unitCode.endsWith("000000")) {
                unitCode = unitCode.substring(0, 6);
            } else if (unitCode.endsWith("0000")) {
                unitCode = unitCode.substring(0, 8);
            } else if (unitCode.endsWith("00")) {
                unitCode = unitCode.substring(0, 10);
            }
        }
        return unitCode;
    }

    public static String countryCode(String unitCode, String grade) {
       if (grade.equals("T") || grade.equals("S")){
           unitCode = unitCode.substring(0,2);
       }else if(grade.equals("D")){
           unitCode = unitCode.substring(0,4);
       }else if(grade.equals("X")){
           unitCode = unitCode.substring(0,6);
       }else if(grade.equals("K")){
           unitCode = unitCode.substring(0,8);
       }
        return unitCode;
    }

    //没有时分秒的时间转换
    public static Date StoDate(String num){
        Date date = null;
        try{
            if(num != null && !"".equals(num)){
                DateFormat fmt =new SimpleDateFormat("yyyy-MM-dd");
                date = fmt.parse(num);
            }
        }catch(Exception e){
            System.out.println("StoDate(A)方法执行异常："+e.getMessage());
        }
        return date;
    }
    //转换加上时分秒
    public static Date StoDateSFM(String num){
        Date date = null;
        try{
            if(num != null && !"".equals(num)){
                DateFormat fmt =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                date = fmt.parse(num);
            }
        }catch(Exception e){
            System.out.println("StoDate(A)方法执行异常："+e.getMessage());
        }
        return date;
    }

    //获取系统的前后n天时间
    public static String afterNDay(int n){
        DateFormat df=new SimpleDateFormat("yyyy-MM-dd");
        Calendar c=Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.DATE,n);
        Date d2=c.getTime();
        String date=df.format(d2);
        return date;
    }
    public static void writeRow(List<Object> row, BufferedWriter csvWriter) throws IOException {
        // 写入文件头部
        for (Object data : row) {
            StringBuffer sb = new StringBuffer();
            String rowStr = sb.append("\"").append(data).append("\",").toString();
            csvWriter.write(rowStr);
        }
        csvWriter.newLine();
    }
    public static void downFile(HttpServletResponse response, String filename,
                                String filepath) {
        try {
            File file = new File(filepath);
            if (file.exists()) {
                InputStream ins = new FileInputStream(filepath);
                BufferedInputStream bins = new BufferedInputStream(ins);// 放到缓冲流里面
                OutputStream outs = response.getOutputStream();// 获取文件输出IO流
                BufferedOutputStream bouts = new BufferedOutputStream(outs);
                response.setContentType("application/x-download");// 设置response内容的类型
                response.setHeader(
                        "Content-disposition",
                        "attachment;filename="
                                + URLEncoder.encode(filename, "UTF-8"));// 设置头部信息
                int bytesRead = 0;
                byte[] buffer = new byte[8192];
                // 开始向网络传输文件流
                while ((bytesRead = bins.read(buffer, 0, 8192)) != -1) {
                    bouts.write(buffer, 0, bytesRead);
                }
                bouts.flush();// 这里一定要调用flush()方法
                ins.close();
                bins.close();
                outs.close();
                bouts.close();
            } else {
                System.out.println("文件不存在，无法下载");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除单个文件
     *
     * @param fileName
     *            要删除的文件的文件名
     * @return 单个文件删除成功返回true，否则返回false
     */
    public static boolean deleteFile(String fileName) {
        File file = new File(fileName);
        // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
        if (file.exists() && file.isFile()) {
            if (file.delete()) {
                System.out.println("删除单个文件" + fileName + "成功！");
                return true;
            } else {
                System.out.println("删除单个文件" + fileName + "失败！");
                return false;
            }
        } else {
            System.out.println("删除单个文件失败：" + fileName + "不存在！");
            return false;
        }
    }
}
