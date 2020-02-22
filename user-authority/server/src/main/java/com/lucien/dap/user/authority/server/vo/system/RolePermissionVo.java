package com.lucien.dap.user.authority.server.vo.system;

import lombok.Data;

/**
 * @ClassName RolePermissionVo
 * @Description
 * @Author GUWM
 * @Date 2019/9/5 14:38
 * @Version 1.0
 */
@Data
public class RolePermissionVo {
    private Long id;
    private Long roleId;
    private String roleName;
    private Long permissionId;
    private String desc;
    private String url;
}
