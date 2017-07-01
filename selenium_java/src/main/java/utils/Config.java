package utils;

import org.yaml.snakeyaml.Yaml;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.constructor.Constructor;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

/**
 * Read parameters from .yml file
 */
public class Config {

    public static String configPath = "src/main/resources/config.yaml";

    private static class Factory {

        public static final Config instance =
                getInstance(configPath);
    }

    public static Config getInstance() {
        return Factory.instance;
    }

    public static Config getInstance(String path){
        Logger logger = LoggerFactory.getLogger(Config.class);

        try {
            logger.info("Fetching config class");
            Yaml yaml = new Yaml(new Constructor(Config.class));
            InputStream input = new FileInputStream(new File(path));
            Config p = (Config)yaml.load(input);
            return p;
        } catch (Exception e){
            logger.error("Fail to fetch config class");
            throw new RuntimeException(e);
        }
    }

    /**
     * properties
     */
    public Driver driver;

    public static class Driver {
        public String browser;
        public String remoteAddress;
        public String address;
    }

    public List<Account> accounts;

    public static class Account {
        public String username;
        public String password;
    }
}
