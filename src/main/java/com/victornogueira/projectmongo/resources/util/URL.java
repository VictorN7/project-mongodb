package com.victornogueira.projectmongo.resources.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class URL {

	public static String decodeParam(String title) {
		try {
			return URLDecoder.decode(title, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return "";
		}
	}

	public static Date convertDate(String textDate, Date defaultVDate) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT")); // Não é o melhor padrão a ser implementado!
		
		try {
			return sdf.parse(textDate);
		} catch (Exception e) {
			return defaultVDate; 
		}
	}
}
