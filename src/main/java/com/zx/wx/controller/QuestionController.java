package com.zx.wx.controller;

import com.zx.common.controller.BaseController;
import com.zx.common.entity.ResponseResult;
import com.zx.common.entity.ReturnMsg;
import com.zx.common.util.StringUtil;
import com.zx.contants.Contants;
import com.zx.wx.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@RequestMapping("question")
@Controller
public class QuestionController extends BaseController {

    @Autowired
    private QuestionService questionService;

    @RequestMapping(value = "list")
    public @ResponseBody
    ReturnMsg list(HttpServletRequest request, String organizationId, HttpServletResponse response) throws Exception {
        setAllowAllAccessControlHeader(response);
        ReturnMsg msg = new ReturnMsg();

        if (StringUtil.notEmpty(organizationId)){

            Map<String, Object> map = new HashMap<>();
            map.put("organizationId",organizationId);
            map.put("token","123");

            ResponseResult<List<Map<String, Object>>> responseResult = questionService.searchQuestionList(map);

            if (responseResult == null){
                msg.addErrorMsg("查询PC端问卷失败");
            }else {
                if (responseResult.getState() == 1){
                    List<Map<String, Object>> data = responseResult.getData();
                    Collections.sort(
                            data,
                            new Comparator<Map<String, Object>>() {
                                public int compare(Map<String, Object> p1, Map<String, Object> p2){
                                    Integer optionType1 = (Integer) p1.get("questionSerial");
                                    Integer optionType2 = (Integer) p2.get("questionSerial");
                                    if (optionType1 == null){
                                        optionType1 = 0;
                                    }
                                    if (optionType2 == null){
                                        optionType2 = 0;
                                    }
                                    return optionType1 - optionType2;
                                }
                            }
                    );

                    msg.setData(data);
                }else {
                    msg.addErrorMsg(responseResult.getMessage());
                }
            }

            if (msg.isValid()){
                msg.setErrorCode(Contants.SUCCESS);
            }
        }else {
            msg.addErrorMsg("机构id不能为空");
        }
        return msg;
    }

    @RequestMapping(value = "result")
    public @ResponseBody
    ReturnMsg result(HttpServletRequest request, String packageId, String answerStr, HttpServletResponse response) throws Exception {
        setAllowAllAccessControlHeader(response);
        ReturnMsg msg = new ReturnMsg();
        if (StringUtil.notEmpty(packageId) && StringUtil.notEmpty(answerStr)){
            String[] answerArr = answerStr.split(Contants.COMMA_CHAR);
            if (answerArr != null && answerArr.length > 0){
                String organizationId = answerArr[0];
                List<String> answers = new ArrayList<>();
                if (answerArr.length > 1){
                    for (int i = 1; i < answerArr.length ; i++){
                        String[] strings = answerArr[i].split("_");
                        if (strings != null && strings.length == 3){
                            answers.add(strings[1]);
                        }
                    }
                }
                String[] packageIdArr = packageId.split(Contants.COMMA_CHAR);
                Map<String, Object> map = new HashMap<>();
                map.put("packageId",packageIdArr);
                map.put("organizationId",organizationId);
                map.put("answerId",answers);
                map.put("token","123");

                ResponseResult<Map<String, Object>> responseResult = questionService.searchQuestionResult(map);
                if (responseResult == null){
                    msg.addErrorMsg("查询PC端问卷结果失败");
                }else {
                    if (responseResult.getState() == 1) {
                        Map<String, Object> data = responseResult.getData();
                        msg.setData(data);
                        msg.setErrorCode(Contants.SUCCESS);
                    } else {
                        msg.addErrorMsg(responseResult.getMessage());
                    }
                }
            }
        }else {
            msg.addErrorMsg("参数answerStr : " + answerStr + ",packageId:" + packageId + "都不能为空");
        }
        return msg;
    }
}