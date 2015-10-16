package Policy_Registration_MA11_Package;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PolicyRegistration_Functions
{

	static String[] apolicyDetailsArray = new String[50];
	static String[] aAutopolicyDetailsArray = new String[50];
	static String[] aHomepolicyDetailsArray = new String[50];
	
	static String sAddressLine1 = null;
	static String sPhoneNumber = null;
	static String sDateOfBirth = null;
	
	static String sAutoPolicyNumber = null;
	static String sAutoOwnerName = null;
	static String sAutoRenewalDate = null;
	static boolean bHomePolicy = false;
	static String sHomeRenewalDate = null;
	
	//Final Variables 
	static String sRegisterPolicyNumber = null;
	static String sRegisterPolicyFirstName = null;
	static String sRegisterPolicyLastName = null;
	
	static String sRegisterPolicyStreet = null;
	static String sRegisterPolicyCity = null;
	static String sRegisterPolicyState = null;
	static String sRegisterPolicyZipp = null;
		
	static String sRegisterPolicyDateOfMonth = null;
	static String sRegisterPolicyDateOfDay = null;
	static String sRegisterPolicyDateOfYear = null;
	
	static String sRegisterPolicyPhoneFirst = null;
	static String sRegisterPolicyPhoneMiddle  = null;
	static String sRegisterPolicyPhonelast = null;
	static String sRegisterRenewalDate = null;
	
	static String sHomePolicyNumber = null;	
	
	//Test
	
    public static  void fUserLoginEAgent(WebDriver driver,String UserID, String password) throws InterruptedException
    {
     	    	
    	// Go to the Google Suggest home page
        driver.get("https://eagenttz.farmersinsurance.com");
    	
        //send user name
        driver.findElement(By.name("USER")).sendKeys(UserID);
    	
        //send password
    	driver.findElement(By.name("PASSWORD")).sendKeys(password);
    	
    	//submit login
    	//driver.findElement(By.xpath("/html/body/div/form/table/tbody/tr[5]/td/input")).click();
    	driver.findElement(By.xpath("/html/body/div[2]/div/form/p[4]/input")).click();
    	
    	Thread.sleep(3000);
    	//Select the e agent link to navigate
    	driver.findElement(By.xpath("/html/body/div/center/table/tbody/tr[2]/td/a")).click(); 	
    	
    	
    	
    	// Wait the element to load in next page
    	WebDriverWait wait = new WebDriverWait(driver,30);
    	wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("txtSearchVal")));
    	
     	
    }

	 public static WebDriver fDriverInitialization(String AppPath)
	 {
		 
			System.setProperty("webdriver.chrome.driver", AppPath);
	    	ChromeOptions options = new ChromeOptions();
	    	options.addArguments("--start-maximized");
	    	WebDriver driver = new ChromeDriver(options);   	
	    	
	    	return driver;
		 
	 }

	 public static void fPolicySearchScreen(WebDriver driver, String sPolicynumber)
	 {
		WebDriverWait wait = new WebDriverWait(driver,60);
 	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[5]/div/table[3]/tbody/tr/td/table/tbody/tr/td[3]/div/div[2]/div[3]/table/tbody/tr/td[4]/a/img")));
	
		//Send customer policy number
    	driver.findElement(By.name("txtSearchVal")).sendKeys(sPolicynumber);
    	    	
    	driver.findElement(By.className("dropbox_ext")).sendKeys("Customer View");    
    	
 	    //Click search button to retrieve the policy details    	
 	    driver.findElement(By.xpath("/html/body/div[5]/div/table[3]/tbody/tr/td/table/tbody/tr/td[3]/div/div[2]/div[3]/table/tbody/tr/td[4]/a/img")).click();

	}
	 
	 public static void fFetchPolicyNumer(WebDriver driver)
	 {	 
		 
	     //Wait for the name element to load 
	     WebDriverWait wait = new WebDriverWait(driver,60);
	 	 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/doctype/div/div/form[1]/table[4]/tbody/tr[2]/td/table/tbody[1]/tr/td/table/tbody/tr[3]/td[1]")));

		
		if (driver.findElements( By.xpath("/html/body/doctype/div/div/form[1]/table[4]/tbody/tr[2]/td/table/tbody[1]/tr/td/table/tbody/tr[3]") ).size() != 0)
		{
			int j = 1;
			int i = 3;
			while(driver.findElements( By.xpath("/html/body/doctype/div/div/form[1]/table[4]/tbody/tr[2]/td/table/tbody[1]/tr/td/table/tbody/tr[" + i + "]") ).size() != 0)
			{
				
				apolicyDetailsArray[j] = driver.findElement(By.xpath("/html/body/doctype/div/div/form[1]/table[4]/tbody/tr[2]/td/table/tbody[1]/tr/td/table/tbody/tr[" + i + "]/td[1]/acronym[1]")).getText().trim() ;
				j = j + 1;
				
				apolicyDetailsArray[j] = driver.findElement(By.xpath("/html/body/doctype/div/div/form[1]/table[4]/tbody/tr[2]/td/table/tbody[1]/tr/td/table/tbody/tr["+ i +"]/td[2]")).getText().trim() ;
				j = j + 1;
				
				//System.out.println(apolicyDetailsArray[j]);
				i = i + 1; 
	   
			}
			
			
			if (driver.findElements( By.xpath("/html/body/doctype/div/div/form[1]/table[4]/tbody/tr[2]/td/table/tbody[1]/tr/td/table/tbody/tr[3]/td[5]") ).size() != 0)
			{
			  sAddressLine1 = driver.findElement(By.xpath("/html/body/doctype/div/div/form[1]/table[4]/tbody/tr[2]/td/table/tbody[1]/tr/td/table/tbody/tr[3]/td[5]")).getText().trim();
			}

			if (driver.findElements( By.xpath("/html/body/doctype/div/div/form[1]/table[4]/tbody/tr[2]/td/table/tbody[1]/tr/td/table/tbody/tr[3]/td[6]") ).size() != 0)
			{
			  sPhoneNumber = driver.findElement(By.xpath("/html/body/doctype/div/div/form[1]/table[4]/tbody/tr[2]/td/table/tbody[1]/tr/td/table/tbody/tr[3]/td[6]")).getText().trim();
			
			  sPhoneNumber = sPhoneNumber.substring(0, 12);
			
			}

		}		
		else
		{
			System.out.println("No Entries available");
			System.exit(0);
		}
		
		
	}
 
	 public static String fVerifyAutopolicyDetails(WebDriver driver)
	 {
		String Empty = null;
		bHomePolicy = false;
		
		boolean rough = false;
		try{

		 rough = driver.findElement(By.xpath("/html/body/doctype/div/div/table[2]/tbody/tr/td/table/tbody/tr[2]/td/table[2]/tbody[2]/tr[3]/td[3]")).isDisplayed();
		
		} catch (NoSuchElementException e) {
			rough = false;
		}
		if (rough == true)
		{
			if (driver.findElements( By.xpath("/html/body/doctype/div/div/table[2]/tbody/tr/td/table/tbody/tr[2]/td/table[2]/tbody[2]/tr[3]/td[1]") ).size() != 0)
			{
				sAutoPolicyNumber = driver.findElement(By.xpath("/html/body/doctype/div/div/table[2]/tbody/tr/td/table/tbody/tr[2]/td/table[2]/tbody[2]/tr[3]/td[1]")).getText().trim();
				sAutoOwnerName = driver.findElement(By.xpath("/html/body/doctype/div/div/table[2]/tbody/tr/td/table/tbody/tr[2]/td/table[2]/tbody[2]/tr[3]/td[3]")).getText().trim();
				sAutoRenewalDate = driver.findElement(By.xpath("/html/body/doctype/div/div/table[2]/tbody/tr/td/table/tbody/tr[2]/td/table[2]/tbody[2]/tr[3]/td[4]")).getText().trim();
				
				Empty = sAutoPolicyNumber;
			}
		}
	
		
		return Empty;
	 }
	 
	 public static void fVerifyHomepolicyDetails(WebDriver driver)
	 {
		
		 if (driver.findElements( By.xpath("/html/body/doctype/div/div/table[2]/tbody/tr/td/table/tbody/tr[2]/td/table[3]/tbody[2]/tr[3]/td[1]") ).size() != 0)
		{
			sHomePolicyNumber = driver.findElement(By.xpath("/html/body/doctype/div/div/table[2]/tbody/tr/td/table/tbody/tr[2]/td/table[3]/tbody[2]/tr[3]/td[1]")).getText().trim();
			sHomeRenewalDate = driver.findElement(By.xpath("/html/body/doctype/div/div/table[2]/tbody/tr/td/table/tbody/tr[2]/td/table[3]/tbody[2]/tr[3]/td[4]")).getText().trim();
			
			bHomePolicy = true;
		}
		
	 }
 
	 public static void fVerifyUmbrellapolicyDetails(WebDriver driver)
	 {
	     //   driver.findElement(By.name("first_name")).sendKeys(sRegisterPolicyFirstName);	        
		    //    driver.findElement(By.name("last_name")).sendKeys(sRegisterPolicyLastName);
		       
			     //   driver.close();
			        
			     //   	driver.switchTo().window(aSessionId[0]);     
				 //   	wait1 = new WebDriverWait(driver,60);
				 //    	wait1.until(ExpectedConditions.visibilityOfElementLocated(By.className("logOutStyle")));
				 	
				  //	    driver.findElement(By.className("logOutStyle")).click();
				 //	    driver.quit();
				 	//System.exit(0);
	 }
	 
	 public static void fFinalAutoPolicyDetailsExtract(WebDriver driver)
	 {
		int i = 1;
		//	if ((sAutoPolicyNumber != null) & (sAutoOwnerName != null))
		if (sAutoPolicyNumber != null) 
		{
			
			while(apolicyDetailsArray[i] != null)
			{
		
				if (apolicyDetailsArray[i].equals(sAutoOwnerName)) 
		         {
					
					sRegisterPolicyFirstName = sAutoOwnerName;
					sRegisterPolicyNumber = sAutoPolicyNumber;
					sRegisterRenewalDate = sAutoRenewalDate.trim();
					sDateOfBirth = apolicyDetailsArray[i + 1];
					
					//sRegisterPolicyStreet = sAddressLine1;
					
					String[] aAddresssplit = sAddressLine1.split(",|\\n");
					
					sRegisterPolicyStreet = aAddresssplit[0].trim();
					
					sRegisterPolicyCity = aAddresssplit[1].trim();			

					String sTemopPolicyStateZip = aAddresssplit[2];			
					String[] aAddresssplit1 = sTemopPolicyStateZip.split("\\s+");			
					
					sRegisterPolicyState = aAddresssplit1[1].trim();					
					sRegisterPolicyZipp = aAddresssplit1[2].trim();
				
					fSplitPhoneNumber();
					fSplitDateofBirth();
					sRegisterPolicyDateOfMonth = fGetMonthValue(sRegisterPolicyDateOfMonth);
					sRegisterPolicyState = fGetStateValue(sRegisterPolicyState);
					
		             break;
   
		         }
				
				i= i + 1;
	   
			}
			

		}
		
	 }

	 public static void fFinalHomePolicyDetailsExtract(WebDriver driver)
	 {

		if (sHomePolicyNumber != null) 
		{
			
			sRegisterPolicyFirstName = apolicyDetailsArray[1];
			sRegisterPolicyNumber = sHomePolicyNumber;
			sRegisterRenewalDate = sHomeRenewalDate.trim();
			sDateOfBirth = apolicyDetailsArray[2];
			
			//sRegisterPolicyStreet = sAddressLine1;
			
			String[] aAddresssplit = sAddressLine1.split(",|\\n");
			
			sRegisterPolicyStreet = aAddresssplit[0].trim();
			
			sRegisterPolicyCity = aAddresssplit[1].trim();			

			String sTemopPolicyStateZip = aAddresssplit[2];			
			String[] aAddresssplit1 = sTemopPolicyStateZip.split("\\s+");			
			
			sRegisterPolicyState = aAddresssplit1[1].trim();					
			sRegisterPolicyZipp = aAddresssplit1[2].trim();
		
			
			fSplitPhoneNumber();
			fSplitDateofBirth();
			
			sRegisterPolicyDateOfMonth = fGetMonthValue(sRegisterPolicyDateOfMonth);
			sRegisterPolicyState = fGetStateValue(sRegisterPolicyState);

		}
		
	 }
	 
	 public static void fSplitPhoneNumber()
	 {
		
		String[] aAddresssplit3 = sPhoneNumber.split("-");
		
		sRegisterPolicyPhoneFirst = aAddresssplit3[0].trim();
		sRegisterPolicyPhoneMiddle = aAddresssplit3[1].trim();
		sRegisterPolicyPhonelast = aAddresssplit3[2].trim();
	 }

	 public static void fSplitDateofBirth()
	 {
		String[] aAddresssplit2 = sDateOfBirth.split("\\.|,");
		
		sRegisterPolicyDateOfMonth = aAddresssplit2[0].trim();
		sRegisterPolicyDateOfDay = aAddresssplit2[1].trim();
		sRegisterPolicyDateOfYear = aAddresssplit2[2].trim();
		
	 }

	 public static String fGetMonthValue(String month)
	    {
	    	String sMonthValue = null;
	    	
	    	switch (month)
	    	{
	    	case "Jan":  sMonthValue = "January";
	        break;
	    	case "Feb":  sMonthValue = "February";
	        break;
	    	case "Mar":  sMonthValue = "March";
	        break;
	    	case "Apr":  sMonthValue = "April";
	        break;
	    	case "May":  sMonthValue = "May";
	        break;
	    	case "Jun":  sMonthValue = "June";
	        break;
	    	case "Jul":  sMonthValue = "July";
	        break;
	    	case "Aug":  sMonthValue = "August";
	        break;
	    	case "Sep":  sMonthValue = "September";
	        break;
	    	case "Oct":  sMonthValue = "October";
	        break;
	    	case "Nov":  sMonthValue = "November";
	        break;
	    	case "Dec":  sMonthValue = "December";
	        break;   
	        
	    	}
	    	
	    	  return sMonthValue;
	    }
	
	 public static String fGetStateValue(String state)
	 {
 	String sStateValue = null;
 	
 	switch (state)
 	{
 	case "AZ":  sStateValue = "Arizona";
     break;
 	case "AL":  sStateValue = "Alabama";
     break;
 	case "AR":  sStateValue = "Arkansas";
     break;
 	case "CA":  sStateValue = "California";
     break;
 	case "CO":  sStateValue = "Colorado";
     break;
 	case "CT":  sStateValue = "Connecticut";
     break;
 	case "GA":  sStateValue = "Georgia";
     break;
 	case "ID":  sStateValue = "Idaho";
     break;
 	case "IL":  sStateValue = "Illinois";
     break;
 	case "IN":  sStateValue = "Indiana";
     break;
 	case "IA":  sStateValue = "Iowa";
     break;
 	case "KS":  sStateValue = "Kansas";
     break;
 	case "KY":  sStateValue = "Kentucky";
     break;
 	case "LA":  sStateValue = "Lousiana";
     break;
 	case "ME":  sStateValue = "Maine";
     break;
 	case "MD":  sStateValue = "Maryland";
     break;
 	case "MI":  sStateValue = "Michigan";
     break;
 	case "MN":  sStateValue = "Minnesota";
     break;
 	case "MO":  sStateValue = "Missouri";
     break;
 	case "MS":  sStateValue = "Mississippi";
     break;
 	case "MT":  sStateValue = "Montana";
     break;
 	case "NC":  sStateValue = "North Carolina";
     break;
 	case "NE":  sStateValue = "Nebraska";
     break;
 	case "NH":  sStateValue = "New Hampshire";
     break;
 	case "NV":  sStateValue = "Nevada";
     break;
 	case "NM":  sStateValue = "New Mexico";
     break;
 	case "ND":  sStateValue = "North Dakota";
     break;
 	case "NJ":  sStateValue = "New Jersey";
     break;
 	case "NY":  sStateValue = "New York";
     break;
 	case "OH":  sStateValue = "Ohio";
     break;
 	case "OK":  sStateValue = "Oklahoma";
     break;
 	case "OR":  sStateValue = "Oregon";
     break;
 	case "PA":  sStateValue = "Pennsylvania";
     break;
 	case "SD":  sStateValue = "South Dakota";
     break;
 	case "TN":  sStateValue = "Tennessee";
     break;
 	case "TX":  sStateValue = "Texas";
     break;
 	case "UT":  sStateValue = "Utah";
     break;
 	case "VA":  sStateValue = "Virginia";
     break;
 	case "VT":  sStateValue = "Vermont";
     break;
 	case "WA":  sStateValue = "Washington";
     break;
 	case "WI":  sStateValue = "Wisconsin";
     break;
 	case "WY":  sStateValue = "Wyoming";
     break;
     
 	}
 	
 	return sStateValue;

 }
	
	 public static void fRegisterPolicyDetails(WebDriver driver, String sEmailID , HSSFSheet worksheet , int k) throws InterruptedException
	 {
		
		// Click on the registration link to register through EAgent site.	    	
	    driver.findElement(By.xpath("/html/body/doctype/div/div/form[1]/table[4]/tbody/tr[2]/td/table/tbody[2]/tr/td/a")).click();
	 	    
	     
	    String sCustomerWindow = driver.getWindowHandle();
	  	for (String handle : driver.getWindowHandles())
	  	 {
	  		  //System.out.println(handle);
	          if (handle.equals(sCustomerWindow)) 
	          {
	         	 System.out.println("closed");
	         	 driver.close();
	              break;        
	          }
	          
	      }
	  			
	 	String[] aSessionId = new String[3];
	  	int i = 0;	  	
	  	
	  	for (String handle : driver.getWindowHandles())
	 	 {     		
	  		aSessionId[i]  = handle;     		
	         i = i + 1;	            
	     } 
	  	
	  	driver.switchTo().window(aSessionId[1]);
	  	  	
	  	
	  	WebDriverWait wait1 = new WebDriverWait(driver,120);
        wait1.until(ExpectedConditions.visibilityOfElementLocated(By.name("plcyNumber")));
        
        //bHomePolicy = true;
        if (bHomePolicy == true)
	  	{
	  		driver.findElement(By.xpath("//*[@id=\"FormBox1\"]/p[1]/input[2]")).click();;
	  	}
        else
        {
        	driver.findElement(By.xpath("//*[@id=\"FormBox1\"]/p[1]/input[1]")).click();
        }
        
        driver.findElement(By.name("plcyNumber")).sendKeys(sRegisterPolicyNumber);
    
        driver.findElement(By.name("street")).sendKeys(sRegisterPolicyStreet);        
        driver.findElement(By.name("city")).sendKeys(sRegisterPolicyCity);
        Thread.sleep(1000);
        driver.findElement(By.name("state")).sendKeys(sRegisterPolicyState);
        driver.findElement(By.name("zip")).sendKeys(sRegisterPolicyZipp);
        
        
        driver.findElement(By.id("monthDOB")).sendKeys(sRegisterPolicyDateOfMonth);
        driver.findElement(By.id("dayDOB")).sendKeys(sRegisterPolicyDateOfDay);
        driver.findElement(By.id("yearDOB")).sendKeys(sRegisterPolicyDateOfYear);
        
        driver.findElement(By.name("homePhoneArea")).sendKeys(sRegisterPolicyPhoneFirst);
        driver.findElement(By.name("homePhoneMiddle")).sendKeys(sRegisterPolicyPhoneMiddle);
        driver.findElement(By.name("homePhoneLast")).sendKeys(sRegisterPolicyPhonelast);
        
        driver.findElement(By.id("email_id")).sendKeys(sEmailID);
        driver.findElement(By.name("emailConfirmation")).sendKeys(sEmailID);
        

        fSeparationofNameDetails(driver );        
      
        worksheet.getRow(k).createCell(3).setCellValue(sRegisterPolicyNumber);
        worksheet.getRow(k).createCell(4).setCellValue(sRegisterPolicyFirstName);
        worksheet.getRow(k).createCell(5).setCellValue(sRegisterPolicyLastName);
        worksheet.getRow(k).createCell(6).setCellValue(sRegisterPolicyStreet);
        worksheet.getRow(k).createCell(7).setCellValue(sRegisterPolicyCity);
        worksheet.getRow(k).createCell(8).setCellValue(sRegisterPolicyState);
        worksheet.getRow(k).createCell(9).setCellValue(sRegisterPolicyZipp);
        
        sRegisterPolicyDateOfMonth = sRegisterPolicyDateOfMonth + "/" + sRegisterPolicyDateOfDay + "/" + sRegisterPolicyDateOfYear;
        worksheet.getRow(k).createCell(10).setCellValue(sRegisterPolicyDateOfMonth);
        
        sRegisterPolicyPhoneFirst = sRegisterPolicyPhoneFirst + "-" + sRegisterPolicyPhoneMiddle +"-" + sRegisterPolicyPhonelast;        
        worksheet.getRow(k).createCell(11).setCellValue(sRegisterPolicyPhoneFirst);
        
        worksheet.getRow(k).createCell(13).setCellValue(sRegisterRenewalDate);
        
	    driver.findElement(By.xpath("//*[@id=\"FormBox1\"]/div[3]/a[2]")).click(); 
	    
	    Thread.sleep(1000); 
	    
	    
	    try 
	    { 
	    	//System.out.println(driver.switchTo().alert().getText());
	    	driver.switchTo().alert().accept();

	    }
	    catch (NoAlertPresentException Ex) 
	    { 
	    }  
        
        try
	     {	
	    	driver.findElement(By.id("errMsg")).isDisplayed();
	    	String sErrorMessage = driver.findElement(By.id("errMsg")).getText();  	
	    	worksheet.getRow(k).createCell(12).setCellValue(sErrorMessage);	    	
	    	
	     }
	     catch (NoSuchElementException e)
		{	  
	 	  worksheet.getRow(k).createCell(12).setCellValue("Registered");	   
		}     
 
	 }	
 		 
	 public static void fSeparationofNameDetails(WebDriver driver )
	 {
			String[] aNameSplit = sRegisterPolicyFirstName.split("\\s+"); 
			
			if (aNameSplit.length == 2)
			{
				sRegisterPolicyFirstName = aNameSplit[0].trim();
				sRegisterPolicyLastName = aNameSplit[1].trim();
				  driver.findElement(By.name("first_name")).sendKeys(sRegisterPolicyFirstName);	        
			      driver.findElement(By.name("last_name")).sendKeys(sRegisterPolicyLastName);				
		      
			}
				
			if (aNameSplit.length == 3)
			{
				sRegisterPolicyFirstName = aNameSplit[0].trim();
				sRegisterPolicyLastName = aNameSplit[1].trim() + " " +  aNameSplit[2].trim();
				  driver.findElement(By.name("first_name")).sendKeys(sRegisterPolicyFirstName);	        
			      driver.findElement(By.name("last_name")).sendKeys(sRegisterPolicyLastName);  
			      
			}
			
			if (aNameSplit.length == 4)
			{
				sRegisterPolicyFirstName = aNameSplit[0].trim();
				sRegisterPolicyLastName = aNameSplit[1].trim() + " " +  aNameSplit[2].trim() + " " +  aNameSplit[3].trim();
				  driver.findElement(By.name("first_name")).sendKeys(sRegisterPolicyFirstName);	        
			      driver.findElement(By.name("last_name")).sendKeys(sRegisterPolicyLastName);
			      
			}
			
			if (aNameSplit.length == 5)
			{
				sRegisterPolicyFirstName = aNameSplit[0].trim() + " " +  aNameSplit[1].trim();
				sRegisterPolicyLastName = aNameSplit[2].trim() + " " +  aNameSplit[3].trim() + " " +  aNameSplit[4].trim();
				  driver.findElement(By.name("first_name")).sendKeys(sRegisterPolicyFirstName);	        
			      driver.findElement(By.name("last_name")).sendKeys(sRegisterPolicyLastName);
			      
			}
		
	 }
			
	 public static boolean fVerifyPolicyAlreadyRegistered(WebDriver driver)
	 {
		 boolean bElementAvaialble = false;
			try
			{
				 //Wait for the name element to load 
			     WebDriverWait wait = new WebDriverWait(driver,60);
			 	 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/doctype/div/div/form[1]/table[4]/tbody/tr[2]/td/table/tbody[1]/tr/td/table/tbody/tr[3]/td[1]")));
 
			 	bElementAvaialble = driver.findElement(By.xpath("/html/body/doctype/div/div/form[1]/table[4]/tbody/tr[2]/td/table/tbody[1]/tr/td/table/tbody/tr[3]/td[7]/a")).isDisplayed();
				//Valid = true;
			
			}
			catch (NoSuchElementException e)
			{
				bElementAvaialble = false;
			}
		 return  bElementAvaialble;
	 }

	 public static void fSwitchDriver1(WebDriver driver)
	 {
		 String mainWindow = driver.getWindowHandle();    	
		 
	     for (String handle : driver.getWindowHandles())	     	
	     {
	    	 //System.out.println(handle);
	         if (!handle.equals(mainWindow)) 
	         {	        	
	            driver.switchTo().window(handle);
	            driver.close();	            
	            break;
	         } 	     
	     }
	     
	     driver.switchTo().window(mainWindow);
	 }
	 
	 public static void fSwitchDriver2(WebDriver driver)
	 {
		 driver.close();
		 for (String handle : driver.getWindowHandles())		     	
	     {
			 driver.switchTo().window(handle); 
	     }  	
		   
	 }

	 public static void fSwitchDriver3(WebDriver driver)
	 {
		 
		 String mainWindow = driver.getWindowHandle();    	
		 
	     for (String handle : driver.getWindowHandles())	     	
	     {
	    	 //System.out.println(handle);
	         if (handle.equals(mainWindow)) 
	         {	        	
	            driver.switchTo().window(handle);
	            driver.close();	            
	            break;
	         } 	     
	     }
	     
	     for (String handle : driver.getWindowHandles())	     	
	     {
	    	 driver.switchTo().window(handle);
	     }
	    
	 }
	 
	 public static boolean fVerifyPolicyPresent(WebDriver driver) throws InterruptedException
	 {
		 boolean bPolicyPresent = false;
		 try
		 {
			 String mainWindow = driver.getWindowHandle();    	
		     for (String handle : driver.getWindowHandles())			     	
		     {			     	
		     	 //System.out.println(handle);
		         if (!handle.equals(mainWindow)) 
		         {
		             driver.switchTo().window(handle);
		             break;
		         }
		     }			 
		     
   
		     //Wait for the name element to load 
		     WebDriverWait wait = new WebDriverWait(driver,60);
		 	 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"custommenubar\"]/table/tbody/tr/td[1]/table/tbody")));
		     
		     //bPolicyPresent = driver.findElement(By.xpath("/html/body/div/div/table[2]/tbody/tr/td[1]/span")).isDisplayed();
		 	 
		 	 
		     
		 }
		catch (NoSuchElementException e)
		{
			bPolicyPresent = false;
		}

	     return bPolicyPresent;
		 
	 }

	 public static boolean fVerfyActivePoliciesPresent(WebDriver driver) throws InterruptedException
	 {
		 boolean bActivePolicyPresent = false;
		 try
		 {  
		     //Wait for the name element to load 
		     WebDriverWait wait = new WebDriverWait(driver,60);
		 	 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"custommenubar\"]/table/tbody/tr/td[1]/table/tbody")));

		 	//bActivePolicyPresent = driver.findElement(By.xpath("/html/body/div/div/form[1]/table[4]/tbody/tr[2]/td/table/tbody[1]/tr/td/table/tbody/tr[3]/td[6]")).isDisplayed();
		     
		 	
		 	 
		 }
		catch (NoSuchElementException e)
		{
			bActivePolicyPresent = false;
		}

	     return bActivePolicyPresent;
		 
	 }
	 
	 
	 public static void fSearchhouseHoldNumber(WebDriver driver, String sPolicynumber) throws InterruptedException
	 {
		 Thread.sleep(3000);
		 driver.findElement(By.xpath("/html/body/div[1]/div/table[4]/tbody/tr[2]/td/form/table/tbody/tr/td/table/tbody/tr[2]/td[1]/table/tbody/tr/td[1]/select")).sendKeys("Household Number");
		 
		 driver.findElement(By.xpath("/html/body/div[1]/div/table[4]/tbody/tr[2]/td/form/table/tbody/tr/td/table/tbody/tr[2]/td[1]/table/tbody/tr/td[1]/input[1]")).sendKeys(sPolicynumber);
		 
		 driver.findElement(By.xpath("/html/body/div[1]/div/table[4]/tbody/tr[2]/td/form/table/tbody/tr/td/table/tbody/tr[4]/td/span/a")).click();
			 
		 
	 }
	 
}
