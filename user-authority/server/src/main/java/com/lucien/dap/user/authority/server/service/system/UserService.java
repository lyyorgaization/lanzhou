package com.lucien.dap.user.authority.server.service.system;

import com.lucien.dap.framework.core.domain.BaseListVo;
import com.lucien.dap.user.authority.server.model.MenuEntity;
import com.lucien.dap.user.authority.server.model.PermissionEntity;
import com.lucien.dap.user.authority.server.model.UserEntity;

import java.util.List;

public interface UserService {
    UserEntity getUser(String userName);

    BaseListVo<UserEntity> getUserList(UserEntity userModel, Integer pSize, Integer pNo);

    Boolean add(UserEntity userModel);

    Boolean edit(UserEntity userModel);

    List<Long> getUserRoleList(Long userId);

    List<MenuEntity> getUserMenu(Long userId);

    Boolean editUserRole(Long userId, List<Long> roleIds);

    Boolean deleteUser(Long userId);

    Boolean initPassword(Long userId);

    List<PermissionEntity> getPermissionByUserIds(Long userIds);
}
