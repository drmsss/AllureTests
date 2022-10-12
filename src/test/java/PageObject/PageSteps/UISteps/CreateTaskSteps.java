package PageObject.PageSteps.UISteps;

import PageObject.PageElements.CreateTaskPage;
import io.cucumber.java.en.When;
import io.qameta.allure.Step;

import static PageObject.PageElements.CreateTaskPage.*;

public class CreateTaskSteps {

    @Step("Пользователь создал задачу типа {string} с названием {string} и описанием {string}")
    @When("Пользователь создал задачу типа {string} с названием {string} и описанием {string}")
    public static void createTask(String taskType, String summary, String description) {
        taskTypeSelector.click();
        typeTask.sendKeys(taskType);
        summaryField.setValue(summary);
        textButton.click();
        descriptionField.sendKeys(description);
        assignMeButton.click();
        createButton.click();
    }


}
