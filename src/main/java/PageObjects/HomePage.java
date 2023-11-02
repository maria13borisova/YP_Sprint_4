package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class HomePage {
    // WebDriver
    private WebDriver driver;

    // Активный ответ (на странице одновременно виден только один ответ)
    private By currentAnswer = By.xpath("//div[@class='accordion__panel' and not(@hidden)]/p");
    // Элемент списка вопросов
    private By questonsDiv = By.xpath("//div[@class='Home_FourPart__1uthg']");
    // кнопка принятия использования Cookie
    private By acceptCookieButton = By.xpath("//button[@class='App_CookieButton__3cvqF']");

    // кнопка "Заказать" вверху страницы
    private By orderButtonUp = By.xpath("//button[@class='Button_Button__ra12g']");
    // кнопка "Заказать" внизу страницы
    private By orderButtonDown = By.xpath("//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");


    // Конструктор класса
    public HomePage(WebDriver driver){
        this.driver = driver;
    }

    //Кликнуть по элементу
    private void clickOnQuestionElement(By elementLocator){
        driver.findElement(elementLocator).click();
    }

    // Вернуть текст ответа
    public String getAnswerText(){
        String answer = driver.findElement(currentAnswer).getText();
        return answer;
    }

    // кликнуть на вопрос по номеру, вернуть ответ
    public void clickQuestion(String questionNumber){
        By questionLocator = By.xpath("//div[@data-accordion-component='AccordionItem'][" + questionNumber + "]");
        driver.findElement(questionLocator).click();
    }


    //получить элемент с вопросами
    public WebElement getQuestionsElement(){
        return driver.findElement(questonsDiv);
    }

    // получить нижнюю кнопку "Заказать"
    public WebElement getOrderButtonDownElement(){
        return driver.findElement(orderButtonDown);
    }

    // нажать кнопку принятия куки
    public void acceptCookies(){
        driver.findElement(acceptCookieButton).click();
    }

    //Клик по кнопке Заказать сверху и снизу
    public void clickUpOrderButton(){
        driver.findElement(orderButtonUp).click();
    }

    //Клик по кнопке Заказать сверху и снизу
    public void clickDownOrderButton(){
        driver.findElement(orderButtonDown).click();
    }

}
