package add.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.Select;

/////商品再出品専用class
class AddGoods{
	private boolean check1;
	private boolean check2;
	private boolean check3;
	private String loginName=null;
	private String loginPWD=null;
	private String fileUrl="C:\\Users\\xujio\\Desktop\\Admin\\ビジネスID.txt";
	private String loginUrl="https://login.bizmanager.yahoo.co.jp/login?url=http://batch.auctions.yahoo.co.jp%2ff%2fstore_manage%2findex";
	private String controlUrl="https://onavi.auctions.yahoo.co.jp/onavi/show/storelist?select=closed&haswinner=0&page=1&op=7&od=2&rpp=100";
	public AddGoods(){
		String key="webdriver.ie.driver";
    	String value="C://IEDriverServer_Win32_3.0.0//IEDriverServer.exe";
    	System.setProperty(key,value);
	}
	public void readInI() throws IOException{
		FileReader f=new FileReader(fileUrl);
        BufferedReader reader = new BufferedReader(f);
        String line = null;  
        String[] item=new String[2];
        for(int i=0;(line=reader.readLine())!=null;i++){ 
			item[i]=line;
        }
        loginName=item[0];
        loginPWD=item[1];
	}
	public void addGoods() throws IOException, InterruptedException{
		readInI();
		WebDriver driver = new InternetExplorerDriver();
		driver.navigate().to(loginUrl);
		//login
		WebElement login_name=driver.findElement(By.id("user_name"));
		login_name.sendKeys(loginName);
		WebElement login_pwd=driver.findElement(By.id("password"));
		login_pwd.sendKeys(loginPWD);
		WebElement login_bt=driver.findElement(By.name("login_form"));
		login_bt.submit();
		
		//落札データ一覧画面
		driver.navigate().to(controlUrl);
		try{
			WebElement we=driver.findElement(By.xpath("//td[contains(.,'終了したオークションはありません。')]"));  
			check1=true;
		}catch(NoSuchElementException e1){
			check2=false;
		}
		if(check1==false){
			do{
				//再出品選択
				WebElement chooseAll=driver.findElement(By.linkText("すべてを選択"));
				((JavascriptExecutor)driver).executeScript("arguments[0].click();", chooseAll);//すべてを選択 
				Thread.sleep(1000);
				WebElement transferGoods=driver.findElement(By.xpath("//center//center[5]//table//tbody//tr//td//center[2]//table//tbody//tr//td//table//tbody//tr[1]//td//table//tbody//tr//td//small//input[1]"));
				((JavascriptExecutor)driver).executeScript("arguments[0].click();", transferGoods);//再出品button
				Thread.sleep(2000);
				try{
					WebElement we2=driver.findElement(By.xpath("//td[contains(.,'選択された')]")); 
					check3=true;
				}catch(NoSuchElementException e1){
					check3=false;
				}
				if(check3==true){
					break;
				}
				//一括再出品の基本項目
				WebElement duration = driver.findElement(By.xpath("//div[@id='modFormReqrd']//fieldset//div//table//tbody//tr[2]//td//div//select")); 
				Select mySelect= new Select(duration);
				mySelect.selectByValue("2");//オークション期間
				Select overTime=new Select(driver.findElement(By.name("closing_time")));
				overTime.selectByValue("23");//終了時間
				WebElement buttonGoods=driver.findElement(By.xpath("//center//table[3]//tbody//tr//td//input[1]"));
				((JavascriptExecutor)driver).executeScript("arguments[0].click();", buttonGoods);//確認
				Thread.sleep(2000);
				WebElement buttonok=driver.findElement(By.xpath("//center//center[5]//table//tbody//tr//td//table[3]//tbody//tr[1]//td//small//input[1]"));
				((JavascriptExecutor)driver).executeScript("arguments[0].click();", buttonok);//確認
				Thread.sleep(1000);
				driver.navigate().to(controlUrl);
				//再び確認
				try{
					WebElement we=driver.findElement(By.xpath("//center//center[5]//table//tbody//tr//td//table[6]//tbody//tr//td//table//tbody//tr//td[2]//table//tbody//tr//td[1]//small//a//b"));  
					check2=true;
				}catch(NoSuchElementException e1){
					check2=false;
				}
				Thread.sleep(2000);
			}while(check2==false);
			///最終確認
			//再出品選択
			WebElement chooseAll=driver.findElement(By.linkText("すべてを選択"));
			((JavascriptExecutor)driver).executeScript("arguments[0].click();", chooseAll);//すべてを選択 
			Thread.sleep(1000);
			WebElement transferGoods=driver.findElement(By.xpath("//center//center[5]//table//tbody//tr//td//center[2]//table//tbody//tr//td//table//tbody//tr[1]//td//table//tbody//tr//td//small//input[1]"));
			((JavascriptExecutor)driver).executeScript("arguments[0].click();", transferGoods);//再出品button
			Thread.sleep(2000);
			try{
				WebElement we2=driver.findElement(By.xpath("//td[contains(.,'選択された')]")); 
				check3=true;
			}catch(NoSuchElementException e1){
				check3=false;
			}
			if(check3==true){
				Thread.sleep(3000);
				driver.close();
			}else{
				//一括再出品の基本項目
				WebElement duration = driver.findElement(By.xpath("//div[@id='modFormReqrd']//fieldset//div//table//tbody//tr[2]//td//div//select")); 
				Select mySelect= new Select(duration);
				mySelect.selectByValue("2");//オークション期間
				Select overTime=new Select(driver.findElement(By.name("closing_time")));
				overTime.selectByValue("23");//終了時間
				WebElement buttonGoods=driver.findElement(By.xpath("//center//table[3]//tbody//tr//td//input[1]"));
				((JavascriptExecutor)driver).executeScript("arguments[0].click();", buttonGoods);//確認
				Thread.sleep(2000);
				WebElement buttonok=driver.findElement(By.xpath("//center//center[5]//table//tbody//tr//td//table[3]//tbody//tr[1]//td//small//input[1]"));
				((JavascriptExecutor)driver).executeScript("arguments[0].click();", buttonok);//はい
				Thread.sleep(1000);
			}			
			driver.navigate().to(controlUrl);
			//再び確認
//			try{
//				WebElement we=driver.findElement(By.xpath("//center//center[5]//table//tbody//tr//td//table[6]//tbody//tr//td//table//tbody//tr//td[2]//table//tbody//tr//td[1]//small//a//b"));  
//				check2=true;
//			}catch(NoSuchElementException e1){
//				check2=false;
//			}
			Thread.sleep(2000);
			
//			driver.close();
		}else{
			Thread.sleep(2000);
			driver.close();
		}
	}
}