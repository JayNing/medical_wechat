/**
 * 
 */
package com.zx.wx.controller;

import com.zx.common.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Administrator
 *
 */
@RequestMapping("medical")
@Controller
public class MedicalController extends BaseController{
	
	@RequestMapping("personalAppointment")
	public String personalAppointment(HttpServletRequest request, HttpServletResponse response){
		setAllowAllAccessControlHeader(response);
		return "redirect:/templates/appointmentHome.html";
	}

	@RequestMapping("teamAppointment")
	public String teamAppointment(HttpServletRequest request, HttpServletResponse response){
		setAllowAllAccessControlHeader(response);
		return "redirect:/templates/appointmentHome.html";
	}

	@RequestMapping("packageDetails")
	public String packageDetails(HttpServletRequest request, HttpServletResponse response){
		setAllowAllAccessControlHeader(response);
		return "redirect:/test.html";
	}

	@RequestMapping("healthManagerReport")
	public String healthManagerReport(HttpServletRequest request, HttpServletResponse response){
		setAllowAllAccessControlHeader(response);
		return "redirect:/templates/home.html";
	}

	@RequestMapping("medicalExaminationReport")
	public String medicalExaminationReport(HttpServletRequest request, HttpServletResponse response){
		setAllowAllAccessControlHeader(response);
		return "redirect:/templates/home.html";
	}

	@RequestMapping("appointmentManagement")
	public String appointmentManagement(HttpServletRequest request, HttpServletResponse response){
		setAllowAllAccessControlHeader(response);
		return "redirect:/templates/home.html";
	}

	@RequestMapping("orderServer")
	public String orderServer(HttpServletRequest request, HttpServletResponse response){
		setAllowAllAccessControlHeader(response);
		return "redirect:/templates/home.html";
	}

	@RequestMapping("guideCheckServer")
	public String guideCheckServer(HttpServletRequest request, HttpServletResponse response){
		return "redirect:/test.html";
	}

	@RequestMapping("historyASQ")
	public String historyASQ(HttpServletRequest request, HttpServletResponse response){
		return "redirect:/test.html";
	}

	@RequestMapping("myCollection")
	public String myCollection(HttpServletRequest request, HttpServletResponse response){
		return "redirect:/test.html";
	}

	
}
