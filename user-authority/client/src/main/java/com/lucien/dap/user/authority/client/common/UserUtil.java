package com.lucien.dap.user.authority.client.common;

import com.alibaba.fastjson.JSONObject;
import com.lucien.dap.user.authority.client.vo.UserVo;
import com.lucien.dap.framework.core.cache.CacheKeyConstants;
import com.lucien.dap.framework.core.cache.redis.RedisUtil;
import com.lucien.dap.framework.core.utils.CookieUtils;
import com.lucien.dap.framework.core.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class UserUtil {
    private RedisUtil redisUtil;

    @Autowired
    public void setRedisUtil(RedisUtil redisUtil) {
        this.redisUtil = redisUtil;
    }

    public UserVo getCurrentUser(HttpServletRequest request) {
        String sid = CookieUtils.getSessionIdFromCookie(request, null);
        String userJson = redisUtil.get(CacheKeyConstants.OMP_LOGIN_TOKEN + sid);
        if (!StringUtils.isEmpty(userJson)) {
            return JSONObject.parseObject(userJson, UserVo.class);
        }
        return null;
    }
}
