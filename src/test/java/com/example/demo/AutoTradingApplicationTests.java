package com.example.demo;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.model.alertDataTest;

@SpringBootTest
class AutoTradingApplicationTests {
	
	//中文版tradingview官網
	public static final String URL="https://tw.tradingview.com/";
	
	@Test
	void webhook() {
		alertDataTest data=new alertDataTest();
		System.out.println(data.toString());
	}

//	@Test
//	void tradingviewLogin() throws InterruptedException {
//		String projectPath=System.getProperty("user.dir");
//		
//		System.setProperty("webdriver.chrome.driver", projectPath+"/driver/chromedriver/chromedriver.exe");
//		WebDriver webDriver= new ChromeDriver();
//		webDriver.get(URL);
//		
//		//打開用戶選單
//		WebElement userMenu=webDriver.findElement(By.cssSelector("button[aria-label=打開用戶選單]"));
//		if(userMenu!=null) {
//			userMenu.click();
//		}
//		
//		Thread.sleep(6000);
//		
//		//點擊登入
//		WebElement login=webDriver.findElement(By.xpath("//*[@data-name='header-user-menu-sign-in']"));
//		if(login!=null) {
//			login.click();
//		}
//		
//		Thread.sleep(6000);
//		
//		//點擊email
//		WebElement loginByEmail=webDriver.findElement(By.className("i-clearfix"));
//		if(loginByEmail!=null) {
//			loginByEmail.click();
//		}
//		
//		Thread.sleep(6000);
//		
//		//輸入email帳密
//        WebElement email=webDriver.findElement(By.name("username"));
//        if(email!=null) {
//        	 email.sendKeys("lyinjue456852@gmail.com");
//        }
//        
//        WebElement password=webDriver.findElement(By.name("password"));
//        if(password!=null) {
//        	password.sendKeys("jasper840831");
//        }
//		
//		Thread.sleep(6000);
//		
//		//點擊登入
//		WebElement Parent = webDriver.findElement(By.className("tv-signin-dialog__footer-item--login"));
//		WebElement submit=Parent.findElement(By.xpath("./child::*"));
//		if(submit!=null) {
//			submit.click();
//		}
//		
//		Thread.sleep(6000);
//		
//		webDriver.close();
//		webDriver.quit();
//	}
	
}
