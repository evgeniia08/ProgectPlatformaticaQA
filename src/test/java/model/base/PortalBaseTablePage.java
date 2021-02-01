package model.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public abstract class PortalBaseTablePage<S, E> extends PortalBaseIndexPage {

    private final BaseTableModule table = new BaseTableModule(getDriver());

    public PortalBaseTablePage(WebDriver driver) {
        super(driver);
    }

    protected abstract E createPortalEditPage();

    protected List<WebElement> getRows() {
        return table.getRows();
    }

    public E clickNewFolder() {
        table.clickNewFolder();
        return createPortalEditPage();
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

    public E editRow(int rowNumber) {
        table.clickRowMenuEditRow(rowNumber);
        return createPortalEditPage();
    }

    public E editRow() {
        return editRow(getRows().size() - 1);
    }

    public S deleteRow(int rowNumber) {
        table.clickRowMenuDeleteRow(rowNumber);
        return (S)this;
    }

    public S deleteRow() {
        return deleteRow(getRows().size() - 1);
    }

    public S clickListButton() {
        table.clickListButton();
        return (S)this;
    }

}
