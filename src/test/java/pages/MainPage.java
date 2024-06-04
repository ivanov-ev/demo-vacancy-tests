package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class MainPage {

    private final SelenideElement headerItemLinkProducts = $(byXpath("//*[@class='header__item__link' and text()='Продукты']")),
            headerItemLinkCompany = $(byXpath("//*[@class='header__item__link' and text()='Компания']"));


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
    public MainPage cookiesBarIsHidden() {
        cookiesBar.shouldNotBe(visible);
        return this;
    }

    @Step("Open the 'Products' top bar")
    public MainPage openProductsTopBar() {
        headerItemLinkProducts.click();
        return this;
    }

    @Step("Check the 'Products' top bar content")
    public MainPage checkProductsTopBarTextsAndLinks() {
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
        return this;
    }

    @Step("Open the 'Company' top bar")
    public MainPage openCompanyTopBar() {
        headerItemLinkCompany.click();
        return this;
    }

    @Step("Check the 'Company' top bar content")
    public MainPage checkCompanyTopBarTextsAndLinks() {
        headerItemLinkCompanyTeam.shouldBe(visible);
        headerItemLinkCompanyClients.shouldBe(visible);
        headerItemLinkCompanyCareer.shouldBe(visible);
        headerItemLinkCompanySocial.shouldBe(visible);
        return this;
    }

    @Step("Click the request submission button")
    public MainPage clickRequestSubmissionButton() {
        submitRequestButton.click();
        return this;
    }


    @Step("Change the language to EN")
    public MainPage changeLanguageToEn() {
        changeLanguageToEnSelector.click();
        return this;
    }

    @Step("Check the language = EN")
    public MainPage checkLanguageIsEn() {
        pageTitleEn.should(exist);
        return this;
    }

    @Step("Change the language to RU")
    public MainPage changeLanguageToRu() {
        changeLanguageToRuSelector.click();
        return this;
    }

    @Step("Check the language = RU")
    public MainPage checkLanguageIsRu() {
        pageTitleRu.should(exist);
        return this;
    }
}
