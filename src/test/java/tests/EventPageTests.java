package tests;

import components.EventCardComponent;
import components.EventSubscriptionComponent;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIf;
import pages.EventPage;

import java.util.ArrayList;
import java.util.List;

import static types.EventCardType.*;

@Tag("smoke_tests")
@Feature("hflabs.ru")
@Story("Event page tests")
@Owner("ivanov-ev")
@Severity(SeverityLevel.NORMAL)
@Link(name = "https://hflabs.ru/events/", url = "https://hflabs.ru/events/")
@DisplayName("Event page tests")
public class EventPageTests extends TestBase {

    final EventPage eventPage = new EventPage();
    final EventCardComponent eventCardComponent = new EventCardComponent();
    final EventSubscriptionComponent eventSubscriptionComponent = new EventSubscriptionComponent();

    @Test
    @EnabledIf("allRadioExists")
    @DisplayName("Test the 'All' radio button, and event cards that appear on the right")
    void eventTypeAllTest() {
        eventPage.openPage()
                .changeEventType(ALL);
        eventPage.showMore(3); // The number of found events in the 'all' radio is unknown,
        // so the test clicks the 'Show More' button 3 times (or less) and it is supposed to be enough to pass.
        eventCardComponent.checkEventCardAll(getOtherRadioButtonsDisplayState());
    }

    private boolean allRadioExists() {
        return eventPage.openPage().checkRadioButtonExists(ALL);
    }

    @Test
    @EnabledIf("webinarRadioExists")
    @DisplayName("Test the 'Webinar' radio button, and event cards that appear on the right")
    void eventTypeWebinarTest() {
        eventPage.openPage()
                .changeEventType(WEBINAR);
        eventCardComponent.checkEventCardOther(WEBINAR);
    }

    private boolean webinarRadioExists() {
        return eventPage.openPage().checkRadioButtonExists(WEBINAR);
    }

    @Test
    @EnabledIf("conferenceRadioExists")
    @DisplayName("Test the 'Conference' radio button, and event cards that appear on the right")
    void eventTypeConferenceTest() {
        eventPage.openPage()
                .changeEventType(CONFERENCE);
        eventCardComponent.checkEventCardOther(CONFERENCE);
    }

    private boolean conferenceRadioExists() {
        return eventPage.openPage().checkRadioButtonExists(CONFERENCE);
    }

    @Test
    @EnabledIf("courseRadioExists")
    @DisplayName("Test the 'Course' radio button, and event cards that appear on the right")
    void eventTypeCourseTest() {
        eventPage.openPage()
                .changeEventType(COURSE);
        eventCardComponent.checkEventCardOther(COURSE);
    }

    private boolean courseRadioExists() {
        return eventPage.openPage().checkRadioButtonExists(COURSE);
    }

    @Test
    @EnabledIf("archiveRadioExists")
    @DisplayName("Test the 'Archive' radio button, and event cards that appear on the right")
    void eventTypeArchiveTest() {
        eventPage.openPage()
                .changeEventType(ARCHIVE);
        eventCardComponent.checkEventCardArchive();
    }

    @Test
    @EnabledIf("archiveRadioExists")
    @DisplayName("Check that the 'Archive' radio contains every event type")
    void eventTypesForArchiveTest() {
        eventPage.openPage()
                .changeEventType(ARCHIVE)
                .showMore(10); // The size of the website's archive is unknown,
                // so the test clicks the 'Show More' button 10 times (or less) and it is supposed to be enough to pass.
        eventCardComponent.checkArchiveRadioContainsEveryEventType();
    }

    private boolean archiveRadioExists() {
        return eventPage.openPage().checkRadioButtonExists(ARCHIVE);
    }

    @Test
    @DisplayName("Check an event card's content")
    void checkEventCardTest() {
        eventPage.openPage();
        eventCardComponent.checkEventCardDetails();
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

    @Step("Get radio buttons' configuration")
    public List<String> getOtherRadioButtonsDisplayState() {
        List<String> otherRadioButtons = new ArrayList<>();
        eventPage.openPage();
        if (webinarRadioExists()) otherRadioButtons.add("Вебинар");
        if (courseRadioExists()) otherRadioButtons.add("Курс");
        if (conferenceRadioExists()) otherRadioButtons.add("Конференция");
        return otherRadioButtons;
    }
}
