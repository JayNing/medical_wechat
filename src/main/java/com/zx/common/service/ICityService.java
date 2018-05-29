package com.zx.common.service;

import com.zx.common.entity.City;

import java.util.List;

public interface ICityService {
    List<City> getByProvinceCode(String provincecode);
}