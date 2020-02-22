package com.lucien.dap.framework.core.domain;

import com.lucien.dap.framework.common.constants.RetEnum;
import com.lucien.dap.framework.core.exception.ApplicationException;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @ClassName Response
 * @Description
 * @Author MALUHAN
 * @Date 2019/6/20 14:52
 * @Version 1.0
 */
public class Response<T> implements Serializable {
    @Getter
    @Setter
    private Integer code;
    @Getter
    @Setter
    private String msg;
    @Getter
    @Setter
    private T module;

    public static <S> Response<S> success(S obj) {
        Response<S> response = new Response<>();
        response.code = RetEnum.Success.getCode();
        response.msg = RetEnum.Success.getMsg();
        response.module = obj;
        return response;
    }

    private static <T> Response<T> failed(int code, String msg) {
        Response<T> response = new Response<T>();
        response.code = code;
        response.msg = msg;
        return response;
    }

    public static <T> Response<T> failed(RetEnum retEnum) {
        return failed(retEnum.getCode(), retEnum.getMsg());
    }

    public static <T> Response<T> failed(RetEnum retEnum, String msg) {
        return failed(retEnum.getCode(), msg);
    }

    public static <T> Response<T> failed(ApplicationException ex) {
        return failed(ex.getCode(), ex.getMessage());
    }
}
