package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;
import utils.WebDriverFactory;

/**
 * 1. launch chromedriver at 9515
 * 2. Run this test cases with testng-parallel.xml
 * 3. two browser launched with with chromedriver server
 * 4. Try 3 threads with 2 node grids hub
 */

public class PlayParallel {
    Logger logger = LoggerFactory.getLogger(PlayParallel.class);

    @Test
    public void test1(){
        logger.info("step 1");
        WebDriver driver = WebDriverFactory.getInstance();

        driver.get("http://baidu.com");
        sleep(2000);

        logger.info("step 2");

        WebElement element = driver.findElement(By.id("kw"));
        element.sendKeys("曼联");
        element.submit();
        sleep(2000);

        logger.info("step 3");

        driver.quit();
        logger.info("quit");
    }

    @Test
    public void test2(){
        logger.info("step 1");
        WebDriver driver = WebDriverFactory.getInstance();

        driver.get("http://baidu.com");
        sleep(2000);

        logger.info("step 2");

        WebElement element = driver.findElement(By.id("kw"));
        element.sendKeys("曼联");
        element.submit();
        sleep(2000);

        logger.info("step 3");

        driver.quit();
        logger.info("quit");
    }

    @Test
    public void test3(){
        logger.info("step 1");
        WebDriver driver = WebDriverFactory.getInstance();

        driver.get("http://baidu.com");
        sleep(2000);

        logger.info("step 2");

        WebElement element = driver.findElement(By.id("kw"));
        element.sendKeys("曼联");
        element.submit();
        sleep(2000);

        logger.info("step 3");

        driver.quit();
        logger.info("quit");
    }

    private static void sleep(int mills) {
        try {
            Thread.sleep(mills);
        } catch (Exception e){
        }
    }
}
