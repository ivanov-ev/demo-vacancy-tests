package pages;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.ex.ElementNotFound;
import io.qameta.allure.Step;
import types.EventCardType;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static tests.TestBase.logger;

public class EventPage {

    private final SelenideElement headerBlock = $(byXpath("//header[contains(@class, 'header')]")),
            eventsListBlock = $(byXpath("//div[contains(@class, 'events-list')]")),
            subscribeBlock = $(byId("subscribe")),
            footerBlock = $(byXpath("//footer[contains(@class, 'footer')]"));

    private final SelenideElement selectSectionText = $(byXpath("//div[contains(@class, 'events-list__control-title')]")),
            eventTypeAllRadio = $(byXpath("//*[contains(@class, 'events-list__chooses')]//li[contains(@data-tag, 'all')]")),
            eventTypeWebinarRadio = $(byXpath("//*[contains(@class, 'events-list__chooses')]//li[contains(@data-tag, '1')]")),
            eventTypeCourseRadio = $(byXpath("//*[contains(@class, 'events-list__chooses')]//li[contains(@data-tag, '2')]")),
            eventTypeConferenceRadio = $(byXpath("//*[contains(@class, 'events-list__chooses')]//li[contains(@data-tag, '4')]")),
            eventTypeArchiveRadio = $(byXpath("//*[contains(@class, 'events-list__chooses')]//li[contains(@data-tag, 'archive')]"));

    private final SelenideElement showMoreButton = $(byXpath("//div[contains(@class, 'events-list__more')]"));

    @Step("Open the events page")
    public EventPage openPage() {
        open("/events");
        return this;
    }

    @Step("Check the events page contains all required blocks")
    public EventPage checkPageBlocks() {
        headerBlock.should(exist);
        eventsListBlock.should(exist);
        subscribeBlock.should(exist);
        footerBlock.should(exist);
        return this;
    }

    @Step("Change the event type using the radio button, and return 'true' if this radio button exists and is clicked")
    public boolean changeEventType(EventCardType eventType) {
        selectSectionText.scrollIntoView(true);
        switch (eventType) {
            case ALL: {
                try {
                    eventTypeAllRadio.click();
                    break;
                } catch (ElementNotFound e) {
                    logger.error("The 'All events' radio is currently unavailable");
                    return false;
                }
            }
            case WEBINAR: {
                try {
                    eventTypeWebinarRadio.click();
                    break;
                } catch (ElementNotFound e) {
                    logger.error("The 'Webinar' radio is currently unavailable");
                    return false;
                }
            }
            case COURSE: {
                try {
                    eventTypeCourseRadio.click();
                    break;
                } catch (ElementNotFound e) {
                    logger.error("The 'Course' radio is currently unavailable");
                    return false;
                }
            }
            case CONFERENCE: {
                try {
                    eventTypeConferenceRadio.click();
                    break;
                } catch (ElementNotFound e) {
                    logger.error("The 'Conference' radio is currently unavailable");
                    return false;
                }
            }
            case ARCHIVE: {
                try {
                    eventTypeArchiveRadio.click();
                    break;
                } catch (ElementNotFound e) {
                    logger.error("The 'Archive' radio is currently unavailable");
                    return false;
                }
            }
        }
        return true;
    }

    @Step("Click the 'Show More' button when the radio button = 'archive'")
    public EventPage showMore(int n) {
        while (n > 0) {
            try {
                showMoreButton.scrollIntoView(false);
                showMoreButton.click();
                logger.info("A click on the 'Show More' button occurred");
                waitSecond();//A bad practice, but it is easier to wait a bit instead of getting the number
                //of event cards before the click, and getting it again a few moments after the click.
                //Also, if the button is clicked twice too fast, it loads cards only once, not twice.
            } catch (ElementNotFound e) {
                logger.error("The 'Show More' is not displayed. The end is reached.");
                break;
            }
            n--;
        }
        return this;
    }

    private void waitSecond() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
