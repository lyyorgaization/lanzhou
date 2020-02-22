package com.lucien.dap.user.authority.server.dto.system;

import lombok.Data;

/**
 * @ClassName QueryPermissionListDto
 * @Description
 * @Author GUWM
 * @Date 2019/9/5 17:03
 * @Version 1.0
 */
@Data
public class QueryPermissionListDto {
    private String url;
    private String remark;
    private Integer pNo;
    private Integer pSize;
}
