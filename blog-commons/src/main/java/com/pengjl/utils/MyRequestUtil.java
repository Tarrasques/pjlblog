package com.pengjl.utils;

import com.alibaba.excel.util.StringUtils;
import com.pengjl.exception.SystemException;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.servlet.http.HttpServletRequest;

/**
 * httprequest工具类，目前提供了从url获取分页数据，以及根据参数名获取参数
 * @author pengjl 2022年10月30日15:46:15
 */
public class MyRequestUtil {

    private HttpServletRequest request;

    private MyRequestUtil(){
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        request = requestAttributes.getRequest();
    }

    public static MyRequestUtil getRequest(){
        MyRequestUtil myRequestUtil = new MyRequestUtil();
        return myRequestUtil;
    }

    public Integer getPageNum() {
        if (StringUtils.isNotBlank(request.getParameter("pageNum"))){
            return Integer.valueOf(request.getParameter("pageNum"));
        }else{
            return 1;
        }
    }
    public Integer getPageSize() {
        if (StringUtils.isNotBlank(request.getParameter("pageSize"))){
            return Integer.valueOf(request.getParameter("pageSize"));
        }else{
            return 10;
        }
    }
    public String getValue(String key) {
           return request.getParameter(key);
    }


    public String getValue(String key,String defaultValue) {
        if (StringUtils.isBlank(defaultValue)) {
            throw new SystemException(AppHttpCodeEnum.PARAMETER_BLANK);
        }
        String parameter = request.getParameter(key);
        if (StringUtils.isBlank(parameter)) {
            return defaultValue;
        }
        return parameter;
    }
}
