package com.lucien.dap.user.authority.server.service.impl.common;

import com.alibaba.fastjson.JSONObject;
import com.lucien.dap.user.authority.client.vo.UserVo;
import com.lucien.dap.user.authority.server.model.UserEntity;
import com.lucien.dap.user.authority.server.service.system.UserService;
import com.lucien.dap.user.authority.server.service.common.LoginService;
import com.lucien.dap.framework.common.constants.Constants;
import com.lucien.dap.framework.common.constants.RetEnum;
import com.lucien.dap.framework.core.cache.CacheKeyConstants;
import com.lucien.dap.framework.core.cache.redis.RedisUtil;
import com.lucien.dap.framework.core.exception.ApplicationException;
import com.lucien.dap.framework.core.utils.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    private UserService userService;

    private RedisUtil redisUtil;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setRedisUtil(RedisUtil redisUtil) {
        this.redisUtil = redisUtil;
    }

    @Override
    public UserEntity login(String username, String password, String token) {
        UserEntity user = userService.getUser(username);
        if (user == null || !user.getPassword().equals(password)) {
            throw new ApplicationException(RetEnum.UserOrPasswordInvalid);
        }
        if (user.getStatus().equals(Constants.NO)) {
            throw new ApplicationException(RetEnum.UserStatusDisabled);
        }
        UserVo vo = new UserVo();
        BeanUtils.copyProperties(user, vo);
        //存入redis
        redisUtil.setEx(CacheKeyConstants.OMP_LOGIN_TOKEN + token, CacheKeyConstants.OMP_LOGIN_EXPIRE, JSONObject.toJSONString(vo));
        return user;
    }

    @Override
    public void logout(String token) {
        redisUtil.del(CacheKeyConstants.OMP_LOGIN_TOKEN + token);
    }

    @Override
    public UserVo getCurrentUser(String token) {
        String userJson = redisUtil.get(CacheKeyConstants.OMP_LOGIN_TOKEN + token);
        if (!StringUtils.isEmpty(userJson)) {
            UserVo userVo = JSONObject.parseObject(userJson, UserVo.class);
            return userVo;
        }
        return null;
    }
}
