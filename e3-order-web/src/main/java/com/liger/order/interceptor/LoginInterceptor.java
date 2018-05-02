package com.liger.order.interceptor;

import com.liger.cart.service.CartService;
import com.liger.common.utils.CookieUtils;
import com.liger.common.utils.E3Result;
import com.liger.common.utils.JsonUtils;
import com.liger.model.Item;
import com.liger.model.User;
import com.liger.sso.service.TokenService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by roc_peng on 2017/9/4.
 * Email 18817353729@163.com
 * Url https://github.com/RocPeng/
 * Description 这个世界每天都有太多遗憾,所以你好,再见!
 */

public class LoginInterceptor implements HandlerInterceptor {
    @Autowired
    private TokenService tokenService;
    @Autowired
    private CartService cartService;
    @Value("${SSO_URL}")
    private String SSO_URL;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        //先判断cookie中是否有token
        String token = CookieUtils.getCookieValue(request, "token");
        if(StringUtils.isEmpty(token)){
            response.sendRedirect(SSO_URL+"/page/login?returnUrl="+request.getRequestURL());
            return false;
        }
        //在判断token是否能得到用户
        E3Result result = tokenService.getUserByToken(token);
        if(result.getStatus()!=200){
            response.sendRedirect(SSO_URL+"/page/login?returnUrl="+request.getRequestURL());
            return false;
        }
        //用户已登录状况
        User user = (User) result.getData();
        request.setAttribute("user",user);
        String cart = CookieUtils.getCookieValue(request, "cart", true);
        if(StringUtils.isNotBlank(cart)){
            cartService.mergeCart(user.getId(), JsonUtils.jsonToList(cart, Item.class));
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
