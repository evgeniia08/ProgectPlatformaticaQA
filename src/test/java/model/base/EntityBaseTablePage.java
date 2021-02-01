package model.base;

import model.entity.common.MainPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public abstract class EntityBaseTablePage<TablePage, EditPage> extends MainPage {

    private final BaseTableModule table = new BaseTableModule(getDriver());

    public EntityBaseTablePage(WebDriver driver) {
        super(driver);
    }

    protected abstract EditPage createEditPage();

    protected List<WebElement> getRows() {
        return table.getRows();
    }

    public EditPage clickNewFolder() {
        table.clickNewFolder();
        return createEditPage();
    }

    public int getRowCount() {
        return table.getRowCount();
    }

    public String getRowIconClass(int rowNumber) {
        return table.getRowIconClass(rowNumber);
    }

    public List<String> getRow(int rowNumber) {
        return table.getRow(rowNumber);
    }

    public List<String> getRow(int rowNumber, String xpath) {
        return table.getRow(rowNumber, xpath);
    }

    public EntityBaseViewPage viewRow(int rowNumber) {
        table.clickRowMenuViewRow(rowNumber);
        return new EntityBaseViewPage(getDriver());
    }

    public EntityBaseViewPage viewRow() {
        return viewRow(getRows().size() - 1);
    }

    public EditPage editRow(int rowNumber) {
        table.clickRowMenuEditRow(rowNumber);
        return createEditPage();
    }

    public EditPage editRow() {
        return editRow(getRows().size() - 1);
    }

    public TablePage deleteRow(int rowNumber) {
        table.clickRowMenuDeleteRow(rowNumber);
        return (TablePage)this;
    }

    public TablePage deleteRow() {
        return deleteRow(getRows().size() - 1);
    }

    public TablePage clickListButton() {
        table.clickListButton();
        return (TablePage)this;
    }
}
