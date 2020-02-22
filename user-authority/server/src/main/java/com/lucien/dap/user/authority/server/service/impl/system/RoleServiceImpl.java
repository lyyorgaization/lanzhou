package com.lucien.dap.user.authority.server.service.impl.system;

import com.lucien.dap.framework.common.constants.Constants;
import com.lucien.dap.framework.common.constants.RetEnum;
import com.lucien.dap.framework.core.domain.BaseListVo;
import com.lucien.dap.framework.core.exception.ApplicationException;
import com.lucien.dap.user.authority.server.mapper.generate.*;
import com.lucien.dap.user.authority.server.model.*;
import com.lucien.dap.user.authority.server.service.system.MenuService;
import com.lucien.dap.user.authority.server.service.system.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @ClassName RoleServiceImpl
 * @Description
 * @Author MALUHAN
 * @Date 2019/7/9 18:14
 * @Version 1.0
 */
@Service
public class RoleServiceImpl implements RoleService {
    private MenuService menuService;

    private RoleEntityMapper roleEntityMapper;

    private RoleMenuEntityMapper roleMenuEntityMapper;

    private UserRoleEntityMapper userRoleEntityMapper;

    private RolePermissionEntityMapper rolePermissionEntityMapper;

    private PermissionEntityMapper permissionEntityMapper;

    @Autowired
    public void setMenuService(MenuService menuService) {
        this.menuService = menuService;
    }

    @Autowired
    public void setRoleEntityMapper(RoleEntityMapper roleEntityMapper) {
        this.roleEntityMapper = roleEntityMapper;
    }

    @Autowired
    public void setRoleMenuEntityMapper(RoleMenuEntityMapper roleMenuEntityMapper) {
        this.roleMenuEntityMapper = roleMenuEntityMapper;
    }

    @Autowired
    public void setUserRoleEntityMapper(UserRoleEntityMapper userRoleEntityMapper) {
        this.userRoleEntityMapper = userRoleEntityMapper;
    }

    @Autowired
    public void setRolePermissionEntityMapper(RolePermissionEntityMapper rolePermissionEntityMapper) {
        this.rolePermissionEntityMapper = rolePermissionEntityMapper;
    }

    @Autowired
    public void setPermissionEntityMapper(PermissionEntityMapper permissionEntityMapper) {
        this.permissionEntityMapper = permissionEntityMapper;
    }

    @Override
    public Boolean edit(RoleEntity entity) {
        if (entity.getId() == null) {
            entity.setCreateTime(new Date());
            entity.setModifyTime(new Date());
            entity.setId(null);
            roleEntityMapper.insert(entity);
        } else {
            entity.setModifyTime(new Date());
            roleEntityMapper.updateByPrimaryKeySelective(entity);
        }
        return true;
    }

    @Override
    public BaseListVo<RoleEntity> getRoleList(RoleEntity roleEntity, Integer pNo, Integer pSize) {
        RoleEntityExample example = new RoleEntityExample();
        RoleEntityExample.Criteria criteria = example.createCriteria();
        if (roleEntity.getRoleName() != null) {
            criteria.andRoleNameLike("%" + roleEntity.getRoleName() + "%");
        }

        BaseListVo<RoleEntity> baseListVo = new BaseListVo<>();
        long count = roleEntityMapper.countByExample(example);
        baseListVo.setTotalCount(count);
        if (pNo == null) {
            pNo = Constants.DEFAULT_PNO;
        }
        if (pSize == null) {
            pSize = Constants.DEFAULT_PSIZE;
        }
        baseListVo.setPNo(pNo);
        baseListVo.setPSize(pSize);
        if (count > 0) {
            example.setOffset((pNo - 1) * pSize);
            example.setLimit(pSize);
            List<RoleEntity> roleEntitys = roleEntityMapper.selectByExample(example);
            baseListVo.setList(roleEntitys);
            List<RoleEntity> entities = roleEntityMapper.selectByExample(example);
            baseListVo.setList(entities);
        }
        return baseListVo;
    }

    @Override
    public List<MenuEntity> getRoleMenuList(List<Long> roleIds) {
        if (CollectionUtils.isEmpty(roleIds)) {
            throw new ApplicationException(RetEnum.RequestEmpty);
        }
        RoleMenuEntityExample example = new RoleMenuEntityExample();
        RoleMenuEntityExample.Criteria criteria = example.createCriteria();
        criteria.andRoleIdIn(roleIds);
        List<RoleMenuEntity> roleMenuEntitys = roleMenuEntityMapper.selectByExample(example);
        if (!CollectionUtils.isEmpty(roleMenuEntitys)) {
            List<Long> menuIds = roleMenuEntitys.stream().map(RoleMenuEntity::getMenuId).collect(Collectors.toList());
            return menuService.getMenuList(menuIds);
        } else {
            return null;
        }
    }


    @Override
    @Transactional
    public Boolean editRoleMenu(Long roleId, List<Long> menuIds) {
        if (roleId == null || CollectionUtils.isEmpty(menuIds)) {
            throw new ApplicationException(RetEnum.RequestEmpty);
        }
        RoleMenuEntityExample delExample = new RoleMenuEntityExample();
        RoleMenuEntityExample.Criteria delCriteria = delExample.createCriteria();
        delCriteria.andRoleIdEqualTo(roleId);
        roleMenuEntityMapper.deleteByExample(delExample);

        for (Long menuId : menuIds) {
            RoleMenuEntity Entity = new RoleMenuEntity();
            Entity.setMenuId(menuId);
            Entity.setRoleId(roleId);
            roleMenuEntityMapper.insert(Entity);
        }
        return true;
    }

    @Override
    @Transactional
    public Boolean delete(Long roleId) {
        if (roleId == null) {
            throw new ApplicationException(RetEnum.RequestEmpty);
        }

        UserRoleEntityExample userRoleExample = new UserRoleEntityExample();
        UserRoleEntityExample.Criteria criteria = userRoleExample.createCriteria();
        criteria.andRoleIdEqualTo(roleId);
        userRoleEntityMapper.deleteByExample(userRoleExample);

        roleEntityMapper.deleteByPrimaryKey(roleId);
        RoleMenuEntityExample delExample = new RoleMenuEntityExample();
        RoleMenuEntityExample.Criteria delCriteria = delExample.createCriteria();
        delCriteria.andRoleIdEqualTo(roleId);
        roleMenuEntityMapper.deleteByExample(delExample);

        //级联删除role_permission表数据
        RolePermissionEntityExample roleExample = new RolePermissionEntityExample();
        RolePermissionEntityExample.Criteria roleCriteria = roleExample.createCriteria();
        roleCriteria.andRoleIdEqualTo(roleId);
        rolePermissionEntityMapper.deleteByExample(roleExample);
        return true;
    }

    @Override
    public List<PermissionEntity> getPermissionByRoleIds(List<Long> roleIds) {
        RolePermissionEntityExample example = new RolePermissionEntityExample();
        RolePermissionEntityExample.Criteria criteria = example.createCriteria();
        criteria.andRoleIdIn(roleIds);
        List<RolePermissionEntity> rolePermissionEntities = rolePermissionEntityMapper.selectByExample(example);
        List<Long> permissionIds = rolePermissionEntities.stream().map(RolePermissionEntity::getPermissionId).collect(Collectors.toList());

        PermissionEntityExample permissionExample = new PermissionEntityExample();
        PermissionEntityExample.Criteria permissionCriteria = permissionExample.createCriteria();
        permissionCriteria.andIdIn(permissionIds);

        return permissionEntityMapper.selectByExample(permissionExample);
    }

    @Override
    public Map<Long, RoleEntity> getByRoleIds(List<Long> roleIds) {
        if (roleIds.size() <= 0) {
            throw new ApplicationException(RetEnum.RequestInvalid);
        }

        RoleEntityExample example = new RoleEntityExample();
        RoleEntityExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(roleIds);
        List<RoleEntity> entities = roleEntityMapper.selectByExample(example);
        Map<Long, RoleEntity> resultMap = entities.stream().collect(Collectors.toMap(RoleEntity::getId, Function.identity()));
        return resultMap.isEmpty() ? null : resultMap;
    }
}

