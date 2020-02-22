package com.lucien.dap.user.authority.server.service.impl.system;

import com.lucien.dap.user.authority.server.mapper.generate.MenuEntityMapper;
import com.lucien.dap.user.authority.server.mapper.generate.RoleMenuEntityMapper;
import com.lucien.dap.user.authority.server.model.MenuEntity;
import com.lucien.dap.user.authority.server.model.MenuEntityExample;
import com.lucien.dap.user.authority.server.model.RoleMenuEntityExample;
import com.lucien.dap.user.authority.server.service.system.MenuService;
import com.lucien.dap.framework.common.constants.Constants;
import com.lucien.dap.framework.common.constants.RetEnum;
import com.lucien.dap.framework.core.domain.BaseListVo;
import com.lucien.dap.framework.core.exception.ApplicationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;

/**
 * @ClassName MenuServiceImpl
 * @Description
 * @Author MALUHAN
 * @Date 2019/7/10 10:37
 * @Version 1.0
 */
@Service
public class MenuServiceImpl implements MenuService {

    private MenuEntityMapper menuEntityMapper;

    private RoleMenuEntityMapper roleMenuEntityMapper;

    @Autowired
    public void setMenuEntityMapper(MenuEntityMapper menuEntityMapper) {
        this.menuEntityMapper = menuEntityMapper;
    }

    @Autowired
    public void setRoleMenuEntityMapper(RoleMenuEntityMapper roleMenuEntityMapper) {
        this.roleMenuEntityMapper = roleMenuEntityMapper;
    }

    @Override
    public boolean edit(MenuEntity model) {
        if (model.getId() == null || model.getId() == 0) {
            model.setId(null);
            model.setCreateTime(new Date());
            model.setModifyTime(new Date());
            menuEntityMapper.insert(model);
        } else {
            model.setModifyTime(new Date());
            menuEntityMapper.updateByPrimaryKeySelective(model);
        }
        return true;
    }
    @Override
    public BaseListVo<MenuEntity> getMenuList(MenuEntity model, Integer pNo, Integer pSize) {
        MenuEntityExample example = new MenuEntityExample();
        MenuEntityExample.Criteria criteria = example.createCriteria();
        if (model.getMenuName() != null) {
            criteria.andMenuNameLike("%" + model.getMenuName() + "%");
        }
        if (pNo == null) {
            pNo = Constants.DEFAULT_PNO;
        }
        if (pSize == null) {
            pSize = Constants.DEFAULT_PSIZE;
        }

        //按sortNumber升序排序
        example.setOrderByClause("sort_number asc");

        long count = menuEntityMapper.countByExample(example);
        BaseListVo<MenuEntity> baseListVo = new BaseListVo<>();
        baseListVo.setPNo(pNo);
        baseListVo.setPSize(pSize);
        baseListVo.setTotalCount(count);
        if (count > 0) {
            example.setOffset((pNo - 1) * pSize);
            example.setLimit(pSize);
            List<MenuEntity> menuModels = menuEntityMapper.selectByExample(example);
            baseListVo.setList(menuModels);
        }
        return baseListVo;
    }

    @Override
    public List<MenuEntity> getMenuList(List<Long> menuIds) {
        if (CollectionUtils.isEmpty(menuIds)) {
            throw new ApplicationException(RetEnum.RequestEmpty);
        }
        MenuEntityExample example = new MenuEntityExample();
        MenuEntityExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(menuIds);
        example.setOrderByClause("sort_number asc");
        return menuEntityMapper.selectByExample(example);
    }

    @Override
    @Transactional
    public boolean delete(Long menuId) {
        if (menuId == null || menuId == 0) {
            throw new ApplicationException(RetEnum.RequestEmpty);
        }
        RoleMenuEntityExample example = new RoleMenuEntityExample();
        RoleMenuEntityExample.Criteria criteria = example.createCriteria();
        criteria.andMenuIdEqualTo(menuId);
        roleMenuEntityMapper.deleteByExample(example);
        menuEntityMapper.deleteByPrimaryKey(menuId);
        return true;
    }
}
