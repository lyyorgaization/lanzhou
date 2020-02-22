package com.lucien.dap.data.server.mapper.extend;

import com.lucien.dap.data.server.entity.extend.DataStatisticEntity;

public interface DataEntityExtendMapper {
    DataStatisticEntity getStatistic(String column, Integer limit);

    Float getLast(String column);
}
