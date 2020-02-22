package com.lucien.dap.user.authority.server.vo.system;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @ClassName UserListVo
 * @Description
 * @Author MALUHAN
 * @Date 2019/7/1 14:38
 * @Version 1.0
 */
@Data
public class UserVo {
    private Long id;
    private String username;
    private String nickname;
    private String userNumber;
    private Integer status;
    private String statusDesc;
    private Integer defaultPassword;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date createTime;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date modifyTime;

    public String getStatusDesc() {
        if (status != null && status == 1) {
            return "已启用";
        } else {
            return "已禁用";
        }
    }
}
