package com.admin.common.filter;

import com.admin.user.ResultBean.ResultCode;
import com.admin.user.ResultBean.ResultData;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class LoginFilter implements HandlerInterceptor {

    private static List<String> loginPath = null;

    static {
        loginPath = new ArrayList<>();
        loginPath.add("/admin/test");
    }


    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler)
            throws Exception {
        System.out.println("preHandle:请求前调用");
        String method = request.getMethod();
        String servlet = request.getServletPath();
        if(StringUtils.isEmpty(servlet)){
            return false;
        }
        if(!loginPath.contains(servlet)){
            this.responseWrite(response, JSON.toJSONString(new ResultData<>(ResultCode.ERROR_LOGIN)));
            return false;
        }
        //返回 false 则请求中断
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        log.info("postHandle:请求后调用");
    }

    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        log.info("afterCompletion:请求调用完成后回调方法，即在视图渲染完成后回调");
    }

    public void responseWrite(HttpServletResponse response,String string){
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter printWriter= null;
        try{
            printWriter = response.getWriter();
            printWriter.write(string);
        }catch (IOException e){

        }finally {
            if(printWriter!=null){
                printWriter.close();
            }
        }
    }
}
