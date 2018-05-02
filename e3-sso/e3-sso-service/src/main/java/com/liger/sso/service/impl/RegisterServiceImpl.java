package com.liger.sso.service.impl;

import com.liger.common.utils.E3Result;
import com.liger.mapper.TbUserMapper;
import com.liger.model.User;
import com.liger.sso.global.Global;
import com.liger.sso.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

/**
 * Created by roc_peng on 2017/9/2.
 * Email 18817353729@163.com
 * Url https://github.com/RocPeng/
 * Description 这个世界每天都有太多遗憾,所以你好,再见!
 */
@Service
public class RegisterServiceImpl implements RegisterService {
    @Autowired
    private TbUserMapper userMapper;
    
    @Override
    public E3Result checkData(String param, int type) {
        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        if(Global.USERNAMETYPE == type){
            criteria.andEqualTo("username",param);
        }else if(Global.PHONETYPE == type){
            criteria.andEqualTo("phone",param);
        }else if(Global.EMAILTYPE == type){
            criteria.andEqualTo("email",param);
        } else{
            return E3Result.build(400,"参数传入错误");
        }
        List<User> users = userMapper.selectByExample(example);
        if(!CollectionUtils.isEmpty(users)){
            return E3Result.ok(false);
        }
        return E3Result.ok(true);
    }

    @Override
    public E3Result register(User user) {
        if(StringUtils.isEmpty(user.getUsername())){
            return E3Result.build(400,"用户名不能为空!");
        }
        if(StringUtils.isEmpty(user.getPassword())){
            return E3Result.build(400,"密码不能为空!");
        }
        //检查用户是否可用
        E3Result result = checkData(user.getUsername(), Global.USERNAMETYPE);
        if(!(boolean)result.getData()){
            return E3Result.build(400,"用户名已经占用!!");
        }
        //检查phone是否可用
        if(!StringUtils.isEmpty(user.getPhone())){
            E3Result phoneResult = checkData(user.getPhone(), Global.PHONETYPE);
            if(!(boolean)phoneResult.getData()){
                return E3Result.build(400,"电话已注册!!");
            }
        }
        //检查email是都可用
        if(!StringUtils.isEmpty(user.getEmail())){
            E3Result emailResult = checkData(user.getEmail(), Global.EMAILTYPE);
            if(!(boolean)emailResult.getData()){
                return E3Result.build(400,"邮箱已注册!!");
            }
        }
        //补全数据
        user.setCreated(new Date());
        user.setUpdated(new Date());
        //密码加密
        String s = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
        user.setPassword(s);
        userMapper.insert(user);
        return E3Result.ok();
    }
}
