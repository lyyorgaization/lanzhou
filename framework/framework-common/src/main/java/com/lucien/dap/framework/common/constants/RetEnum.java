package com.lucien.dap.framework.common.constants;


public enum RetEnum {
    Success(0, "成功"),
    RequestEmpty(100, "参数为空"),
    RequestInvalid(101, "参数无效"),
    UnAuthorization(102, "未授权访问"),
    UserOrPasswordInvalid(103, "用户名或密码错误"),
    UserStatusDisabled(104, "账户已禁用"),
    UnLogin(105, "用户未登陆"),
    SignFailed(106, "签名验证失败"),
    DataEmpty(201, "未查到数据"),
    DataError(202, "数据异常"),
    DataSourceError(203, "数据库异常"),
    SystemError(500, "系统异常"),
    NetConnectError(501, "");

    private int code;

    public int getCode() {
        return code;
    }

    private String msg;

    public String getMsg() {
        return msg;
    }

    RetEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
