package com.lucien.dap.user.authority.server.vo.system;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName MenuVo
 * @Description
 * @Author MALUHAN
 * @Date 2019/7/11 9:56
 * @Version 1.0
 */
@Data
public class MenuVo implements Serializable {
	private Long id;
	private Long parentId;
	private String menuName;
	private String code;
	private String url;
	private String icon;
	private Integer sortNumber;
}
