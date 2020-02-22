package com.lucien.dap.user.authority.server.dto.system;

import lombok.Data;

import java.util.List;

/**
 * @ClassName RolePermissionDto
 * @Description
 * @Author GUWM
 * @Date 2019/9/6 15:21
 * @Version 1.0
 */
@Data
public class RolePermissionDto {
    private Long id;
    private Long roleId;
    private List<Long> permissionIds;
}
