package com.zx.common.service.impl;

import com.zx.common.entity.City;
import com.zx.common.mapper.CityMapper;
import com.zx.common.service.ICityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("cityService")
public class ICityServiceImpl implements ICityService {

    @Autowired
    private CityMapper cityMapper;

    @Override
    public List<City> getByProvinceCode(String provincecode) {
        return cityMapper.selectByProvinceCode(provincecode);
    }
}