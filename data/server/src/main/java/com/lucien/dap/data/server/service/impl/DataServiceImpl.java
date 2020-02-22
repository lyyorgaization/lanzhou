package com.lucien.dap.data.server.service.impl;

import com.lucien.dap.data.server.entity.DataEntity;
import com.lucien.dap.data.server.entity.DataEntityExample;
import com.lucien.dap.data.server.entity.extend.DataStatisticEntity;
import com.lucien.dap.data.server.mapper.extend.DataEntityExtendMapper;
import com.lucien.dap.data.server.mapper.generate.DataEntityMapper;
import com.lucien.dap.data.server.vo.SeriesVo;
import com.lucien.dap.framework.common.constants.RetEnum;
import com.lucien.dap.framework.core.exception.ApplicationException;
import com.lucien.dap.framework.core.utils.CollectionUtil;
import jdk.nashorn.internal.ir.EmptyNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lucien.dap.data.server.service.DataService;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class DataServiceImpl implements DataService {

    private DataEntityExtendMapper entityExtendMapper;
    private DataEntityMapper dataEntityMapper;

    @Autowired
    public void setEntityExtendMapper(DataEntityExtendMapper entityExtendMapper) {
        this.entityExtendMapper = entityExtendMapper;
    }

    @Autowired
    public void setDataEntityMapper(DataEntityMapper dataEntityMapper) {
        this.dataEntityMapper = dataEntityMapper;
    }

    @Override
    public DataStatisticEntity getStatistic(String type, Integer hours) {
        return entityExtendMapper.getStatistic(type, hours * 60);
    }

    @Override
    public Float getLast(String type) {
        return entityExtendMapper.getLast(type);
    }

    @Override
    public List<SeriesVo> getSeries(String type, Integer hours) {
        DataEntityExample example = new DataEntityExample();
        example.setLimit(hours * 60);
        example.setOrderByClause(" time desc");
        List<DataEntity> dataEntities = dataEntityMapper.selectByExample(example);
        dataEntities.sort(Comparator.comparing(DataEntity::getTime));
        List<SeriesVo> listVo = new ArrayList<>();
        Method method;
        try {
            method = DataEntity.class.getMethod("get" + type);
        } catch (NoSuchMethodException e) {
            throw new ApplicationException(RetEnum.SystemError);
        }

        for (DataEntity entity : dataEntities) {
            SeriesVo vo = new SeriesVo();
            vo.setDate(entity.getTime());
            try {
                vo.setValue(Float.valueOf(method.invoke(entity).toString()));
            } catch (InvocationTargetException | IllegalAccessException e) {
                e.printStackTrace();
            }
            listVo.add(vo);
        }
        return listVo;
    }
}
