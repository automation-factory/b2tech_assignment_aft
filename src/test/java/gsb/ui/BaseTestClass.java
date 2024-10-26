package gsb.ui;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import config.Config;
import config.EnvironmentConfig;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.BeforeSuite;

import static io.qameta.allure.selenide.LogType.*;
import static java.util.logging.Level.INFO;

@Slf4j
public abstract class BaseTestClass {

    public static final EnvironmentConfig ENVIRONMENT_CONFIG = Config.environmentConfig();

    @BeforeSuite
    public void baseSetup() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide()
                .savePageSource(true)
                .includeSelenideSteps(true)
                .enableLogs(SERVER, INFO)
                .enableLogs(CLIENT, INFO)
                .enableLogs(DRIVER, INFO)
                .enableLogs(BROWSER, INFO)
                .screenshots(true));

        Configuration.baseUrl = ENVIRONMENT_CONFIG.baseUrl();
        Configuration.timeout = ENVIRONMENT_CONFIG.timeout();
        Configuration.headless = ENVIRONMENT_CONFIG.isHeadless();

        log.info("Base URL: {}", ENVIRONMENT_CONFIG.baseUrl());
        log.info("Timeout: {}", ENVIRONMENT_CONFIG.timeout());
        log.info("Is Headless: {}", ENVIRONMENT_CONFIG.isHeadless());
    }

}
