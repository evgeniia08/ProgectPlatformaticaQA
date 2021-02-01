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

    public FieldsPageEntityBase clickMenuFields() {
        clickMenu(menuFields);
        return new FieldsPageEntityBase(getDriver());
    }

    public ImportValuesPageEntityBase clickMenuImportValues() {
        clickMenu(menuImportValues);
        return new ImportValuesPageEntityBase(getDriver());
    }
  
    public Chain2PageEntityBase clickMenuEventsChain2() {
        clickMenu(menuEventsChain2);
        return new Chain2PageEntityBase(getDriver());
    }

    public ExportPageEntityBase clickMenuExport() {
        clickMenu(menuExport);
        return new ExportPageEntityBase(getDriver());
    }

    public Chain1PageEntityBase clickMenuEventsChain1() {
        clickMenu(menuEventsChain1);
        return new Chain1PageEntityBase(getDriver());
    }

    public AssignPageEntityBase clickMenuAssign() {
        clickMenu(menuAssign);
        return new AssignPageEntityBase(getDriver());
    }

    public MyAssignmentsPage clickMenuMyAssignments() {
        clickMenu(menuMyAssignments);
        return new MyAssignmentsPage(getDriver());
    }

    public DefaultPageEntityBase clickMenuDefault() {
        clickMenu(menuDefault);
        return new DefaultPageEntityBase(getDriver());
    }

    public PlaceholderPage clickMenuPlaceholder() {
        clickMenu(menuPlaceholder);
        return new PlaceholderPage(getDriver());
    }

    public BoardPageEntityBase clickMenuBoard() {
        clickMenu(menuBoard);
        return new BoardPageEntityBase(getDriver());
    }

    public PlatformFuncPageEntityBase clickMenuPlatformFunctions() {
        clickMenu(menuPlatformFunctions);
        return new PlatformFuncPageEntityBase(getDriver());
    }

    public ArithmeticFunctionPageEntityBase clickMenuArithmeticFunction() {
        clickMenu(menuArithmeticFunction);
        return new ArithmeticFunctionPageEntityBase(getDriver());
    }

    public InitPageEntityBase clickMenuInit() {
        clickMenu(init);
        return new InitPageEntityBase(getDriver());
    }

    public ChevronPageEntityBase clickMenuChevron() {
        clickMenu(menuChevron);
        return new ChevronPageEntityBase(getDriver());
    }

    public ArithmeticInlinePageEntityBase clickMenuArithmeticInline() {
        clickMenu(menuArithmeticInline);
        return new ArithmeticInlinePageEntityBase(getDriver());
    }

    public ReferenceValuesPageEntityBase clickMenuReferenceValues() {
        clickMenu(menuReferenceValues);
        return new ReferenceValuesPageEntityBase(getDriver());
    }

    public CalendarPageEntityBase clickMenuCalendar() {
        clickMenu(menuCalendar);
        return new CalendarPageEntityBase(getDriver());
    }

    public ParentPageEntityBase clickMenuParent() {
        clickMenu(menuParent);
        return new ParentPageEntityBase(getDriver());
    }

    public FootersPageEntityBase clickMenuFooters() {
        clickMenu(menuFooters);
        return new FootersPageEntityBase(getDriver());
    }
}
