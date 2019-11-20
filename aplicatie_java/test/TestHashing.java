package test;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;


public class TestHashing {

	@Test
	public void testDateToString() {
		Date now = new Date();
		String pattern = "YYYY-MM-DD HH:MM:SS";
		SimpleDateFormat formatter = new SimpleDateFormat(pattern);
		String mysqlDateString = formatter.format(now);
		System.out.println("Java's Default Date Format: " + now);
		System.out.println("Mysql's Default Date Format: " + mysqlDateString);
	}

	@Test
	public void testStrings() {
		String str = "Data transmitere";
		
		str = str.toLowerCase().replace(" ", "_");
		System.out.println(str);
	}

}
