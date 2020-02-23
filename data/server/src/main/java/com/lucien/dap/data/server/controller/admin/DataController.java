package com.lucien.dap.data.server.controller.admin;

import com.lucien.dap.data.server.dto.DataStatisticDto;
import com.lucien.dap.data.server.entity.extend.DataStatisticEntity;
import com.lucien.dap.data.server.service.DataService;
import com.lucien.dap.data.server.vo.DataStatisticVo;
import com.lucien.dap.data.server.vo.SeriesVo;
import com.lucien.dap.framework.core.domain.Response;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin/data")
public class DataController {
    private DataService dataService;

    @Autowired
    public void setDataService(DataService dataService) {
        this.dataService = dataService;
    }

    @RequestMapping("/getStatistic")
    public Response<DataStatisticVo> getStatistic(@RequestBody DataStatisticDto dto) {
        DataStatisticEntity statistic = dataService.getStatistic(dto.getType(), dto.getHours());
        DataStatisticVo vo = new DataStatisticVo();
        BeanUtils.copyProperties(statistic, vo);
        String last = dataService.getLast(dto.getType());
        vo.setLast(last);
        return Response.success(vo);
    }
    @RequestMapping("/getSeries")
    public Response<List<SeriesVo>> getSeries(@RequestBody DataStatisticDto dto) {
        List<SeriesVo> series = dataService.getSeries(dto.getType(), dto.getHours());
        return Response.success(series);
    }
}
