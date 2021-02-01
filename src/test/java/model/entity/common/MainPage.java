package model.entity.common;

import model.base.BaseIndexPage;
import model.entity.table.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainPage extends BaseIndexPage {

    @FindBy(xpath = "//li[@id = 'pa-menu-item-45']")
    private WebElement menuFields;

    @FindBy(xpath = "//p[contains(text(),'Import values')]")
    private WebElement menuImportValues;

    @FindBy(xpath = "//p[contains(text(),'Home')]")
    private WebElement leftMenu;

    @FindBy(xpath = "//p[contains(text(), 'Export')]")
    private WebElement menuExport;

    @FindBy(css = "#menu-list-parent>ul>li>a[href*='id=62")
    private WebElement menuEventsChain2;

    @FindBy(css = "#menu-list-parent>ul>li>a[href*='id=61']")
    private WebElement menuEventsChain1;

    @FindBy(xpath = "//a[contains(@href, 'id=37')]")
    private WebElement menuAssign;

    @FindBy(xpath = "//li[@id='pa-menu-item-41']")
    private WebElement menuMyAssignments;

    @FindBy(xpath = "//p[contains (text(), 'Default')]")
    private WebElement menuDefault;

    @FindBy(xpath = "//p[contains(text(),'Placeholder')]/preceding-sibling::i")
    private WebElement menuPlaceholder;

    @FindBy(xpath = "//p[contains(text(),'Platform functions')]")
    private WebElement menuPlatformFunctions;

    @FindBy(xpath = "//p[contains(text(),'Board')]")
    private WebElement menuBoard;

    @FindBy(xpath = "//p[contains(text(),'Arithmetic Function')]")
    private WebElement menuArithmeticFunction;

    @FindBy(xpath = "//p[contains (text(), 'Init')]/parent::a")
    private WebElement init;

    @FindBy(xpath = "//p[contains(text(), 'Chevron')]")
    private WebElement menuChevron;

    @FindBy(xpath = "//p[contains(text(), 'Arithmetic Inline')]")
    private WebElement menuArithmeticInline;

    @FindBy(xpath = "//p[contains(text(), 'Reference values')]")
    private WebElement menuReferenceValues;

    @FindBy(xpath = "//p[contains(text(),'Calendar')]")
    private WebElement menuCalendar;

    @FindBy(xpath = "//p[contains(text(),'Parent')]")
    private WebElement menuParent;

    @FindBy(xpath = "//p[contains(text(),'Footers')]")
    private WebElement menuFooters;


    public MainPage(WebDriver driver) {
        super(driver);
    }

    public FieldsPage clickMenuFields() {
        clickMenu(menuFields);
        return new FieldsPage(getDriver());
    }

    public ImportValuesPage clickMenuImportValues() {
        clickMenu(menuImportValues);
        return new ImportValuesPage(getDriver());
    }
  
    public Chain2Page clickMenuEventsChain2() {
        clickMenu(menuEventsChain2);
        return new Chain2Page(getDriver());
    }

    public ExportPage clickMenuExport() {
        clickMenu(menuExport);
        return new ExportPage(getDriver());
    }

    public Chain1Page clickMenuEventsChain1() {
        clickMenu(menuEventsChain1);
        return new Chain1Page(getDriver());
    }

    public AssignPage clickMenuAssign() {
        clickMenu(menuAssign);
        return new AssignPage(getDriver());
    }

    public MyAssignmentsPage clickMenuMyAssignments() {
        clickMenu(menuMyAssignments);
        return new MyAssignmentsPage(getDriver());
    }

    public DefaultPage clickMenuDefault() {
        clickMenu(menuDefault);
        return new DefaultPage(getDriver());
    }

    public PlaceholderPage clickMenuPlaceholder() {
        clickMenu(menuPlaceholder);
        return new PlaceholderPage(getDriver());
    }

    public BoardPageEntityBase clickMenuBoard() {
        clickMenu(menuBoard);
        return new BoardPageEntityBase(getDriver());
    }

    public PlatformFuncPage clickMenuPlatformFunctions() {
        clickMenu(menuPlatformFunctions);
        return new PlatformFuncPage(getDriver());
    }

    public ArithmeticFunctionPage clickMenuArithmeticFunction() {
        clickMenu(menuArithmeticFunction);
        return new ArithmeticFunctionPage(getDriver());
    }

    public InitPage clickMenuInit() {
        clickMenu(init);
        return new InitPage(getDriver());
    }

    public ChevronPage clickMenuChevron() {
        clickMenu(menuChevron);
        return new ChevronPage(getDriver());
    }

    public ArithmeticInlinePage clickMenuArithmeticInline() {
        clickMenu(menuArithmeticInline);
        return new ArithmeticInlinePage(getDriver());
    }

    public ReferenceValuesPage clickMenuReferenceValues() {
        clickMenu(menuReferenceValues);
        return new ReferenceValuesPage(getDriver());
    }

    public CalendarPage clickMenuCalendar() {
        clickMenu(menuCalendar);
        return new CalendarPage(getDriver());
    }

    public ParentPage clickMenuParent() {
        clickMenu(menuParent);
        return new ParentPage(getDriver());
    }

    public FootersPage clickMenuFooters() {
        clickMenu(menuFooters);
        return new FootersPage(getDriver());
    }
}
