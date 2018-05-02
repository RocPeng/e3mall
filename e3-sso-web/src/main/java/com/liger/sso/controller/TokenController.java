package com.liger.sso.controller;

import com.liger.common.utils.E3Result;
import com.liger.sso.service.TokenService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by roc_peng on 2017/9/2.
 * Email 18817353729@163.com
 * Url https://github.com/RocPeng/
 * Description 解决跨域问题
 */
@Controller
public class TokenController {

    @Autowired
    private TokenService tokenService;

    @RequestMapping("/user/token/{token}")
    @ResponseBody
    public Object getUserByToken(@PathVariable("token") String token,String callback){
        E3Result result = tokenService.getUserByToken(token);
        //响应之前先判断是否为jsonp请求
        if(StringUtils.isNotEmpty(callback)){
            MappingJacksonValue jacksonValue = new MappingJacksonValue(result);
            jacksonValue.setJsonpFunction(callback);
            return jacksonValue;
        }
        return result;
    }

    /**
     * 清空Token 用户退出时的操作
     * @return
     */
    @RequestMapping("/user/token/clear")
    @ResponseBody
    public E3Result clearToken(){
        E3Result result = tokenService.clearUserByToken();
        return result;
    }
}
