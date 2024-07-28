package ru.yandex.prakticum.page;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static java.time.Duration.ofSeconds;

public class MainPage {
    private final WebDriver webDriver;
//согласие кук
    private final By cookiesButtonLocator = By.id("rcc-confirm-button");
//кнопка Заказать в заголовке
    private final By createOrderButtonLocator = By.xpath("//div[contains(@class, 'Header')]/button[text()='Заказать']");
    //кнопка Заказать внизу
    private final By createOrder2ButtonLocator = By.xpath("//div[contains(@class, 'Home_FinishButton')]/button[text()='Заказать']");

    //акордеон - вопрос
    private final String questionLocator = "accordion__heading-%s";
    //акордеон-ответ
    private final String answerLocator = "//div[contains(@id, 'accordion__panel')][.='%s']";

    public MainPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }


//клик Заказать
    public void clickCreateOrderButton(String button) {
        WebElement createOrderButton;
        if(button.equals("Header")) {
            createOrderButton = webDriver.findElement(createOrderButtonLocator);
            // createOrderButton.click();

        } else {
            createOrderButton = webDriver.findElement(createOrder2ButtonLocator);
            ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView();", createOrderButton);
           // createOrderButton.click();
        }
        createOrderButton.click();
    }
//закрыть окно кук
    public void closeCookiesWindow() {
        webDriver.findElement(cookiesButtonLocator).click();
    }
//клик по акордеон-вопрос
    public void expandQuestion(int index) {

        WebElement element = webDriver.findElement(By.id(String.format(questionLocator, index)));

        ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView();", element);

        new WebDriverWait(webDriver, ofSeconds(15)).until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }
//проверка акордеон-ответ
    public boolean answerIsDisplayed(String expectedAnswer) {
        WebElement element = webDriver.findElement(By.xpath(String.format(answerLocator, expectedAnswer)));
        return element.isDisplayed();
    }
}
