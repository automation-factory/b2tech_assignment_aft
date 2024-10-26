package page.sportsbook;

import annotation.Page;
import com.codeborne.selenide.SelenideElement;
import org.apache.commons.lang3.NotImplementedException;
import page.AbstractPage;

import static constant.Messages.NOT_IMPLEMENTED;

@Page() //EMPTY url will call baseUrl only
public class HomePage extends AbstractPage {

    @Override
    public SelenideElement getPageTitleElement() {
        throw new NotImplementedException(NOT_IMPLEMENTED);
    }

    @Override
    public String getPageTitleText() {
        throw new NotImplementedException(NOT_IMPLEMENTED);
    }
}
