package tests;

import components.BlogSearchResultsComponent;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.BlogPage;

@Tag("smoke_tests")
@Feature("hflabs.ru")
@Story("Blog page tests")
@Owner("ivanov-ev")
@Severity(SeverityLevel.MINOR)
@Link(name = "https://hflabs.ru/blog/", url = "https://hflabs.ru/blog/")
@DisplayName("Blog page tests")
public class BlogPageTests extends TestBase {
    final BlogPage blogPage = new BlogPage();
    final BlogSearchResultsComponent blogSearchResultsComponent = new BlogSearchResultsComponent();

    @Test
    @DisplayName("Open the blog page, use the search field, and check found articles")
    void blogSearchTest() {
        String searchQuery = "Банк";
        blogPage.openPage()
                .useSearch(searchQuery);
        blogSearchResultsComponent.checkSuccessfulSearchTitle(searchQuery);
        blogSearchResultsComponent.checkTitlesOrDescriptionsContainSearchText(blogSearchResultsComponent.
                getFoundArticlesFromSinglePage(), searchQuery, true);
    }

    @Test
    @DisplayName("Open the blog page, use the search field, and check that nothing found")
    void blogSearchTestN() {
        String searchQuery = "TextNotSupposedToBeFound";
        blogPage.openPage()
                .useSearch(searchQuery);
        blogSearchResultsComponent.checkNothingFoundSearchTitle();
        blogSearchResultsComponent.checkTitlesOrDescriptionsContainSearchText(blogSearchResultsComponent.
                getFoundArticlesFromSinglePage(), searchQuery, false);
    }
}
