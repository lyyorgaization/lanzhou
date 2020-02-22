package com.lucien.dap.user.authority.server.service.impl.system;

import com.lucien.dap.user.authority.server.mapper.generate.PermissionEntityMapper;
import com.lucien.dap.user.authority.server.mapper.generate.RolePermissionEntityMapper;
import com.lucien.dap.user.authority.server.model.PermissionEntity;
import com.lucien.dap.user.authority.server.model.PermissionEntityExample;
import com.lucien.dap.user.authority.server.model.RolePermissionEntityExample;
import com.lucien.dap.user.authority.server.service.system.AuthPermissionService;
import com.lucien.dap.user.authority.server.service.system.PermissionService;
import com.lucien.dap.framework.common.constants.Constants;
import com.lucien.dap.framework.common.constants.RetEnum;
import com.lucien.dap.framework.core.domain.BaseListVo;
import com.lucien.dap.framework.core.exception.ApplicationException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class PermissionServiceImpl implements PermissionService {

    private PermissionEntityMapper permissionEntityMapper;
    private AuthPermissionService authPermissionService;

    @Autowired
    public void setPermissionEntityMapper(PermissionEntityMapper permissionEntityMapper) {
        this.permissionEntityMapper = permissionEntityMapper;
    }

    private RolePermissionEntityMapper rolePermissionEntityMapper;

    @Autowired
    public void setRolePermissionEntityMapper(RolePermissionEntityMapper rolePermissionEntityMapper) {
        this.rolePermissionEntityMapper = rolePermissionEntityMapper;
    }

    @Autowired
    public void setAuthPermissionService(AuthPermissionService authPermissionService) {
        this.authPermissionService = authPermissionService;
    }

    @Override
    public BaseListVo<PermissionEntity> getList(PermissionEntity model, Integer pNo, Integer pSize) {
        PermissionEntityExample example = new PermissionEntityExample();
        PermissionEntityExample.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotBlank(model.getUrl())) {
            criteria.andUrlLike("%" + model.getUrl() + "%");
        }
        if (StringUtils.isNotBlank(model.getRemark())) {
            criteria.andRemarkLike("%" + model.getRemark() + "%");
        }
        if (pNo == null) {
            pNo = Constants.DEFAULT_PNO;
        }
        if (pSize == null) {
            pSize = Constants.DEFAULT_PSIZE;
        }

        long count = permissionEntityMapper.countByExample(example);
        BaseListVo<PermissionEntity> baseListVo = new BaseListVo<>();
        baseListVo.setPNo(pNo);
        baseListVo.setPSize(pSize);
        baseListVo.setTotalCount(count);
        if (count > 0) {
            example.setOffset((pNo - 1) * pSize);
            example.setLimit(pSize);
            List<PermissionEntity> models = permissionEntityMapper.selectByExample(example);
            baseListVo.setList(models);
        }
        return baseListVo;
    }

    @Override
    public boolean edit(PermissionEntity model) {
        Date nowDate = new Date();
        if (model.getId() == null || model.getId() == 0) {
            model.setId(null);
            model.setCreateTime(nowDate);
            model.setModifyTime(nowDate);
            permissionEntityMapper.insert(model);
        } else {
            model.setModifyTime(nowDate);
            permissionEntityMapper.updateByPrimaryKeySelective(model);
        }
        authPermissionService.updatePermission();
        return true;
    }

    @Override
    @Transactional
    public boolean delete(Long permissionId) {
        if (permissionId == null || permissionId == 0) {
            throw new ApplicationException(RetEnum.RequestEmpty);
        }
        PermissionEntityExample example = new PermissionEntityExample();
        PermissionEntityExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(permissionId);
        permissionEntityMapper.deleteByExample(example);
        //级联删除role_permission表数据
        RolePermissionEntityExample roleExample = new RolePermissionEntityExample();
        RolePermissionEntityExample.Criteria roleCriteria = roleExample.createCriteria();
        roleCriteria.andPermissionIdEqualTo(permissionId);
        rolePermissionEntityMapper.deleteByExample(roleExample);
        authPermissionService.updatePermission();
        return true;
    }

    @Override
    public Map<Long, PermissionEntity> getByPermissionIds(List<Long> permissionIds) {
        if (permissionIds.size() <= 0) {
            throw new ApplicationException(RetEnum.RequestInvalid);
        }

        PermissionEntityExample example = new PermissionEntityExample();
        PermissionEntityExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(permissionIds);
        List<PermissionEntity> entities = permissionEntityMapper.selectByExample(example);
        Map<Long, PermissionEntity> resultMap = entities.stream().collect(Collectors.toMap(PermissionEntity::getId, Function.identity()));
        return resultMap.isEmpty() ? null : resultMap;
    }
}
