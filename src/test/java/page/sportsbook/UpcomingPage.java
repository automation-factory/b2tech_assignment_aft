package page.sportsbook;

import annotation.Page;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.awaitility.Awaitility;
import org.hamcrest.Matchers;
import page.AbstractPage;
import ui.dto.Event;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;
import static org.awaitility.Durations.ONE_SECOND;
import static org.awaitility.Durations.TWO_MINUTES;

@Page(url = "sportsbook/upcoming")
public class UpcomingPage extends AbstractPage {

    private static final String SELECTED_SPORT_BOOK_SLIDER_MENU_ITEM = "//li[@class = 'au-s-s' and @id = '%s']//a/span[@id = '%s']";
    private final SelenideElement menuSportBookPreMatch = $x(SELECTED_SPORT_BOOK_SLIDER_MENU_ITEM.formatted("menusportbookprematch", "menusportbookprematch"));
    private final ElementsCollection eventRows = $$x("//tr[@class = 'event-row']");
    private final SelenideElement showMore = $x("//span[@auhtml = 'SportsBookEventTableInfiniteShowMore']");

    @Override
    public SelenideElement getPageTitleElement() {
        return menuSportBookPreMatch;
    }

    @Override
    public String getPageTitleText() {
        return "Upcoming";
    }

    public List<Event> getAllEvents() {
        List<Event> eventList = new ArrayList<>();
        eventRows.shouldHave(sizeGreaterThan(0))
                .forEach(eventRow -> {
                    var teamHome = eventRow.$x(".//div[@class = 'team-home']").getText();
                    var teamAway = eventRow.$x(".//div[@class = 'team-away']").getText();
                    List<Double> odds = eventRow.$$x(".//span[@class = 'odds none']").texts()
                            .stream()
                            .map(Double::parseDouble)
                            .toList();

                    var eventDto = Event.builder()
                            .teamHome(teamHome)
                            .teamAway(teamAway)
                            .odds(odds)
                            .build();

                    eventList.add(eventDto);
                });

        return eventList;
    }

    public List<Event> filterEventsByOddsRange(List<Event> allEvents, double fromOdd, double toOdd) {
        return allEvents.stream()
                .filter(event -> event.getOdds().stream().allMatch(odds -> odds >= fromOdd && odds <= toOdd))
                .collect(Collectors.toList());
    }

    public UpcomingPage clickShowMoreUntilIsVisible() {
        Awaitility.await("Click and wait for show more is not exist")
                .pollInSameThread()
                .atMost(TWO_MINUTES)
                .pollInterval(ONE_SECOND)
                .until(() -> {
                    if (showMore.is(clickable)) showMore.scrollIntoView(false).click();
                    return showMore.is(not(exist));
                }, Matchers.is(true));
        return this;
    }

}
