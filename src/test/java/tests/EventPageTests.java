package tests;

import components.EventCardComponent;
import components.EventSubscriptionComponent;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.EventPage;

@Tag("smoke_tests")
@Feature("hflabs.ru")
@Story("Event page tests")
@Owner("ivanov-ev")
@Severity(SeverityLevel.NORMAL)
@Link(name = "https://hflabs.ru/events/", url = "https://hflabs.ru/events/")
@DisplayName("Event page tests")
public class EventPageTests extends TestBase {
    EventPage eventPage = new EventPage();
    EventCardComponent eventCardComponent = new EventCardComponent();
    EventSubscriptionComponent eventSubscriptionComponent = new EventSubscriptionComponent();

    @Test
    @DisplayName("Test the radio button for event type selection, and event cards that appear on the right for this radio button")
    void chooseEventTypeTest() {
        eventPage.openPage();
        eventPage.verifyDefaultRadio();
        eventCardComponent.checkEventCardContent("all");

        eventPage.changeEventType("webinar");
        eventCardComponent.checkEventCardContent("webinar");

        eventPage.changeEventType("course");
        eventCardComponent.checkEventCardContent("course");

        eventPage.changeEventType("conference");
        eventCardComponent.checkEventCardContent("conference");

        eventPage.changeEventType("archive");
        eventCardComponent.checkEventCardContent("archive");

        eventPage.changeEventType("all");
        eventCardComponent.checkEventCardContent("all");
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
