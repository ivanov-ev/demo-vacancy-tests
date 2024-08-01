package components;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import types.EventCardType;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static tests.TestBase.logger;

public class EventCardComponent {

    private final ElementsCollection eventCardTypes = $$(byXpath("//*[@class='event-card__type']"));

    private final SelenideElement eventCardType = $(byXpath("//*[@class='event-card__type']")),
            eventCardTitle = $(byXpath("//*[@class='event-card__title']")),
            eventCardPrice = $(byXpath("//*[@class='event-card__price']")),
            eventCardDate = $(byXpath("//*[@class='event-card__date']")),
            eventCard = $(byXpath("//*[@class='event-card']"));

    final String xpathFromEventCardTypeToEventCardDate = "./parent::div//div[@class='event-card__date']";

    @Step("Check an event card content")
    public EventCardComponent checkEventCardContent() {
        eventCardTitle.should(exist);
        eventCardPrice.should(exist);
        eventCardDate.should(exist);
        eventCardType.shouldHave(Condition.or("Check the event type", text("Курс"), text("Вебинар"),
                text("Конференция"), text("Бизнес-завтрак"), text("Мастер-класс для детей")));
        eventCard.shouldHave(attribute("href"));
        return this;
    }

    @Step("Check an event card content")
    public void checkEventCardType(EventCardType selectedRadio) {
        eventCardType.should(exist);//A workaround to wait some time to load event cards after changing a radio button
        switch (selectedRadio) {
            case ALL: {
                for (SelenideElement element : eventCardTypes) {
                    element.shouldHave(Condition.or("Check the event type", text("Курс"), text("Вебинар"),
                            text("Конференция"), text("Бизнес-завтрак"), text("Мастер-класс для детей")));
                    logger.info("The element = " + element.getText());
                    logger.info("The event card found when the radio button = ALL.");
                    getDateFromCard(element.$(byXpath(xpathFromEventCardTypeToEventCardDate)), true);
                }
                break;
            }
            case WEBINAR: {
                for (SelenideElement element : eventCardTypes) {
                    element.shouldHave(text("Вебинар"));
                    logger.info("The element = " + element.getText());
                    logger.info("The event card found when the radio button = WEBINAR.");
                    getDateFromCard(element.$(byXpath(xpathFromEventCardTypeToEventCardDate)), true);
                }
                break;
            }
            case COURSE: {
                for (SelenideElement element : eventCardTypes) {
                    element.shouldHave(text("Курс"));
                    logger.info("The element: " + element.getText());
                    logger.info("The event card found when the radio button = COURSE.");
                    getDateFromCard(element.$(byXpath(xpathFromEventCardTypeToEventCardDate)), true);
                }
                break;
            }
            case CONFERENCE: {
                for (SelenideElement element : eventCardTypes) {
                    element.shouldHave(text("Конференция"));
                    logger.info("The element: " + element.getText());
                    logger.info("The event card found when the radio button = CONFERENCE.");
                    getDateFromCard(element.$(byXpath(xpathFromEventCardTypeToEventCardDate)), true);
                }
                break;
            }
            case ARCHIVE: {
                for (SelenideElement element : eventCardTypes) {
                    element.shouldHave(Condition.or("Check the event type", text("Курс"), text("Вебинар"),
                            text("Конференция"), text("Бизнес-завтрак"), text("Мастер-класс для детей")));
                    logger.info("The element: " + element.getText());
                    logger.info("The event card found when the radio button = ARCHIVE.");
                    getDateFromCard(element.$(byXpath(xpathFromEventCardTypeToEventCardDate)), false);
                }
                break;
            }
        }
    }

    @Step("Check that cards of all event types are displayed when the radio button = 'archive'")
    public void checkArchiveForEventTypes() {
        List<String> target = Arrays
                .asList("Курс", "Вебинар", "Конференция", "Бизнес-завтрак", "Мастер-класс для детей");
        List<String> actual = new ArrayList<>();
        for (SelenideElement element : eventCardTypes) {
            actual.add(element.getText());
        }
        logger.info("Target event cards: " + target);
        logger.info("Real event cards: " + actual);
        Assertions.assertTrue(actual.containsAll(target), "Real event cards cover all existing event types.");
    }

    @Step("Get the start date from an event card and check whether it is an upcoming or past event")
    private void getDateFromCard(SelenideElement element, boolean isUpcomingEvent) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM yyyy", Locale.forLanguageTag("ru-RU"));
        LocalDate currentDate = LocalDate.now();
        String textFromCard = element.getText();
        if (textFromCard.contains("-")) {
            String[] strings = textFromCard.trim().split("-");
            int index = -1;
            for (int i = 0; i < textFromCard.trim().length(); i++) {
                if (Character.isAlphabetic(textFromCard.trim().charAt(i))) {
                    index = i;
                    break;
                }
            }
            String monthAndYear = textFromCard.trim().substring(index);
            strings[0] = strings[0] + " " + monthAndYear;
            LocalDate startDateFromCard = LocalDate.parse(strings[0], formatter);
            LocalDate endDateFromCard = LocalDate.parse(strings[1], formatter);
            logger.info("The start date = " + startDateFromCard);
            logger.info("The end date = " + endDateFromCard);
            if (isUpcomingEvent) {
                Assertions.assertTrue(startDateFromCard.isAfter(currentDate), "The card has a future date");
            } else {
                Assertions.assertTrue(startDateFromCard.isBefore(currentDate), "The card has a past date");
            }
        } else {
            LocalDate startDateFromCard = LocalDate.parse(textFromCard.trim(), formatter);
            logger.info("The start date = " + startDateFromCard);
            if (isUpcomingEvent) {
                Assertions.assertTrue(startDateFromCard.isAfter(currentDate), "The card has a future date");
            } else {
                Assertions.assertTrue(startDateFromCard.isBefore(currentDate), "The card has a past date");
            }
        }
    }
}
