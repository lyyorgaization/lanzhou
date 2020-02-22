package com.lucien.dap.user.authority.server.controller.system;

import com.lucien.dap.framework.core.domain.BaseListVo;
import com.lucien.dap.framework.core.domain.Response;
import com.lucien.dap.user.authority.server.controller.BaseController;
import com.lucien.dap.user.authority.server.dto.system.QueryRoleDto;
import com.lucien.dap.user.authority.server.dto.system.RoleDto;
import com.lucien.dap.user.authority.server.dto.system.RoleMenuDto;
import com.lucien.dap.user.authority.server.model.MenuEntity;
import com.lucien.dap.user.authority.server.model.RoleEntity;
import com.lucien.dap.user.authority.server.service.system.RoleService;
import com.lucien.dap.user.authority.server.vo.system.MenuVo;
import com.lucien.dap.user.authority.server.vo.system.RoleVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName RoleController
 * @Description
 * @Author MALUHAN
 * @Date 2019/7/9 18:10
 * @Version 1.0
 */
@RestController
@RequestMapping("/role")
public class RoleController extends BaseController {

	private RoleService roleService;

	@Autowired
	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

	@RequestMapping("/edit")
	public Response<Boolean> edit(@RequestBody RoleDto dto) {
		RoleEntity model = new RoleEntity();
		BeanUtils.copyProperties(dto, model);
		roleService.edit(model);
		return Response.success(true);
	}

	@RequestMapping("/getAllRole")
	public Response<BaseListVo<RoleVo>> getAllRole(@RequestBody QueryRoleDto dto) {
		RoleEntity model = new RoleEntity();
		model.setRoleName(dto.getRoleName());
		BaseListVo<RoleEntity> listModel = roleService.getRoleList(model, dto.getPNo(), dto.getPSize());
		BaseListVo<RoleVo> baseListVo = new BaseListVo<>();
		if (!CollectionUtils.isEmpty(listModel.getList())) {
			List<RoleVo> roleVoList = new ArrayList<>();
			for (RoleEntity RoleEntity : listModel.getList()) {
				RoleVo vo = new RoleVo();
				BeanUtils.copyProperties(RoleEntity, vo);
				roleVoList.add(vo);
			}
			baseListVo.setList(roleVoList);
		}
		baseListVo.setTotalCount(listModel.getTotalCount());
		baseListVo.setPSize(listModel.getPSize());
		baseListVo.setPNo(listModel.getPNo());
		return Response.success(baseListVo);
	}

	@RequestMapping("/delete")
	public Response<Boolean> delete(@RequestBody RoleDto dto) {
		roleService.delete(dto.getId());
		return Response.success(true);
	}

	@RequestMapping("/editRoleMenu")
	public Response<Boolean> editRoleMenu(@RequestBody RoleMenuDto dto) {
		roleService.editRoleMenu(dto.getRoleId(), dto.getMenuIds());
		return Response.success(true);
	}

	@RequestMapping("/getRoleMenu")
	public Response<List<MenuVo>> getRoelMenu(@RequestBody RoleDto dto) {
		List<Long> roleIds = new ArrayList<>();
		if (dto.getId() != null && dto.getId() != 0) {
			roleIds.add(dto.getId());
		}
		List<MenuEntity> roleMenuList = roleService.getRoleMenuList(roleIds);
		if (!CollectionUtils.isEmpty(roleMenuList)) {
			List<MenuVo> menuVos = new ArrayList<>();
			for (MenuEntity model : roleMenuList) {
				MenuVo vo = new MenuVo();
				BeanUtils.copyProperties(model, vo);
				menuVos.add(vo);
			}
			return Response.success(menuVos);
		} else {
			return Response.success(null);
		}
	}
}
