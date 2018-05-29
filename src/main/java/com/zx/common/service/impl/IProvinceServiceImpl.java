package com.zx.common.service.impl;

import com.zx.common.entity.Province;
import com.zx.common.mapper.ProvinceMapper;
import com.zx.common.service.IProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("provinceService")
public class IProvinceServiceImpl implements IProvinceService {

    @Autowired
    private ProvinceMapper provinceMapper;

    @Override
    public List<Province> getAll() {
        return provinceMapper.selectAll();
    }
}