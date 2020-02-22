package com.lucien.dap.user.authority.server.service.system;

import com.lucien.dap.framework.core.domain.BaseListVo;
import com.lucien.dap.user.authority.server.model.MenuEntity;

import java.util.List;

/**
 * @ClassName MenuService
 * @Description
 * @Author MALUHAN
 * @Date 2019/7/10 10:37
 * @Version 1.0
 */
public interface MenuService {
    boolean edit(MenuEntity model);

    BaseListVo<MenuEntity> getMenuList(MenuEntity model, Integer pNo, Integer pSize);

    List<MenuEntity> getMenuList(List<Long> menuIds);

	boolean delete(Long menuId);
}
