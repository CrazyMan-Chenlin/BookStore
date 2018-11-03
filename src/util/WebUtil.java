package util;

import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

public class WebUtil {
    public  static <T>T copyRequestToBean(HttpServletRequest request,Class<T> clazz){
       T t;
        Map<String, String[]> parameterMap = request.getParameterMap();
        try {
            t=clazz.getDeclaredConstructor().newInstance();
            BeanUtils.copyProperties(t,parameterMap);
            return t;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    //生成一个uuid算法
    public static String uuid() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
    static SimpleDateFormat sdf= new SimpleDateFormat("yyyyMMddhhmmss");
    //生成一个订单号
    public static String getOrderId(Object obj){
        return obj.hashCode()+sdf.format(new Date());
    }
}
