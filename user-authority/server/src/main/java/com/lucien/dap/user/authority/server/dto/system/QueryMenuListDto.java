package com.lucien.dap.user.authority.server.dto.system;

import lombok.Data;

/**
 * @ClassName GetMenuList
 * @Description
 * @Author MALUHAN
 * @Date 2019/7/10 17:03
 * @Version 1.0
 */
@Data
public class QueryMenuListDto {
    private String code;
    private String menuName;
    private Long parentId;
    private Integer pNo;
    private Integer pSize;
}
