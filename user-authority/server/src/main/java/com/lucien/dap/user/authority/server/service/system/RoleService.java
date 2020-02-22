package com.lucien.dap.user.authority.server.service.system;

import com.lucien.dap.framework.core.domain.BaseListVo;
import com.lucien.dap.user.authority.server.model.MenuEntity;
import com.lucien.dap.user.authority.server.model.PermissionEntity;
import com.lucien.dap.user.authority.server.model.RoleEntity;

import java.util.List;
import java.util.Map;

public interface RoleService {
    Boolean edit(RoleEntity model);

    BaseListVo<RoleEntity> getRoleList(RoleEntity RoleEntity, Integer pNo, Integer pSize);

    List<MenuEntity> getRoleMenuList(List<Long> roleIds);

    Boolean editRoleMenu(Long roleId, List<Long> menuId);

    Map<Long, RoleEntity> getByRoleIds(List<Long> roleIds);

    Boolean delete(Long roleId);

    List<PermissionEntity> getPermissionByRoleIds(List<Long> roleIds);
}
