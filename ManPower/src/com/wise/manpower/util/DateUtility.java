package com.wise.manpower.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.sun.org.apache.bcel.internal.generic.RETURN;

public class DateUtility{
	private final static String DATEFORMAT = "yyyy-mm-dd";
	public static Date convertStringToUtil(String stringDate) {
		Date date = null;
		try {
			
			date = new SimpleDateFormat(DATEFORMAT).parse(stringDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;
		
	}

	
}
