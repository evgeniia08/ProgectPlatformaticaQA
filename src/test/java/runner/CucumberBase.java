package runner;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.WebDriver;
import runner.type.ProfileType;
import runner.type.RunType;

import java.net.URI;
import java.util.Collection;

public class CucumberBase extends BaseTest {

    private static URI uri = null;
    private static BaseTest baseTest = null;

    private static long startMillis;

    public static <T extends Enum<T>> T valueOf(Class<T> enumType, Collection<String> names, T defaultValue) {
        for (T value : enumType.getEnumConstants()) {
            if (names.contains("@" + value.name())) {
                return value;
            }
        }

        return defaultValue;
    }

    @Before
    public static void before(Scenario scenario) {
        ProfileType profileType = valueOf(ProfileType.class, scenario.getSourceTagNames(), ProfileType.DEFAULT);

        if (baseTest == null || (baseTest.getRunType() == RunType.Multiple && scenario.getUri().equals(uri))) {
            if (baseTest != null) {
                baseTest.afterClass();
            }
            baseTest = new CucumberBase();

            uri = scenario.getUri();
            baseTest.beforeClass(valueOf(RunType.class, scenario.getSourceTagNames(), RunType.Single), profileType);
        }

        startMillis = System.currentTimeMillis();
        baseTest.beforeMethod(String.format("<%s>", scenario.getName()), profileType);
    }

    @After
    public static void after(Scenario scenario) {
        baseTest.afterMethod(String.format("<%s>", scenario.getName()),
                (System.currentTimeMillis() - startMillis) / 1000);
    }

    public static WebDriver getCurrentDriver() {
        return baseTest.getDriver();
    }
}
