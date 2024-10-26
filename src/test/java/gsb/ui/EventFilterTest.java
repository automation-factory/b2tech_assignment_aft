package gsb.ui;

import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import org.testng.annotations.Test;
import page.sportsbook.HomePage;
import page.sportsbook.UpcomingPage;

@Slf4j
public class EventFilterTest extends BaseTestClass {

    @Test
    public void validateFilteredEventsWithOdds() {
        new HomePage().open(); //without page validation

        var allEvents = new UpcomingPage()
                .validatePageTitle()
                .validateUrl(UpcomingPage.class) //validate page redirection after accessing base url
                .clickShowMoreUntilIsVisible()
                .getAllEvents();

        //TODO: There are not enough technical details! Current code collects all the odds from the row and check if all of them fits the range.

        log.info("Filter all events, which have odds value between 1.5 - 3.34");
        var filteredEvents = new UpcomingPage().filterEventsByOddsRange(allEvents, 1.5, 3.34);

        log.info("Verify that events list and odds list are not empty");
        Assert.assertFalse(filteredEvents.isEmpty(), "No events found.");
        Assert.assertTrue(filteredEvents.stream().noneMatch(dto -> dto.getOdds().isEmpty()), "Some events do not have odds listed.");

        filteredEvents.forEach(event -> log.info("Home: [{}] \nAway: [{}] \nOdds: [{}]", event.getTeamHome(), event.getTeamAway(), event.getOdds()));
    }

}
