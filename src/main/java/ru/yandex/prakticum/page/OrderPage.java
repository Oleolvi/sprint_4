package ru.yandex.prakticum.page;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class OrderPage {
    private final WebDriver webDriver;
//поле Имя
    private final By nameInputLocator = By.xpath("//input[@placeholder = '* Имя']");
    //поле Фамилия
    private final By surnameInputLocator = By.xpath("//input[@placeholder = '* Фамилия']");
    //поле адресс
    private final By addressInputLocator = By.xpath("//input[@placeholder = '* Адрес: куда привезти заказ']");
    //поле Станция метро
    private final By subwayInputLocator = By.xpath("//input[@placeholder = '* Станция метро']");
    //выбор названия станции
    private final String stationMenuLocator = "//div[text()='%s']";
    //поле номер телефона
    private final By phoneInputLocator = By.xpath("//input[@placeholder = '* Телефон: на него позвонит курьер']");

//кнопка Далее
    private final By clickNextButton = By.xpath("//button[text()='Далее']");

    public OrderPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }
//метод для заполнения данных пользователя
    public void fillCustomerInfo(String name, String surname, String address, String subwayTitle, String phone) {

        WebElement nameInput = webDriver.findElement(nameInputLocator);
        nameInput.sendKeys(name); //тестовые данные можно генерировать с помощью библиотеки java-faker


        WebElement surnameInput = webDriver.findElement(surnameInputLocator);
        surnameInput.sendKeys(surname);


        WebElement addressInput = webDriver.findElement(addressInputLocator);
        addressInput.sendKeys(address);


        WebElement subwayInput = webDriver.findElement(subwayInputLocator);
        subwayInput.click();


        WebElement subwayStation = webDriver.findElement(By.xpath(String.format(stationMenuLocator, subwayTitle)));
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView();", subwayStation);
        subwayStation.click();


        WebElement phoneInput = webDriver.findElement(phoneInputLocator);
        phoneInput.sendKeys(phone);
    }
//клик Далее
    public void clickNextButton() {

        WebElement nextButton = webDriver.findElement(clickNextButton);
        nextButton.click();
    }
}
