package com.lucien.dap.user.authority.server.controller.system;

import com.lucien.dap.framework.core.domain.BaseListVo;
import com.lucien.dap.framework.core.domain.Response;
import com.lucien.dap.framework.core.utils.CollectionUtil;
import com.lucien.dap.user.authority.server.common.MenuUtil;
import com.lucien.dap.user.authority.server.dto.system.MenuDto;
import com.lucien.dap.user.authority.server.dto.system.QueryMenuListDto;
import com.lucien.dap.user.authority.server.model.MenuEntity;
import com.lucien.dap.user.authority.server.service.system.MenuService;
import com.lucien.dap.user.authority.server.vo.system.MenuTreeVo;
import com.lucien.dap.user.authority.server.vo.system.MenuVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @ClassName MenuController
 * @Description
 * @Author MALUHAN
 * @Date 2019/7/10 16:53
 * @Version 1.0
 */
@RestController
@RequestMapping("/menu")
public class MenuController {
	private MenuService menuService;

	@Autowired
	public void setMenuService(MenuService menuService) {
		this.menuService = menuService;
	}

	@RequestMapping("/menuList")
	public Response<BaseListVo<MenuVo>> getMenuList(@RequestBody QueryMenuListDto dto) {
		MenuEntity Entity = new MenuEntity();
		Entity.setCode(dto.getCode());
		Entity.setMenuName(dto.getMenuName());
		Entity.setParentId(dto.getParentId());
		BaseListVo<MenuEntity> menuList = menuService.getMenuList(Entity, dto.getPNo(), dto.getPSize());
		BaseListVo<MenuVo> baseListVo = new BaseListVo<>();
		BeanUtils.copyProperties(menuList, baseListVo);
		if (menuList.getTotalCount() > 0) {
			List<MenuVo> voList = new ArrayList<>();
			for (MenuEntity menuEntity : menuList.getList()) {
				MenuVo vo = new MenuVo();
				BeanUtils.copyProperties(menuEntity, vo);
				voList.add(vo);
			}
			baseListVo.setList(voList);
		}
		return Response.success(baseListVo);
	}

	@RequestMapping("/menuTree")
	public Response<List<MenuTreeVo>> getMenuTree() {
		BaseListVo<MenuEntity> menuList = menuService.getMenuList(new MenuEntity(), 1, 1000);
		List<MenuTreeVo> menuTreeVoList = new ArrayList<>();
		if (!CollectionUtil.isEmpty(menuList.getList())) {
			Stream<MenuEntity> menuEntityStream = menuList.getList().stream().filter(Entity ->
					Entity.getParentId() == null || Entity.getParentId().equals(0L)
			);
			List<MenuEntity> topMenuList = menuEntityStream.collect(Collectors.toList());
			Map<Long, List<MenuEntity>> map = menuList.getList().stream().collect(Collectors.groupingBy(MenuEntity::getParentId));
			for (MenuEntity entity : topMenuList) {
				menuTreeVoList.add(MenuUtil.convertVo(entity, map));

			}
		}
		return Response.success(menuTreeVoList);
	}

	@RequestMapping("/edit")
	public Response<Boolean> edit(@RequestBody MenuDto dto) {
		MenuEntity entity = new MenuEntity();
		BeanUtils.copyProperties(dto, entity);
		menuService.edit(entity);
		return Response.success(true);
	}

	@RequestMapping("/delete")
	public Response<Boolean> delete(@RequestBody MenuDto dto) {
		menuService.delete(dto.getId());
		return Response.success(true);
	}
}
