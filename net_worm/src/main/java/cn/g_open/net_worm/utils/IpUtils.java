package cn.g_open.net_worm.utils;

import java.io.IOException;

import org.jsoup.Jsoup;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class IpUtils
{
    public static String findByIp(String ip) throws IOException
    {
        //请求baidu接口获取IP详细信息
        String body = Jsoup.connect("https://sp0.baidu.com/8aQDcjqpAAV3otqbppnN2DJv/api.php?query=" + ip + "&resource_id=6006").ignoreContentType(true).execute().body();
        //解析获取的参数
        JSONObject json= JSONObject.parseObject(body);
        JSONArray jsonArray = json.getJSONArray("data");
        JSONObject json2 = jsonArray.getJSONObject(0);
        //获取IP归属地信息
        String location = json2.getString("location").toString();
        return location;
    }
}
