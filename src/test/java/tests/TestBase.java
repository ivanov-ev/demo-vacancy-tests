package tests;

import config.WebDriverConfigurator;
import helpers.Attach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public class TestBase {

    public static final Logger logger = LoggerFactory.getLogger(TestBase.class);

    @BeforeAll
    static void setup() {
        WebDriverConfigurator.initConfiguration();
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
