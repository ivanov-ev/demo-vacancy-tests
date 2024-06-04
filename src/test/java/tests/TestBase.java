package tests;

import com.codeborne.selenide.Configuration;
import helpers.Attach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Map;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public class TestBase {

    @BeforeAll
    static void setup() {
        //pay attention to the trailing slash, because it concatenates with open() and may result in 2 slashes
        Configuration.baseUrl = "https://hflabs.ru";
        Configuration.pageLoadStrategy = "eager";

        if (Configuration.remote != null) {
            Configuration.remote = "https://user1:1234@" + Configuration.remote + "/wd/hub";
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
