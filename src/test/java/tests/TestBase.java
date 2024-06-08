package tests;

import config.WebDriverProvider;
import helpers.Attach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public class TestBase {

    @BeforeAll
    static void setup() {
        WebDriverProvider.initConfiguration();
    }

    @AfterEach
    void tearDown() {
        addAttachments();
        closeWebDriver();
    }

    void addAttachments() {
        Attach.screenshotAs("Screenshot");
        Attach.addVideo();
        Attach.browserConsoleLogs();
        Attach.pageSource();
    }
}
