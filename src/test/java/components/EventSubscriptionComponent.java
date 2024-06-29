package components;

import com.codeborne.selenide.*;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class EventSubscriptionComponent {

    private final SelenideElement eventsSubscribeTitle = $(byXpath("//*[@class='events-subscribe__title']")),
        eventsSubscribeText = $(byXpath("//*[@class='events-subscribe__text']")),
        eventsSubscribeTextEmailInput = $(byXpath("//*[@class='events-subscribe__input']")),
        eventsSubscribeSubmitButton = $(byXpath("//*[@class='events-subscribe__button']")),
        eventsSubscribeCheckboxAgreeContainer = $(byXpath("//*[@class='checkbox__container']")),
        eventsSubscribeCheckboxAgreeContainerLink = $(byXpath("//*[@class='checkbox__container']//a")),
        eventsSubscribeConfirmationText = $(byXpath("//div[contains(@class, 'subscribeP__bottom-text')]"));
    private final ElementsCollection eventsSubscribeConfirmationTextLink = $$(byXpath("//div[contains(@class, 'subscribeP__bottom-text')]//a"));

    @Step("Check the content of the event subscription form")
    public EventSubscriptionComponent checkEventSubscriptionForm() {
        eventsSubscribeTitle.shouldHave(exactText("Новые мероприятия уже скоро!"));
        eventsSubscribeText.shouldHave(exactText("Подпишитесь на рассылку, чтобы быть в курсе предстоящих мероприятий."));
        eventsSubscribeTextEmailInput.should(exist);
        eventsSubscribeSubmitButton.should(exist);
        eventsSubscribeCheckboxAgreeContainer.shouldHave(text("Я согласен(-на) на обработку своих персональных данных"));
        eventsSubscribeCheckboxAgreeContainerLink.shouldHave(attribute("href", Configuration.baseUrl + "/privacy/"));
        eventsSubscribeConfirmationText.shouldHave(exactText("Нажимая «Подписаться», я соглашаюсь получать email-рассылку" +
                " и иные маркетинговые сообщения на условиях и для целей, определенных в Согласии и Политике" +
                " в отношении обработки персональных данных."));
        eventsSubscribeConfirmationTextLink
                .get(0).shouldHave(attribute("href", "https://solutions.hflabs.ru/subscriber-consent"));
        eventsSubscribeConfirmationTextLink
                .get(1).shouldHave(attribute("href", "https://hflabs.ru/privacy/"));
        return this;
    }
}
