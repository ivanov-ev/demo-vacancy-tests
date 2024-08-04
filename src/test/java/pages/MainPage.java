package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class MainPage {

    private final SelenideElement headerProducts = $(byXpath("//a[@class='header__item__link' and text()='Продукты']")),
            headerCompany = $(byXpath("//a[@class='header__item__link' and text()='Компания']"));

    private final SelenideElement submitRequestButton = $(byXpath("//*[@class='header__btn__wrap js-openSend']"));

    private final SelenideElement headerItemLinkProductsFactor = $(byXpath("//a[@class='header__item__link' and text()='Продукты']" +
                    "/following-sibling::div/ul/li[@class='header__item__inner']/a[text()='«Фактор»']")),
            headerItemLinkProductsSingleClient = $(byXpath("//a[@class='header__item__link' and text()='Продукты']" +
                    "/following-sibling::div/ul/li[@class='header__item__inner']/a[text()='«Единый клиент»']")),
            headerItemLinkProductsSingleAddress = $(byXpath("//a[@class='header__item__link' and text()='Продукты']" +
                    "/following-sibling::div/ul/li[@class='header__item__inner']/a[text()='«Единый адрес»']")),
            headerItemLinkProductsKnowYourCustomer = $(byXpath("//a[@class='header__item__link' and text()='Продукты']" +
                    "/following-sibling::div/ul/li[@class='header__item__inner']/a[text()='Know Your Customer']")),
            headerItemLinkProductsEcosystemClient = $(byXpath("//a[@class='header__item__link' and text()='Продукты']" +
                    "/following-sibling::div/ul/li[@class='header__item__inner']/a[text()='«Экосистемный клиент»']")),
            headerItemLinkProductsConsentManagementCenter = $(byXpath("//a[@class='header__item__link' and text()='Продукты']" +
                    "/following-sibling::div/ul/li[@class='header__item__inner']/a[text()='«Центр управления согласиями»']")),
            headerItemLinkProductsSingleHints = $(byXpath("//a[@class='header__item__link' and text()='Продукты']" +
                    "/following-sibling::div/ul/li[@class='header__item__inner']/a[text()='«Подсказки» ']")),
            headerItemLinkProductsDaDaWebsite = $(byXpath("//a[@class='header__item__link' and text()='Продукты']" +
                    "/following-sibling::div/ul/li[@class='header__item__inner']/a[text()='DaData.ru ']")),
            headerItemLinkProductsAudit = $(byXpath("//a[@class='header__item__link' and text()='Продукты']" +
                    "/following-sibling::div/ul/li[@class='header__item__inner']/a[text()='Аудит']")),
            headerItemLinkProductsMasker = $(byXpath("//a[@class='header__item__link' and text()='Продукты']" +
                    "/following-sibling::div/ul/li[@class='header__item__inner']/a[text()='«Маскировщик»']"));

    private final SelenideElement headerItemLinkCompanyTeam = $(byXpath("//a[@class='header__item__link' and text()='Компания']" +
                    "/following-sibling::div/ul/li[@class='header__item__inner']/a[text()='Команда']")),
            headerItemLinkCompanyClients = $(byXpath("//a[@class='header__item__link' and text()='Компания']" +
                    "/following-sibling::div/ul/li[@class='header__item__inner']/a[text()='Клиенты']")),
            headerItemLinkCompanyCareer = $(byXpath("//a[@class='header__item__link' and text()='Компания']" +
                    "/following-sibling::div/ul/li[@class='header__item__inner']/a[text()='Карьера']")),
            headerItemLinkCompanySocial = $(byXpath("//a[@class='header__item__link' and text()='Компания']" +
                    "/following-sibling::div/ul/li[@class='header__item__inner']/a[text()='Социальные проекты']"));

    private final SelenideElement changeToDifferentLanguageSelector = $(byXpath("//*[contains(@class, \"header__lang\")]/a"));

    private final SelenideElement pageTitle = $("title");

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
        headerProducts.click();
        return this;
    }

    @Step("Check the 'Products' top bar content")
    public MainPage checkProductsTopBarTextsAndLinks() {
        headerItemLinkProductsFactor.shouldBe(visible);
        headerItemLinkProductsSingleClient.shouldBe(visible);
        headerItemLinkProductsSingleAddress.shouldBe(visible);
        headerItemLinkProductsKnowYourCustomer.shouldBe(visible);
        headerItemLinkProductsEcosystemClient.shouldBe(visible);
        headerItemLinkProductsConsentManagementCenter.shouldBe(visible);
        headerItemLinkProductsSingleHints.shouldBe(visible);
        headerItemLinkProductsDaDaWebsite.shouldBe(visible);
        headerItemLinkProductsAudit.shouldBe(visible);
        headerItemLinkProductsMasker.shouldBe(visible);
        return this;
    }

    @Step("Open the 'Company' top bar")
    public MainPage openCompanyTopBar() {
        headerCompany.click();
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

    @Step("Check the language = {language}")
    public MainPage checkLanguage(String language) {
        switch (language.toUpperCase()) {
            case "RU": {
                pageTitle.shouldHave(innerText("HFLabs — умные решения data quality и master data management"));
                break;
            }
            case "EN": {
                pageTitle.shouldHave(innerText("HFLabs - smart data quality and master data management solution"));
                break;
            }
        }
        return this;
    }

    @Step("Change the language")
    public MainPage changeLanguage() {
        changeToDifferentLanguageSelector.click();
        return this;
    }
}
