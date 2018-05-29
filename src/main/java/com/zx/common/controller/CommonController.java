package com.zx.common.controller;

import com.zx.common.entity.Area;
import com.zx.common.entity.City;
import com.zx.common.entity.Province;
import com.zx.common.entity.ReturnMsg;
import com.zx.common.service.IAreaService;
import com.zx.common.service.ICityService;
import com.zx.common.service.IProvinceService;
import com.zx.contants.Contants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/public/common")
public class CommonController extends BaseController {

    @Autowired
    private IProvinceService provinceService;
    @Autowired
    private ICityService cityService;
    @Autowired
    private IAreaService areaService;

    @RequestMapping(value = "provincelist")
    public @ResponseBody
    ReturnMsg provincelist(HttpServletRequest request, HttpServletResponse response) {
        setAllowAllAccessControlHeader(response);
        ReturnMsg msg = new ReturnMsg();
        List<Province> all = provinceService.getAll();
        msg.setData(all);
        msg.setErrorCode(Contants.SUCCESS);
        return msg;
    }

    @RequestMapping(value = "citylist")
    public @ResponseBody ReturnMsg citylist(HttpServletRequest request, String provincecode,  HttpServletResponse response) {
        setAllowAllAccessControlHeader(response);
        ReturnMsg msg = new ReturnMsg();
        List<City> list = cityService.getByProvinceCode(provincecode);
        msg.setData(list);
        msg.setErrorCode(Contants.SUCCESS);
        return msg;
    }

    @RequestMapping(value = "arealist")
    public @ResponseBody ReturnMsg list(HttpServletRequest request, String citycode, HttpServletResponse response) {
        setAllowAllAccessControlHeader(response);
        ReturnMsg msg = new ReturnMsg();
        List<Area> list = areaService.getByCityCode(citycode);
        msg.setData(list);
        msg.setErrorCode(Contants.SUCCESS);
        return msg;
    }

}