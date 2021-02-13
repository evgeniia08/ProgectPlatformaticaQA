package cucumber;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import model.entity.common.ErrorPage;
import model.entity.edit.FieldsEditPage;
import model.entity.table.FieldsPage;
import model.entity.common.MainPage;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import runner.CucumberBase;
import test.data.AppConstant;

import java.util.Arrays;

public class EntityFields {

    private FieldsPage fieldsPage;
    private FieldsEditPage fieldsEditPage;
    private ErrorPage errorPage;

    private String currentUser;

    @Given("Go to Fields")
    public void goToFields() {
        MainPage mainPage = new MainPage(CucumberBase.getCurrentDriver());

        currentUser = mainPage.getCurrentUser();
        fieldsPage = mainPage.clickMenuFields();
    }

    @Then("Push add button")
    public void pushAddButton() {
        fieldsEditPage = fieldsPage.clickNewFolder();
    }

    @When("Input {string}, {string}, {string}, {string}, {string} and save")
    public void inputAndSave(String title, String comment, String int_, String decimal, String date) {
        fieldsEditPage
                .fillTitle(title)
                .fillComments(comment)
                .fillInt(int_)
                .fillDecimal(decimal)
                .fillDate(date)
                .selectUser(currentUser)
                .clickSaveButton();
    }

    @When("Input invalid int {string} and save")
    public void inputInvalidIntAndSave(String int_) {
        errorPage = fieldsEditPage
                .fillInt(int_)
                .clickSaveButtonErrorExpected();
    }

    @Then("Result record is {string}, {string}, {string}, {string}, {string}")
    public void resultRecordIs(String title, String comment, String int_, String decimal, String date) {
        Assert.assertEquals(fieldsPage.getRowCount(), 1);
        Assert.assertEquals(fieldsPage.getRow(0),
                Arrays.asList(title, comment, int_, decimal, date, "", "", currentUser, ""));
    }

    @Then("We are on error page")
    public void weAreOnErrorPage() {
        Assert.assertNotNull(errorPage);
        Assert.assertEquals(errorPage.getErrorMessage(), AppConstant.ERROR_MESSAGE);
    }
}
