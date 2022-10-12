package PageObject.PageSteps.UISteps;

import com.codeborne.selenide.Condition;
import io.cucumber.java.en.Then;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;

import static PageObject.PageElements.ProjectPageElem.statusLabel;
import static PageObject.PageElements.TaskScreenElem.*;
import static com.codeborne.selenide.Selenide.sleep;

public class TaskScreenSteps {

    @Then("Пользователь переводит задачу в статус В РАБОТЕ")
    public static void setStatusInProgress() {
        inProgressButton.shouldBe(Condition.enabled).click();
        closePopupButton.shouldBe(Condition.visible).click();
        sleep(2000); // ждем обновления статуса
        Assertions.assertEquals("В РАБОТЕ", statusLabel.text());
    }
    @Then("Пользователь переводит задачу в статус ВЫПОЛНЕНО")
    public static void setStatusDone() {
        businessProcessButton.shouldBe(Condition.enabled).click();
        doneButton.shouldBe(Condition.visible).shouldBe(Condition.enabled).click();
        sleep(2000); // ждем обновления статуса
        Assertions.assertEquals("ГОТОВО", statusLabel.text());
}

}
