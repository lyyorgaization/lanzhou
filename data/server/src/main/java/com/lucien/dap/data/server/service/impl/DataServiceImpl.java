package com.lucien.dap.data.server.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.google.common.base.CaseFormat;
import com.lucien.dap.data.server.entity.DataEntity;
import com.lucien.dap.data.server.entity.DataEntityExample;
import com.lucien.dap.data.server.entity.extend.DataStatisticEntity;
import com.lucien.dap.data.server.mapper.extend.DataEntityExtendMapper;
import com.lucien.dap.data.server.mapper.generate.DataEntityMapper;
import com.lucien.dap.data.server.service.DataService;
import com.lucien.dap.data.server.vo.SeriesVo;
import com.lucien.dap.framework.common.constants.RetEnum;
import com.lucien.dap.framework.core.exception.ApplicationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    public String getLast(String type) {
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
//            byte[] items = type.getBytes();
//            items[0] = (byte) ((char) items[0] - 'a' + 'A');//首字母大写
            method = DataEntity.class.getMethod("get" + this.humpToLine2(type));
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

    public String humpToLine2(String str) {
        return CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, str);
    }
}
