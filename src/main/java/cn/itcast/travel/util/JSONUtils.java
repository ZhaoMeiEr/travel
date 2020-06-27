package cn.itcast.travel.util;

import cn.itcast.travel.domain.ResultInfo;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @authon timber
 * @description 数据转换为 json
 * @date 2020/6/22 下午5:26
 */
public class JSONUtils {
    /*
     * @Author: timber
     * @Description: 将对象转换为 json 字符串并返回
     * @Date: 2020/6/23 下午6:49
     * @param obj
     * @param response
     * @Return
     */
    public static String writeValueAsString(Object obj, HttpServletResponse response) throws IOException {
        // 创建 ObjectMapper 对象
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }

    /*
     * 将 Object 转换称 json 格式并以字节输入流响应回浏览器
     * @param obj
     * @param response
     * @throws IOException
     */
    public static void writeValue(Object obj, HttpServletResponse response) throws IOException{
        // 创建 ObjectMapper 对象
        ObjectMapper objectMapper = new ObjectMapper();
        // 设置响应数据格式为 json
        response.setContentType("application/json;charset=utf-8");
        // 将对象用字节输入流响应回浏览器
        objectMapper.writeValue(response.getOutputStream(), obj);
    }
}
