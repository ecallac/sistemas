package com.ecallac.rentcar;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;

import com.ecallac.rentcar.domain.Alquiler;
import com.ecallac.rentcar.domain.Conductor;
import com.ecallac.rentcar.util.BeanParser;
import com.ecallac.rentcar.util.Util;
import com.ecallac.rentcar.view.AlquilerView;

public class Test {

	public static void main(String[] args) throws ParseException {
		// TODO Auto-generated method stub
		
		String sd = "2018-12-25";
		String sb = "true";
		String a = "2";
		Long b = 1L;
		Integer c = 1;
		BigDecimal d = BigDecimal.ONE;
		Date e = new Date();
		Double f = 2.4;
		Boolean g = false;
		
		System.out.println(Util.convertObjectToString(b));
		System.out.println(Util.convertObjectToString(c));
		System.out.println(Util.convertObjectToString(d));
		System.out.println(Util.convertDateToString(e, "YYYY-MM-dd"));
		System.out.println(Util.convertObjectToString(f));
		System.out.println(Util.convertObjectToString(g));
		System.out.println("=========");
		System.out.println(Util.convertStringToLong(a));
		System.out.println(Util.convertStringToInteger(a));
		System.out.println(Util.convertObjectToBigDecimal(a));
		System.out.println(Util.convertStringToDate(sd, "YYYY-MM-dd"));
		System.out.println(Util.convertStringToDouble(a));
		System.out.println(Util.convertStringToBoolean(a));
		
		Alquiler object = new Alquiler();
		object.setId(1L);
		object.setStatus("s");
		object.setCuentapactada(BigDecimal.ONE);
		object.setFechainicio(new Date());
		Conductor conductor = new Conductor();
		conductor.setNombre("nomb");
		object.setConductor(conductor);
		AlquilerView view = (AlquilerView)BeanParser.parseObjectToNewClass(object, AlquilerView.class, null);
		System.out.println(view);
	}

}
