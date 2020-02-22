package com.lucien.dap.user.authority.server.service.impl.system;

import com.lucien.dap.user.authority.server.mapper.generate.RolePermissionEntityMapper;
import com.lucien.dap.user.authority.server.model.PermissionEntity;
import com.lucien.dap.user.authority.server.model.RoleEntity;
import com.lucien.dap.user.authority.server.model.RolePermissionEntity;
import com.lucien.dap.user.authority.server.model.RolePermissionEntityExample;
import com.lucien.dap.user.authority.server.service.system.RolePermissionService;
import com.lucien.dap.user.authority.server.service.system.RoleService;
import com.lucien.dap.user.authority.server.vo.system.RolePermissionVo;
import com.lucien.dap.user.authority.server.service.system.PermissionService;
import com.lucien.dap.framework.common.constants.RetEnum;
import com.lucien.dap.framework.core.domain.BaseListVo;
import com.lucien.dap.framework.core.exception.ApplicationException;
import com.lucien.dap.framework.core.utils.CollectionUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class RolePermissionServiceImpl implements RolePermissionService {

    private RoleService roleService;

    private PermissionService permissionService;

    private RolePermissionEntityMapper rolePermissionEntityMapper;

    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    @Autowired
    public void setPermissionService(PermissionService permissionService) {
        this.permissionService = permissionService;
    }

    @Autowired
    public void setRolePermissionEntityMapper(RolePermissionEntityMapper rolePermissionEntityMapper) {
        this.rolePermissionEntityMapper = rolePermissionEntityMapper;
    }

    @Override
    public boolean editRolePermission(Long roleId, List<Long> permissionIds) {
        Date nowDate = new Date();
        if (roleId == null || CollectionUtils.isEmpty(permissionIds)) {
            throw new ApplicationException(RetEnum.RequestEmpty);
        }
        RolePermissionEntityExample example = new RolePermissionEntityExample();
        RolePermissionEntityExample.Criteria criteria = example.createCriteria();
        criteria.andRoleIdEqualTo(roleId);
        rolePermissionEntityMapper.deleteByExample(example);

        for (Long permissionId : permissionIds) {
            RolePermissionEntity entity = new RolePermissionEntity();
            entity.setRoleId(roleId);
            entity.setCreateTime(nowDate);
            entity.setPermissionId(permissionId);
            rolePermissionEntityMapper.insert(entity);
        }
        return true;
    }

    @Override
    public BaseListVo<RolePermissionVo> getRolePermissionList(RolePermissionEntity model) {

        RolePermissionEntityExample example = new RolePermissionEntityExample();
        RolePermissionEntityExample.Criteria criteria = example.createCriteria();
        if (model.getRoleId() != null) {
            criteria.andRoleIdEqualTo(model.getRoleId());
        }
        long count = rolePermissionEntityMapper.countByExample(example);
        BaseListVo<RolePermissionVo> baseListVo = new BaseListVo<>();
        baseListVo.setTotalCount(count);
        List<RolePermissionVo> voList = new ArrayList<>();
        if (count > 0) {
            List<RolePermissionEntity> entitys = rolePermissionEntityMapper.selectByExample(example);
            if (CollectionUtil.isNotEmpty(entitys)) {
                //批量查询role表
                List<Long> roleIds = entitys.stream().map(RolePermissionEntity::getRoleId).collect(Collectors.toList());
                Map<Long, RoleEntity> roleEntityMap = roleService.getByRoleIds(roleIds);

                //批量查询permission表
                List<Long> permissionIds = entitys.stream().map(RolePermissionEntity::getPermissionId).collect(Collectors.toList());
                Map<Long, PermissionEntity> permissionEntityMap = permissionService.getByPermissionIds(permissionIds);

                for (RolePermissionEntity entity : entitys) {
                    RolePermissionVo vo = new RolePermissionVo();
                    BeanUtils.copyProperties(entity, vo);
                    if (!roleEntityMap.isEmpty()) {
                        RoleEntity roleEntity = roleEntityMap.get(entity.getRoleId());
                        vo.setRoleName(roleEntity.getRoleName());
                    }

                    if (!permissionEntityMap.isEmpty()) {
                        PermissionEntity permissionEntity = permissionEntityMap.get(entity.getPermissionId());
                        vo.setUrl(permissionEntity.getUrl());
                        vo.setDesc(permissionEntity.getRemark());
                    }
                    voList.add(vo);
                }
            }

            baseListVo.setList(voList);
        }
        return baseListVo;
    }

    @Override
    public boolean delete(Long rolePermissionId) {
        if (rolePermissionId == null || rolePermissionId == 0) {
            throw new ApplicationException(RetEnum.RequestEmpty);
        }
        RolePermissionEntityExample example = new RolePermissionEntityExample();
        RolePermissionEntityExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(rolePermissionId);
        rolePermissionEntityMapper.deleteByExample(example);
        return true;
    }
}
