import PageObjects.HomePage;
import PageObjects.OrderPage;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;



public class PlaceOrder {
    private WebDriver driver;
    private HomePage homePage;
    private OrderPage orderPage;

    //наборы данных для тестирования
    String[] firstArgumentsSet = new String[]{
            "Василий", "Васильев",
            "Москва, Цв. Бульвар, д. 4", "Курская",
            "89859600606",
            //вторая страница
            "22", "двое суток",
            "grey", "привезите в первой половине дня"
    };

    String[] secondArgumentsSet = new String[]{
            "Ольга", "Петрова",
            "Москва, Широкая ул., д.25", "Медведково",
            "89859500505",
            //вторая страница
            "9", "трое суток",
            "black", ""
    };

    @Before
    public void openHomePage() {
        ChromeOptions options = new ChromeOptions();
        //options.addArguments("--no-sandbox", "--disable-dev-shm-usage");
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.get("https://qa-scooter.praktikum-services.ru/");
        homePage = new HomePage(driver);
        orderPage = new OrderPage(driver);
        homePage.acceptCookies();
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(By.className("accordion")));
    }

    // заполнение заказа №1 по верхней кнопке
    @Test
    public void firstPlaceOrderByUpButton(){
        homePage.clickUpOrderButton();
        fillAndConfirmOrder(firstArgumentsSet);
        Assert.assertTrue(checkOrderCompleted());
    }
    // заполнение заказа №2 по верхней кнопке
    @Test
    public void secondPlaceOrderByUpButton(){
        homePage.clickUpOrderButton();
        fillAndConfirmOrder(secondArgumentsSet);
        Assert.assertTrue(checkOrderCompleted());
    }


    // заполнение заказа №1 по нижней кнопке
    @Test
    public void firstPlaceOrderByDownButton(){
        homePage.clickDownOrderButton();
        fillAndConfirmOrder(firstArgumentsSet);
        Assert.assertTrue(checkOrderCompleted());
    }
    // заполнение заказа №2 по нижней кнопке
    @Test
    public void secondPlaceOrderByDownButton(){
        homePage.clickDownOrderButton();
        fillAndConfirmOrder(secondArgumentsSet);
        Assert.assertTrue(checkOrderCompleted());
    }





    // заполнить и подтвердить данные заказа
    private void fillAndConfirmOrder(String[] argumentsSet){
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", orderPage.getNextAndOrderButton());
        orderPage.fillOrderPage1(argumentsSet[0], argumentsSet[1], argumentsSet[2], argumentsSet[3], argumentsSet[4]);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", orderPage.getNextAndOrderButton());
        orderPage.fillOrderPage2(argumentsSet[5], argumentsSet[6],argumentsSet[7], argumentsSet[8]);
        orderPage.clickConfirmOrderButton();
    }

    //
    private boolean checkOrderCompleted(){
            try {
                WebElement confirm = orderPage.getOrderCreatedConfirmHeader();
                return confirm.isDisplayed();
            } catch (NoSuchElementException e) {
                return false;
            }
    }


    @After
    public void closeBrowser(){
        // закрыть браузер
        driver.quit();
    }
}
