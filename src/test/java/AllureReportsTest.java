
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;
import static io.qameta.allure.Allure.step;
import static org.openqa.selenium.By.linkText;
import static org.openqa.selenium.By.partialLinkText;

public class AllureReportsTest {
    private final String REPOSITORY = "fanisgilmanov/WorkToFile";
    private final Integer ISSUE_NUMBER = 1;

    @Test
    void selenideTest(){
        Selenide.open("https://github.com/");
        $(".header-search-input").setValue(REPOSITORY).pressEnter();
        $(linkText(REPOSITORY)).click();
        $(partialLinkText("Issues")).click();
        $(withText("#" + ISSUE_NUMBER )).should(exist);
    }



    @Test
    void lambdaStepTest(){
        SelenideLogger.addListener("allure", new AllureSelenide());
        step("Открываем главную страницу", () -> {
            Selenide.open("https://github.com/");
        });
        step("Ищем репозиторий по имени: " + REPOSITORY, () -> {
            $(".header-search-input").setValue(REPOSITORY).pressEnter();
        });
        step("В результатах поиска переходим по ссылке репозитория " + REPOSITORY, () -> {
            $(linkText(REPOSITORY)).click();
        });
        step("Открываем таб Issues", () -> {
            $(partialLinkText("Issues")).click();
        });
        step("Проверяем что существует Issue c номером " + ISSUE_NUMBER, () -> {
            $(withText("#" + ISSUE_NUMBER)).should(exist);
        });
    }

    @Test
    public void testAnnotatedSteps(){
        SelenideLogger.addListener("allure", new AllureSelenide());
        WebSteps webSteps = new WebSteps();

        webSteps.openMainPage();
        webSteps.searchRepository(REPOSITORY);
        webSteps.openRepository(REPOSITORY);
        webSteps.openTabRepository();
        webSteps.shouldSeeIssueWithNumber(ISSUE_NUMBER);
    }
}
