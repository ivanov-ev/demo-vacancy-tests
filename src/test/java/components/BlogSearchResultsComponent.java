package components;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;

import java.util.HashMap;
import java.util.Map;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static tests.TestBase.logger;

public class BlogSearchResultsComponent {

    private final SelenideElement searchContainer = $(byXpath("//div[contains(@class, 'Search_container')]")),
            searchResultTitleText = searchContainer.$(byXpath("./preceding-sibling::div/div"));

    private final ElementsCollection cardTitles = searchContainer.$$(byXpath(".//div[contains(@class, 'Card_title')]"));

    final String pathFromTitleToDescription = "./following-sibling::div";

    @Step("Check the search result title text when some results are found")
    public void checkSuccessfulSearchTitle(String searchQuery) {
        String text = searchResultTitleText.innerText();
        Assertions.assertTrue(text.contains("По вашему запросу") && text.contains(searchQuery));
    }

    @Step("Check the search result title text when nothing is found")
    public void checkNothingFoundSearchTitle() {
        String text = searchResultTitleText.innerText();
        Assertions.assertTrue(text.contains("Мы ничего не нашли по этому запросу"));
    }

    @Step("Get articles from a single page of search results")
    public Map<String, String> getFoundArticlesFromSinglePage() {
        Map<String, String> titlesAndDescriptions = new HashMap<>();
        for (SelenideElement element : cardTitles) {
            element.shouldBe(visible);
            titlesAndDescriptions.put(element.getText(), element.$(byXpath(pathFromTitleToDescription)).getText());
        }
        return titlesAndDescriptions;
    }

    @Step("Check that titles or descriptions contain the search text")
    public void checkTitlesOrDescriptionsContainSearchText(Map<String, String> titlesAndDescriptions, String searchQuery, boolean mustContainSearchText) {
        for (Map.Entry<String, String> entry : titlesAndDescriptions.entrySet()) {
            boolean titleContainsSearchText = entry.getKey().toLowerCase().contains(searchQuery.toLowerCase());
            logger.info("Title = " + entry.getKey());
            boolean descriptionContainsSearchText = entry.getValue().toLowerCase().contains(searchQuery.toLowerCase());
            logger.info("Description = " + entry.getValue());
            Assertions.assertEquals(mustContainSearchText, titleContainsSearchText || descriptionContainsSearchText);
        }
    }
}
