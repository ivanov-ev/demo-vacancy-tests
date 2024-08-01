package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class MainPage {

    private final SelenideElement headerProducts = $(byXpath("(//*[@class='header__item__link'])[2]")),
            headerCompany = $(byXpath("(//*[@class='header__item__link'])[5]"));

    private final SelenideElement submitRequestButton = $(byXpath("//*[@class='header__btn__wrap js-openSend']"));

    final String partialPathToMenuItem = "./following-sibling::div/ul/li[@class='header__item__inner']";

    private final SelenideElement getHeaderItemLinkProductsFactor = headerProducts.$(byXpath(partialPathToMenuItem + "[1]/a")),
            getHeaderItemLinkProductsSingleClient = headerProducts.$(byXpath(partialPathToMenuItem + "[2]/a")),
            getHeaderItemLinkProductsSingleAddress = headerProducts.$(byXpath(partialPathToMenuItem + "[3]/a")),
            getHeaderItemLinkProductsKnowYourCustomer = headerProducts.$(byXpath(partialPathToMenuItem + "[4]/a")),
            getHeaderItemLinkProductsEcosystemClient = headerProducts.$(byXpath(partialPathToMenuItem + "[5]/a")),
            getHeaderItemLinkProductsConsentManagementCenter = headerProducts.$(byXpath(partialPathToMenuItem + "[6]/a")),
            getHeaderItemLinkProductsSingleHints = headerProducts.$(byXpath(partialPathToMenuItem + "[7]/a")),
            getHeaderItemLinkProductsDaDaWebsite = headerProducts.$(byXpath(partialPathToMenuItem + "[8]/a")),
            getHeaderItemLinkProductsAudit = headerProducts.$(byXpath(partialPathToMenuItem + "[9]/a")),
            getHeaderItemLinkProductsMasker = headerProducts.$(byXpath(partialPathToMenuItem + "[10]/a"));

    private final SelenideElement headerItemLinkCompanyTeam = headerCompany.$(byXpath(partialPathToMenuItem + "[1]/a")),
            headerItemLinkCompanyClients = headerCompany.$(byXpath(partialPathToMenuItem + "[2]/a")),
            headerItemLinkCompanyCareer = headerCompany.$(byXpath(partialPathToMenuItem + "[3]/a")),
            headerItemLinkCompanySocial = headerCompany.$(byXpath(partialPathToMenuItem + "[4]/a"));

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
