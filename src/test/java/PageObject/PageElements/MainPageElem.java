package PageObject.PageElements;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class MainPageElem {
    public static SelenideElement projectsButton = $x("//a[@title='Просмотр недавних проектов или списка всех проектов']");
    public static SelenideElement testProjectLink = $x("//a[@href='/browse/TEST' and @id='admin_main_proj_link_lnk']");
}
