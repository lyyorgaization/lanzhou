package com.lucien.dap.user.authority.client.vo;

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
}
