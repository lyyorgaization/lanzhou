package com.lucien.dap.user.authority.server.dto.system;

import java.util.List;

import lombok.Data;

@Data
public class RoleMenuDto {
	private Long roleId;
	private List<Long> menuIds;
}
