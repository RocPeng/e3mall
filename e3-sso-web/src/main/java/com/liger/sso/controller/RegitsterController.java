package com.liger.sso.controller;

import com.liger.common.utils.E3Result;
import com.liger.model.User;
import com.liger.sso.service.RegisterService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by roc_peng on 2017/9/2.
 * Email 18817353729@163.com
 * Url https://github.com/RocPeng/
 * Description 这个世界每天都有太多遗憾,所以你好,再见!
 */
@Controller
public class RegitsterController {

    @Autowired
    private RegisterService registerService;

    /**
     * 其他系统重定向到登录首页  携带回调地址
     * @param model
     * @param returnUrl
     * @return
     */
    @RequestMapping("/")
    public String index(Model model,String returnUrl){
        if(StringUtils.isNotBlank(returnUrl)){
            model.addAttribute("redirect",returnUrl);
        }
        return "/login";
    }

    @RequestMapping("/page/{pageName}")
    public String registerIndex(@PathVariable("pageName") String pageName,Model model,String returnUrl){
        if(StringUtils.isNotBlank(returnUrl)){
            model.addAttribute("redirect",returnUrl);
        }
        return pageName;
    }

    @RequestMapping("/user/check/{param}/{type}")
    @ResponseBody
    public E3Result checkData(@PathVariable("param") String param, @PathVariable("type") Integer type){
        E3Result result = registerService.checkData(param, type);
        return result;
    }

    @RequestMapping(value = "/user/register",method = RequestMethod.POST)
    @ResponseBody
    public E3Result register(User user){
        E3Result result = registerService.register(user);
        return result;
    }

}
