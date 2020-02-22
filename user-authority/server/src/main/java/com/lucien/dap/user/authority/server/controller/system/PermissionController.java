package com.lucien.dap.user.authority.server.controller.system;

import com.lucien.dap.framework.core.domain.BaseListVo;
import com.lucien.dap.framework.core.domain.Response;
import com.lucien.dap.user.authority.server.dto.system.PermissionDto;
import com.lucien.dap.user.authority.server.dto.system.QueryPermissionListDto;
import com.lucien.dap.user.authority.server.model.PermissionEntity;
import com.lucien.dap.user.authority.server.service.system.PermissionService;
import com.lucien.dap.user.authority.server.vo.system.PermissionVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName PermissionController
 * @Description
 * @Author GUWM
 * @Date 2019/9/5 16:53
 * @Version 1.0
 */
@RestController
@RequestMapping("/permission")
public class PermissionController {

    private PermissionService permissionService;

    @Autowired
    public void setPermissionService(PermissionService permissionService) {
        this.permissionService = permissionService;
    }

    @RequestMapping("/list")
    public Response<BaseListVo<PermissionVo>> getPermissionList(@RequestBody(required = false) QueryPermissionListDto dto) {
        PermissionEntity entity = new PermissionEntity();
        if (dto != null && StringUtils.isNotBlank(dto.getUrl())) {
            entity.setUrl(dto.getUrl());
        }
        if (dto != null && StringUtils.isNotBlank(dto.getRemark())) {
            entity.setUrl(dto.getRemark());
        }

        BaseListVo<PermissionEntity> permissionList = permissionService.getList(entity, dto == null ? null : dto.getPNo(), dto == null ? null : dto.getPSize());
        BaseListVo<PermissionVo> baseListVo = new BaseListVo<>();
        BeanUtils.copyProperties(permissionList, baseListVo);
        if (permissionList.getTotalCount() > 0) {
            List<PermissionVo> voList = new ArrayList<>();
            for (PermissionEntity PermissionEntity : permissionList.getList()) {
                PermissionVo vo = new PermissionVo();
                BeanUtils.copyProperties(PermissionEntity, vo);
                voList.add(vo);
            }
            baseListVo.setList(voList);
        }
        return Response.success(baseListVo);
    }

    @RequestMapping("/edit")
    public Response<Boolean> edit(@RequestBody PermissionDto dto) {
        PermissionEntity entity = new PermissionEntity();
        BeanUtils.copyProperties(dto, entity);
        permissionService.edit(entity);
        return Response.success(true);
    }

    @RequestMapping("/delete")
    public Response<Boolean> delete(@RequestBody PermissionDto dto) {
        permissionService.delete(dto.getId());
        return Response.success(true);
    }
}
