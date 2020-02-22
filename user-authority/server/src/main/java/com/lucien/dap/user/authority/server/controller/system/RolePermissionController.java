package com.lucien.dap.user.authority.server.controller.system;

import com.lucien.dap.framework.core.domain.BaseListVo;
import com.lucien.dap.framework.core.domain.Response;
import com.lucien.dap.user.authority.server.dto.system.RoleDto;
import com.lucien.dap.user.authority.server.dto.system.RolePermissionDto;
import com.lucien.dap.user.authority.server.model.RolePermissionEntity;
import com.lucien.dap.user.authority.server.service.system.RolePermissionService;
import com.lucien.dap.user.authority.server.vo.system.RolePermissionVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName PermissionController
 * @Description
 * @Author GUWM
 * @Date 2019/9/5 16:53
 * @Version 1.0
 */
@RestController
@RequestMapping("/role_permission")
public class RolePermissionController {

    private RolePermissionService rolePermissionService;

    @Autowired
    public void setRolePermissionService(RolePermissionService rolePermissionService) {
        this.rolePermissionService = rolePermissionService;
    }

    @RequestMapping("/list")
    public Response<BaseListVo<RolePermissionVo>> getRolePermissionList(@RequestBody(required = false) RolePermissionDto dto) {
        RolePermissionEntity entity = new RolePermissionEntity();
        if (dto != null && dto.getRoleId() != null) {
            entity.setRoleId(dto.getRoleId());
        }

        BaseListVo<RolePermissionVo> permissionList = rolePermissionService.getRolePermissionList(entity);
        return Response.success(permissionList);
    }

    @RequestMapping("/rolePermissonIds")
    public Response<List<Long>> getRolePermissonIds(@RequestBody(required = false) RoleDto dto) {
        RolePermissionEntity entity = new RolePermissionEntity();
        entity.setRoleId(dto.getId());
        BaseListVo<RolePermissionVo> permissionList = rolePermissionService.getRolePermissionList(entity);
        if (!CollectionUtils.isEmpty(permissionList.getList())) {
            List<Long> ids = permissionList.getList().stream().map(RolePermissionVo::getPermissionId).collect(Collectors.toList());
            return Response.success(ids);
        }
        return Response.success(new ArrayList<>());
    }

    @RequestMapping("/editRolePermission")
    public Response<Boolean> editRolePermission(@RequestBody RolePermissionDto dto) {
        rolePermissionService.editRolePermission(dto.getRoleId(), dto.getPermissionIds());
        return Response.success(true);
    }

    @RequestMapping("/delete")
    public Response<Boolean> delete(@RequestBody RolePermissionDto dto) {
        rolePermissionService.delete(dto.getId());
        return Response.success(true);
    }
}
