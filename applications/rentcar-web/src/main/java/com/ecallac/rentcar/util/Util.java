/**
 * 
 */
package com.ecallac.rentcar.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.InetAddress;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;


/**
 * @author ecalla
 *
 */
public final class Util {
	
//	private static final Log LOG = LogFactory.getLog(PricingUtil.class);
	
	private Util() {
	   }
	
	public static Object convertObjectType(Object valueFrom,String typeFrom,String typeTo) throws ParseException{
		if (typeTo.equals("java.lang.String")) {
			if (typeFrom.equals("java.util.Date")) {
				return convertDateToString((Date) valueFrom, "yyyy-MM-dd");
			} else {
				return convertObjectToString(valueFrom);
			}
		} else if(typeFrom.equals("java.lang.String")){
			if (typeTo.equals("java.lang.Long")) {
				return convertStringToLong((String) valueFrom);
			} else if (typeTo.equals("java.lang.Integer")) {
				return convertStringToInteger((String) valueFrom);
			} else if (typeTo.equals("java.math.BigDecimal")) {
				return convertObjectToBigDecimal(valueFrom);
			} else if (typeTo.equals("java.util.Date")) {
				return convertStringToDate((String) valueFrom, "yyyy-MM-dd");
			} else if (typeTo.equals("java.lang.Double")) {
				return convertStringToDouble((String) valueFrom);
			} else if (typeTo.equals("java.lang.Boolean")) {
				return convertStringToBoolean((String) valueFrom);
			} 
		}
		return null;
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
	
	public static Boolean convertStringToBoolean(String object){
		if(object!=null){
			return  Boolean.valueOf(object);
		}
		return null;
	}
	
	public static String convertObjectToString(Object object){
		if(object!=null){
			return  String.valueOf(object);
		}
		return null;
	}
	public static Double convertStringToDouble(String object){
		if(object!=null){
			return  Double.valueOf(object);
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
	
	public static String convertDateToString(java.util.Date date, String pattern) {
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
	public static java.util.Date convertStringToDate(String dateStr, String pattern) throws ParseException{
		Date date = null;
		if (dateStr == null || dateStr.trim().equals("")) {
			return null;
		}
		SimpleDateFormat formatter = new SimpleDateFormat(pattern);
		date = formatter.parse(dateStr);
		return date;
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
	
	public static BigDecimal convertObjectToBigDecimal(Object object){
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
}
