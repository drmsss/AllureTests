package PageObject.PageSteps.UISteps;

import com.codeborne.selenide.Condition;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;

import static PageObject.PageElements.ProjectPageElem.*;
import static PageObject.PageElements.TaskScreenElem.taskDescription;
import static PageObject.PageElements.TaskScreenElem.taskSummary;

public class ProjectPageSteps {
    @Step("Пользователь открыл все задачи проекта")
    @Then("Пользователь открыл все задачи проекта")
    public static void openAllTasks() {
        allTasksButton.click();
    }

    @Step("Проверить количество задач в проекте TEST")
    @When("Проверить количество задач в проекте TEST")
    public static void getTasksCount() {
        String tasksCount = tasksCountLabel.text();
        System.out.println("Количество задач в проекте: " + tasksCount.substring(tasksCount.lastIndexOf(" ")+1));
    }

    @Step("Пользователь нашел задачу с названием {string}")
    @Then("Пользователь нашел задачу с названием {string}")
    public static void searchTask(String taskSummary) {
        searchField.setValue(taskSummary);
        searchButton.click();
    }

    @Step("Проверить статус задачи")
    @Then("Проверить статус задачи")
    public static void getTaskStatus() {
        String taskStatus = statusLabel.text();
        System.out.println("Статус задачи: " + taskStatus);
        Assertions.assertEquals("СДЕЛАТЬ", taskStatus);
    }

    @Step("Проверить затронутую версию в задаче")
    @Then("Проверить затронутую версию в задаче")
    public static void getTaskAffectedVersion() {
        String taskAffectedVersion = affectedVersionsValue.text();
        System.out.println("Затронута версия: " + taskAffectedVersion);
        Assertions.assertEquals("Version 2.0", taskAffectedVersion);
    }

    @Step("Пользователь открыл окно создания задачи")
    @Given("Пользователь открыл окно создания задачи")
    public static void clickCreateButton() {
        createTaskButton.shouldBe(Condition.enabled).click();
    }

    @Step("Пользователь открыл созданную задачу")
    @And("Пользователь открыл созданную задачу")
    public static void goToCreatedTask() {
        taskCreationLabel.shouldBe(Condition.visible).click();
        Assertions.assertEquals("Test", projectNameValue.text());
        Assertions.assertEquals("Test bug", taskSummary.text());
        Assertions.assertEquals("Test bug description", taskDescription.text());
    }
}
