package com.vTiger.tests;

import org.testng.annotations.Test;

import com.vTiger.pages.LoginPage;

public class LoginTest extends BaseTest {
	
	
	
	
	@Test
	public void TestCases1_InValidLogin()
	{
		String TCName = "TestCases1_InValidLogin";
		logger = extent.createTest(TCName);
		LoginPage lp = new LoginPage(driver,logger);
		lp.login(td.get(TCName).get("Userid"),td.get(TCName).get("Password"));
		extent.flush();
	}
	

	
	@Test
	public void TestCases2_ValidLogin()
	{
		String TCName = "TestCases2_ValidLogin";
		logger = extent.createTest(TCName);
		LoginPage lp = new LoginPage(driver,logger);
		lp.login(td.get(TCName).get("Userid"),td.get(TCName).get("Password"));
		extent.flush(); 
	}
}
