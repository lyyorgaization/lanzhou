package com.lucien.dap.framework.core.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName BaselListVo
 * @Description
 * @Author MALUHAN
 * @Date 2019/6/20 15:48
 * @Version 1.0
 */
@Data
public class BaseListVo<T> implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer pNo;
    private Integer pSize;
    private Long totalCount;
    private List<T> list;
}
