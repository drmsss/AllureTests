package PageObject.PageElements;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class CreateTaskPage {
    public static SelenideElement taskTypeSelector = $x("//div[@id='issuetype-single-select']//child::span");
    public static SelenideElement typeTask = $x("//input[@id='issuetype-field']");
    public static SelenideElement summaryField = $x("//input[@id='summary']");
    public static SelenideElement textButton = $x("//button[text()='Текст']");
    public static SelenideElement descriptionField = $x("//textarea[@id='description']");
    public static SelenideElement assignMeButton = $x("//button[text()='Назначить меня']");
    public static SelenideElement createButton = $x("//input[@value='Создать']");

}
