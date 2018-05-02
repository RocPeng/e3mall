package com.liger.sso.service;

import com.liger.common.utils.E3Result;

/**
 * Created by roc_peng on 2017/9/2.
 * Email 18817353729@163.com
 * Url https://github.com/RocPeng/
 * Description 根据token查询用户信息
 */

public interface TokenService {
    E3Result getUserByToken(String token);
    E3Result clearUserByToken();
}
