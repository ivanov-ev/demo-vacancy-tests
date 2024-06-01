package components;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;

public class SubmitRequestComponent {

    private final SelenideElement formTitle =  $(byXpath("//*[@class='buyForm__title']")),
            closeForm = $(byXpath("//*[@class='buyForm__btnClose btnCross']")),
            phoneLabel = $(byXpath("//*[@class='buyForm__form__label' and @for=\"phone\"]")),
            phoneInput = $(byXpath("//*[@class='buyForm__form__input' and @type=\"tel\"]")),
            emailLabel = $(byXpath("//*[@class='buyForm__form__label' and @for=\"email\"]")),
            emailInput = $(byXpath("//*[@id='email']")),
            nameLabel = $(byXpath("//*[@class='buyForm__form__label' and @for=\"name\"]")),
            nameInput = $(byXpath("//*[@id='fullname']")),
            agreeLabel = $(byXpath("//*[@name='agree']")),
            submitForm =  $(byXpath("//*[@name='submit']")),
            submissionFormIsOpen = $(byXpath("//*[@class='buyForm' and @style='display: block; opacity: 1;']"));

    @Step("Check the submission form")
    public void checkSubmissionForm() {
        formTitle.shouldBe(visible);
        closeForm.shouldBe(visible);
        phoneLabel.shouldBe(visible);
        phoneInput.shouldBe(visible);
        emailLabel.shouldBe(visible);
        emailInput.shouldBe(visible);
        nameLabel.shouldBe(visible);
        nameInput.shouldBe(visible);
        agreeLabel.shouldBe(visible);
        submitForm.shouldBe(visible);
    }

    @Step("Close the submission form using the 'x' icon")
    public void closeSubmissionForm() {
        closeForm.click();
        submissionFormIsOpen.shouldNot(exist);
    }
}
