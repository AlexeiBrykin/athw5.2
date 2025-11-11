
package pobeda;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

public class Pobeda {
    private WebDriver driver;

    @Before
    public void setUp() {
        // System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
        driver = new ChromeDriver();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
        driver.manage().window().maximize();

    }

    @Test
    public void findPobedaPage() {
        driver.get("https://www.google.com/");
        driver.findElement(By.cssSelector("[aria-label=\"Найти\"]")).click();
        driver.findElement(By.cssSelector("[aria-label=\"Найти\"]")).sendKeys("Сайт компании Победа");
        driver.findElement(By.cssSelector("[aria-label=\"Найти\"]")).sendKeys(Keys.ENTER);
        driver.findElement(By.cssSelector("h3")).click();
        Assert.assertEquals("https://www.flypobeda.ru/", driver.getCurrentUrl());
    }

    @Test
    public void pobedaChek(){
        driver.get("https://www.flypobeda.ru/");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
