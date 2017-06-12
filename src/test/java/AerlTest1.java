import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AerlTest1 {

    protected WebDriver driver;

    @BeforeMethod
    public void initDriver(){
        System.setProperty("webdriver.gecko.driver", "G:\\Masha\\geckodriver.exe");
        driver = new FirefoxDriver();
    }

    @Test
    public void verifyAerl1(){


        driver.get("https://www.aerlingus.com/html/en-US/home.html");
        new WebDriverWait(driver, 50).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='origin']/div")));
        driver.findElement(By.xpath("//*[@id='origin']/div")).click();
        driver.findElement(By.xpath("//*[@id='origin']/div")).sendKeys("dublin");
        driver.findElement(By.xpath("//li[contains(@id,'typeahead')]")).click();

        new WebDriverWait(driver, 50).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='dest']/div")));
        driver.findElement(By.xpath("//*[@id='dest']/div")).click();
        driver.findElement(By.xpath("//*[@id='dest']/div")).sendKeys("paris");
        driver.findElement(By.xpath("//li[contains(@id,'typeahead')]")).click();

        driver.findElement (By.xpath("//button[@data-test-id='test_booker_search']")).click();


        new WebDriverWait(driver, 50).until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@data-test-id='test_continue_btn']")));
        String resultsOutboundText = driver.findElement(By.xpath("//div[contains(@data-test-id,'first')]//div[contains(@class,'flight-results')]//span")).getText();
        Assert.assertEquals("Dublin to Paris", resultsOutboundText);

        String resultsInboundText = driver.findElement(By.xpath("//div[contains(@data-test-id,'second')]//div[contains(@class,'flight-results')]//span")).getText();
        Assert.assertEquals("Paris to Dublin", resultsInboundText);

        driver.findElement(By.xpath("//button[@data-test-id='test_continue_btn']")).click();

        new WebDriverWait(driver, 50).until(ExpectedConditions.elementToBeClickable(By.id("test_adultTitle-0-1")));
        driver.findElement (By.id("test_adultTitle-0-1")).click();
        driver.findElement (By.xpath("//option[text()='Mr']")).click();
        driver.findElement (By.id("test_adultFname-0-1")).sendKeys("John");
        driver.findElement (By.id("test_adultLname-0-2")).sendKeys("Blare");
        driver.findElement (By.xpath("//*[@id='test_email-3']")).sendKeys("maalshi@mail.ru");
        driver.findElement (By.xpath("//*[@id='test_confirmemail-6']")).sendKeys("maalshi@mail.ru");
        driver.findElement (By.xpath("//*[@id='test_acode-4']")).sendKeys("34");
        driver.findElement (By.xpath("//*[@id='test_mobnumber-5']")).sendKeys("3434");

        driver.findElement(By.id("test_continueTravelEsstl")).click();
        new WebDriverWait(driver, 50).until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='test-left']//div[@class='relative ng-scope'][1]//div[contains(@class,'head-section')]//span[@class='ng-binding']")));
        String essentialsOutboundText = driver.findElement(By.xpath("//div[@id='test-left']//div[@class='relative ng-scope'][1]//div[contains(@class,'head-section')]//span[@class='ng-binding']")).getText();
        Assert.assertEquals("DUBLIN to PARIS/CDG", essentialsOutboundText);

        String essentialsInboundText = driver.findElement(By.xpath("//div[@id='test-left']//div[@class='relative ng-scope'][2]//div[contains(@class,'head-section')]//span[@class='ng-binding']")).getText();
        Assert.assertEquals("PARIS/CDG to DUBLIN", essentialsInboundText);


    }

    @AfterMethod
    public void closeDriver(){
        driver.quit();
    }




}