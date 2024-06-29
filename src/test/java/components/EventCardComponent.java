package components;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;

public class EventCardComponent {

    private final SelenideElement eventCardType =  $(byXpath("//*[@class='event-card__type']")),
        eventCardTitle = $(byXpath("//*[@class='event-card__title']")),
        eventCardPrice = $(byXpath("//*[@class='event-card__price']")),
        eventCardDate = $(byXpath("//*[@class='event-card__date']")),
        eventCard = $(byXpath("//*[@class='event-card']"));

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
    public EventCardComponent checkEventCardContent(String selectedRadio) {
        switch (selectedRadio) {
            case "all":
            case "archive":{
                eventCardType.shouldHave(Condition.or("Check the event type", text("Курс"), text("Вебинар"),
                        text("Конференция"), text("Бизнес-завтрак"), text("Мастер-класс для детей")));
                break;
            }
            case "webinar": {
                eventCardType.shouldHave(text("Вебинар"));
                break;
            }
            case "course": {
                eventCardType.shouldHave(text("Курс"));
                break;
            }
            case "conference": {
                eventCardType.shouldHave(text("Конференция"));
                break;
            }
        }
        return this;
    }
}
