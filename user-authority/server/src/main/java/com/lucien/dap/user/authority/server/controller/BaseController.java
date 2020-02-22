package com.lucien.dap.user.authority.server.controller;

import com.lucien.dap.user.authority.client.common.UserUtil;
import com.lucien.dap.user.authority.client.vo.UserVo;
import com.lucien.dap.user.authority.server.service.common.LoginService;
import com.lucien.dap.framework.common.constants.RetEnum;
import com.lucien.dap.framework.core.exception.ApplicationException;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName BaseController
 * @Description
 * @Author MALUHAN
 * @Date 2019/7/3 9:39
 * @Version 1.0
 */
public class BaseController {
    protected LoginService loginService;

    private UserUtil userUtil;

    @Autowired
    public void setLoginService(LoginService loginService) {
        this.loginService = loginService;
    }

    @Autowired
    public void setUserUtil(UserUtil userUtil) {
        this.userUtil = userUtil;
    }

    protected UserVo getCurrentUser(HttpServletRequest request) {
        UserVo currentUser = userUtil.getCurrentUser(request);
        if (currentUser == null) {
            throw new ApplicationException(RetEnum.UnLogin);
        }
        return currentUser;
    }
}
