package com.vTiger.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;

public class LeadPage extends HeaderPage{

	
	public LeadPage(WebDriver driver, ExtentTest logger)
	{
		super(driver, logger);
		
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(name="firstname")
	WebElement tb_firstname;
	
	@FindBy(name="lastname")
	WebElement tb_lastname;
	
	@FindBy(name="company")
	WebElement tb_company;
	
	@FindBy(name="button")
	WebElement btn_save;
	
	@FindBy(xpath="//td[text()='Last Name:']/following::td[1]")
	WebElement lbl_LastNameValue;
	
	@FindBy(xpath="//td[text()='Company:']/following::td[1]")
	WebElement lbl_CompanyValue;
	
	
	
	
	
	
	
	public void CreateLead(String lname, String com)
	{
		type(tb_lastname, lname,lname+"has been entered into lastname field");
		type(tb_company, com,com+"has been entered into company field");
        click(btn_save, "Save button click");
	}

	public void VerifyLeadCreation(String lname, String com)
	{
		getTextandValidate(lbl_LastNameValue, lname, "Lastname Expected text matched with actual");
		getTextandValidate(lbl_CompanyValue, com, "company Expected text matched with actual");

	}
	
	
}
