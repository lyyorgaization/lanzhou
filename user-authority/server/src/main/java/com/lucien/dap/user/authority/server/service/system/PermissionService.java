package com.lucien.dap.user.authority.server.service.system;

import com.lucien.dap.framework.core.domain.BaseListVo;
import com.lucien.dap.user.authority.server.model.PermissionEntity;

import java.util.List;
import java.util.Map;

public interface PermissionService {

    boolean edit(PermissionEntity model);

    BaseListVo<PermissionEntity> getList(PermissionEntity model, Integer pNo, Integer pSize);

    boolean delete(Long permissionId);

    Map<Long, PermissionEntity> getByPermissionIds(List<Long> permissionIds);
}
