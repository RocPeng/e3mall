package com.liger.sso.service.impl;

import com.liger.common.utils.E3Result;
import com.liger.common.utils.JsonUtils;
import com.liger.mapper.TbUserMapper;
import com.liger.model.User;
import com.liger.sso.global.Global;
import com.liger.sso.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.DigestUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.UUID;

/**
 * Created by roc_peng on 2017/9/2.
 * Email 18817353729@163.com
 * Url https://github.com/RocPeng/
 * Description 这个世界每天都有太多遗憾,所以你好,再见!
 */
@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private TbUserMapper userMapper;
//    @Autowired
//    private JedisClient jedisClient;
    @Value("${SESSION_EXPIRE}")
    private Integer SESSION_EXPIRE;

    @Override
    public E3Result userLogin(String username, String password) {

        //1.先验证username
        Example example = new Example(User.class);
        example.createCriteria().andEqualTo("username",username);
        List<User> users = userMapper.selectByExample(example);
        if(CollectionUtils.isEmpty(users)){
            return E3Result.build(400, "用户名或密码错误");
        }
        //2.验证密码
        User user = users.get(0);
        String pwd = DigestUtils.md5DigestAsHex(password.getBytes());
        if(!pwd.equals(user.getPassword())){
            return E3Result.build(400, "用户名或密码错误");
        }
        //生成token并且存储在redis
        String token = UUID.randomUUID().toString();
        user.setPassword(null);
        Global.tokenMap.put("SESSION:"+token,JsonUtils.objectToJson(user));
//        jedisClient.set("SESSION:"+token, JsonUtils.objectToJson(user));
//        jedisClient.expire("SESSION:"+token,SESSION_EXPIRE);

        return E3Result.ok(token);
    }
}
