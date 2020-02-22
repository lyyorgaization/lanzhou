package com.lucien.dap.user.authority.server.service.common;

import com.lucien.dap.user.authority.client.vo.UserVo;
import com.lucien.dap.user.authority.server.model.UserEntity;

public interface LoginService {
    /**
     * @param username
     * @param password
     * @param token    可以使用sessionId代替
     */
    UserEntity login(String username, String password, String token);

    void logout(String token);

    UserVo getCurrentUser(String token);
}
