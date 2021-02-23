package model.entity.table;

import model.base.EntityBaseTablePage;
import model.entity.edit.TagEditPage;
import model.entity.view.TagViewPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import runner.ProjectUtils;

import java.util.List;
import java.util.stream.Collectors;

public class TagPage extends EntityBaseTablePage<TagPage, TagEditPage, TagViewPage> {

    @FindBy(css = "div[class$=list-tags] label")
    private List<WebElement> tags;

    @FindBy(css = "button[value=delete]")
    private WebElement deleteTagsButton;

    @FindBy(css = "input[name=new_tag]")
    private WebElement inputTag;

    @FindBy(css = "button[value=create]")
    private WebElement createTagButton;

    @FindBy(css = "button[value=assign]")
    private WebElement assignTagsButton;

    public TagPage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected TagEditPage createEditPage() {
        return new TagEditPage(getDriver());
    }

    @Override
    protected TagViewPage createViewPage() {
        return new TagViewPage(getDriver());
    }

    public List<String> getTags() {
        return tags.stream().map(WebElement::getText).collect(Collectors.toList());
    }

    public TagPage selectTagByName(String tagName) {
            for (WebElement tag : tags) {
                if (tag.getText().trim().equals(tagName)) {
                getActions().moveToElement(tag).click().build().perform();
                    return this;
                }
        }
        Assert.fail(String.format("Tag with name '%s' not found", tagName));
        return this;
    }

    public TagPage clickAssignButton() {
        assignTagsButton.click();
        return this;
    }

    public TagPage clickDeleteTagsButton() {
        getActions().moveToElement(deleteTagsButton).click().build().perform();
        return this;
    }

    public TagPage createNewTag(String tag) {
        getActions().moveToElement(inputTag).perform();
        ProjectUtils.fill(getWait(), inputTag, tag);
        getActions().moveToElement(createTagButton).click().build().perform();
        return this;
    }

    public int getTagCountByName(String tag) {
        int count = 0;
        for (WebElement tagElement : tags) {
            if (tagElement.getText().equals(tag)) {
                count++;
            }
        }
        return count;
    }

    public TagPage selectTagRecordByString(String string) {
        for (WebElement row : getRows()) {
            if (row.findElement(By.xpath("td[3]")).getText().equals(string)) {
                row.findElement(By.className("check")).click();
                return this;
            }
        }
        Assert.fail(String.format("Tag record with string '%s' not found", string));
        return null;
    }

    public List<String> getTagsByRecordString(String string) {
        for (WebElement row : getRows()) {
            if (row.findElement(By.xpath("td[3]")).getText().equals(string)) {
                if (row.findElement(By.xpath("td[2]")).getText().equals("")) {
                    return null;
                } else {
                    List<WebElement> tagElements = row.findElements(By.cssSelector("span.pa-tag"));
                    return tagElements.stream().map(WebElement::getText).collect(Collectors.toList());
                }
            }
        }
        Assert.fail(String.format("Tag record with string '%s' not found", string));
        return null;
    }
}
