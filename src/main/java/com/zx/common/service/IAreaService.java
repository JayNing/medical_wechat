package com.zx.common.service;

import com.zx.common.entity.Area;

import java.util.List;

public interface IAreaService {
    List<Area> getByCityCode(String citycode);
}