package runner;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.UUID;

public abstract class ProjectUtils {

    public static void click(WebDriverWait wait, WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    public static void click(WebDriver driver, WebElement element) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click()", element);
    }

    public static void scroll(WebDriver driver, WebElement element) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].scrollIntoView();", element);
    }

    public static void fill(WebDriverWait wait, WebElement element, String text) {
        wait.until(ExpectedConditions.visibilityOf(element));
        if (element.toString().toLowerCase().contains("date")) {
            click(wait, element);
        }
        if (!element.getAttribute("value").isEmpty()) {
            element.clear();
        }
        element.sendKeys(text);
        wait.until(d -> element.getAttribute("value").equals(text));
    }

    public static void inputKeys(WebDriver driver, WebElement element, String keys) {
        if (!"input".equals(element.getTagName())) {
            throw new RuntimeException(element + " is not input");
        }
        ProjectUtils.fill(new WebDriverWait(driver, 10), element, keys);
    }

    public static String createUUID() {
        return UUID.randomUUID().toString();
    }

    public static String getGMTDate() {
        SimpleDateFormat dateFormatGmt = new SimpleDateFormat("dd/MM/yyyy");
        dateFormatGmt.setTimeZone(TimeZone.getTimeZone("GMT"));
        return dateFormatGmt.format(new Date());
    }
}
