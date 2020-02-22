package com.lucien.dap.framework.core.exception;

import com.lucien.dap.framework.common.constants.RetEnum;
import lombok.Data;

/**
 * @ClassName ApplicationException
 * @Description
 * @Author MALUHAN
 * @Date 2019/6/20 15:21
 * @Version 1.0
 */
@Data
public class ApplicationException extends RuntimeException {
    private int code;
    private String message;

    public ApplicationException(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public ApplicationException(RetEnum retEnum) {
        this.code = retEnum.getCode();
        this.message = retEnum.getMsg();
    }
}
