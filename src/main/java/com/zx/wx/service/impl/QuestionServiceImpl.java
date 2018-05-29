package com.zx.wx.service.impl;

import com.zx.common.entity.ResponseResult;
import com.zx.contants.ApiContants;
import com.zx.wx.http.WechatPcCaller;
import com.zx.wx.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("questionService")
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private WechatPcCaller wechatPcCaller;

    @Override
    public ResponseResult<List<Map<String, Object>>> searchQuestionList(Map<String, Object> map) {

        ResponseResult<List<Map<String, Object>>> result = wechatPcCaller.getPcApiByGet(map, ApiContants.LOAD_PAPER_BY_ORGANIZATION_ID);

        return result;
    }

    @Override
    public ResponseResult<Map<String, Object>> searchQuestionResult(Map<String, Object> map) {
        return wechatPcCaller.getPcApi(map, ApiContants.SEARCH_QUESTION_RESULT);
    }
}