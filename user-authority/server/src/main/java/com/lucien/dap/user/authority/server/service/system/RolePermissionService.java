package com.lucien.dap.user.authority.server.service.system;

import com.lucien.dap.framework.core.domain.BaseListVo;
import com.lucien.dap.user.authority.server.model.RolePermissionEntity;
import com.lucien.dap.user.authority.server.vo.system.RolePermissionVo;

import java.util.List;

public interface RolePermissionService {

    boolean editRolePermission(Long roleId, List<Long> permissionIds);

    BaseListVo<RolePermissionVo> getRolePermissionList(RolePermissionEntity model);

    boolean delete(Long rolePermissionId);
}
