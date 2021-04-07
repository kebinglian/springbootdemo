package com.kbldemo.system;

/**
 * Created by kbl on 2019/7/3.
 */

import lombok.extern.log4j.Log4j;
import org.springframework.util.StringUtils;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 功能描述：程序工具类，提供大量的便捷方法
 * 作者： elearning
 * 时间：2016/03/31.
 */
public class SystemCommUtil {
    /**
     * 产生一个36个字符的UUID(不带"-")
     *
     * @return UUID
     */
    public static String randomUUID() {
        String s = UUID.randomUUID().toString();
        return s.replace("-", "");
    }
    /**
     * 获取当前中文时间
     *
     * @return
     */
    public static String getChineseDateString() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
        String dateString = sdf.format(date);
        return dateString;
    }
    /**
     * 获取当前秒的时间戳
     *
     * @return
     */
    public static String getDateString() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSSS");
        String dateString = sdf.format(date);
        return dateString;
    }
    /**
     * 获取当前秒
     *
     * @return
     */
    public static String getDateString(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = sdf.format(date);
        return dateString;
    }
    /**
     * 获取当前日期的时间戳
     *
     * @return
     */
    public static String getDateStringForDate() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String dateString = sdf.format(date);
        return dateString;
    }
    /**
     * 获取当前昨天
     *
     * @return
     */
    public static String getYestodayDateString() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE,   -1);
        return new SimpleDateFormat( "yyyyMMdd").format(cal.getTime());
    }
    /**
     * 日期年的增减
     *
     * @return
     */
    public static Date getAddYearDateString(Date date,int year) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.YEAR,   year);
        return cal.getTime();
    }
    /**
     * 获取当前时间
     *
     * @return
     */
    public static Date getCurrentDate(){
       return new Date();
    }
    /**
     * 当前年
     *
     * @return
     */
    public static int getCurrentYear() {
        return Calendar.getInstance().get(Calendar.YEAR);
    }
    /**
     * 当前月份
     *
     * @return
     */
    public static int getCurrentMonth() {
        return Calendar.getInstance().get(Calendar.MONTH);
    }
    /**
     * 字符转日期
     *
     * @return
     */
    public static Date toDate(String dateString){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = format.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
    /**
     * 日期转字符
     *
     * @return
     */
    public static String dateToString(Date date,String dataType){
        if(!StringUtils.isEmpty(date)){
            SimpleDateFormat sdf = new SimpleDateFormat(dataType);
            String dateString = sdf.format(date);
            return dateString;
        }else{
            return null;
        }
    }
    /**
     * 获取项目路径
     *
     * @return
     */
    public static String getWebRootUrl(HttpServletRequest request) {
        return request.getSession().getServletContext().getRealPath("/");//获取web项目的路径
    }
    /**
     * 获取[]的参数
     *
     * @return
     */
    public static List<String> getParList(String str){
        List<String> stringList=new ArrayList<String>();
        if(!StringUtils.isEmpty(str)){
            String regEx = "(?=[\\s\\S]*?)(\\[([\\s\\S]*?)])(?=[\\s\\S]*?)";
            Pattern pat = Pattern.compile(regEx);
            Matcher mat = pat.matcher(str);
            while(mat.find()){
                String par=mat.group(1).toString();
                if(!stringList.contains(par)){
                    stringList.add(mat.group(1).toString());
                }
            }
        }
        return stringList;
    }
    /**
     * 是否为数字
     *
     * @return
     */
    public static boolean isNumber(String str) {
        if(StringUtils.isEmpty(str)){
            return false;
        }else {
            String regEx = "^[-]?[0-9]+(\\.[0-9]+)?$";
            Pattern pattern = Pattern.compile(regEx);
            Matcher matcher = pattern.matcher(str);
            return matcher.matches();
        }
    }
    /**
     * 字符转INT
     *
     * @return
     */
    public static Integer strToInteger(String str){
        if(isNumber(str)){
            return Integer.parseInt(str);
        }else{
            return null;
        }
    }
    /**
     * 字符转DOUBLE
     *
     * @return
     */
    public static Double strToDouble(String str){
        if(isNumber(str)){
            return Double.parseDouble(str);
        }else{
            return null;
        }
    }
    /**
     * 获取DOUBLE的默认小数保留
     *
     * @return
     */
    public static String getDefaultDoubleValue(Double df){
        return String.format("%.2f", df);
    }
    /**
     * 获取DOUBLE的小数保留
     *
     * @return
     */
    public static String getDoubleValue(Double df,int kepPoint){
        return String.format("%."+kepPoint+"f", df);
    }
    /**
     * 判断文件是否存在
     *
     * @return
     */
    public static boolean fileExists(String fileUrl) {
        File file=new File(fileUrl);
        boolean isExist=false;
        if (file.exists()) {
            isExist=true;
        } else {
            isExist=false;
        }
        return isExist;
    }
    /**
     * 判断文件夹是否存在,不存在就建立
     *
     * @return
     */
    public static void folderExists(String folderUrl) {
        File file=new File(folderUrl);
        if (file.exists()) {

        }else{
            file.mkdir();
        }
    }
    /**
     * 删除文件夹下的文件
     *
     * @return
     */
    public static void removeFolderFile(String fileUrl){
        File outFile = new File(fileUrl);
        if (outFile.isDirectory()) {
            String[] children = outFile.list();
            //递归删除目录中的子目录下
            for (int i=0; i<children.length; i++) {
                File newFile=new File(outFile, children[i]);
                newFile.delete();
            }
        }
    }
    /**
     * 日期天数的增减
     *
     * @return
     */
    public static Date addDateDay(Date curDate,int days) {
        SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
        Calendar date = Calendar.getInstance();
        date.setTime(curDate);
        date.set(Calendar.DATE, date.get(Calendar.DATE) + days);
        Date endDate = null;
        try {
            endDate = dft.parse(dft.format(date.getTime()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return endDate;
    }

    /**
     * 日期天数的增减小时
     *
     * @return
     */
    public static Date addDateHour(Date curDate,int hours) {
        SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar date = Calendar.getInstance();
        date.setTime(curDate);
        date.set(Calendar.HOUR, date.get(Calendar.HOUR) + hours);
        Date endDate = null;
        try {
            endDate = dft.parse(dft.format(date.getTime()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return endDate;
    }
    /**
     * list 去重复
     *
     * @return
     */
    public static List removeDuplicate(List list)  {
        Set set=new HashSet();
        List newList = new  ArrayList();
        for (Iterator it=list.iterator();it.hasNext();) {
            Object element=it.next();
            if(set.add(element)){
                newList.add(element);
            }
        }
        list.clear();
        list.addAll(newList);
        return list;
    }
    /**
     * 合并图片
     *
     * @return
     */
    public static void mergeImage(File file1, File file2,String outPath,String name) throws IOException {
        BufferedImage image1 = ImageIO.read(file1);
        BufferedImage image2 = ImageIO.read(file2);

        BufferedImage combined = new BufferedImage(image1.getWidth() * 2, image1.getHeight(), BufferedImage.TYPE_INT_RGB);

        Graphics g = combined.getGraphics();
        g.drawImage(image1, 0, 0, null);
        g.drawImage(image2, image1.getWidth(), 0, null);
        ImageIO.write(combined, "PNG", new File(outPath, name+".png"));
    }
    /**
     * 通过时间天数数判断两个时间的间隔
     * @param date1
     * @param date2
     * @return
     */
    public static int differentDaysByDay(Date date1,Date date2)
    {
        int days = (int) ((date2.getTime() - date1.getTime()) / (1000*3600*24));
        return days;
    }
    /**
     * 通过时间秒数判断两个时间的间隔
     * @param date1
     * @param date2
     * @return
     */
    public static int differentDaysBySecond(Date date1,Date date2)
    {
        int second = (int) ((date2.getTime() - date1.getTime()) / (1000));
        return second;
    }

    /**
     * 本月最后一天
     * @return
     */
    public static Date lastDayForMonth() {
        Calendar cale = null;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        cale = Calendar.getInstance();
        cale.add(Calendar.MONTH, 1);
        cale.set(Calendar.DAY_OF_MONTH, 0);
        return cale.getTime();
    }

    /**
     * 本月第一天
     * @return
     */
    public static Date firstDayForMonth() {
        Calendar cale = null;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        cale = Calendar.getInstance();
        cale.add(Calendar.MONTH, 0);
        cale.set(Calendar.DAY_OF_MONTH, 1);
        return cale.getTime();
    }

    /**
     * 月份第一天
     * @return
     */
    public static Date getFirstDayOfMonth(Integer year, Integer month) {
        Calendar cal = Calendar.getInstance();
        //设置年份
        cal.set(Calendar.YEAR, year);
        //设置月份
        cal.set(Calendar.MONTH, month-1);
        //获取某月最小天数
        int firstDay = cal.getMinimum(Calendar.DATE);
        //设置日历中月份的最小天数
        cal.set(Calendar.DAY_OF_MONTH,firstDay);
        Date date =  SystemCommUtil.toDate(SystemCommUtil.dateToString(cal.getTime(),"yyyy-MM-dd")+" 00:00:00");
        return date;
    }

    /**
     * 月份最后一天
     * @return
     */
    public static Date getLastDayOfMonth(int year, int month) {
        Calendar cal = Calendar.getInstance();
        //设置年份
        cal.set(Calendar.YEAR, year);
        //设置月份
        cal.set(Calendar.MONTH, month-1);
        //获取某月最大天数
        int lastDay = cal.getActualMaximum(Calendar.DATE);
        //设置日历中月份的最大天数
        cal.set(Calendar.DAY_OF_MONTH, lastDay);
        return cal.getTime();
    }

    /**
     * 获取某年第一天日期
     * @param year 年份
     * @return Date
     */
    public static Date getCurrYearFirst(int year){
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        Date currYearFirst = calendar.getTime();
        return currYearFirst;
    }

    /**
     * 获取某年最后一天日期
     * @param year 年份
     * @return Date
     */
    public static Date getCurrYearLast(int year){
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        calendar.roll(Calendar.DAY_OF_YEAR, -1);
        Date currYearLast = calendar.getTime();

        return currYearLast;
    }
    /**
     * 获取当前年
     * @return
     */
    public static int getCurrYear() {
        Calendar date = Calendar.getInstance();
        return date.get(Calendar.YEAR);
    }
    /**
     * 根据日期获取年份
     * @return
     */
    public static int getDateYear(Date date) {
        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        return ca.get(Calendar.YEAR);
    }
    /**
     * 获取本服务器的IP
     * @return
     */
    public static String getServerIp (){
        try{
            String ip = InetAddress.getLocalHost().getHostAddress();
            return ip;
        }catch (Exception ex){
            /*Log4j.showLogInfo(ex.getMessage(),ex);*/
            return null;
        }
    }
    /**
     * 过滤查询字符串
     * @return
     */
    public static String doSQLFilter(String str){
        str=str.replaceAll("\\.","。");
        str=str.replaceAll(":","：");
        str=str.replaceAll(";","；");
        str=str.replaceAll("&","＆");
        str=str.replaceAll("<","＜");
        str=str.replaceAll(">","＞");
        str=str.replaceAll("'","＇");
        str=str.replaceAll("\"","“");
        str=str.replaceAll("--","－－");
        str=str.replaceAll("/","／");
        str=str.replaceAll("%","％");
        str=str.replaceAll("\\+", "＋");
        str=str.replaceAll("\\(", "（");
        str=str.replaceAll("\\)", "）");
        return str;
    }
    public static String unicodeToString(String unicode) {
        if (!StringUtils.isEmpty(unicode)) {
            StringBuilder sb = new StringBuilder();
            int i = -1;
            int pos = 0;
            while ((i = unicode.indexOf("\\u", pos)) != -1) {
                sb.append(unicode.substring(pos, i));
                if (i + 5 < unicode.length()) {
                    pos = i + 6;
                    sb.append((char) Integer.parseInt(unicode.substring(i + 2, i + 6), 16));
                }
            }
            if(sb.length() > 0){
                return sb.toString();
            }else{
                return unicode;
            }
        }else{
            return unicode;
        }
    }
    public static Integer ranFourNumber(){
        Integer x = 0;
        Random ne = new Random();
        x = ne.nextInt(9999-1000+1)+1000;
        return x;
    }

    public static Boolean isMobileRequest(HttpServletRequest request){
        String userAgent = request.getHeader("user-agent");
        if (userAgent.indexOf("Android") != -1) {
            return true;
        } else if (userAgent.indexOf("iPhone") != -1 || userAgent.indexOf("iPad") != -1) {
            return true;
        } else {
            return false;
        }
    }
    public static String getStringDecode(String value){
        if(!StringUtils.isEmpty(value)){
            try {
                value = SystemCommUtil.unicodeToString(value);
                value = java.net.URLDecoder.decode(value, "UTF-8");
            }catch (Exception ex){

            }
        }
        return value;
    }

}
