//package com.zx.common;
//
//import com.zx.common.entity.Area;
//import com.zx.common.entity.City;
//import com.zx.common.entity.Province;
//import com.zx.common.service.IAreaService;
//import com.zx.common.service.ICityService;
//import com.zx.common.service.IProvinceService;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import java.util.List;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration({"classpath:spring/spring-context.xml"})
//public class ProvinceTest {
//
//    @Autowired
//    private IProvinceService provinceService;
//    @Autowired
//    private ICityService cityService;
//    @Autowired
//    private IAreaService areaService;
//
//    @Test
//    public void testGetAllProvince(){
//        List<Province> all = provinceService.getAll();
//        System.out.println(all);
//    }
//
//    @Test
//    public void testSelectCityByProvinceCode(){
//        List<City> all = cityService.getByProvinceCode("140000");
//        System.out.println(all);
//    }
//
//    @Test
//    public void testSelectAreaByCityCode(){
//        List<Area> all = areaService.getByCityCode("130100");
//        System.out.println(all);
//    }
//
//}