package com.zx.wx.controller;

import com.google.gson.internal.LinkedTreeMap;
import com.zx.common.config.CommonConfig;
import com.zx.common.controller.BaseController;
import com.zx.common.entity.NormalUser;
import com.zx.common.entity.ResponseResult;
import com.zx.common.entity.ReturnMsg;
import com.zx.common.util.CommonUtil;
import com.zx.common.util.DateUtil;
import com.zx.common.util.JsonUtil;
import com.zx.common.util.StringUtil;
import com.zx.contants.Contants;
import com.zx.wx.api.ReportApiCaller;
import com.zx.wx.dto.report.CheckItemDTO;
import com.zx.wx.dto.report.ItemDetailDTO;
import com.zx.wx.dto.report.ReportDTO;
import com.zx.wx.dto.report.common.ReportResultDTO;
import com.zx.wx.dto.report.trend.ItemInfoDTO;
import com.zx.wx.dto.report.trend.ItemValueDTO;
import com.zx.wx.service.ReportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@Controller
@RequestMapping("report")
public class ReportController extends BaseController{

    private static Logger logger = LoggerFactory.getLogger(ReportController.class);

    @Autowired
    private ReportApiCaller reportApiCaller;

    @Autowired
    private ReportService reportService;

    @Autowired
    private CommonConfig commonConfig;

    @RequestMapping("searchTrendData")
    public @ResponseBody
    ReturnMsg searchTrendData(HttpServletRequest request, HttpServletResponse response) {
        setAllowAllAccessControlHeader(response);
        ReturnMsg msg = new ReturnMsg();
        NormalUser normalUser = getNormalUser(request);
        if (normalUser == null) {
            msg.addErrorMsg("请先登陆");
        } else {
            List<ItemInfoDTO> itemInfoDTOList = new ArrayList<>();
            //TODO 1、根据身份证号，查出此人所有的体检报告
            Map<String, Object> listMap = new HashMap<>();
            listMap.put("idcard",normalUser.getIdCard());
            ReportResultDTO reportResultDTO = null;//reportApiCaller.getReportPcApi(listMap, commonConfig.getOpenServerName() + ApiContants.REPORT_LIST);
            if (reportResultDTO == null){
                reportResultDTO = JsonUtil.getDemoDataList();
            }
            if (reportResultDTO == null){
                msg.addErrorMsg("未获取到数据");
            }else {
                if (!reportResultDTO.getResultcode().equals("0")) {
                    msg.addErrorMsg(reportResultDTO.getReason());
                } else {
                    //TODO 转换成我们的类型
                    List<LinkedTreeMap<String, Object>> rpItemDTOList = (List<LinkedTreeMap<String, Object>>) reportResultDTO.getResult();
                    List<ReportDTO> reportDTOList = converReportList(rpItemDTOList,normalUser);
                    if (!CommonUtil.isListEmpty(reportDTOList) && reportDTOList.size() > 1){

                        //只有至少两份报告，才会生成对比
                        Collections.sort(
                                reportDTOList,
                                new Comparator<ReportDTO>() {
                                    public int compare(ReportDTO p1, ReportDTO p2){
                                        Date date1 = p1.getReportDate();
                                        Date date2 = p2.getReportDate();
                                        return (int) (date2.getTime() - date1.getTime());
                                    }
                                }
                        );
                        //存储每一次体检的所有单项数据
                        List<ItemDetailDTO> totalItemDetailDTOList = new ArrayList<>();
                        for (ReportDTO reportDTO : reportDTOList) {
                            //TODO 2、检索每个体检报告中相同的项，如果有哪个检查单项在多个报告中出现，将数据提取出来（提取每次的检查时间和响应的结果数据）
                            String tjbh = reportDTO.getReportId();

                            Map<String, Object> detailMap = new HashMap<>();
                            detailMap.put("tjbh",tjbh);
                            ReportResultDTO reportResultDTODetail = null;//reportApiCaller.getReportPcApi(detailMap, commonConfig.getOpenServerName() + ApiContants.REPORT_DETAIL);
                            if (reportResultDTODetail == null){
                                if ("818030012".equals(tjbh)){
                                    reportResultDTODetail = JsonUtil.getDemoDataDetail();
                                }else if ("818030019".equals(tjbh)){
                                    reportResultDTODetail = JsonUtil.getDemoDataDetail2();
                                }
                            }
                            if (reportResultDTODetail == null){
                                msg.addErrorMsg("未获取到数据");
                            }else {
                                //获取本次报告中所有的检查单项
                                if (!reportResultDTODetail.getResultcode().equals("0")){
                                    msg.addErrorMsg(reportResultDTODetail.getReason());
                                }else {
                                    LinkedTreeMap<String,Object> menuDTO =  (LinkedTreeMap<String,Object>) reportResultDTODetail.getResult();
                                    List<CheckItemDTO> checkItemDTOList = converReportDetailList(menuDTO);
                                    if (!CommonUtil.isListEmpty(checkItemDTOList)){
                                        for (CheckItemDTO checkItemDTO : checkItemDTOList) {
                                            List<ItemDetailDTO> itemDetailDTOList = checkItemDTO.getItemDetailDTOList();
                                            if (itemDetailDTOList != null){
                                                totalItemDetailDTOList.addAll(itemDetailDTOList);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        //TODO 3、封装对象，返回前台
                        if (totalItemDetailDTOList.size() > 0) {
                            //按照体检单项名字分组
                            Map<String, List<ItemDetailDTO>> itemDetailMap = new HashMap<>();
                            for (ItemDetailDTO itemDetailDTO : totalItemDetailDTOList) {
                                String key = itemDetailDTO.getItemDetailName();
                                List<ItemDetailDTO> itemDetailDTOS = itemDetailMap.get(key);
                                if (itemDetailDTOS == null) {
                                    itemDetailDTOS = new ArrayList<>();
                                }
                                itemDetailDTOS.add(itemDetailDTO);
                                itemDetailMap.put(key, itemDetailDTOS);
                            }
                            //得到的itemDetailMap是所有单项分类集合
                            //将所有value的List大于1的提取出来，表示同一单项至少是2次体检
                            if (itemDetailMap.size() > 0) {
                                for (Map.Entry<String, List<ItemDetailDTO>> entry : itemDetailMap.entrySet()) {
                                    String key = entry.getKey();
                                    List<ItemDetailDTO> entryValue = entry.getValue();
                                    if (!CommonUtil.isListEmpty(entryValue) && entryValue.size() > 1){
                                        ItemInfoDTO itemInfoDTO = new ItemInfoDTO();
                                        List<ItemValueDTO> itemValueDTOList = new ArrayList<>();
                                        List<String> dateStrList = new ArrayList<>();
                                        List<Integer> valueList = new ArrayList<>();
                                        for (ItemDetailDTO itemDetailDTO : entryValue) {
                                            //每一条表示体检一次
                                            itemInfoDTO.setItemName(key);
                                            itemInfoDTO.setUnit(itemDetailDTO.getItemDetailUnits());
                                            itemInfoDTO.setMax(itemDetailDTO.getNormalMaxValue());
                                            itemInfoDTO.setMin(itemDetailDTO.getNormalMinValue());

                                            ItemValueDTO itemValueDTO = new ItemValueDTO();
                                            String dateStr = itemDetailDTO.getCheckupdate();
                                            String formatDateStr = DateUtil.formatDate(DateUtil.parseDate(dateStr,DateUtil.FORMAT_DATE_DAY));
                                            itemValueDTO.setDateStr(formatDateStr);
                                            itemValueDTO.setValue(itemValueDTO.getValue());
                                            itemValueDTOList.add(itemValueDTO);

                                            dateStrList.add(formatDateStr);
                                            String value = itemDetailDTO.getItemDetailValue();
                                            if (StringUtil.notEmpty(value)){
                                                valueList.add(Integer.valueOf(itemDetailDTO.getItemDetailValue()));
                                            }else {
                                                valueList.add(0);
                                            }
                                        }
                                        itemInfoDTO.setItemValueDTOList(itemValueDTOList);
                                        itemInfoDTO.setValueList(valueList);
                                        itemInfoDTO.setDateStrList(dateStrList);
                                        itemInfoDTOList.add(itemInfoDTO);
                                    }
                                }
                            }
                        }
                    }
                }
            }
            msg.setData(itemInfoDTOList);
            msg.setErrorCode(Contants.SUCCESS);
        }
        return msg;
    }


    @RequestMapping("list")
    public @ResponseBody
    ReturnMsg list(HttpServletRequest request, HttpServletResponse response){
        setAllowAllAccessControlHeader(response);
        ReturnMsg msg = new ReturnMsg();
        NormalUser normalUser = getNormalUser(request);
        if (normalUser == null){
            msg.addErrorMsg("请先登陆");
        }else {
            Map<String, Object> map = new HashMap<>();
            map.put("idcard",normalUser.getIdCard());
            ReportResultDTO reportResultDTO = null;//reportApiCaller.getReportPcApi(map, commonConfig.getOpenServerName() + ApiContants.REPORT_LIST);
            if (reportResultDTO == null){
                reportResultDTO = JsonUtil.getDemoDataList();
            }
            if (reportResultDTO == null){
                msg.addErrorMsg("未获取到数据");
            }else {
                if (!reportResultDTO.getResultcode().equals("0")){
                    msg.addErrorMsg(reportResultDTO.getReason());
                }else {
                    //TODO 转换成我们的类型
                    List<LinkedTreeMap<String,Object>> rpItemDTOList = (List<LinkedTreeMap<String,Object>>) reportResultDTO.getResult();
                    List<ReportDTO> reportDTOList = converReportList(rpItemDTOList,normalUser);
                    msg.setData(reportDTOList);
                    msg.setErrorCode(Contants.SUCCESS);
                }
            }
        }

     /*   NormalUser normalUser = new NormalUser();
        ReportResultDTO reportResultDTO = JsonUtil.getDemoDataList();
        if (reportResultDTO == null){
            msg.addErrorMsg("未获取到数据");
        }else {
            if (!reportResultDTO.getResultcode().equals("0")){
                msg.addErrorMsg(reportResultDTO.getReason());
            }else {
                //TODO 转换成我们的类型
                List<LinkedTreeMap<String,Object>> rpItemDTOList = (List<LinkedTreeMap<String,Object>>) reportResultDTO.getResult();
                List<ReportDTO> reportDTOList = converReportList(rpItemDTOList,normalUser);
                msg.setData(reportDTOList);
                msg.setErrorCode(Contants.SUCCESS);
            }
        }
*/
        return msg;
    }

    private List<ReportDTO> converReportList(List<LinkedTreeMap<String,Object>> rpItemDTOList, NormalUser normalUser) {
        List<ReportDTO> list = new ArrayList<>();
        if (!CommonUtil.isListEmpty(rpItemDTOList)){
            for (LinkedTreeMap<String,Object> rpItemDTO : rpItemDTOList) {
                ReportDTO reportDTO = new ReportDTO();
                reportDTO.setHeadImgUrl(normalUser.getHeadImgUrl());
                reportDTO.setIdCard(normalUser.getIdCard());
                reportDTO.setMobile(normalUser.getMobile());
                String checkupdate = (String) rpItemDTO.get("checkupdate");
                logger.info("checkupdate : " + checkupdate);
                if (StringUtil.notEmpty(checkupdate)){
                    Date checkDate = DateUtil.parseDate(checkupdate,DateUtil.FORMAT_DATE_DAY);
                    reportDTO.setReportDate(checkDate);
                    reportDTO.setReportDateShow(DateUtil.formatDate(checkDate, DateUtil.DEFAULT_FORMAT_DATE));
                }
                reportDTO.setReportId((String) rpItemDTO.get("tjbh"));
                reportDTO.setUsername((String) rpItemDTO.get("examcategory"));
                list.add(reportDTO);
            }
        }
        return list;
    }

    @RequestMapping("reportDetail")
    public @ResponseBody
    ReturnMsg reportDetail(HttpServletRequest request, HttpServletResponse response){
        setAllowAllAccessControlHeader(response);
        ReturnMsg msg = new ReturnMsg();
        String tjbh = getPara(request,"tjbh");
        String reportType = getPara(request,"reportType");
        if (StringUtil.notEmpty(tjbh)){
            //TODO 判断查看类型
            if ("1".equals(reportType)){
                Map<String, Object> map = new HashMap<>();
                map.put("tjbh",tjbh);
                ReportResultDTO reportResultDTO = null;//reportApiCaller.getReportPcApi(map, commonConfig.getOpenServerName() + ApiContants.REPORT_DETAIL);
                if (reportResultDTO == null){
                    if ("818030012".equals(tjbh)){
                        reportResultDTO = JsonUtil.getDemoDataDetail();
                    }else if ("818030019".equals(tjbh)){
                        reportResultDTO = JsonUtil.getDemoDataDetail2();
                    }
                }
//            ReportResultDTO reportResultDTO = JsonUtil.getDemoDataDetail();
                if (reportResultDTO == null){
                    msg.addErrorMsg("未获取到数据");
                }else {
                    if (!reportResultDTO.getResultcode().equals("0")){
                        msg.addErrorMsg(reportResultDTO.getReason());
                    }else {
                        //TODO 转换成我们的类型
//                    MenuDTO menuDTO = (MenuDTO) reportResultDTO.getResult();
                        LinkedTreeMap<String,Object> menuDTO =  (LinkedTreeMap<String,Object>) reportResultDTO.getResult();
                        List<CheckItemDTO> checkItemDTOList = converReportDetailList(menuDTO);
                        msg.setData(checkItemDTOList);
                        msg.setErrorCode(Contants.SUCCESS);
                    }
                }
            }else if ("2".equals(reportType)){
                //获取图片Base64字符串
                Map<String, Object> map = new HashMap<>();
                ResponseResult<Object> responseResult = reportService.searchPdfBase64(map);
                if (responseResult != null){
                    if (responseResult.getState() == 1){
                        String pdfBase64Str = (String) responseResult.getData();
                        if (StringUtil.notEmpty(pdfBase64Str)){
                            //TODO pdf转图片
//                            String imageBase64 = Base64Utils.pdfToImageBase64(pdfBase64Str, null);
//                            msg.setData(imageBase64);
                            msg.setErrorCode(Contants.SUCCESS);
                        }else {
                            msg.addErrorMsg("获取到pdf数据为空");
                        }
                    }else {
                        msg.addErrorMsg(responseResult.getMessage());
                    }
                }else {
                    msg.addErrorMsg("未获取到pdf数据");
                }
            }
        }else {
            msg.addErrorMsg("请传递体检报告编号");
        }
        return msg;
    }

    private List<CheckItemDTO> converReportDetailList(LinkedTreeMap<String,Object> menuDTO) {
        List<CheckItemDTO>  checkItemDTOList = new ArrayList<>();
        if (menuDTO != null){
            List<LinkedTreeMap<String,Object>> reportitems = (List<LinkedTreeMap<String,Object>>) menuDTO.get("reportitems");
            if (!CommonUtil.isListEmpty(reportitems)){
                for (LinkedTreeMap<String,Object> reportitem : reportitems) {
                    CheckItemDTO checkItemDTO = new CheckItemDTO();
                    String checkupdate = (String) reportitem.get("checkupdate");
                    checkItemDTO.setCheckItemDate(checkupdate);
                    checkItemDTO.setCheckItemDateStr(checkupdate);

                    checkItemDTO.setItemId((String) reportitem.get("itemid"));
                    checkItemDTO.setItemName((String) reportitem.get("itemname"));
                    checkItemDTO.setDisporder((String) reportitem.get("disporder"));
                    checkItemDTO.setItemtype((String) reportitem.get("itemtype"));
                    List<LinkedTreeMap<String,Object>> itemresults = (List<LinkedTreeMap<String, Object>>) reportitem.get("itemresults");
                    boolean isTwoColumn = true;
                    List<ItemDetailDTO> itemDetailDTOList = new ArrayList<>();
                    if (!CommonUtil.isListEmpty(itemresults)){
                        for (LinkedTreeMap<String,Object> itemresult : itemresults) {
                            ItemDetailDTO itemDetailDTO = new ItemDetailDTO();
                            itemDetailDTO.setItemDetailDisporder((String) itemresult.get("disporder"));
                            itemDetailDTO.setItemDetailId((String) itemresult.get("nodeid"));
                            itemDetailDTO.setItemDetailName((String) itemresult.get("nodename"));
                            itemDetailDTO.setItemDetailPrompt((String) itemresult.get("prompt"));
                            itemDetailDTO.setItemDetailStatus((String) itemresult.get("status"));
                            itemDetailDTO.setItemDetailUnits((String) itemresult.get("unit"));
                            itemDetailDTO.setCheckupdate(checkupdate);
                            String value = (String) itemresult.get("valuestr");
                            itemDetailDTO.setItemDetailValue(value);
                            String rangested = (String) itemresult.get("rangestd");
                            if (StringUtil.notEmpty(rangested)){
                                isTwoColumn = false;
                                logger.info("rangested ： " + rangested);
                                String[] rangeArr = rangested.split("～");
                                if (rangeArr.length == 2){
                                    String minValue = rangeArr[0].trim();
                                    String maxValue = rangeArr[1].trim();
                                    itemDetailDTO.setNormalMaxValue(maxValue);
                                    itemDetailDTO.setNormalMinValue(minValue);
                                    if (StringUtil.notEmpty(value)){
                                        Double intValue = Double.valueOf(value);
                                        Double minV = Double.valueOf(minValue);
                                        Double maxV = Double.valueOf(maxValue);
                                        if (minV > intValue){
                                            itemDetailDTO.setRange(1);
                                        }else if (maxV < intValue){
                                            itemDetailDTO.setRange(2);
                                        }
                                    }
                                }
                            }
                            itemDetailDTO.setRangestd(rangested);

                            itemDetailDTOList.add(itemDetailDTO);
                        }
                    }
                    checkItemDTO.setItemDetailDTOList(itemDetailDTOList);
                    if (isTwoColumn){
                        checkItemDTO.setColumnNum(2);
                    }else {
                        checkItemDTO.setColumnNum(3);
                    }
                    checkItemDTOList.add(checkItemDTO);
                }
            }
        }
        return checkItemDTOList;
    }


}