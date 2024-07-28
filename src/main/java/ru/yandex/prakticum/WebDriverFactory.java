package ru.yandex.prakticum;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WebDriverFactory {
//    public static WebDriver getWebDriver(String browserType) {
//        if (browserType.equalsIgnoreCase("firefox")) {
//            WebDriverManager.firefoxdriver().setup();
//            return new FirefoxDriver();
//        } else if (browserType.equalsIgnoreCase("yandex")) {
//            return null;//WebDriverManager.yandexdriver().setup();
//            //return new YandexDriver();
//        } else {
//            WebDriverManager.chromedriver().setup();
//            return new ChromeDriver();
//        }
//    }

    public static WebDriver getWebDriver(String browserType) {
        switch (browserType.toLowerCase()) {
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                return new FirefoxDriver();
            case "opera":
                return null;
            default:
                WebDriverManager.chromedriver().setup();
                return new ChromeDriver();
        }
    }
}
