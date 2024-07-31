package com.wrj.medium;

import com.alibaba.fastjson.JSONObject;
import com.wrj.netty.handler.param.ServerRequest;
import com.wrj.netty.util.Response;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName media
 * @Description TODO
 * @Author @O_o
 * @Date 2024-07-11 15:23
 * @Version 1.0
 */
public class Media {
    public static Map<String,BeanMethod> beanMap;
    static {
        beanMap=new HashMap<String,BeanMethod>();
    }
    private static Media m = null;
    private Media() {
    }

    public static Media newInstance() {
        if (m == null) {
            m = new Media();
        }
        return m;
    }
    // 反射处理业务代码
    public Response process(ServerRequest request) {
        Response result = null;
        try {
            String command = request.getCommand();
            BeanMethod beanMethod = beanMap.get(command);
            if (beanMethod == null) {
                return null;
            }
            Object bean = beanMethod.getBean();
            Method m = beanMethod.getMethod();
            Class paramType = m.getParameterTypes()[0];
            Object content = request.getContent();

            Object args = JSONObject.parseObject(JSONObject.toJSONString(content), paramType);
            result = (Response) m.invoke(bean, args);
            result.setId(request.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


}
