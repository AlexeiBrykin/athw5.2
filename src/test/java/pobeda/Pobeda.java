
package pobeda;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;

@Slf4j
public class Pobeda {
    private WebDriver driver;

    @Before
    public void setUp() {
        // System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
        driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
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
    public void pobedaChek() {
        //1 шаг открыть сайт победы
        driver.get("https://www.flypobeda.ru/");
        log.info("Открыт сайт Победа");
        Assert.assertEquals("https://www.flypobeda.ru/", driver.getCurrentUrl());
        log.info("Проверен адрес");

        //2 шаг дождаться Калининграда
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(), 'Полетели в Калининград')]")));
        Assert.assertNotNull(element);
        log.info("Элемент найден: {}", element.getText());
//        WebElement element = driver.findElement(By.cssSelector("div.dp-1ihjhh6-root:contains('Полетели в Калининград!')"));
//        CustomWait.waitForVisibilityOfElement(element);
        Assert.assertEquals("Полетели в Калининград!", element.getText()); // Добавлено для проверки видимости элемента
        log.info("Успешно найден Калининград");
        Assert.assertTrue(driver.findElement(By.cssSelector(".dp-5myq2-root[src*=\"Kaliningrad_banner_fall_d1902e0766.jpg\"]")).isDisplayed());
        log.info("Отображается картинка Калининград");
    }


    @After
    public void tearDown() {
        driver.quit();
    }

}
