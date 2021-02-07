package com.qa.util;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import com.qa.factory.DriverFactory;

public class BaseTest {

	
	DriverFactory dr = new DriverFactory();
	ConfigReader cr=new ConfigReader();
	
	
	
	public  void startApp()
	{
		String url=cr.init_prop().getProperty("url");
		String browser=cr.init_prop().getProperty("browser");
		String waitTime=cr.init_prop().getProperty("implicitwaitInSeconds");		
		dr.init_driver(browser);
		DriverFactory.getDriver().get(url);
		DriverFactory.getDriver().manage().timeouts().implicitlyWait(Integer.parseInt(waitTime),TimeUnit.SECONDS);
		
		
	}
	
	
	public void setSessionName() throws FileNotFoundException, IOException {
		String currentUrl = dr.getDriver().getCurrentUrl();
		String[] split = currentUrl.split("org/");
		System.out.println(split[1]);
		Properties prop=cr.init_prop();
		prop.setProperty("sessionName", split[1]);
		prop.store(new FileOutputStream("src/test/java/config/config.properties"), null);
	}
	
	
	
	public String getSessionName()  {
		Properties prop=cr.init_prop();
		String sessionName=prop.getProperty("sessionName");
		return sessionName;
	
	}
	
	
	
}
