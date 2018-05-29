package com.zx.common.util;/*package com.zx.common.util;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.zx.common.entity.ReturnMsg;
import com.zx.contants.Contants;

public class FileUploadUtil {

    public static List<String> uploadFile(HttpServletRequest request, ReturnMsg msg, String dir, Set<String> extLimit, String language, String buildId) {
        List<String> filePathList = new ArrayList<String>();
        // 创建一个通用的多部分解析器
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        // 判断 request 是否有文件上传,即多部分请求
        if (multipartResolver.isMultipart(request)) {
            // 从request中取出上传文件
            // multipartResolver.resolveMultipart(request);
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;

            // 取得request中的所有文件名
            Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
            if (fileMap != null && fileMap.size() > 0) {
                for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
                    // 上传文件
                    MultipartFile mf = entity.getValue();
                    if (mf.getSize() <= 30000000) {
                        // 获取原文件名
                        String fileName = mf.getOriginalFilename();
                        String preFileName = mf.getOriginalFilename();
                        // 检查扩展名
                        String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
                        if (extLimit.contains(fileExt)) {
                            // 上传根目录
                            // request.getServletContext().getRealPath("/") + "/chemical-data/";// /chemical-data/
                            String savePath = PathUtil.getUploadPath();
                            // 20160623
                            String dateStr = DateUtil.formatDate(new Date(), "yyyyMMdd");
                            // /chemical-data/200001/alarmMusic/20160623
                            String fileDatePath = savePath + dir + File.separator + buildId + File.separator + dateStr;
                            File fileDatePathFolder = new File(fileDatePath);
                            if (!fileDatePathFolder.exists()) {
                                fileDatePathFolder.mkdirs();
                            }
                            // new fileName
                            fileName = String.getUuid() + "." + fileExt;
                            if("alarmMusic".equals(dir)){
                                fileName = preFileName;
                                //20160623163010_123_abc.mp3
                            }
                            // 20160623163010_123_daeqr123aat1adadada.jpg
                            String destFileName = DateUtil.formatDate(new Date(), "yyyyMMddHHmmss") + "_" + new Random().nextInt(1000) + "_"
                                    + fileName;
                            try {
                                File uploadedFile = new File(fileDatePath + File.separator + destFileName);
                                FileCopyUtils.copy(mf.getBytes(), uploadedFile);
                                String filePath = fileDatePath + File.separator + destFileName;
                                if("iconPicture".equals(dir) || "headImage".equals(dir)){
                                    filePath = filePath.replace(File.separator, Contants.PICTURE_CONVERT);
                                }
                                filePathList.add(filePath);
                                // filePathList.add("/uploads" + File.separator + dir + File.separator + dateStr + File.separator + destFileName);
                            } catch (Exception e) {
                                String errMsg = I18NUtil.getLanguageMsgByStrCode(language, ErrorCodeConstants.UPLOAD_FILE_FAIL);
                                msg.addErrorMsg(errMsg);
                            }
                        } else {
                            String errMsg = I18NUtil.getMsgByLanguageAndStr(language, ErrorCodeConstants.UPLOAD_FILE_FORMAT_ERROR,extLimit.toString());
                            msg.addErrorMsg(errMsg);
                        }
                    } else {
                        String errMsg = I18NUtil.getMsgByLanguageAndStr(language, ErrorCodeConstants.UPLOAD_FILE_SIZE_LIMIT, "30M");
                        msg.addErrorMsg(errMsg);
                    }
                }
            } else {
                String errMsg = I18NUtil.getLanguageMsgByStrCode(language, ErrorCodeConstants.UPLOAD_FILE_CAN_NOT_NULL);
                msg.addErrorMsg(errMsg);
            }
        } else {
            String errMsg = I18NUtil.getLanguageMsgByStrCode(language, ErrorCodeConstants.UPLOAD_FILE_CAN_NOT_NULL);
            msg.addErrorMsg(errMsg);
        }

        return filePathList;
    }

}
*/