package ru.yandex.prakticum.plain;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.yandex.prakticum.WebDriverFactory;
import ru.yandex.prakticum.page.MainPage;
import ru.yandex.prakticum.page.OrderPage;
import ru.yandex.prakticum.page.RentalPage;

import org.openqa.selenium.WebDriver;

import java.time.Duration;

import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class) // указали, что тесты будет запускать раннер Parameterized
public class CreateOrderTest {

    private final String name;
    private final String surname;
    private final String address;
    private final String subwayTitle;
    private final String phone;
    private final String button;
    private final String data;
    private final String period;
    WebDriver webDriver = WebDriverFactory.getWebDriver(System.getProperty("browser", "firefox"));


    public CreateOrderTest(String name, String surname, String address, String subwayTitle, String phone, String button, String data, String period) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.subwayTitle = subwayTitle;
        this.phone = phone;
        this.button = button;
        this.data = data;
        this.period = period;
    }

    @Parameterized.Parameters // добавили аннотацию
    public static Object[][] data() {
        return new Object[][] {
                {"Бильбо", "Бэггинс", "Ленина 21", "Арбатская", "79213453434", "Header", "14.09.2024", "трое суток"},
                { "Иван", "Петров", "Южная 1", "Сокол", "79114565656", "Middle", "20.09.2024", "пятеро суток"},
        };
    }

    @Before
    public void setup() {
        webDriver.get("https://qa-scooter.praktikum-services.ru/");
    }

    @Test
    public void createOrderTest() {
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        MainPage mainPage = new MainPage(webDriver);
        mainPage.clickCreateOrderButton(button);

        OrderPage orderPage = new OrderPage(webDriver);
        orderPage.fillCustomerInfo(name, surname, address, subwayTitle, phone);
        orderPage.clickNextButton();

        RentalPage rentalPage = new RentalPage(webDriver);
        rentalPage.fillRentalPage(data, period);
        rentalPage.clickOrder2Button();

        rentalPage.clickOrderConfirmationButton();

        rentalPage.successfulOrderCreation();
        assertTrue(rentalPage.successfulOrderCreation());

    }
    @After
    public void tearDown() {
        webDriver.quit();
    }
}

