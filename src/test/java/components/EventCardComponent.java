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
    public EventCardComponent checkEventCardDetails() {
        eventCardTitle.should(exist);
        eventCardPrice.should(exist);
        eventCardDate.should(exist);
        eventCardType.shouldHave(Condition.or("Check the event type", text("Курс"), text("Вебинар"),
                text("Конференция"), text("Бизнес-завтрак"), text("Мастер-класс для детей")));
        eventCard.shouldHave(attribute("href"));
        return this;
    }

    @Step("Check an event card content for the radio button ALL")
    public void checkEventCardAll(List<String> otherRadioButtons) {
        eventCardType.should(exist);//A workaround to wait some time to load event cards after changing a radio button
        for (SelenideElement element : eventCardTypes) {
            checkAllRadioContainsOtherEventTypes(otherRadioButtons);
            logger.info("The element = " + element.getText());
            logger.info("The event card found when the radio button = ALL.");
            checkDateInCard(element.$(byXpath(xpathFromEventCardTypeToEventCardDate)), true);
        }
    }

    @Step("Check an event card content for the radio button {selectedRadio}")
    public void checkEventCardOther(EventCardType selectedRadio) {
        eventCardType.should(exist);//A workaround to wait some time to load event cards after changing a radio button
        String text = "";
        switch (selectedRadio) {
            case WEBINAR -> text = "Вебинар";
            case COURSE -> text = "Курс";
            case CONFERENCE -> text = "Конференция";
        }
        for (SelenideElement element : eventCardTypes) {
            element.shouldHave(text(text));
            logger.info("The element = " + element.getText());
            logger.info("The event card found when the radio button = " + selectedRadio);
            checkDateInCard(element.$(byXpath(xpathFromEventCardTypeToEventCardDate)), true);
        }
    }

    @Step("Check an event card content for the radio button ARCHIVE")
    public void checkEventCardArchive() {
        eventCardType.should(exist);//A workaround to wait some time to load event cards after changing a radio button
        for (SelenideElement element : eventCardTypes) {
            element.shouldHave(Condition.or("Check the event type", text("Курс"), text("Вебинар"),
                    text("Конференция"), text("Бизнес-завтрак"), text("Мастер-класс для детей")));
            logger.info("The element: " + element.getText());
            logger.info("The event card found when the radio button = ARCHIVE.");
            checkDateInCard(element.$(byXpath(xpathFromEventCardTypeToEventCardDate)), false);
        }
    }

    @Step("Check that cards of every event type are displayed when the radio button = 'archive'")
    public void checkArchiveRadioContainsEveryEventType() {
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

    @Step("Check that all the types of other radio buttons (i.e. expect for the 'Archived' radio) are displayed " +
            "when the radio button = 'all'")
    public void checkAllRadioContainsOtherEventTypes(List<String> otherRadioButtons) {
        List<String> actual = new ArrayList<>();
        for (SelenideElement element : eventCardTypes) {
            actual.add(element.getText());
        }
        logger.info("Target event cards: " + otherRadioButtons);
        logger.info("Real event cards: " + actual);
        Assertions.assertTrue(actual.containsAll(otherRadioButtons), "When the 'all' radio button is selected, " +
                "it displays the same event types as the other radio buttons together.");
    }

    @Step("Get the start date from an event card and check whether it is an upcoming or past event")
    private void checkDateInCard(SelenideElement element, boolean isUpcomingEvent) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM yyyy", Locale.forLanguageTag("ru-RU"));
        LocalDate currentDate = LocalDate.now();
        String textFromCard = element.getText();
        LocalDate startDateFromCard, endDateFromCard;
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
            startDateFromCard = LocalDate.parse(strings[0], formatter);
            endDateFromCard = LocalDate.parse(strings[1], formatter);
            logger.info("The start date = " + startDateFromCard);
            logger.info("The end date = " + endDateFromCard);
        } else {
            startDateFromCard = LocalDate.parse(textFromCard.trim(), formatter);
            logger.info("The start date = " + startDateFromCard);
        }
        String assertionMessage = isUpcomingEvent ? "The card has a future date" : "The card has a past date";
        Assertions.assertEquals(isUpcomingEvent, startDateFromCard.isAfter(currentDate), assertionMessage);
    }
}
