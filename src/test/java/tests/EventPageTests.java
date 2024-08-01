package tests;

import components.EventCardComponent;
import components.EventSubscriptionComponent;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.EventPage;
import pages.MainPage;

import static types.EventCardType.*;

@Tag("smoke_tests")
@Feature("hflabs.ru")
@Story("Event page tests")
@Owner("ivanov-ev")
@Severity(SeverityLevel.NORMAL)
@Link(name = "https://hflabs.ru/events/", url = "https://hflabs.ru/events/")
@DisplayName("Event page tests")
public class EventPageTests extends TestBase {

    final MainPage mainPage = new MainPage();
    final EventPage eventPage = new EventPage();
    final EventCardComponent eventCardComponent = new EventCardComponent();
    final EventSubscriptionComponent eventSubscriptionComponent = new EventSubscriptionComponent();

    @Test
    @DisplayName("Test the radio button for event type selection, and event cards that appear on the right for this radio button")
    void chooseEventTypeTest() {
        mainPage.openPage()
                .closeCookiesBar();
        eventPage.openPage();
        if (eventPage.changeEventType(ALL)) {
            eventCardComponent.checkEventCardType(ALL);
        }

        if (eventPage.changeEventType(WEBINAR)) {
            eventCardComponent.checkEventCardType(WEBINAR);
        }

        if (eventPage.changeEventType(COURSE)) {
            eventCardComponent.checkEventCardType(COURSE);
        }

        if (eventPage.changeEventType(CONFERENCE)) {
            eventCardComponent.checkEventCardType(CONFERENCE);
        }

        if (eventPage.changeEventType(ARCHIVE)) {
            eventCardComponent.checkEventCardType(ARCHIVE);
        }

        if (eventPage.changeEventType(ALL)) {
            eventCardComponent.checkEventCardType(ALL);
        }
    }

    @Test
    @DisplayName("Test the radio button for event type selection: check that the 'Archive' radio contains all available event types")
    void testEventTypesForArchiveTest() {
        mainPage.openPage()
                .closeCookiesBar();
        eventPage.openPage();
        if (eventPage.changeEventType(ARCHIVE)) {
            eventPage.showMore(10); // The size of the website's archive is unknown,
            // so the test clicks the 'Show More' button 10 times (or less) and it is supposed to be enough to pass.
            eventCardComponent.checkArchiveForEventTypes();
        }
    }

    @Test
    @DisplayName("Check an event card's content")
    void checkEventCardTest() {
        eventPage.openPage();
        eventCardComponent.checkEventCardContent();
    }

    @Test
    @DisplayName("Check subsections of the event page")
    void checkSubSectionsTest() {
        eventPage.openPage()
                .checkPageBlocks();
    }

    @Test
    @DisplayName("Check the event subscription form content")
    void eventSubscriptionFormContentTest() {
        eventPage.openPage();
        eventSubscriptionComponent.checkEventSubscriptionForm();
    }
}
