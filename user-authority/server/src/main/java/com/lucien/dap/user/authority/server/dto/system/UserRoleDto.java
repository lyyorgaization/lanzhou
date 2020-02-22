package com.lucien.dap.user.authority.server.dto.system;

import lombok.Data;
import java.util.List;
/**
 * @ClassName UserRoleDto
 * @Description
 * @Author MALUHAN
 * @Date 2019/7/12 10:45
 * @Version 1.0
 */
@Data
public class UserRoleDto {
    private Long userId;
    private List<Long> roleIdList;
}
