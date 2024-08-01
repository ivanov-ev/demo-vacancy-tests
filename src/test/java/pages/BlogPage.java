package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class BlogPage {

    private final SelenideElement searchInput = $(byXpath("//input[contains(@class, 'Search_input')]")),
            searchButton = searchInput.$(byXpath("./following-sibling::button"));

    @Step("Open the blog page")
    public BlogPage openPage() {
        open("/blog");
        return this;
    }

    @Step("Enter a search query and click 'Search'")
    public void useSearch(String searchQuery) {
        searchInput.sendKeys(searchQuery);
        searchButton.click();
    }
}
