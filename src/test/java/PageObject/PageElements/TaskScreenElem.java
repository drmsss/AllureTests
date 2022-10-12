package PageObject.PageElements;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class TaskScreenElem {
    public static SelenideElement inProgressButton = $x("//span[text()='В работе']");
    public static SelenideElement businessProcessButton = $x("//span[text()='Бизнес-процесс']");
    public static SelenideElement closePopupButton = $x("//button[@class='aui-close-button']");
    public static SelenideElement doneButton = $x("//a[@role='menuitem']/span[text()='Выполнено']");

    public static SelenideElement taskSummary = $x("//h1[@id='summary-val']");
    public static SelenideElement taskDescription = $x("//div[@class='user-content-block']");
}
