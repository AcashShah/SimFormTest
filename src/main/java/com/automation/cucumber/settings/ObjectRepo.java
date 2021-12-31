package com.automation.cucumber.settings;

import java.util.LinkedHashMap;
import java.util.Map;
import org.openqa.selenium.WebDriver;

import com.automation.cucumber.interfaces.IconfigReader;


public class ObjectRepo {
	
	//STATIC It used for common property for all object and gets memory only once in the class area at the time of class loading
		public static WebDriver driver;
		public static IconfigReader reader;
		public static Map<String, Object> data = new LinkedHashMap<String, Object>();	

}
