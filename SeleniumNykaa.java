package week4_day2;

import java.io.File;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.google.common.graph.ElementOrder.Type;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SeleniumNykaa {
	@Test
//	public static void main(String[] args) throws Exception {

	public void loginPage() throws Exception {	
		
		System.out.println("//       1) Go to https://www.nykaa.com/");
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		ChromeDriver driver = new ChromeDriver(options);
		Actions action = new Actions(driver);
		driver.manage().window().maximize();
		driver.navigate().to("https://www.nykaa.com/");

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		System.out.println("//	    2) Mouseover on Brands and Search L'Oreal Paris	");
		WebElement brandsTab = driver.findElement(By.xpath("//a[contains(text(),'brands')]"));
		action.moveToElement(brandsTab).perform();
		Thread.sleep(1000);
		System.out.println("first step");
		driver.findElement(By.id("brandSearchBox")).sendKeys("L'Oreal Paris");

		System.out.println("//		3) Click L'Oreal Paris	");
		WebElement listsec = driver.findElement(By.xpath("(//div[@Class='css-ov2o3v']//a)[1]"));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));

		wait.until(ExpectedConditions.elementToBeClickable(listsec));
		action.moveToElement(listsec).perform();
		action.click().perform();

		System.out.println("//		4) Check the title contains L'Oreal Paris(Hint-GetTitle)	");
		String BrandNameTitle = driver.getTitle();
		boolean tit = BrandNameTitle.contains("L'Oreal Paris");
		Assert.assertEquals(tit, true);

		Thread.sleep(5000);
		System.out.println("//	    5) Click sort By and select customer top rated    ");
		WebElement drop = driver.findElement(By.xpath("//span[contains(text(),'Sort By :')]"));
		wait.until(ExpectedConditions.elementToBeClickable(drop)).click();
		driver.findElement(By.xpath("//span[contains(text(),'customer top rated')]")).click();

		Thread.sleep(5000);

		System.out.println("//	    6) Click Category and click Hair->Click haircare->Shampoo");
		WebElement hair = driver
				.findElement(By.xpath("//li[@class='MegaDropdownHeadingbox']/a[contains(text(),'hair')]"));
		
		action.moveToElement(hair).perform();

		WebElement shamp = driver.findElement(By.xpath("(//a[text()='Shampoo'])[1]"));
		wait.until(ExpectedConditions.visibilityOf(shamp));
		shamp.click();

		Set<String> windows = driver.getWindowHandles();
		List<String> winHan = new ArrayList<String>(windows);
		System.out.println("total windows" + winHan.size());

		driver.switchTo().window(winHan.get(1));

		System.out.println("Navigated to new tab");

		System.out.println("//	7) Click->Concern->Color Protection");
		Thread.sleep(20000);

//		List<WebElement> opt = driver.findElements(By.xpath("//div[@class='filter-sidebar__filter-title pull-left']"));
//		for (int i = 1; i <= opt.size(); i++) {
//			String bu = opt.get(i).getText();
//			System.out.println(bu);
//			if (bu.contains("CONCERN")) {
//				opt.get(i).click();
//				break;
//			}
//		}
		
		
		driver.findElement(By.xpath("//div[text()='Concern'] | //span[text()='Concern']")).click();
		driver.findElement(By.xpath("//span[contains(text(),'Color Protection')]")).click();

		System.out.println("//	8)check whether the Filter is applied with Shampoo");
		Assert.assertEquals(true, driver.getTitle().contains("Shampoo"));
		System.out.println("Shampoo screen displayed");

		System.out.println("//9) Click on L'Oreal Paris Colour Protect Shampoo");

		///Thread.sleep(30000);
		driver.findElement(By.xpath("//span[contains(text(),'Oreal Paris Colour Protect Shampoo')] | //div[contains(text(),'Oreal Paris Colour Protect Shampoo')]")).click();


		System.out.println("//	10) GO to the new window and select size as 175ml");

		System.out.println("total windows" + winHan.size());
		Set<String> windows2 = driver.getWindowHandles();
		List<String> winHan2 = new ArrayList<String>(windows2);
		driver.switchTo().window(winHan2.get(2));
		System.out.println("Navigated to new window ");

		System.out.println("//	11) Print the MRP of the product");

		String prize = driver.findElement(By.xpath("(//span[@class='post-card__content-price-offer'])[1]")).getText();

		System.out.println("Product Prize list" + prize);

		System.out.println("//	12) Click on ADD to BAG");

		driver.findElement(By.xpath("(//button[text()='ADD TO BAG'])[1]")).click();

		System.out.println("//	13) Go to Shopping Bag ");

		 Thread.sleep(3000);

		driver.findElement(By.xpath("//div[@class='AddBagIcon']")).click();

		System.out.println("	//	14) Print the Grand Total amount");

		Thread.sleep(3000);

		String grandtotall = driver.findElement(By.xpath("//div[@class='value medium-strong']")).getText();
		String grandtotal = grandtotall.replaceAll("\\D+", "");

		System.out.println(" GRAND TOTAL : " + grandtotal);

		try {
			driver.switchTo().frame(driver.findElement(By.id("__ta_notif_frame_2")));
			System.out.println("switched to frame");
			driver.findElement(By.xpath("//div[@class='close']")).click();
			driver.switchTo().defaultContent();
			System.out.println("switched outt of frame");
		} catch (Exception e) {
			System.out.println("Frame not displayed");
		}

		System.out.println("	//	15) Click Proceed");

		driver.findElement(By.xpath("//span[text()='Proceed']")).click();

		System.out.println("//	16) Click on Continue as Guest");

		driver.findElement(By.xpath("//button[text()='CONTINUE AS GUEST']")).click();

		System.out.println("//	17) Check if this grand total is the same in step 14");

		String grandtotal1 = driver
				.findElement(
						By.xpath("//div[@class='payment-details-tbl grand-total-cell prl20']/div[@class='value']/span"))
				.getText();
		System.out.println("second total" + grandtotal1);
		Assert.assertEquals(grandtotal1.equals(grandtotal), true);
		System.out.println("Both total is equal");

		File src = driver.getScreenshotAs(OutputType.FILE);
		File dst = new File("./Snaps/Nykaa_Passed.png");
		FileUtils.copyFile(src, dst);

		System.out.println("//	18) Close all windows");
		driver.quit();
	}
	}


