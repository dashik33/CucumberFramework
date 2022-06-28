package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import steps.PageInitializers;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class CommonMethods extends PageInitializers {

    public static WebDriver driver;

    /**
     * this method will open and launch the browser, reading config.properties,
     * maximizing a window, giving Implicit Wait and initializing the new objects
     */
    public void openBrowserAndLaunchApplication() {
        ConfigReader.readProperties(Constants.CONFIGURATION_FILEPATH);
        switch (ConfigReader.getPropertyValue("browser")) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            default:
                throw new RuntimeException("Invalid browser name");
        }
        driver.get(ConfigReader.getPropertyValue("url"));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Constants.IMPLICIT_WAIT, TimeUnit.SECONDS);
        initializePageObjects();
    }

    /**
     * this method will clear the text box and send text to it
     *
     * @param element
     * @param textToSend
     */
    public static void sendText(WebElement element, String textToSend) {
        element.clear(); //we clear in case smth already written in the field
        element.sendKeys(textToSend);
    }

    /**
     * this method will give Explicit wait
     *
     * @return
     */
    public static WebDriverWait getWait() {
        WebDriverWait wait = new WebDriverWait(driver, Constants.EXPLICIT_WAIT);
        return wait;
    }

    /**
     * this method will wait for the clickability of the element
     *
     * @param element
     */
    public static void waitForClikckability(WebElement element) {
        getWait().until(ExpectedConditions.elementToBeClickable(element));
    }

    /**
     * this method will click on the element
     *
     * @param element
     */
    public static void click(WebElement element) {
        waitForClikckability(element);
        element.click();
    }

    /**
     * this method will call JavascriptExecutor
     *
     * @return
     */
    public static JavascriptExecutor getJSExecutor() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        return js;
    }

    /**
     * this method will click on the element using JavascriptExecutor
     *
     * @param element
     */
    public static void jsClick(WebElement element) {
        getJSExecutor().executeScript("arguments[0].click();", element);
    }

    /**
     * this method will close the browser
     */
    public static void tearDown() {
        driver.quit();
    }

    /**
     * this method will identify if message is displayed or not
     *
     * @param errMsg
     */
    public static boolean errMessage(WebElement errMsg) {
        if (errMsg.isDisplayed()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * this method will print message text
     *
     * @param element
     */
    public static void getMsgText(WebElement element) {
        System.out.println(element.getText());
    }

    /**
     * this method will switch to frame by Web element
     *
     * @param element
     */
    public static void switchToFrame(WebElement element) {
        try{
            driver.switchTo().frame(element);
        }catch (NoSuchFrameException e) {
            e.printStackTrace();
        }
    }

    /**
     * this method will switch to frame by index
     *
     * @param index
     */
    public static void switchToFrame(int index) {
        try{
        driver.switchTo().frame(index);
        }catch (NoSuchFrameException e) {
            e.printStackTrace();
        }
        }

    /**
     * this method will switch to frame by name/ID
     *
     * @param nameOrId
     */
    public static void switchToFrame(String nameOrId) {
        try {
            driver.switchTo().frame(nameOrId);
        } catch (NoSuchFrameException e) {
            e.printStackTrace();
        }
    }
    /**
     * this method will select dropdowns' options by visible text
     *
     * @param element
     * @param textToSelect
     */
    public static void selectDD(WebElement element, String textToSelect) {
        Select select = new Select(element);
        List<WebElement> options = select.getOptions();
        for (WebElement option : options) {
            if (option.getText().equals(textToSelect)) {
                select.selectByVisibleText(textToSelect);
                break;
            }
        }
    }

    /**
     * this method will select dropdowns' options by index of the option
     *
     * @param element
     * @param index
     */
    public static void selectDD(WebElement element, int index) {
        Select select = new Select(element);
        int optionsSize=select.getOptions().size();
        if (optionsSize>index){
            select.selectByIndex(index);
        }

    }

    public static byte[] takeScreenShot(String fileName) {
        TakesScreenshot obj = (TakesScreenshot) driver;
        byte[] picBytes = obj.getScreenshotAs(OutputType.BYTES);
        File scrFile = obj.getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(scrFile, new File(Constants.SCREENSHOT_FILEPATH +
                    fileName+" "+getTimeStamp("yyyy-MM-dd-HH-mm-ss")+" .png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return picBytes;
    }

    public static String getTimeStamp(String pattern){
        Date date=new Date();
        //formatting the date according to our choice
        SimpleDateFormat sdf=new SimpleDateFormat(pattern);
        return sdf.format(date);

    }
}
