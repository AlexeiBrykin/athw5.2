
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
import java.util.concurrent.TimeUnit;

import static org.awaitility.Awaitility.with;

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

        //3 шаг переключить язык
        driver.findElement(By.cssSelector("button.dp-etauof-root-root")).click();
        log.info("клик на выбор языка");
        driver.findElement(By.cssSelector(".dp-1loxy1b-root .dp-1ct2iey-root div.dp-8gxax4-root-root[role=\"menuitem\"]:nth-child(2)")).click();
        log.info("клик на английский");
        Assert.assertEquals("Ticket search",driver.findElement(By.cssSelector("div.dp-3iyfpf-container-container > button[data-active=\"true\"]")).getText());
        Assert.assertEquals("Online check-in",driver.findElement(By.cssSelector("div.dp-3iyfpf-container-container > button:nth-child(2)")).getText());
        Assert.assertEquals("Manage my booking",driver.findElement(By.cssSelector("div.dp-3iyfpf-container-container > button:nth-child(3)")).getText());
        log.info("текст на английском отобразился");
    }


    @After
    public void tearDown() {
        driver.quit();
    }
    public static void waitVisibleElement(WebElement we) {
        with().pollDelay(100, TimeUnit.MILLISECONDS).await().atMost(10, TimeUnit.SECONDS).until(we::isDisplayed);
    }

    public static void waitForVisibilityOfElement(WebElement element) {
        float waitingTime = 0;
        float MAX_WAITING_TIME = 1000;
        float startLoadingTime = System.currentTimeMillis();


        while (!element.isDisplayed()) {
            if (waitingTime <= MAX_WAITING_TIME) {
                waitingTime = System.currentTimeMillis() - startLoadingTime;
            } else {
                System.out.println("Время вышло");
                break;
            }
        }
        if (element.isDisplayed()) {
            System.out.println("Элемент найден за" + waitingTime + " секунд");
        }
    }

}
