package com.zx.wx.service;

import com.zx.common.entity.ResponseResult;

import java.util.Map;

public interface ReportService {
    ResponseResult<Object> searchPdfBase64(Map<String, Object> map);
}