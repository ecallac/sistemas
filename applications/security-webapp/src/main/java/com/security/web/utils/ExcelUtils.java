/**
 * 
 */
package com.security.web.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Workbook;

import net.sf.jxls.transformer.XLSTransformer;

/**
 * @author EFRAIN
 * @dateCreated 1 may. 2017 20:52:50
 */
public class ExcelUtils {
	public static void createReport(Class<?> clazz,Map<String, Object> beans,String reportTemplate,String fileName,HttpServletResponse response) throws IOException{
		int VALOR_BUFFER = 1024;
		ByteArrayOutputStream output = createReport(clazz,beans,reportTemplate);
		ByteArrayInputStream in = new ByteArrayInputStream(output.toByteArray());
        if (in != null) {
            try {
                response.setHeader("Cache-Control", "public");
                response.setHeader("Content-Description", "File Transfer");
                response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
                response.setHeader("Content-Type", "application/vnd.ms-excel");
                response.setHeader("Content-Transfer-Encoding", "binary");

                ServletOutputStream out = response.getOutputStream();
                byte[] buffer = new byte[VALOR_BUFFER];
                for (int bytesRead = in.read(buffer); bytesRead >= 0; bytesRead = in.read(buffer)) {
                    out.write(buffer, 0, bytesRead);
                }
            } finally {
                try {
                    in.close();
                } catch (IOException e) {
//                	logger.warn("I/O Exception while attempting to close the stream: \n" + e.getMessage());
                	e.printStackTrace();
                }
            }
        }
	}
	
	protected static ByteArrayOutputStream createReport(Class<?> clazz, Map<String, Object> beans,String reportTemplate){
    	
		
		XLSTransformer transformer = new XLSTransformer();
		
		InputStream  inputStream  = clazz.getClassLoader().getResourceAsStream(reportTemplate);
		ByteArrayOutputStream   out = new ByteArrayOutputStream();
		Workbook workbook;
		
		try {
			workbook = (Workbook) transformer.transformXLS(inputStream, beans);
			workbook.write(out);
			
		} catch (Exception e) {
//			logger.info("Exception \n"+e.getMessage());
			e.printStackTrace();
		}
		return out;
    }
}
