package com.vTiger.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;
import com.vTiger.utilities.CommanActions;

public class HomePage extends HeaderPage {
	
	
	public HomePage(WebDriver driver, ExtentTest logger)
	{
		super(driver, logger);
		
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(linkText="Logout")
	WebElement lnk_logout;
	
	@FindBy(linkText="New Lead")
	WebElement lnk_newlead;
	
	@FindBy(name="pipeline")
	WebElement pipeline;
	
	public void clickNewLead()
	{
		click(lnk_newlead, "Clicked on New Lead menu");
	}
	
	public void clickLogout()
	{
		click(lnk_logout, "Clicked on logout");
	}
	
	public void verifyPipeline()
	{
		click(pipeline, "Pipeline dashboard is visible on home page");
	}

}
