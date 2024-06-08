package config;

import com.codeborne.selenide.Configuration;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Map;

public class WebDriverProvider {
    public static void initConfiguration() {
        WebDriverConfig webDriverConfig = ConfigFactory.create(WebDriverConfig.class, System.getProperties());

        Configuration.browser = webDriverConfig.browser();
        Configuration.browserVersion = webDriverConfig.browserVersion();
        Configuration.browserSize = webDriverConfig.browserSize();

        //pay attention to the trailing slash, because it concatenates with open() and may result in 2 slashes
        Configuration.baseUrl = "https://hflabs.ru";

        Configuration.pageLoadStrategy = "eager";

        Configuration.remote = webDriverConfig.remote();
        if (Configuration.remote != null) {
            Configuration.remote = "https://"
                    + webDriverConfig.remoteUsername() + ":"
                    + webDriverConfig.remotePassword() + "@"
                    + Configuration.remote
                    + "/wd/hub";
        }

        Configuration.webdriverLogsEnabled = true;

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("selenoid:options", Map.of(
                "enableVNC", true,
                "enableVideo",true
        ));
        Configuration.browserCapabilities = capabilities;
    }
}
