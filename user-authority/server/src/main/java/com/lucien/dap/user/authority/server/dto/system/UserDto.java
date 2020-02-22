package com.lucien.dap.user.authority.server.dto.system;

import lombok.Data;

/**
 * @ClassName AddUserDto
 * @Description
 * @Author MALUHAN
 * @Date 2019/7/3 15:21
 * @Version 1.0
 */
@Data
public class UserDto {
    private Long id;
    private String username;
    private String nickname;
    private String userNumber;
    private Integer status;
    private String password;
}
