package page;

import annotation.Page;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import lombok.NonNull;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.net.URI;
import java.time.Duration;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static java.lang.String.format;
import static java.time.Duration.ZERO;
import static org.apache.commons.lang3.StringUtils.SPACE;
import static org.awaitility.Awaitility.await;
import static org.awaitility.Durations.ONE_HUNDRED_MILLISECONDS;
import static org.awaitility.Durations.ONE_MINUTE;
import static org.hamcrest.Matchers.is;

@Slf4j
public abstract class AbstractPage {

    private static final String OPEN_PAGE = "Open page with url: [{}]";
    private static final String VALIDATE_PAGE_TITLE = "Validate page title text [{}]";
    private static final String VALIDATE_URL = "Validate url [{}]";

    public abstract SelenideElement getPageTitleElement();

    public abstract String getPageTitleText();

    @SneakyThrows
    private String createFullUrl(String baseUrl, String urlPath) {
        var fullUrl = baseUrl + "/" + urlPath;
        return new URI(fullUrl).normalize().toURL().toString();
    }

    private String getFullUrl() {
        return createFullUrl(getBaseUrl(), getUrlPath());
    }

    private String getBaseUrl() {
        return baseUrl;
    }

    private String getUrlPath() {
        return this.getClass().getAnnotation(Page.class).url();
    }

    private void open(String url) {
        Selenide.open(url);
        log.warn(OPEN_PAGE, url);
    }

    public void open() {
        var fullUrl = getFullUrl();

        if (fullUrl.isEmpty())
            throw new IllegalArgumentException("Current page full full URL was empty!");

        open(fullUrl);
    }

    public <T extends AbstractPage> T validatePageTitle() {
        var pageTitleText = getPageTitleText();
        getPageTitleElement()
                .as(format("wait for page title with text [%s]", pageTitleText))
                .shouldHave(text(pageTitleText), Duration.ofMinutes(1));

        log.info(VALIDATE_PAGE_TITLE, pageTitleText);
        return (T) this;
    }

    public <T> T validateUrl(@NonNull Class<T> targetClass) {
        validateUrl();
        return targetClass.cast(this);
    }

    public <T extends AbstractPage> T validateUrl() {
        var fullUrl = getFullUrl();

        if (fullUrl.contains("%s"))
            throw new IllegalArgumentException("Current page can't be opened due to the dynamic URL params!");

        await(VALIDATE_URL.concat(SPACE + fullUrl))
                .pollInSameThread()
                .atMost(ONE_MINUTE)
                .pollInterval(ONE_HUNDRED_MILLISECONDS)
                .pollDelay(ZERO)
                .ignoreExceptions()
                .until(() -> getCurrentUrl().equalsIgnoreCase(fullUrl), is(true));

        log.info(VALIDATE_URL, fullUrl);
        return (T) this;
    }

    public String getCurrentUrl() {
        return getWebDriver().getCurrentUrl();
    }

}
