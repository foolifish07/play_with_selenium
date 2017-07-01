package tests;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.chrome.ChromeDriver;

import java.net.URL;

/**
 *
 * It is recommanded to use remote chromedriver instead of local driver
 */
public class PlayWebDriver {

    /**
     * Use local driver
     */
    @Test
    public void localDriver(){
        Logger logger = LoggerFactory.getLogger("PlayWebDriver.localDriver");

        logger.info("Start");

        System.setProperty("webdriver.chrome.driver",
                "/home/foolifish07/env/dev_env/bin/chromedriver-2.27");

        WebDriver driver = new ChromeDriver();


        // After this
        // It runs chromedriver at some port
        //
        // $ ps axu | grep chromedriver
        // foolifi+  8723  0.3  0.1 149804 15556 ?        Sl   13:20   0:00 /home/foolifish07/env/dev_env/bin/chromedriver-2.27 --port=32442
        //

        driver.get("http://www.baidu.com");

        WebElement element = driver.findElement(By.id("kw"));

        element.sendKeys("nba");
        element.submit();

        logger.info("Page title is: " + driver.getTitle());

        (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.findElement(By.id("1")).isDisplayed();
            }
        });

        logger.info("ok");
        WebElement firstelement = driver.findElement(By.id("1"));
        firstelement.findElement(By.cssSelector("a:first-child")).click();

        logger.info("ok {}", firstelement.getText());
        logger.info("before sleep");

        try{
            Thread.sleep(5000L);
        } catch(InterruptedException e) {
            logger.info("test1: thread error");
        }

        logger.info("After sleep");

        //Close the browser
        driver.quit();

        //
        // After this
        // chromedriver quit
        // $ ps axu | grep chromedriver

        logger.info("End");
    }

    /**
     * First you need to run:
     *
     * $ ./chromedriver
     * Starting ChromeDriver 2.27.440175 (9bc1d90b8bfa4dd181fbbf769a5eb5e575574320) on port 9515
     * Only local connections are allowed.
     * .....
     *
     * chrome will never close without driver.quit() been called.
     */
    @Test
    public void remoteDriver(){
        Logger logger = LoggerFactory.getLogger("PlayWebDriver.remoteDriver");

        logger.info("Start");
        URL url;
        try {
            url = new URL("http://127.0.0.1:9515");
        } catch (Exception e){
            throw new RuntimeException(e);
        }
        DesiredCapabilities c = DesiredCapabilities.chrome();
        c.setBrowserName("chrome");

        WebDriver driver = new RemoteWebDriver(url, c);

        // And now use this to visit Google
        driver.get("http://www.ruanyifeng.com/blog/javascript");

        logger.info("before quit");

        /* close the browser */
        driver.quit();

        //
        // Chrome browser quit here, after driver.quit called.
        //

        logger.info("End");
    }

    /**
     * Two browser with a chromedriver server at 9515
     * each have a session.
     */
    @Test
    public void multiSession(){
        Logger logger = LoggerFactory.getLogger("PlayWebDriver.multipleSession");

        logger.info("Start");

        URL url;
        try {
            url = new URL("http://127.0.0.1:9515");
        } catch (Exception e){
            throw new RuntimeException(e);
        }
        DesiredCapabilities c = DesiredCapabilities.chrome();
        c.setBrowserName("chrome");

        WebDriver driver1 = new RemoteWebDriver(url, c);
        WebDriver driver2 = new RemoteWebDriver(url, c);

        logger.info("launched two brower");

        // And now use this to visit Google
        driver1.get("http://www.ruanyifeng.com/blog/javascript");
        driver2.get("http://www.baidu.com");

        // close the browser
        driver1.quit();
        driver2.quit();

        logger.info("End");
    }

}