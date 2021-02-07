package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {


	private WebDriver driver;
	
	//1. by.locators
	private By CreateButton = By.xpath("//*[@type ='submit']");
	
	
	
	//2. constructor of the page class
	public HomePage (WebDriver driver) {
		this.driver = driver;
	}
	

	public String getHomePageTitle() {
		return driver.getTitle();
	}
	
	
	public void clickCreateYourDailyToDOList() {
		driver.findElement(CreateButton).click();
	}
	
	

	
	
}
