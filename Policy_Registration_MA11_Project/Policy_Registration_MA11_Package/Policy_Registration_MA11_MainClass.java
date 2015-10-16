package Policy_Registration_MA11_Package;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;


public class Policy_Registration_MA11_MainClass

{	
	
	public static void main(String[] args) throws Exception 
  {
		try
		{	

		//*************** Set the Input before Starting the Scripts ****************************************************//	
			
		String UserID = "uswshw41";
		String Password = "test11";		
		
		//Set the path of Chrome browser 
		String sAppPath = "D:\\chromedriver_win32\\chromedriver.exe";
		
		//Set the excel path where your input resides 
		String sExcelPath = "D:\\Registration scripts\\Policy_Registration_MA11_Project\\Policy_Registration_MA11.xls";
		String sSheetName = "Policy_Details";		 
		
		//*************** End of Input Setup **************************************************************************//
		
		
		
		//Read Excel and fetch the input policy number and Mail ID.
		FileInputStream fileInputStream = new FileInputStream(sExcelPath);
		HSSFWorkbook workbook = new HSSFWorkbook(fileInputStream);
		HSSFSheet worksheet = workbook.getSheet(sSheetName);	

		int rowcount = worksheet.getLastRowNum();		
		System.out.println(rowcount);
		
		//Driver Initialization
      	WebDriver driver = PolicyRegistration_Functions.fDriverInitialization(sAppPath);
      	
      	//EAgent Login 
      	PolicyRegistration_Functions.fUserLoginEAgent(driver, UserID, Password);      	
      	
      	//Close the additional window which is opened in MI51 region alone.          	
      	PolicyRegistration_Functions.fSwitchDriver1(driver);
      	
      
		for (int i = 1 ; i <= rowcount ; i ++)
		{
	
		//Set the excel column number for policy number, Mail ID and Registration status. 
		int iPolicyRownumber = 1;
		int iMailIDRownumber = 2;
		int iStatusRownumber = 12;
			
		HSSFCell HssPolicyNumber = null;
		HSSFCell HssEmailID = null;
		HSSFCell HssRegisteredStatus = null;
		String sPolicynumber = null;
		String sEmailID =  null;
		String sRegisteredStatus =  null;
		

		worksheet.getRow(i).getCell(iPolicyRownumber).setCellType(Cell.CELL_TYPE_STRING);
		HssPolicyNumber = worksheet.getRow(i).getCell(iPolicyRownumber);		
		sPolicynumber = String.valueOf(HssPolicyNumber).trim();
		
		worksheet.getRow(i).getCell(iMailIDRownumber).setCellType(Cell.CELL_TYPE_STRING);
		HssEmailID = worksheet.getRow(i).getCell(iMailIDRownumber);
		sEmailID = String.valueOf(HssEmailID).trim();
		
		HssRegisteredStatus = worksheet.getRow(i).getCell(iStatusRownumber);
		sRegisteredStatus = String.valueOf(HssRegisteredStatus).trim();
  	
		System.out.println(i);
	  	System.out.println(sPolicynumber);
	  	System.out.println(sEmailID);   
	  	System.out.println(sRegisteredStatus);
	  			
  	
	  	if(sRegisteredStatus == "null")
	  	{
	  		//Search the policy Number in Main window based on customer view.
	      	PolicyRegistration_Functions.fPolicySearchScreen(driver, sPolicynumber);	      	
	      	
	      	//Verify Policy is present or not in the region.
	       	boolean bPolicyPresent = PolicyRegistration_Functions.fVerifyPolicyPresent(driver);   	
	      	
	      	if (bPolicyPresent)
	      	{
	      		System.out.println("Policy Not Available");
	      		worksheet.getRow(i).createCell(12).setCellValue("Policy Not Available");
	      		PolicyRegistration_Functions.fSwitchDriver2(driver);
	      	}
	      	else
	      	{   
	      		//This function call used when we don't have policy number to register. Instead registration by using house hold number
	      		PolicyRegistration_Functions.fSearchhouseHoldNumber(driver, sPolicynumber);
	      		
	      		//Verify the policy has Active Polices or not. If the Policies falls under Cancelled 60 + days then the policy will not be registered It will be skipped.
		      	boolean bActivePolices = PolicyRegistration_Functions.fVerfyActivePoliciesPresent(driver);      		
			      
		      	if(!bActivePolices)
		      	{
		        //Verify policy already registered        	
	      	    boolean bElementPresent = PolicyRegistration_Functions.fVerifyPolicyAlreadyRegistered(driver);   	
      	
		      	if (bElementPresent == true)
		  		{
		      		System.out.println("Policy Already Registered");
		      		worksheet.getRow(i).createCell(12).setCellValue("Policy Already Registered");
		      		PolicyRegistration_Functions.fSwitchDriver2(driver);
		  		}
		      	else
		      	{

		      		//Retrieve Policy Number and its fields in Array.
		      		PolicyRegistration_Functions.fFetchPolicyNumer(driver);            	
		          	
		          	//Retrieve Auto Policy Details
		          	 String sVerifyAutoPolicy = PolicyRegistration_Functions.fVerifyAutopolicyDetails(driver);
	          	
		          	 if (sVerifyAutoPolicy != null)
		      		 {
		      	    	//Construct Auto and split the Final fields
		          		PolicyRegistration_Functions.fFinalAutoPolicyDetailsExtract(driver);	
		      		 }
		          	 else
		          	 {
		      	    	//Retrieve Home Policy Details
		          		PolicyRegistration_Functions.fVerifyHomepolicyDetails(driver);
		
		      	    	//Construct Home and split the Final fields
		          		PolicyRegistration_Functions.fFinalHomePolicyDetailsExtract(driver);
		          		 
		          	 }
	          	
			          	//register the Auto Policy Details
			          	PolicyRegistration_Functions.fRegisterPolicyDetails(driver, sEmailID, worksheet, i);
			
			            //Close the additional window which is opened in MI51 region alone.          	
			          	PolicyRegistration_Functions.fSwitchDriver3(driver);		      		
		      	 }          
		      	
      	       }
		      	
		      	else
		      	{
		      		System.out.println("HouseHold Does not have Active Policies");
		      		worksheet.getRow(i).createCell(12).setCellValue("Household does not have Active Polices. It has only Cancelled policies");
		      		PolicyRegistration_Functions.fSwitchDriver2(driver);
		      	}	
	  	  }
      	
	  	}
	  	else
	  	{
	  		System.out.println("CSS Registration already processed....");
	  	} 
	  	
	  	try
		{      
		FileOutputStream out = new FileOutputStream(sExcelPath); 
		workbook.write(out);  
		out.close(); 
		
		System.out.println("Excel written successfully..");
		} catch (FileNotFoundException e) 
		{           
			e.printStackTrace();
	    }

	}	
		
	fileInputStream.close();
	System.exit(0);

	} catch (NoSuchElementException e)
	{		
		System.out.println("Exception Handled here : " + e );
		System.exit(0);
	}		
		
  }
	

}

