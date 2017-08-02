package cn.g_open.net_worm.utils;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class IpUtils
{
    public static String findByIp(String ip)
    {
        
        if(!checkIpFormat(ip)){
            return "IP地址格式有误！";
        }
        // 请求baidu接口获取IP详细信息
        String body = findJsonBodyByURL("https://sp0.baidu.com/8aQDcjqpAAV3otqbppnN2DJv/api.php?query=" + ip + "&resource_id=6006");
        // 解析获取的参数
        JSONObject json = JSONObject.parseObject(body);
        JSONArray jsonArray = json.getJSONArray("data");
        JSONObject json2 = jsonArray.getJSONObject(0);
        // 获取IP归属地信息
        String location = json2.getString("location").toString();
        return location;
    }
    
    private static String findJsonBodyByURL(String url){
        while (true)
        {
            try
            {
                return Jsoup.connect(url).ignoreContentType(true).execute().body();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }

    // 将127.0.0.1形式的IP地址转换成十进制整数，这里没有进行任何错误处理
    public static long ipToLong(String strIp)
    {
        long[] ip = new long[4];
        // 先找到IP地址字符串中.的位置
        int position1 = strIp.indexOf(".");
        int position2 = strIp.indexOf(".", position1 + 1);
        int position3 = strIp.indexOf(".", position2 + 1);
        // 将每个.之间的字符串转换成整型
        ip[0] = Long.parseLong(strIp.substring(0, position1));
        ip[1] = Long.parseLong(strIp.substring(position1 + 1, position2));
        ip[2] = Long.parseLong(strIp.substring(position2 + 1, position3));
        ip[3] = Long.parseLong(strIp.substring(position3 + 1));
        return (ip[0] << 24) + (ip[1] << 16) + (ip[2] << 8) + ip[3];
    }

    // 将十进制整数形式转换成127.0.0.1形式的ip地址
    public static String longToIP(long longIp)
    {
        StringBuffer sb = new StringBuffer("");
        // 直接右移24位
        sb.append(String.valueOf((longIp >>> 24)));
        sb.append(".");
        // 将高8位置0，然后右移16位
        sb.append(String.valueOf((longIp & 0x00FFFFFF) >>> 16));
        sb.append(".");
        // 将高16位置0，然后右移8位
        sb.append(String.valueOf((longIp & 0x0000FFFF) >>> 8));
        sb.append(".");
        // 将高24位置0
        sb.append(String.valueOf((longIp & 0x000000FF)));
        return sb.toString();
    }

    public static boolean checkIpFormat(String ip)
    {
        String regex = "(([1-9]{1}[0-9]{0,1})||([1]{1}[0-9]{2})||([2]{1}(([0-4]{1}[0-9]{1})||([5]{1}[0-5]{1}))))"
                + "\\."
                + "(([0]{1})||([1-9]{1}[0-9]{0,1})||([1]{1}[0-9]{2})||([2]{1}(([0-4]{1}[0-9]{1})||([5]{1}[0-5]{1}))))"
                + "\\."
                + "(([0]{1})||([1-9]{1}[0-9]{0,1})||([1]{1}[0-9]{2})||([2]{1}(([0-4]{1}[0-9]{1})||([5]{1}[0-5]{1}))))"
                + "\\."
                + "(([0]{1})||([1-9]{1}[0-9]{0,1})||([1]{1}[0-9]{2})||([2]{1}(([0-4]{1}[0-9]{1})||([5]{1}[0-5]{1}))))";
        if (StringUtils.isEmpty(ip) || !ip.matches(regex))
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    public static void main(String[] args)
    {
        System.out.println(longToIP(50491823));
    }
    
    private static String aaa(String url){
        int i = 0;
        while (true)
        {
            try
            {
                String body = null;
                if(i == 100){
                    body = url;
                }else{
                    throw new Exception();
                }
                return body;
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            i++;
        }
    }
    
}
