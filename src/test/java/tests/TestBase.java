package tests;

import com.codeborne.selenide.Configuration;
import config.WebDriverConfig;
import helpers.Attach;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Map;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public class TestBase {

    @BeforeAll
    static void setup() {

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

    void addAttachments() {
        Attach.screenshotAs("Screenshot");
        Attach.addVideo();
        Attach.browserConsoleLogs();
        Attach.pageSource();
    }

    @AfterEach
    void tearDown() {
        addAttachments();
        closeWebDriver();
    }
}
