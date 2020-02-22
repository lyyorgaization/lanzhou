package com.lucien.dap.user.authority.server.common;

import com.lucien.dap.user.authority.server.model.MenuEntity;
import com.lucien.dap.user.authority.server.vo.system.MenuTreeVo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MenuUtil {
    public static MenuTreeVo convertVo(MenuEntity model, Map<Long, List<MenuEntity>> map) {
        MenuTreeVo vo = new MenuTreeVo();
        vo.setId(model.getId());
        vo.setIndex(model.getCode());
        vo.setTitle(model.getMenuName());
        vo.setUrl(model.getUrl());
        vo.setIcon(model.getIcon());
        List<MenuEntity> children = map.get(model.getId());
        if (!org.springframework.util.CollectionUtils.isEmpty(children)) {
            List<MenuTreeVo> listVo = new ArrayList<>();
            for (MenuEntity childModel : children) {
                listVo.add(convertVo(childModel, map));
            }
            vo.setSubs(listVo);
        }
        return vo;
    }
}
