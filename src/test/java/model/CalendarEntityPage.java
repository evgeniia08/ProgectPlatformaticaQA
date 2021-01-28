package model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import runner.ProjectUtils;

import java.time.LocalDate;
import java.util.concurrent.ThreadLocalRandom;

public class CalendarEntityPage extends BasePage{

    public static final String RANDOM_DAY = String.format("%02d", ThreadLocalRandom.current().nextInt(1, 27 + 1));
    private static final LocalDate TODAY = LocalDate.now();
    private static final String OUTPUT = TODAY.toString();
    private static final String[] ARR_OF_DATA = OUTPUT.split("-", 3);
    private static final String CURRENT_YEAR = ARR_OF_DATA[0];
    private static final String CURRENT_MONTH = ARR_OF_DATA[1];

    public CalendarEntityPage(WebDriver driver) {
        super(driver);
    }


    public void clickOnCalendarDate(WebDriver driver) {

         ProjectUtils.click(getDriver(),  driver.findElement(By.xpath(String.format
         ("//td[@data-day = '%1$s%2$s%3$s%2$s%4$s']", CURRENT_MONTH, "/", RANDOM_DAY, CURRENT_YEAR))));

         }

    public  String getRandomDay() {
        return RANDOM_DAY;
    }

    public  String getCurrentYear() {
        return CURRENT_YEAR;
    }

    public  String getCurrentMonth() {
        return CURRENT_MONTH;
    }

}
