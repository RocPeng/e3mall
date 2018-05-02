package com.liger.cart.interceptor;

import com.liger.common.utils.CookieUtils;
import com.liger.common.utils.E3Result;
import com.liger.model.User;
import com.liger.sso.service.TokenService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by roc_peng on 2017/9/3.
 * Email 18817353729@163.com
 * Url https://github.com/RocPeng/
 * Description 用户登录拦截器 判断是否登录
 */

public class LoginInterceptor implements HandlerInterceptor {
    @Autowired
    private TokenService tokenService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        // 前处理，执行handler之前执行此方法。
        //返回true，放行	false：拦截
        //1.从cookie中取token
        String token = CookieUtils.getCookieValue(request, "token");
        if(StringUtils.isBlank(token)){
            //直接放行
            return true;
        }
        E3Result result = tokenService.getUserByToken(token);
        if(result.getStatus()!=200){
            return true;
        }
        User user = (User) result.getData();
        request.setAttribute("user",user);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        //handler执行之后，返回ModeAndView之前
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        //完成处理，返回ModelAndView之后。
        //可以再此处理异常
    }
}
