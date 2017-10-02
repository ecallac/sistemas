package com.security.web.archive;
///**
// * 
// */
//package com.security.web.service;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.naming.NamingException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.stereotype.Service;
//
//import net.sf.jasperreports.engine.JRException;
//import net.sf.jasperreports.engine.JasperCompileManager;
//import net.sf.jasperreports.engine.JasperExportManager;
//import net.sf.jasperreports.engine.JasperPrint;
//import net.sf.jasperreports.engine.JasperReport;
//import net.sf.jasperreports.engine.export.HtmlExporter;
//import net.sf.jasperreports.export.SimpleExporterInput;
//import net.sf.jasperreports.export.SimpleHtmlExporterOutput;
//import net.sf.jasperreports.export.SimpleHtmlReportConfiguration;
//
///**
// * @author efrain.calla
// *
// */
//@Service("jasterReportService")
//public class JasterReportServiceImpl implements JasterReportService {
//
//	/* (non-Javadoc)
//	 * @see com.security.web.service.JasterReportService#getCompiledFile(java.lang.String, javax.servlet.http.HttpServletRequest)
//	 */
//	@Override
//	public JasperReport getCompiledFile(String fileName, HttpServletRequest request) throws Exception {
//		String jrxmlFile = request.getSession().getServletContext().getRealPath("/report/" + fileName + ".jrxml");
//		System.out.println("path " + jrxmlFile);
//		// If compiled file is not found, then compile XML template
////		if (!reportFile.exists()) {
////		           JasperCompileManager.compileReportToFile(request.getSession().getServletContext().getRealPath("/report/" + fileName + ".jrxml"),request.getSession().getServletContext().getRealPath("/report/" + fileName + ".jasper"));
//		          return JasperCompileManager.compileReport(new FileInputStream(new File(jrxmlFile)));
////		}
////	    JasperReport jasperReport = (JasperReport) JRLoader.loadObjectFromFile(reportFile.getPath());
////		return jasperReport;
//	}
//
//	/* (non-Javadoc)
//	 * @see com.security.web.service.JasterReportService#generateReportHtml(net.sf.jasperreports.engine.JasperPrint, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
//	 */
//	@Override
//	public void generateReportHtml(JasperPrint jasperPrint, HttpServletResponse resp)
//			throws IOException, JRException {
//		HtmlExporter exporter=new HtmlExporter();
//		 List<JasperPrint> jasperPrintList = new ArrayList<JasperPrint>();
//		 jasperPrintList.add(jasperPrint);
//		 exporter.setExporterInput(SimpleExporterInput.getInstance(jasperPrintList));
//		 exporter.setExporterOutput( new SimpleHtmlExporterOutput(resp.getWriter()));
//		 SimpleHtmlReportConfiguration configuration =new SimpleHtmlReportConfiguration();
//		 exporter.setConfiguration(configuration);
//		  exporter.exportReport();
//
//	}
//
//	/* (non-Javadoc)
//	 * @see com.security.web.service.JasterReportService#generateReportPDF(javax.servlet.http.HttpServletResponse, java.util.Map, net.sf.jasperreports.engine.JasperReport)
//	 */
//	@Override
//	public void generateReportPDF(HttpServletResponse resp, /*Map parameters, JasperReport jasperReport*/JasperPrint jasperPrint)
//			throws JRException, NamingException, IOException {
////		byte[] bytes = null;
////		bytes = JasperRunManager.runReportToPdf(jasperReport,parameters);
//		
//		resp.reset();
//		resp.resetBuffer();
//		resp.setContentType("application/pdf");
//		JasperExportManager.exportReportToPdfStream(jasperPrint, resp.getOutputStream());
//		resp.getOutputStream().flush();
//		resp.getOutputStream().close();
//		
////		resp.reset();
////		resp.resetBuffer();
////		resp.setContentType("application/pdf");
////		resp.setContentLength(bytes.length);
////		ServletOutputStream ouputStream = resp.getOutputStream();
////		ouputStream.write(bytes, 0, bytes.length);
////		ouputStream.flush();
////		ouputStream.close();
//
//	}
//
//}
