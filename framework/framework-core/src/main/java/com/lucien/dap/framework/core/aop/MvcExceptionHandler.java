package com.lucien.dap.framework.core.aop;

import com.lucien.dap.framework.common.constants.RetEnum;
import com.lucien.dap.framework.core.domain.Response;
import com.lucien.dap.framework.core.exception.ApplicationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName MvcExceptionHandler
 * @Description
 * @Author MALUHAN
 * @Date 2019/6/21 17:39
 * @Version 1.0
 */
@Slf4j
@ControllerAdvice
public class MvcExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Response handlerMvcException(Exception e) {
        if (e instanceof ApplicationException) {
            return Response.failed((ApplicationException) e);
        } else {
            log.error("<<<<访问异常", e);
            return Response.failed(RetEnum.SystemError, "系统异常:" + e.toString());
        }
    }
}
