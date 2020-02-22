package com.lucien.dap.user.authority.server.controller;

import com.lucien.dap.user.authority.server.dto.LoginDto;
import com.lucien.dap.user.authority.server.dto.system.UserDto;
import com.lucien.dap.user.authority.server.model.UserEntity;
import com.lucien.dap.user.authority.server.service.system.UserService;
import com.lucien.dap.user.authority.server.vo.system.UserVo;
import com.lucien.dap.framework.common.constants.Constants;
import com.lucien.dap.framework.common.constants.RetEnum;
import com.lucien.dap.framework.core.domain.Response;
import com.lucien.dap.framework.core.utils.CookieUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName LoginController
 * @Description
 * @Author MALUHAN
 * @Date 2019/7/3 15:46
 * @Version 1.0
 */
@Slf4j
@RestController
public class LoginController extends BaseController {
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    /**
     * @return 这个方法为了shiro未登录时的返回, 路径与登录接口一致, 访问方式为get
     */
    @RequestMapping(value = "/unlogin", method = RequestMethod.GET)
    public Response<String> unlogin() {
        return Response.failed(RetEnum.UnLogin);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Response<UserVo> login(HttpServletRequest request, HttpServletResponse response, @RequestBody LoginDto dto) {
        String sid = CookieUtils.getSessionIdFromCookie(request, response);
        UserEntity currentUser = loginService.login(dto.getUsername(), dto.getPassword(), sid);
        UserVo userVo = new UserVo();
        userVo.setUsername(currentUser.getUsername());
        userVo.setNickname(currentUser.getNickname());
        userVo.setDefaultPassword(currentUser.getPassword().equals("000000") ? Constants.YES : Constants.NO);
        return Response.success(userVo);
    }

    @RequestMapping(value = "/loginout", method = RequestMethod.POST)
    public Response<Boolean> loginout(HttpServletRequest request) {
        String sid = CookieUtils.getSessionIdFromCookie(request, null);
        loginService.logout(sid);
        return Response.success(true);
    }

    @RequestMapping(value = "/unauthorized", method = RequestMethod.GET)
    public Response<Boolean> unauthorized() {
        return Response.failed(RetEnum.UnAuthorization);
    }

    @RequestMapping(value = "/changePassword", method = RequestMethod.POST)
    public Response<String> changePassword(HttpServletRequest request, @RequestBody UserDto dto) {
        com.lucien.dap.user.authority.client.vo.UserVo currentUser = getCurrentUser(request);
        UserEntity entity = new UserEntity();
        entity.setId(currentUser.getId());
        entity.setPassword(dto.getPassword());
        userService.edit(entity);
        return Response.success("");
    }
}
