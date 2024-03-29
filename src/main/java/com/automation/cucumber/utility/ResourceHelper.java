package com.automation.cucumber.utility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class ResourceHelper {
	
	public static String getResourcePath(String resource) {
		String path = 
				getBaseResourcePath() + resource;
		return path;
	}
	
	public static String getBaseResourcePath() {
		String path = ResourceHelper.class.getResource("/").getPath();
		System.out.println(path);
		return path;
	}
	
	public static InputStream getResourcePathInputStream(String resource) throws FileNotFoundException {
		return new FileInputStream(ResourceHelper.getResourcePath(resource));
		
		
	}

}
