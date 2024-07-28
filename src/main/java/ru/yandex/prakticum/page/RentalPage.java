package ru.yandex.prakticum.page;

import org.openqa.selenium.*;

import static org.junit.Assert.assertEquals;

public class RentalPage {
    public final static String RENTAL_SUCCESS_TITLE = "Заказ оформлен";
    private final WebDriver webDriver;

    //поле даты аренды
    private final By dateInputLocator = By.xpath("//input[@placeholder = '* Когда привезти самокат']");

    private final By rentalPeriodInputLocator = By.xpath("//div[text() = '* Срок аренды']");
    private final String rentalPeriodMenuLocator = "//div[text()='%s']";
    private final By order2ButtonLocator = By.xpath("//div[@class = 'Order_Buttons__1xGrp']/button[text()='Заказать']");
    private final By yesButtonLocator = By.xpath("//button[text() = 'Да']");
    public final By successfulOrderCreationLocator = By.xpath(".//div[text() = 'Заказ оформлен']");

    public RentalPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }


    public void fillRentalPage(String date, String period) {

        WebElement dateInput = webDriver.findElement(dateInputLocator);
        dateInput.click();
        dateInput.sendKeys(date, Keys.ENTER); //ввели дату и нажали энтер

        WebElement rentalPeriodInput = webDriver.findElement(rentalPeriodInputLocator);
        rentalPeriodInput.click();
        WebElement rentalPeriodMenu = webDriver.findElement(By.xpath(String.format(rentalPeriodMenuLocator, period)));
        rentalPeriodMenu.click();
    }

    public void clickOrder2Button() {
        WebElement order2Button = webDriver.findElement(order2ButtonLocator);
        order2Button.click();
    }

    public void clickOrderConfirmationButton() {

        WebElement yesButton = webDriver.findElement(yesButtonLocator);
        yesButton.click();
    }

    public boolean successfulOrderCreation() {
        return webDriver.findElement(successfulOrderCreationLocator).isDisplayed();
        // return Assert.assertEquals("Заказ оформлен", successfulOrderCreationLocator);
    }
}
