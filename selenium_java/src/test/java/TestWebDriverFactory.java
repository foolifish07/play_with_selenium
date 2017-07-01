import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.Config;
import utils.WebDriverFactory;

public class TestWebDriverFactory {

    private static final Logger logger = LoggerFactory
            .getLogger(TestWebDriverFactory.class);

    @Test
    public void testChromedriver () {
        Config.configPath = "src/test/resources/config.yaml";
        Config config = Config.getInstance();

        WebDriver driver = WebDriverFactory.getInstance();

        driver.get("http://baidu.com"); sleep(2000);

        driver.get("http://www.imooc.com"); sleep(2000);

        WebDriver driver2 = WebDriverFactory.getInstance();

        logger.info("{} {}", driver, driver2);

        Assert.assertTrue(driver == driver2);

        driver.quit();

        logger.info("test end");
    }

    @Test
    public void testMultiChromedriver () {
        Config.configPath = "src/test/resources/config.yaml";
        Config config = Config.getInstance();

        class MyThread extends Thread {
            public void run() {
                Logger logger = LoggerFactory.getLogger(MyThread.class);

                logger.info("start");

                WebDriver driver = WebDriverFactory.getInstance();

                driver.get("http://baidu.com");

                TestWebDriverFactory.sleep(2000);

                driver.quit();

                logger.info("end");
            }
        }

        logger.info("test start");

        new Thread(new MyThread()).start();
        new Thread(new MyThread()).start();

        sleep(10000);

        logger.info("test end");
    }

    public static void sleep(int mills) {
        try{
            Thread.sleep(mills);
        } catch(InterruptedException e) {
            logger.info("test1: thread error");
        }
    }
}
