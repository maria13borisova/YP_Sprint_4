import PageObjects.HomePage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(Parameterized.class)

public class CheckFAQParameterized {
    private WebDriver driver;
    private String expectedValue;
    private String questionIndex;

    public CheckFAQParameterized(String questionIndex, String expectedValue) {
        this.expectedValue = expectedValue;
        this.questionIndex = questionIndex;

    }

    // параметры тестов
    @Parameterized.Parameters
    public static Object[][] getStringsAnswers() {
        return new Object[][] {
                { "1","Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
                { "2", "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."},
                { "3", "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."},
                { "4", "Только начиная с завтрашнего дня. Но скоро станем расторопнее."},
                { "5", "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."},
                { "6", "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."},
                { "7", "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."},
                { "8", "Да, обязательно. Всем самокатов! И Москве, и Московской области."},
        };
    }

    @Before
    public void openHomePage(){
        ChromeOptions options = new ChromeOptions();
        //options.addArguments("--no-sandbox", "--disable-dev-shm-usage");
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.get("https://qa-scooter.praktikum-services.ru/");
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(By.className("accordion")));
    }

    @Test
    public void checkFAQIsCorrect(){
        HomePage homePage = new HomePage(driver);
        //принять куки
        homePage.acceptCookies();
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", homePage.getQuestionsElement());
        homePage.clickQuestion(questionIndex);
        String answerFromHomePage = homePage.getAnswerText();
        assertThat(answerFromHomePage, is(expectedValue));
    }


    @After
    public void closeBrowser(){
        // закрыть браузер
        driver.quit();
    }
}
