package appium_Khan_Academy;

import java.io.File;
import java.io.FileInputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;

       public class DataDrivenTestKhanAcademy_Test {
	   public AndroidDriver driver ;
  @Test
       public void multipleuselogin() {
	   String Actualtext = driver.findElement(By.xpath("//*[contains(@text, 'Khan Academy')]")).getText();
	   Assert.assertEquals(Actualtext, "Khan Academy");
	   System.out.println("Hard Assertion -> 1st apptext assertion executed.");
	   driver.findElement(MobileBy.AndroidUIAutomator("UiSelector().text(\"Sign in\")")).click();
       driver.findElement(MobileBy.AndroidUIAutomator("UiSelector().text(\"Continue with Google\")")).click();
  }
  @BeforeClass
      public void beforeClass() throws MalformedURLException, InterruptedException {
	  DesiredCapabilities capability= new DesiredCapabilities();
      capability.setCapability("deviceName", "Manzoor");
      capability.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
      capability.setCapability(MobileCapabilityType.NO_RESET, true);
      capability.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "org.khanacademy.android");
      capability.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, "org.khanacademy.android.ui.library.MainActivity");
      capability.setCapability(AndroidMobileCapabilityType.AUTO_GRANT_PERMISSIONS, true);
      driver = new AndroidDriver(new URL("http://0.0.0.0:4723/wd/hub"),capability);
      driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
      Thread.sleep(10000);
  }
  @AfterClass
  	  public void afterClass() throws InterruptedException, Exception {
	  try {
	  String Message= driver.findElement(By.xpath("//*[contains(@text, 'Choose an account')]")).getText();
	  System.out.println("User Google Account Is Seen :"+ Message);
	  driver.findElement(MobileBy.AndroidUIAutomator("UiSelector().text(\"Add another account\")")).click();
	  driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
	  System.out.println("current context of the application is "+driver.getContext());
	  Thread.sleep(5000);
	  System.out.println("list of contexts available on web page is "+driver.getContextHandles());
	  Thread.sleep(5000);
	  System.out.println("current context of the application is as "+driver.getContext());
      Thread.sleep(10000);     
      File file= new File("C:\\Users\\DEBRAJBHAUMIK\\Desktop\\Testdata\\Test5.xlsx");
      FileInputStream fis=new FileInputStream(file);
      XSSFWorkbook wb= new XSSFWorkbook(fis);
      XSSFSheet sheet = wb.getSheet("Sheet1");
      int rc= sheet.getLastRowNum();
      System.out.println("total number of rows having data= "+rc);
	  for(int i=1;i<=rc;i++) {
	  String Email =sheet.getRow(i).getCell(0).getStringCellValue();
	  String Password=sheet.getRow(i).getCell(1).getStringCellValue();
	  driver.findElementByClassName("android.widget.EditText").sendKeys(Email);
	  Thread.sleep(10000);
	  driver.findElement(MobileBy.AndroidUIAutomator("UiSelector().text(\"Next\")")).click();
	  Thread.sleep(10000);
	  driver.findElementByClassName("android.widget.EditText").sendKeys(Password);
      driver.hideKeyboard();
	  Thread.sleep(10000);
	  driver.findElement(MobileBy.AndroidUIAutomator("UiSelector().text(\"Next\")")).click();
	  Thread.sleep(10000);
	  File file1=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	  FileUtils.copyFile(file1,new File("C:\\All Document\\Mobile Testing\\Screenshots\\Scr1.jpg"));
	  driver.hideKeyboard();
	  Thread.sleep(5000);
	  driver.pressKey(new KeyEvent(AndroidKey.BACK));
	  Thread.sleep(10000); }
	  
	  }
	  catch(NoSuchElementException e)
	       {       
	          System.out.println("Desired Link Can Not Be Clicked");
           }
 
 }

}



  

