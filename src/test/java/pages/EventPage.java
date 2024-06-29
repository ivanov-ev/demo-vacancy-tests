package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

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

    @Step("Verify the default radio button = 'All'")
    public EventPage verifyDefaultRadio() {
        eventTypeAllRadio.shouldHave(cssClass("-active"));
        return this;
    }

    @Step("Change the event type using the radio button")
    public EventPage changeEventType(String eventType) {
        switch (eventType) {
            case "all": {
                selectSectionText.scrollIntoView(true);
                eventTypeAllRadio.click();
                break;
            }
            case "webinar": {
                selectSectionText.scrollIntoView(true);
                eventTypeWebinarRadio.click();
                break;
            }
            case "course": {
                selectSectionText.scrollIntoView(true);
                eventTypeCourseRadio.click();
                break;
            }
            case "conference": {
                selectSectionText.scrollIntoView(true);
                eventTypeConferenceRadio.click();
                break;
            }
            case "archive": {
                selectSectionText.scrollIntoView(true);
                eventTypeArchiveRadio.click();
                break;
            }
        }
        return this;
    }
}
