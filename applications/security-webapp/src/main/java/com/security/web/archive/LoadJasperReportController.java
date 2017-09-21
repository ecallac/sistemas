package com.security.web.archive;
///**
// * 
// */
//package com.security.web.controller;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import javax.naming.NamingException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.validation.Valid;
//
//import org.apache.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//
//import com.security.domain.User;
//import com.security.service.UserService;
//import com.security.web.bean.JasperInputUserForm;
//import com.security.web.service.JasterReportService;
//
//import net.sf.jasperreports.engine.JRDataSource;
//import net.sf.jasperreports.engine.JasperFillManager;
//import net.sf.jasperreports.engine.JasperPrint;
//import net.sf.jasperreports.engine.JasperReport;
//import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
//
//@Controller
//public class LoadJasperReportController {
//	private Logger logger = Logger.getLogger(this.getClass());
//	
//	@Autowired
//	UserService userService;
//	
//	@Autowired
//	JasterReportService jasterReportService;
//	
//	@ModelAttribute("jasperRptFormats")
//	public ArrayList getJasperRptFormats() {
//		ArrayList<String> jasperRptFormats = new ArrayList<String>();
//		jasperRptFormats.add("Html");
//		jasperRptFormats.add("PDF");
//
//		return jasperRptFormats;
//	}
//
//	@RequestMapping(value = "/loadJasper", method = RequestMethod.GET)
//	public String loadSurveyPg(
//			@ModelAttribute("jasperInputUserForm") JasperInputUserForm jasperInputUserForm,
//			Model model) {
//		model.addAttribute("jasperInputUserForm", jasperInputUserForm);
//
//		return "internal/loadJasper";
//	}
//
//	@RequestMapping(value = "/generateReport", method = RequestMethod.POST)
//	public String generateReport(
//			@Valid @ModelAttribute("jasperInputUserForm") JasperInputUserForm jasperInputUserForm,
//			BindingResult result, Model model, HttpServletRequest request,
//			HttpServletResponse response) throws Exception,
//			NamingException {
//
//		if (result.hasErrors()) {
//			System.out.println("validation error occured in jasper input form");
//			return "loadJasper";
//
//		}
//
//		String reportFileName = "userJasperReport";
////		JasperReportDAO jrdao = new JasperReportDAO();
//		
//		
//
////		Connection conn = null;
////			conn = jrdao.getConnection();
//
//			String rptFormat = jasperInputUserForm.getRptFmt();
//			String status = jasperInputUserForm.getStatus();
//
//			System.out.println("rpt format " + rptFormat);
//			System.out.println("status " + status);
//			
//			List<User> users = userService.findUsersByStatus(status);
//			
//			logger.info(users.size());
//			
//			List<Map<String,?>> datasource = getUserDatasource(users);
//			JRDataSource jrDataSource = new JRBeanCollectionDataSource(datasource);
//			
//			HashMap<String, Object> hmParams = new HashMap<String, Object>();
//
////			hmParams.put("noy", new Integer(noy));
////
////			hmParams.put("Title", "Employees working more than " + noy
////					+ " Years");
//
//			
//			JasperReport jasperReport = jasterReportService.getCompiledFile(reportFileName,
//					request);
//
//			JasperPrint jasperPrint = JasperFillManager.fillReport(
//					jasperReport, hmParams,jrDataSource);
//			if (rptFormat.equalsIgnoreCase("html")) {
//
//				
//				jasterReportService.generateReportHtml(jasperPrint, response); // For
//																			// HTML
//																			// report
//
//			}
//
//			else if (rptFormat.equalsIgnoreCase("pdf")) {
//
//				jasterReportService.generateReportPDF(response, jasperPrint); // For
//																					// PDF
//																					// report
//
//			}
//
//		return null;
//
//	}
//	
//	
//	public List<Map<String,?>> getUserDatasource(List<User> users){
//		List<Map<String,?>> datasource = new ArrayList<>();
//		for (User user : users) {
//			logger.info("val="+user.getId());
//			Map<String, Object> map = new HashMap<>();
//			map.put("id", user.getId());
//			map.put("userName", user.getUserName());
//			map.put("status", user.getStatus());
//			map.put("question", user.getQuestion());
//			map.put("answer", user.getAnswer());
//			
//		}
//		return datasource;
//	}
//
//}