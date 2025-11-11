package pobeda;

import org.openqa.selenium.WebElement;

public class CustomWait {
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
