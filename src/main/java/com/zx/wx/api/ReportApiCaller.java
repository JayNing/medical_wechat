package com.zx.wx.api;

import com.zx.common.util.HttpUtil;
import com.zx.common.util.JsonUtil;
import com.zx.common.util.StringUtil;
import com.zx.wx.dto.report.common.ReportResultDTO;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ReportApiCaller {
    private static final Logger logger = Logger.getLogger(ReportApiCaller.class);

    private static final int limit_times = 3;

    public static ReportResultDTO getReportPcApi(Map<String, Object> map, String url){
        String result = null;
        int i = 0;
        while (result == null && i < limit_times) {
            result = HttpUtil.doGet(url , map);
            i++;
        }
        logger.info(">>>>>>>>>> getReportPcApi url:  ["+ url+"]<<<<<<<<<<");
        logger.info(">>>>>>>>>>getReportPcApi result: ["+result+"]<<<<<<<<<<");
        if (StringUtil.notEmpty(result)) {
            result = result.replace("package","baoming");
            ReportResultDTO msg = JsonUtil.toObject(result, new ReportResultDTO());
            return msg;
        }
        return null;
    }

}