package com.lucien.dap.user.authority.server.dto.system;

import lombok.Data;

/**
 * @ClassName QueryUserList
 * @Description
 * @Author MALUHAN
 * @Date 2019/7/1 14:37
 * @Version 1.0
 */
@Data
public class QueryUserDto {
    private String username;
    private String userNumber;
    private String nickname;
    private Integer status;
    private Integer pNo;
    private Integer pSize;
}
