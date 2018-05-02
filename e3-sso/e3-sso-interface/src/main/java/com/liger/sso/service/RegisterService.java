package com.liger.sso.service;

import com.liger.common.utils.E3Result;
import com.liger.model.User;

/**
 * Created by roc_peng on 2017/9/2.
 * Email 18817353729@163.com
 * Url https://github.com/RocPeng/
 * Description 这个世界每天都有太多遗憾,所以你好,再见!
 */

public interface RegisterService {
    E3Result checkData(String param, int type);
    E3Result register(User user);
}
