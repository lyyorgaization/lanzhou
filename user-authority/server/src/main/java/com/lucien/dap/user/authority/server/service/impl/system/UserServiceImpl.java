package com.lucien.dap.user.authority.server.service.impl.system;

import com.lucien.dap.framework.common.constants.Constants;
import com.lucien.dap.framework.common.constants.RetEnum;
import com.lucien.dap.framework.core.domain.BaseListVo;
import com.lucien.dap.framework.core.exception.ApplicationException;
import com.lucien.dap.user.authority.server.common.OmpConstants;
import com.lucien.dap.user.authority.server.mapper.generate.UserEntityMapper;
import com.lucien.dap.user.authority.server.mapper.generate.UserRoleEntityMapper;
import com.lucien.dap.user.authority.server.model.*;
import com.lucien.dap.user.authority.server.service.system.MenuService;
import com.lucien.dap.user.authority.server.service.system.RoleService;
import com.lucien.dap.user.authority.server.service.system.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName userService
 * @Description
 * @Author MALUHAN
 * @Date 2019/7/1 11:08
 * @Version 1.0
 */
@Service
public class UserServiceImpl implements UserService {
    private UserEntityMapper userEntityMapper;
    private UserRoleEntityMapper userRoleEntityMapper;
    private RoleService roleService;
    private MenuService menuService;

    @Autowired
    public void setUserEntityMapper(UserEntityMapper userEntityMapper) {
        this.userEntityMapper = userEntityMapper;
    }

    @Autowired
    public void setUserRoleEntityMapper(UserRoleEntityMapper userRoleEntityMapper) {
        this.userRoleEntityMapper = userRoleEntityMapper;
    }

    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    @Autowired
    public void setMenuService(MenuService menuService) {
        this.menuService = menuService;
    }

    @Override
    public UserEntity getUser(String userName) {
        UserEntityExample example = new UserEntityExample();
        UserEntityExample.Criteria criteria = example.createCriteria();
        criteria.andUsernameEqualTo(userName);
        List<UserEntity> entities = userEntityMapper.selectByExample(example);
        if (!CollectionUtils.isEmpty(entities)) {
            return entities.get(0);
        } else {
            return null;
        }
    }

    public BaseListVo<UserEntity> getUserList(UserEntity UserEntity, Integer pSize, Integer pNo) {
        UserEntityExample example = new UserEntityExample();
        UserEntityExample.Criteria criteria = example.createCriteria();
        if (UserEntity.getStatus() != null) {
            criteria.andStatusEqualTo(UserEntity.getStatus());
        }
        if (!StringUtils.isEmpty(UserEntity.getUsername())) {
            criteria.andUsernameLike("%" + UserEntity.getUsername() + "%");
        }
        if (!StringUtils.isEmpty(UserEntity.getNickname())) {
            criteria.andNicknameLike("%" + UserEntity.getNickname() + "%");
        }

        long count = userEntityMapper.countByExample(example);
        BaseListVo<UserEntity> baseListVo = new BaseListVo<>();
        if (count > 0) {
            if (pNo == null) {
                pNo = Constants.DEFAULT_PNO;
            }
            if (pSize == null) {
                pSize = Constants.DEFAULT_PSIZE;
            }
            example.setOffset((pNo - 1) * pSize);
            example.setLimit(pSize);

            List<UserEntity> entities = userEntityMapper.selectByExample(example);
            baseListVo.setList(entities);
            baseListVo.setPNo(pNo);
            baseListVo.setPSize(pSize);
        }
        baseListVo.setTotalCount(count);
        baseListVo.setPNo(1);
        baseListVo.setPSize(pSize);
        return baseListVo;
    }

    @Override
    public Boolean add(UserEntity UserEntity) {
        if (UserEntity.getUsername() == null || UserEntity.getNickname() == null) {
            throw new ApplicationException(RetEnum.RequestEmpty);
        }
        UserEntity.setId(null);
        UserEntity.setCreateTime(new Date());
        UserEntity.setModifyTime(new Date());
        UserEntity.setPassword(OmpConstants.DEFAULT_PASSWORD);
        if (UserEntity.getStatus() == null) {
            UserEntity.setStatus(1);
        }
        userEntityMapper.insert(UserEntity);
        return true;
    }

    @Override
    public Boolean edit(UserEntity UserEntity) {
        if (UserEntity.getId() == null) {
            this.add(UserEntity);
        }
        UserEntity.setModifyTime(new Date());
        userEntityMapper.updateByPrimaryKeySelective(UserEntity);
        return true;
    }

    @Override
    public List<Long> getUserRoleList(Long userId) {
        if (userId == null || userId == 0) {
            throw new ApplicationException(RetEnum.RequestEmpty);
        }
        UserRoleEntityExample example = new UserRoleEntityExample();
        UserRoleEntityExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(userId);
        List<UserRoleEntity> userRoleEntitys = userRoleEntityMapper.selectByExample(example);
        return userRoleEntitys.stream().map(UserRoleEntity::getRoleId).collect(Collectors.toList());
    }

    @Override
    public List<MenuEntity> getUserMenu(Long userId) {
        List<Long> roleIds = this.getUserRoleList(userId);
        if (!CollectionUtils.isEmpty(roleIds)) {
            return roleService.getRoleMenuList(roleIds);
        } else {
            return null;
        }
    }

    @Override
    @Transactional
    public Boolean editUserRole(Long userId, List<Long> roleIds) {
        if (userId == null || CollectionUtils.isEmpty(roleIds)) {
            throw new ApplicationException(RetEnum.RequestEmpty);
        }
        UserRoleEntityExample deleteExample = new UserRoleEntityExample();
        UserRoleEntityExample.Criteria deleteCriteria = deleteExample.createCriteria();
        deleteCriteria.andUserIdEqualTo(userId);
        userRoleEntityMapper.deleteByExample(deleteExample);

        for (Long roleId : roleIds) {
            UserRoleEntity Entity = new UserRoleEntity();
            Entity.setUserId(userId);
            Entity.setRoleId(roleId);
            userRoleEntityMapper.insert(Entity);
        }
        return true;
    }

    @Override
    @Transactional
    public Boolean deleteUser(Long userId) {
        if (userId == null) {
            throw new ApplicationException(RetEnum.RequestEmpty);
        }
        if (userId == 1) {
            throw new ApplicationException(RetEnum.RequestInvalid.getCode(), "该账户不能删除");
        }
        userEntityMapper.deleteByPrimaryKey(userId);
        UserRoleEntityExample example = new UserRoleEntityExample();
        UserRoleEntityExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(userId);
        userRoleEntityMapper.deleteByExample(example);
        return true;
    }

    @Override
    public Boolean initPassword(Long userId) {
        if (userId == null) {
            throw new ApplicationException(RetEnum.RequestEmpty);
        }
        UserEntity UserEntity = new UserEntity();
        UserEntity.setId(userId);
        UserEntity.setPassword(OmpConstants.DEFAULT_PASSWORD);
        userEntityMapper.updateByPrimaryKeySelective(UserEntity);
        return true;
    }

    @Override
    public List<PermissionEntity> getPermissionByUserIds(Long userId) {
        List<Long> roleIds = this.getUserRoleList(userId);
        return roleService.getPermissionByRoleIds(roleIds);
    }
}
