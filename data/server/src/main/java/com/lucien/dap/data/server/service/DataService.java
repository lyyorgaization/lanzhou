package com.lucien.dap.data.server.service;

import com.lucien.dap.data.server.entity.extend.DataStatisticEntity;
import com.lucien.dap.data.server.vo.SeriesVo;

import java.util.List;

public interface DataService {
    DataStatisticEntity getStatistic(String type, Integer hours);

    String getLast(String type);

    List<SeriesVo> getSeries(String type, Integer hours);
}
