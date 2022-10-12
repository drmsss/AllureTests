package PageObject.PageElements;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class ProjectPageElem {
    public static SelenideElement allTasksButton= $x("//a[contains(text(), 'Посмотреть все задачи и фильтры')]");
    public static SelenideElement tasksCountLabel = $x("//div[@class='showing']/child::span");
    public static SelenideElement searchField = $x("//input[@id='searcher-query']");
    public static SelenideElement searchButton = $x("//button[contains(text(), 'Поиск')]");
    public static SelenideElement statusLabel = $x("//span[@id='status-val']/child::span");
    public static SelenideElement affectedVersionsValue = $x("//span[@id='versions-field']/child::span");
    public static SelenideElement createTaskButton = $x("//a[@id='create_link']");
    public static SelenideElement taskCreationLabel = $x("//a[@class='issue-created-key issue-link']");
    public static SelenideElement projectNameValue = $x("//a[@id='project-name-val']");
}
