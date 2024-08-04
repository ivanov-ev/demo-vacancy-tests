package pages;

import com.codeborne.selenide.SelenideElement;
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

    @Step("Check the radio button {eventType} exists")
    public boolean checkRadioButtonExists(EventCardType eventType) {
        selectSectionText.scrollIntoView(true);
        boolean result = false;
        switch (eventType) {
            case ALL -> result = eventTypeAllRadio.exists();
            case WEBINAR -> result = eventTypeWebinarRadio.exists();
            case COURSE -> result = eventTypeCourseRadio.exists();
            case CONFERENCE -> result = eventTypeConferenceRadio.exists();
            case ARCHIVE -> result = eventTypeArchiveRadio.exists();
        }
        return result;
    }

    @Step("Change the event type using the radio button {eventType}")
    public EventPage changeEventType(EventCardType eventType) {
        selectSectionText.scrollIntoView(true);
        switch (eventType) {
            case ALL -> eventTypeAllRadio.click();
            case WEBINAR -> eventTypeWebinarRadio.click();
            case COURSE -> eventTypeCourseRadio.click();
            case CONFERENCE -> eventTypeConferenceRadio.click();
            case ARCHIVE -> eventTypeArchiveRadio.click();
        }
        return this;
    }

    @Step("Click the 'Show More' button")
    public EventPage showMore(int n) {
        while (n > 0) {
            showMoreButton.scrollIntoView(false);
            if (showMoreButton.isDisplayed()) {
                showMoreButton.click();
            }
            logger.info("A click on the 'Show More' button occurred");
            waitSecond();//A bad practice, but it is easier to wait a bit instead of getting the number
            //of event cards before the click, and getting it again a few moments after the click.
            //Also, if the button is clicked twice too fast, it loads cards only once, not twice.
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
