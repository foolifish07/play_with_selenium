import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.Config;

/**
 * Test Config.class
 */
public class TestConfig {

    Logger logger = LoggerFactory.getLogger(TestConfig.class);

    @Test
    public void testSingleton () {
        Config.configPath = "src/test/resources/config.yaml";

        Config config1 = Config.getInstance();
        Config config2 = Config.getInstance();

        logger.info("config1 {}, config2 {}", config1, config2);
        logger.info("equal {}", config1 == config2);

        Assert.assertTrue(config1 == config2);
        Assert.assertTrue(config1.accounts.size() == 3);
    }

    @Test
    public void testValue () {
        Config config = Config.getInstance("src/test/resources/config.yaml");

        logger.info("{}", config.driver.browser);
        logger.info("{}", config.driver.address);
        logger.info("{}", config.driver.remoteAddress);

        logger.info("{}", config.accounts.size());
        logger.info("{}", config.accounts.get(1).username);
        logger.info("{}", config.accounts.get(1).password);
        logger.info("{}", config.accounts.get(1).password == null);

        Assert.assertTrue( config.accounts.get(1).password == null);
    }
}
