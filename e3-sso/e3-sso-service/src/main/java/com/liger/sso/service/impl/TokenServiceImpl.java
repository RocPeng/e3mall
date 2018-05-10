package com.liger.sso.service.impl;

import com.liger.common.utils.E3Result;
import com.liger.common.utils.JsonUtils;
import com.liger.model.User;
import com.liger.sso.global.Global;
import com.liger.sso.service.TokenService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * Created by roc_peng on 2017/9/2.
 * Email 18817353729@163.com
 * Url https://github.com/RocPeng/
 * Description 这个世界每天都有太多遗憾,所以你好,再见!
 */
@Service
public class TokenServiceImpl implements TokenService {
//    @Autowired
//    private JedisPool jedisPool;
    @Value("${SESSION_EXPIRE}")
    private Integer SESSION_EXPIRE;

    /**
     * 根据token去redis中取user对象
     *
     * @param token
     * @return
     */
    @Override
    public E3Result getUserByToken(String token) {
        String json = Global.tokenMap.get("SESSION:" + token);
        if (StringUtils.isEmpty(json)) {
            return E3Result.build(400, "用户已失效,请重新登录!");
        }
        //重置redis缓存时间
//        jedisClient.expire("SESSION:"+token,SESSION_EXPIRE);
        User user = JsonUtils.jsonToPojo(json, User.class);
        return E3Result.ok(user);
    }

    @Override
    public E3Result clearUserByToken() {
        //模糊查找到所有key
//        Jedis jedis = jedisPool.getResource();
//        Set<String> keys = jedis.keys("SESSION:*");
//        for(String key : keys){
//            jedisClient.del(key);
//        }
        Global.tokenMap.clear();
        return E3Result.ok();
    }
}
