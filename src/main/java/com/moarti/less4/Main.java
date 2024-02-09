package com.moarti.less4;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


public class Main {
    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "chromedriverOne.exe");

        WebDriver driver = new ChromeDriver();

        driver.get("https://www.google.com/");

        driver.get("https://librebook.me/the_adventures_of_sherlock_holmes"); //визитная страница
        Thread.sleep(1000);
        driver.get("https://librebook.me/the_adventures_of_sherlock_holmes/vol1/1"); //стр книги 1
        driver.get("https://librebook.me/the_adventures_of_sherlock_holmes/vol2/1"); //стр книги 2
        driver.get("https://librebook.me/the_adventures_of_sherlock_holmes/vol3/1"); //стр книги 3

        WebElement element = driver.findElement(By.className("fa-arrow-circle-left")); // ищет визитную страницу и откидывает туда
        element.click();

        //открывается картинка через x-path
        WebElement elementImg = driver.findElement(By.xpath("/html/body/div[1]/div[3]/div[2]/div[1]/div[1]/div[1]/div[2]/div/div[1]/div[1]"));
        elementImg.click();
        //также закрывается
        Thread.sleep(1000);
        elementImg.click();

        //вывод в консоль текста с элемента
        WebElement sideBarAlert = driver.findElement(By.className("manga-description"));
        String alertText = sideBarAlert.getText();
        System.out.println("sideBarAlert' text: " + alertText);

        System.out.println();

        WebElement lastTwits = driver.findElement(By.className("lastTwitts"));
        String getTwits = lastTwits.getText();
        System.out.println("Last twitts: "+ getTwits);

        driver.quit();
    }
}