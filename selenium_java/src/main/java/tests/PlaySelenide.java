package tests;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import org.openqa.selenium.By;
import static com.codeborne.selenide.Selenide.*;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.testng.ScreenShooter;

@Listeners({ ScreenShooter.class })
public class PlaySelenide {

    Logger logger = LoggerFactory.getLogger(PlaySelenide.class);

    @BeforeClass(alwaysRun = true)
    public void prepareForTest() {
        Configuration.browser = "chrome";
        Configuration.remote = "http://localhost:9515";
    }

    @Test
    public void startup() {
        open("http://www.baidu.com");
        $(By.id("kw")).setValue("nba");
        $("#su").click();

        String s = $("#head").getText();
        logger.info(s);
    }

    /**
     * Selenide takes screenshots automatically on every test failure.
     */
    @Test
    public void screenshot() {
        ScreenShooter.captureSuccessfulTests = true;
        /**
         * By default Selenide puts screenshots to folder build/reports/tests.
         */
        Configuration.reportsFolder = "test-result/reports";

        open("http://www.baidu.com");
        $(By.id("kw")).setValue("nba");
        $("#su").click();

        // Assert.assertTrue(false);
    }

    @AfterClass(alwaysRun = true)
    public void end() {
        close();
    }
}
