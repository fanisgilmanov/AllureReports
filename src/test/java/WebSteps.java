import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static org.openqa.selenium.By.linkText;
import static org.openqa.selenium.By.partialLinkText;

public class WebSteps {
    @Step("Открываем главную страницу")
    public void openMainPage() {
        Selenide.open("https://github.com");
    }

    @Step("Ищем репозиторий по имени:  {repository}")
    public void searchRepository(String repository){
        $(".header-search-input").setValue(repository).pressEnter();
    }

    @Step("В результатах поиска переходим по ссылке репозитория :  {repository}")
    public void openRepository(String repository){
        $(linkText(repository)).click();
    }

    @Step("Открываем таб Issues")
    public void openTabRepository(){
        $(partialLinkText("Issues")).click();
    }

    @Step("Проверяем что существует Issue c номером: {issueNumber}")
    public void shouldSeeIssueWithNumber(Integer issueNumber) {
        $(withText("#" + issueNumber)).should(Condition.exist);
    }

}
