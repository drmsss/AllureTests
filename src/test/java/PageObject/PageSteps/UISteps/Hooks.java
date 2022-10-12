package PageObject.PageSteps.UISteps;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.qameta.allure.selenide.AllureSelenide;

public class Hooks {

    @Before
    public static void setDriverFromProps() {
        Configuration.startMaximized = true;
    }

    @After
    public void driverClose() {
        WebDriverRunner.closeWebDriver();
    }
}
