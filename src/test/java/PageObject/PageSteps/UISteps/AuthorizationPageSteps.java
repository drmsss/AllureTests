package PageObject.PageSteps.UISteps;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.qameta.allure.Step;

import static PageObject.PageElements.AuthorizationPageElem.*;


public class AuthorizationPageSteps {

    @Step("Пользователь перешел по ссылке {string}")
    @Given("Пользователь перешел по ссылке {string}")
    public static void openUrl(String url){
        Selenide.open(url);
    }

    @Step("Пользователь авторизовался с логином {string} и паролем {string}")
    @Then("Пользователь авторизовался с логином {string} и паролем {string}")
    public static void login (String login, String password){
        loginField.shouldBe(Condition.visible).sendKeys(login);
        passwordField.shouldBe(Condition.visible).sendKeys(password);
        loginButton.shouldBe(Condition.enabled).click();
    }

}
