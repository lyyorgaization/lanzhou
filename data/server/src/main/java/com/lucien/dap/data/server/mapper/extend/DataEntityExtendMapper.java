package com.lucien.dap.data.server.mapper.extend;

import com.lucien.dap.data.server.entity.extend.DataStatisticEntity;
import org.apache.ibatis.annotations.Param;

public interface DataEntityExtendMapper {
    DataStatisticEntity getStatistic(@Param("column") String column, @Param("limit") Integer limit);

    String getLast(@Param("column") String column);
}
