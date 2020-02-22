package com.lucien.dap.user.authority.server.dto.system;

import lombok.Data;

@Data
public class MenuDto {
	private Long id;
	private String menuName;
	private Long parentId;
	private String icon;
	private String code;
	private String url;
    private Integer sortNumber;
}
