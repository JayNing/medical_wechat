package com.zx.common.service.impl;

import com.zx.common.entity.Area;
import com.zx.common.mapper.AreaMapper;
import com.zx.common.service.IAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("areaService")
public class IAreaServiceImpl implements IAreaService {

    @Autowired
    private AreaMapper areaMapper;

    @Override
    public List<Area> getByCityCode(String citycode) {
        return areaMapper.selectByCityCode(citycode);
    }
}