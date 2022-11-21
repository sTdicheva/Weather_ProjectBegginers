import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;

public class SDichevaTest {
    @Test
    public void testH2TagText_WhenSearchingCityCountry() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "D:\\Java_code\\ChromeDriver_107v\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        String cityName = "Paris";
        String expectedResult = "Paris, FR";

        driver.get(url);
        Thread.sleep(4000);

        WebElement searchCityField = driver.findElement(
                By.xpath("//div[@id = 'weather-widget']//input[@placeholder = 'Search city']")
        );
        searchCityField.click();

        searchCityField.sendKeys(cityName);
        Thread.sleep(4000);

        WebElement searchButton = driver.findElement(By.xpath("//button[@type='submit']"));
        searchButton.click();
        Thread.sleep(4000);

        WebElement searchParisFranceInDropdown = driver.findElement(
                By.xpath("//ul[@class = 'search-dropdown-menu'] /li/span[text() = 'Paris, FR ']")
        );
        Thread.sleep(4000);

        searchParisFranceInDropdown.click();
        WebElement h2CityNameHeader = driver.findElement(By.xpath("//div[@id = 'weather-widget']//h2"));
        Thread.sleep(4000);
        String actualResult = h2CityNameHeader.getText();

        Assert.assertEquals(actualResult, expectedResult);

        driver.quit();
    }

    @Test
    public void testGuideMenuCheckTitleAndUrl() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "D:\\Java_code\\ChromeDriver_107v\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        String expectedResult = "OpenWeatherMap API guide - OpenWeatherMap";
        String expectedUrl = "https://openweathermap.org/guide";

        driver.get(url);
        Thread.sleep(6000);

        WebElement guideMenu = driver.findElement(By.xpath("//body//nav/ul//div/ul/li/a[@href= '/guide']"));
        guideMenu.click();

        String actualResult = driver.getTitle();
        String actualUrl = driver.getCurrentUrl();

        Assert.assertEquals(actualResult, expectedResult);
        Assert.assertEquals(actualUrl, expectedUrl);

        driver.quit();
    }

    @Test
    public void checkFarTemp() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "D:\\Java_code\\ChromeDriver_107v\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        String expectedResult = "F";

        driver.get(url);
        Thread.sleep(5000);

        WebElement buttonFahrenheit = driver.findElement(
                By.xpath("//div[@id = 'weather-widget']//div[text() = 'Imperial: °F, mph']")
        );
        buttonFahrenheit.click();

        WebElement textWithFahrenheit = driver.findElement(
                By.xpath("//span[@class = 'heading' and text() [contains(.,'F')]]")
        );

        String actualResult = textWithFahrenheit.getText();
        Assert.assertEquals(actualResult.substring(actualResult.length() - 1), expectedResult);

        driver.quit();
    }

    @Test
    public void checkCookies() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "D:\\Java_code\\ChromeDriver_107v\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        String expectedResult = "We use cookies which are essential for the site to work. " +
                "We also use non-essential cookies to help us improve our services. " +
                "Any data collected is anonymised. " +
                "You can allow all cookies or manage them individually.";
        String expectedResult1 = "Allow all";
        String expectedResult2 = "Manage cookies";

        driver.get(url);
        Thread.sleep(4000);

        WebElement coockiesBarText = driver.findElement(By.xpath("//p[@class='stick-footer-panel__description']"));
        String actualResult = coockiesBarText.getText();

        WebElement buttonAllowAll = driver.findElement(By.xpath("//button[@class = 'stick-footer-panel__link']"));
        String actualResult1 = buttonAllowAll.getText();

        WebElement buttonManageCookies = driver.findElement(By.xpath("//a[@class = 'stick-footer-panel__link']"));
        String actualResult2 = buttonManageCookies.getText();

        Assert.assertEquals(actualResult, expectedResult);
        Assert.assertEquals(actualResult1, expectedResult1);
        Assert.assertEquals(actualResult2, expectedResult2);

        driver.quit();
    }

    @Test
    public void checkDropDown() {
        System.setProperty("webdriver.chrome.driver", "D:\\Java_code\\ChromeDriver_107v\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        String expectedResult = "FAQ";
        String expectedResult1 = "How to start";
        String expectedResult2 = "Ask a question";

        WebElement dropDownSearch = driver.findElement(By.xpath("//div[@id = 'support-dropdown']"));
        dropDownSearch.click();

        WebElement faq = driver.findElement(By.xpath("//a[@href = \"/faq\"]"));
        String actualResult = faq.getText();

        WebElement howToStart = driver.findElement(By.xpath("//a[@href = \"/appid\"]"));
        String actualResult1 = howToStart.getText();

        WebElement askQuestion = driver.findElement(
                By.xpath("//ul[@class = 'dropdown-menu dropdown-visible']/li/a[@href = 'https://home.openweathermap.org/questions']")
        );
        String actualResult2 = askQuestion.getText();

        Assert.assertEquals(actualResult, expectedResult);
        Assert.assertEquals(actualResult1, expectedResult1);
        Assert.assertEquals(actualResult2, actualResult2);

        driver.quit();
    }

    @Test //5
    public void checkCaptcha() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "D:\\Java_code\\ChromeDriver_107v\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        String expectedResult = "reCAPTCHA verification failed, please try again.";
        String email = "abc@abc.com";
        String subject = "ala bala";
        String message = "Thank you";

        driver.get(url);
        driver.manage().window().maximize();
        Thread.sleep(5000);
        WebElement dropDownSearch = driver.findElement(By.xpath("//div[@id = 'support-dropdown']"));
        dropDownSearch.click();
        Thread.sleep(2000);
        WebElement askQuestion = driver.findElement(
                By.xpath("//ul[@class = 'dropdown-menu dropdown-visible']/li/a[@href = 'https://home.openweathermap.org/questions']")
        );
        askQuestion.click();
        Thread.sleep(5000);


        //driver.navigate().to("https://home.openweathermap.org/questions");
        ArrayList<String> page = new ArrayList<String>(driver.getWindowHandles()); //переходит на новую страницу
        driver.switchTo().window(page.get(1));

        WebElement emailField = driver.findElement(By.id("question_form_email"));
        emailField.sendKeys(email);
        Thread.sleep(5000);
        WebElement subjectField = driver.findElement(By.id("question_form_subject"));
        subjectField.sendKeys(subject);
        Thread.sleep(5000);
        WebElement messageField = driver.findElement(By.id("question_form_message"));
        messageField.sendKeys(message);
        WebElement submitButton = driver.findElement(By.xpath("//input[@value = 'Submit']"));
        submitButton.click();
        WebElement captchaMessage = driver.findElement(By.xpath("//div[@class = 'has-error']/div[@class = 'help-block']"));
        String actualResult = captchaMessage.getText();

        Assert.assertEquals(actualResult, expectedResult);

        driver.quit();
    }

    @Test
    public void checkCaptcha1() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "D:\\Java_code\\ChromeDriver_107v\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        String subject = "ala bala";
        String message = "Thank you";

        driver.get(url);
        driver.manage().window().maximize();
        Thread.sleep(5000);
        WebElement dropDownSearch = driver.findElement(By.xpath("//div[@id = 'support-dropdown']"));
        dropDownSearch.click();
        Thread.sleep(2000);
        WebElement askQuestion = driver.findElement(
                By.xpath("//ul[@class = 'dropdown-menu dropdown-visible']/li/a[@href = 'https://home.openweathermap.org/questions']")
        );
        askQuestion.click();
        Thread.sleep(5000);

        driver.navigate().to("https://home.openweathermap.org/questions");

        WebElement subjectField = driver.findElement(By.id("question_form_subject"));
        subjectField.sendKeys(subject);
        Thread.sleep(5000);
        WebElement messageField = driver.findElement(By.id("question_form_message"));
        messageField.sendKeys(message);
        WebElement checkBox = driver.findElement(
                By.xpath("//div[@class = 'recaptcha-checkbox-checkmark']"));
        checkBox.click();
        WebElement submitButton = driver.findElement(By.xpath("//input[@value = 'Submit']"));
        submitButton.click();

       // WebElement

        driver.quit();
    }

    ////    TC_11_06
    ////    1.  Открыть базовую ссылку
    ////    2.  Нажать пункт меню Support → Ask a question
    ////    3.  Оставить значение по умолчанию в checkbox Are you an OpenWeather user?
    ////    4.  Оставить пустым поле Email
    ////    5.  Заполнить поля  Subject, Message
    ////    6.  Подтвердить CAPTCHA
    ////    7.  Нажать кнопку Submit
    ////    8.  Подтвердить, что в поле Email пользователю будет показана ошибка "can't be blank"
    //
    //    @Test
    //    public void testErrorEmail() throws InterruptedException {
    //        System.setProperty("webdriver.chrome.driver", "D:\\Programs\\chromedriver\\chromedriver.exe");
    //        WebDriver driver = new ChromeDriver();
    //
    //        String url = "https://openweathermap.org/";
    //        String subject = "Other";
    //        String message = "Hi Lilu we are waiting for you";
    //
    //        String expectedResult = "can't be blank";
    //
    //
    //        driver.get(url);
    //
    //        Thread.sleep(5000);
    //        driver.manage().window().maximize();
    //
    //        WebElement clickOnSupport = driver.findElement(By.xpath("//div[@id='support-dropdown']"));
    //        clickOnSupport.click();
    //
    //        String originalWindow = driver.getWindowHandle();
    //        Thread.sleep(4000);
    //        WebElement selectSubmenu_AskAQuestion = driver.findElement(By.xpath(
    //                "//ul[@id='support-dropdown-menu']//a[@href='https://home.openweathermap.org/questions']"));
    //        selectSubmenu_AskAQuestion.click();
    //
    //        Thread.sleep(4500);
    //
    //        for (String windowHandle : driver.getWindowHandles()) {
    //            if (!originalWindow.contentEquals(windowHandle)) {
    //                driver.switchTo().window(windowHandle);
    //                break;
    //            }
    //        }
    //        Thread.sleep(3000);
    //
    //        WebElement enterSubject = driver.findElement(By.xpath(
    //                "//select[@class='form-control select required']"));
    //
    //        enterSubject.click();
    //
    //        enterSubject.sendKeys(subject);
    //
    //        Thread.sleep(4000);
    //
    //        WebElement enterMessage = driver.findElement(By.xpath(
    //                "//textarea[@class='form-control text required']"));
    //        enterMessage.click();
    //        enterMessage.sendKeys(message);
    //
    //        Thread.sleep(5000);
    //
    //        String window2 = driver.getWindowHandle();
    //
    //        driver.switchTo().frame(driver.findElement(By.cssSelector("iframe[title='reCAPTCHA']")));
    //
    //        WebElement enterCaptcha = driver.findElement(By.xpath(
    //                "//span[@class='recaptcha-checkbox goog-inline-block recaptcha-checkbox-unchecked "
    //                        + "rc-anchor-checkbox']"));
    //        enterCaptcha.click();
    //
    //        Thread.sleep(10000);
    //
    //        driver.switchTo().window(window2);
    //
    //        WebElement pressSubmit = driver.findElement(By.xpath(
    //                "//input[@data-disable-with='Create Question form']"));
    //        pressSubmit.click();
    //
    //        WebElement confirmErrorEmail = driver.findElement(By.xpath("//span[@class='help-block']"));
    //
    //        String actualResult = confirmErrorEmail.getText();
    //
    //        Assert.assertEquals(actualResult, expectedResult);
    //
    //        driver.quit();
    //    }


    @Test //10
    public void checkOrangeButtons() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "D:\\Java_code\\ChromeDriver_107v\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        int expectedResult = 30;

        driver.get(url);
        Thread.sleep(5000);
        WebElement apiPage = driver.findElement(By.xpath("//div[@id = 'desktop-menu']//a[@href = '/api']"));
        apiPage.click();
        int orangeButtons = driver.findElements(
                By.xpath("//a[contains(@class, 'btn_block orange round')  " +
                        "or contains(@class, 'ow-btn round btn-orange') ]")).size();

        int actualResult = orangeButtons;

        Assert.assertEquals(actualResult, expectedResult);
        driver.quit();
    }
}
