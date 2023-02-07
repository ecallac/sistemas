/**
 * 
 */
package com;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.InetAddress;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * @author ecalla
 *
 */
public final class Utils {

//	private static final Log LOG = LogFactory.getLog(PricingUtil.class);

	private Utils() {
	   }
	
	public static boolean containElemnts(List<?> list){
		if(list == null || list.size()==0){
			return false;
		}
		return true;
	}
	
	public static Integer convertLongToInteger(Long object){
		if(object!=null){
			return  object.intValue();
		}
		return null;
	}
	
	public static Integer convertStringToInteger(String object){
		if(object!=null){
			return  Integer.valueOf(object);
		}
		return null;
	}
	
	public static Long convertStringToLong(String object){
		if(object!=null){
			return  Long.valueOf(object);
		}
		return null;
	}
	
	public static String convertObjectToString(Object object){
		if(object!=null){
			return  String.valueOf(object);
		}
		return null;
	}
	public static double convertDouble(Object object){
		if(object!=null){
			return  (Double)object;
		}
		return 0;
	}
	
	public static Date dateMinusDays(Date date, int days) {
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(date);
		cal.add(Calendar.DAY_OF_YEAR, -days);
		return cal.getTime();
	}
	
	public static String dateToString(Date date, String pattern) {
		String result = null;
		if (date == null) {
			return null;
		}
		SimpleDateFormat formatter = new SimpleDateFormat(pattern);
		result = formatter.format(date);
		return result;
	}
	
	public static String getServerName() throws Exception {

//        String message = "";
//        try {
            return InetAddress.getLocalHost().getHostName();
//        } catch (Exception e) {
//            /* logger.error("Error."+e.getMessage(), e); */
//            e.getMessage();
//        }
//        return message;
    }
	
//	public static Date dateFormat(Date date, String pattern) {
//		return stringToDate(dateToString(date, pattern), pattern);
//	}
//	
	public static Date stringToDate(String dateStr, String pattern) throws ParseException {
		Date date = null;
		if (dateStr == null || dateStr.trim().equals("")) {
			return null;
		}
		SimpleDateFormat formatter = new SimpleDateFormat(pattern);
		date = formatter.parse(dateStr);
		return date;
	}
	
	public static Timestamp stringToTimestamp(String dateStr, String pattern) {
		try {
		    SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
		    Date parsedDate = dateFormat.parse(dateStr);
		    return new Timestamp(parsedDate.getTime());
		} catch(Exception e) { //this generic but you can control another types of exception
		    // look the origin of excption 
		}
		return null;
	}
	
	public static List<Date> makePeriodOfDate(Date start, Date end){
		List<Date> dates = new ArrayList<Date>();
		long interval = 24*1000 * 60 * 60;
		long endTime =end.getTime() ;
		long curTime = start.getTime();
		while (curTime <= endTime) {
		    dates.add(new Date(curTime));
		    curTime += interval;
		}
		return dates;
	}
	
	public static List<String> makePeriodOfHours(){
		List<String> list = new ArrayList<String>();
		for(int i=1;i<=24;i++){
			list.add(i+"00");
		}
		return list;
	}
	
	public static BigDecimal objectToBigDecimal(Object object){
		return BigDecimal.valueOf(Double.valueOf(String.valueOf(object))).setScale(6,RoundingMode.HALF_UP);
	}
	
	public static String getFileNameFromPath(String path){
		String[] chars = {"/","\\"};
		for (String string : chars) {
			if (path!=null && path.contains(string)) {
				return path.substring(path.lastIndexOf(string)+1,path.length());
			}
		}
		return null;
	}

	public static String getErrorMessage(String processMessage,String errorMessage){
		return processMessage+" - "+ Utils.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss")+" \nDetalle de Error:"+errorMessage;
	}
	public static String getSuccessMessage(String processMessage){
		return processMessage+" - "+ Utils.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss");
	}
	public static List<String> getFieldsWithAnnotationFromEntity(Class entity, Class annotation){
		List<String> list = new ArrayList<String>();
		Field[] fields = entity.getDeclaredFields();
		for (Field field:fields){
			Annotation anotationtemp = field.getAnnotation(annotation);
			if (anotationtemp!=null){
				list.add(field.getName());
			}
		}
		return list;
	}
}
