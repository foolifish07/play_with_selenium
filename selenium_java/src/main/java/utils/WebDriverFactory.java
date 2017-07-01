package utils;

import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.locks.Lock;

public class WebDriverFactory {
    /**
     * Thread instance support
     */
    private static final ThreadLocal<WebDriver> instance = new ThreadLocal<WebDriver>();

    public static WebDriver getInstance() {
        if (instance.get() == null) {
            instance.set(getDriver(Config.getInstance()));
        }
        return instance.get();
    }

    /**
     * logger
     */
    private static final Logger logger = LoggerFactory
            .getLogger(WebDriverFactory.class);

    private static WebDriver getDriver(Config config) {
        Config.Driver driver = config.driver;

        if (driver.browser.equals("chrome")) {
            return getChromeDriver(driver.remoteAddress, driver.address);
        }
        return null;
    }

    private static WebDriver getChromeDriver(String remoteAddress, String path){

        logger.info("Fetching chrome driver");

        try {
            logger.info("Fetching remote driver");
            return new RemoteWebDriver(getUrl(remoteAddress), DesiredCapabilities.chrome());
        } catch (Exception e) {
            logger.info("Fail to fetch remote driver " + getUrl(remoteAddress));
        }

        try {
            logger.info("Fetching local driver");
            System.setProperty("webdriver.chrome.driver", path);
            return new ChromeDriver();
        } catch (Exception e) {
            logger.info("Fail to fetch local driver");
        }
        return null;
    }

    private static URL getUrl(String url){
        if (!url.startsWith("http")) {
            url = "http://" + url;
        }
        try {
            return new URL(url);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}
