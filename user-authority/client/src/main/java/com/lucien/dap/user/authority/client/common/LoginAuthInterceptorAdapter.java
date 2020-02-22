package com.lucien.dap.user.authority.client.common;

import com.alibaba.fastjson.JSONObject;
import com.lucien.dap.user.authority.client.vo.UserVo;
import com.lucien.dap.framework.common.constants.RetEnum;
import com.lucien.dap.framework.core.domain.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Slf4j
@Component
public class LoginAuthInterceptorAdapter extends HandlerInterceptorAdapter {

    private UserUtil userUtil;

    @Autowired
    public void setUserUtil(UserUtil userUtil) {
        this.userUtil = userUtil;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        String sid = CookieUtils.getSessionIdFromCookie(request, response);
        UserVo currentUser = userUtil.getCurrentUser(request);
        if (currentUser != null) {
            return true;
        }
        convertResponse(response, RetEnum.UnLogin);
        return false;
    }

    private void convertResponse(HttpServletResponse response, RetEnum signFailed) throws IOException {
        response.setHeader("Content-Type", "application/json;charset=UTF-8");
        Response<Boolean> failed = Response.failed(signFailed);
        PrintWriter writer = response.getWriter();
        writer.append(JSONObject.toJSONString(failed));
        writer.flush();
    }

    // 在业务处理器处理请求完成之后，生成视图之前执行
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
            throws Exception {
    }

    // 在DispatcherServlet完全处理完请求之后被调用，可用于清理资源
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
    }
}
