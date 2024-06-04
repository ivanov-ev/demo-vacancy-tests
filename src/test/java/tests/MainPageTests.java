package tests;

import components.SubmitRequestComponent;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.MainPage;

@Tag("smoke_tests")
@Feature("hflabs.ru")
@Story("Main page tests")
@Owner("ivanov-ev")
@Severity(SeverityLevel.NORMAL)
@Link(name = "https://hflabs.ru/", url = "https://hflabs.ru/")
@DisplayName("Main page tests")
public class MainPageTests extends TestBase {

    MainPage mainPage = new MainPage();
    SubmitRequestComponent submitRequestComponent = new SubmitRequestComponent();

    @Test
    @DisplayName("Click the close icon in the cookies bottom bar to close the bar")
    void closeCookiesBarTest() {
        mainPage.openPage()
                .closeCookiesBar()
                .cookiesBarIsHidden();
    }

    @Test
    @DisplayName("Click the request submission button to open the form, and check the form is displayed correctly and can be closed")
    void checkFieldsOnSubmitRequestFormTest() {
        mainPage.openPage()
                .clickRequestSubmissionButton();
        submitRequestComponent.checkSubmissionForm()
                .closeSubmissionForm()
                .checkSubmissionFormIsClosed();
    }

    @Test
    @DisplayName("Change the language, and check the language is changed on the main page")
    void changeLanguageTest() {
        mainPage.openPage()
                .changeLanguageToEn()
                .checkLanguageIsEn()
                .changeLanguageToRu()
                .checkLanguageIsRu();
    }

    @Test
    @DisplayName("Click the 'Products' item in the top bar, and check its sub-menu's items and links")
    void checkProductsTopBarContentTest() {
        mainPage.openPage()
                .openProductsTopBar()
                .checkProductsTopBarTextsAndLinks();
    }

    @Test
    @DisplayName("Click the 'Company' item in the top bar, and check its sub-menu's items and links")
    void checkCompanyTopBarContentTest() {
        mainPage.openPage()
                .openCompanyTopBar()
                .checkCompanyTopBarTextsAndLinks();
    }
}
