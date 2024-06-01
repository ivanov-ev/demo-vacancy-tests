package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class MainPage {

    private final SelenideElement headerItemLinkProducts = $(byXpath("//*[@class='header__item__link' and text()='Продукты']")),
            headerItemLinkEvents = $(byXpath("//*[@class='header__item__link' and text()='Мероприятия']")),
            headerItemLinkBlog = $(byXpath("//*[@class='header__item__link' and text()='Блог']")),
            headerItemLinkCompany = $(byXpath("//*[@class='header__item__link' and text()='Компания']")),
            headerItemLinkContacts = $(byXpath("//*[@class='header__item__link' and text()='Контакты']"));


    private final SelenideElement submitRequestButton = $(byXpath("//*[@class='header__btn__wrap js-openSend']"));

    private final SelenideElement getHeaderItemLinkProductsFactor = $(byXpath("//*[@class='header__item__inner']/a[text()='«Фактор»' and @href=\"/factor/\"]")),
            getHeaderItemLinkProductsSingleClient = $(byXpath("//*[@class='header__item__inner']/a[text()='«Единый клиент»' and @href=\"/uniform-client/\"]")),
            getHeaderItemLinkProductsSingleAddress = $(byXpath("//*[@class='header__item__inner']/a[text()='«Единый адрес»' and @href=\"/address/\"]")),
            getHeaderItemLinkProductsKnowYourCustomer = $(byXpath("//*[@class='header__item__inner']/a[text()='Know Your Customer' and @href=\"/kyc/\"]")),
            getHeaderItemLinkProductsEcosystemClient = $(byXpath("//*[@class='header__item__inner']/a[text()='«Экосистемный клиент»' and @href=\"/federativnuy-cdi/\"]")),
            getHeaderItemLinkProductsConsentManagementCenter = $(byXpath("//*[@class='header__item__inner']/a[text()='«Центр управления согласиями»' and @href=\"/cus/\"]")),
            getHeaderItemLinkProductsSingleHints = $(byXpath("//*[@class='header__item__inner']/a[text()[contains(.,'«Подсказки»')] and @href=\"https://dadata.ru/suggestions/\"]")),
            getHeaderItemLinkProductsDaDaWebsite = $(byXpath("//*[@class='header__item__inner']/a[text()[contains(.,'DaData.ru')] and @href=\"https://dadata.ru\"]")),
            getHeaderItemLinkProductsAudit = $(byXpath("//*[@class='header__item__inner']/a[text()='Аудит' and @href=\"/audit/\"]")),
            getHeaderItemLinkProductsMasker = $(byXpath("//*[@class='header__item__inner']/a[text()='«Маскировщик»' and @href=\"/masking/\"]"));

    private final SelenideElement headerItemLinkCompanyTeam = $(byXpath("//*[@class='header__item__inner']/a[text()='Команда' and @href=\"/about/\"]")),
            headerItemLinkCompanyClients = $(byXpath("//*[@class='header__item__inner']/a[text()='Клиенты' and @href=\"/clients/\"]")),
            headerItemLinkCompanyCareer = $(byXpath("//*[@class='header__item__inner']/a[text()='Карьера' and @href=\"https://career.hflabs.ru/\"]")),
            headerItemLinkCompanySocial = $(byXpath("//*[@class='header__item__inner']/a[text()='Социальные проекты' and @href=\"https://career.hflabs.ru/social\"]"));

    private final SelenideElement changeLanguageToRuSelector = $(byXpath("//*[contains(@class, \"header__lang\")]/a[text()='Ru']")),
            changeLanguageToEnSelector = $(byXpath("//*[contains(@class, \"header__lang\")]/a[text()='En']"));

    private final SelenideElement pageTitleRu = $(byTagAndText("title", "HFLabs — умные решения data quality и master data management")),
            pageTitleEn = $(byTagAndText("title", "HFLabs - smart data quality and master data management solution"));

    private final SelenideElement cookiesBar = $(byXpath("//*[@class='cookies cookies__opened']")),
            cookiesCloseButton = $(byXpath("//button[@class='cookie__close']"));



    @Step("Open the main page")
    public MainPage openPage() {
        open("/");
        return this;
    }

    @Step("Close the cookies bar")
    public MainPage closeCookiesBar() {
        cookiesCloseButton.click();
        return this;
    }

    @Step("Check the cookies bar is closed")
    public void cookiesBarIsHidden() {
        cookiesBar.shouldNotBe(visible);
    }

    @Step("Check the 'Products' top bar content")
    public void checkProductsTopBarTextsAndLinks() {
        headerItemLinkProducts.click();
        getHeaderItemLinkProductsFactor.shouldBe(visible);
        getHeaderItemLinkProductsSingleClient.shouldBe(visible);
        getHeaderItemLinkProductsSingleAddress.shouldBe(visible);
        getHeaderItemLinkProductsKnowYourCustomer.shouldBe(visible);
        getHeaderItemLinkProductsEcosystemClient.shouldBe(visible);
        getHeaderItemLinkProductsConsentManagementCenter.shouldBe(visible);
        getHeaderItemLinkProductsSingleHints.shouldBe(visible);
        getHeaderItemLinkProductsDaDaWebsite.shouldBe(visible);
        getHeaderItemLinkProductsAudit.shouldBe(visible);
        getHeaderItemLinkProductsMasker.shouldBe(visible);
    }

    @Step("Check the 'Company' top bar content")
    public void checkCompanyTopBarTextsAndLinks() {
        headerItemLinkCompany.click();
        headerItemLinkCompanyTeam.shouldBe(visible);
        headerItemLinkCompanyClients.shouldBe(visible);
        headerItemLinkCompanyCareer.shouldBe(visible);
        headerItemLinkCompanySocial.shouldBe(visible);
    }

    @Step("Click the request submission button")
    public void clickRequestSubmissionButton() {
        submitRequestButton.click();
    }

    @Step("Change language to EN")
    public MainPage changeLanguageToEn() {
        changeLanguageToEnSelector.click();
        pageTitleEn.should(exist);
        return this;
    }

    @Step("Change language to RU")
    public MainPage changeLanguageToRu() {
        changeLanguageToRuSelector.click();
        pageTitleRu.should(exist);
        return this;
    }
}
