package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class OrderPage {
    WebDriver driver;


    //поля для ввода (1-я часть)
    private By firstnameInput = By.xpath("//input[@placeholder='* Имя']");
    private By lastnameInput = By.xpath("//input[@placeholder='* Фамилия']");
    private By adressInput = By.xpath("//input[@placeholder='* Адрес: куда привезти заказ']");
    private By undergroundInput = By.xpath("//div[@class='select-search']//input");
    private By phoneInput = By.xpath("//input[@placeholder='* Телефон: на него позвонит курьер']");

    //кнопка Далее и Заказать на странице оформления заказа (локатор совпадает)
    private By nextAndOrderButton = By.xpath("//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");

    //поля для ввода 2-я часть
    // строка выбора даты и календарь
    private By dayInput = By.xpath("//input[@placeholder='* Когда привезти самокат']");
    //private By dayInCalendar = By.className("react-datepicker__day react-datepicker__day--023 react-datepicker__day--selected");
    private By dayInCalendar;

    //строка выбора срока аренды и div с вариантами выбора
    private By rentPeriodInput = By.className("Dropdown-root");
    private By rentPeriod;


    // инпуты с вариантами выбора цветов
    private By colorBlackInput = By.xpath("//input[@id='black']");
    private By colorGreyInput = By.xpath("//input[@id='grey']");

    // комментарий для курьера
    private By commentToCourier = By.xpath("//input[@placeholder='Комментарий для курьера']");

    // Кнопка подтверждения заказа в popup
    private By yesOrderButton = By.xpath("//button[@class='Button_Button__ra12g Button_Middle__1CSJM' and text() = 'Да']");


    // заголовок после успешного оформления заказа
    private By orderCreatedConfirmHeader = By.xpath("//div[@class='Order_ModalHeader__3FDaJ' and text()='Заказ оформлен']");


    // конструктор
    public OrderPage(WebDriver driver){
        this.driver = driver;
    }



    /* Заполнение данных заказа */
    //Ввести имя
    private void setFirstnameInput(String name){
        driver.findElement(firstnameInput).sendKeys(name);
    }
    //Ввести фамилию
    private void setLastnameInput(String lastname){
        driver.findElement(lastnameInput).sendKeys(lastname);
    }

    //Ввести адрес
    private void setAdressInput(String adress){
        driver.findElement(adressInput).sendKeys(adress);
    }

    //Выбрать станцию метро
    private void setUndergroundInput(String undergroundName){
        driver.findElement(undergroundInput).sendKeys(undergroundName + Keys.DOWN + Keys.ENTER);
        //driver.findElement(undergroundInput).sendKeys(Keys.DOWN);
        //driver.findElement(undergroundInput).sendKeys(Keys.ENTER);
    }

    //Ввести номер телефона
    private void setPhoneInput(String phone){
        driver.findElement(phoneInput).sendKeys(phone);
    }

    // Нажать кнопку "Далее" или "Заказать" на странице оформления заказа
    private void pressNextAndOrderButton(){
        driver.findElement(nextAndOrderButton).click();
    }

    // получить кнопку "Далее" или "Заказать" на странице заказа
    public WebElement getNextAndOrderButton(){
        return driver.findElement(nextAndOrderButton);
    }

    // развернуть календарь
    private void clickTodayInput(){
        driver.findElement(dayInput).click();
    }

    // кликнуть по дате в календаре
    private void clickToDateInCalendar(String dayOfMonth){
        int intDayOfMonth = Integer.parseInt(dayOfMonth);
        // отсекаем некорректные значения
        if(intDayOfMonth > 0 && intDayOfMonth < 32) {
            //строка для выбора дня в календаре
            //String stringDayOfMonth;
            //добавляем 0 перед одинарным числом
            if (intDayOfMonth < 10) {
                dayOfMonth = "0" + String.valueOf(intDayOfMonth);
            }
            // выбор элемента в календаре по введенному числу
            dayInCalendar = By.xpath("//div[@class='react-datepicker__day react-datepicker__day--0" + dayOfMonth + "']");
            // клик по числу в календаре
            driver.findElement(dayInCalendar).click();
        }
    }

    // выбрать срок аренды
    private void setRentPeriod(String value){
        driver.findElement(rentPeriodInput).click();
        rentPeriod = By.xpath("//div[@class='Dropdown-option' and text()='" + value + "']");
        driver.findElement(rentPeriod).click();
    }

    // выбрать цвет самоката
    private void checkScooterColor(String color){
        if(color == "black"){
            driver.findElement(colorBlackInput).click();
        }
        else {
            driver.findElement(colorGreyInput).click();
        }
    }

    // добавить комментарий для курьера
    private void addComment(String comment){
        driver.findElement(commentToCourier).sendKeys(comment);
    }


    //заполнить данные на первой странице заказа, нажать кнопку "Далее"
    public void fillOrderPage1(String name, String lastname, String adress, String undergroundName, String phone){
        setFirstnameInput(name);
        setLastnameInput(lastname);
        setAdressInput(adress);
        setUndergroundInput(undergroundName);
        setPhoneInput(phone);
        pressNextAndOrderButton();
    }
    //заполнить данные на второй странице заказа, нажать кнопку "Заказать"
    public void fillOrderPage2(String dayOfMonth, String period, String color, String comment){
        clickTodayInput();
        clickToDateInCalendar(dayOfMonth);
        setRentPeriod(period);
        checkScooterColor(color);
        addComment(comment);
        pressNextAndOrderButton();
    }

    // подтвердить заказ
    public void clickConfirmOrderButton(){
        driver.findElement(yesOrderButton).click();
    }

    // вернуть заголовок popup о подтвержденном заказе
    public WebElement getOrderCreatedConfirmHeader(){
        return driver.findElement(orderCreatedConfirmHeader);
    }

}
