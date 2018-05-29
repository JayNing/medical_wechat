package com.zx.wx.service;

import com.zx.common.entity.ResponseResult;

import java.util.List;
import java.util.Map;

public interface QuestionService {
    ResponseResult<List<Map<String, Object>>> searchQuestionList(Map<String, Object> map);

    ResponseResult<Map<String,Object>> searchQuestionResult(Map<String, Object> map);
}