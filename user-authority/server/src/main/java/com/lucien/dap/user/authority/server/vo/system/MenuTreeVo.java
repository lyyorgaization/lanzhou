package com.lucien.dap.user.authority.server.vo.system;

import lombok.Data;

import java.util.List;

/**
 * @ClassName MenuVo
 * @Description
 * @Author MALUHAN
 * @Date 2019/7/10 17:06
 * @Version 1.0
 */
@Data
public class MenuTreeVo {
    private Long id;
    private String index;
    private String title;
    private String url;
    private String icon;
    private List<MenuTreeVo> subs;
}
