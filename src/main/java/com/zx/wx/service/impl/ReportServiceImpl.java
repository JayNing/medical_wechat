package com.zx.wx.service.impl;

import com.zx.common.entity.ResponseResult;
import com.zx.common.util.Base64Utils;
import com.zx.common.util.PathUtil;
import com.zx.wx.http.WechatPcCaller;
import com.zx.wx.service.ReportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("reportService")
public class ReportServiceImpl implements ReportService {

    private static Logger logger = LoggerFactory.getLogger(ReportServiceImpl.class);

    @Autowired
    private WechatPcCaller wechatPcCaller;

    @Override
    public ResponseResult<Object> searchPdfBase64(Map<String, Object> map) {
//        return wechatPcCaller.getPcApi(map, ApiContants.SEARCH_PDF_BASE64);
        ResponseResult<Object> result = new ResponseResult<>();
        result.setState(1);
        String str = null;
        try {
            str = Base64Utils.encodeBase64File(PathUtil.getUploadPath() + "test.pdf");
            result.setData(str);
        } catch (Exception e) {
            logger.info("searchPdfBase64 : " + e.getMessage());
            e.printStackTrace();
        }
        return result;
    }
}