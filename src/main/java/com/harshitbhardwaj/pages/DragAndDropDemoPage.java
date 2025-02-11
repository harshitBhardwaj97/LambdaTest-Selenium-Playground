package com.harshitbhardwaj.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class DragAndDropDemoPage {

    private final WebDriver driver;

    Actions actions;

    @FindBy(xpath = "//span[.='Draggable 1']")
    @CacheLookup
    private WebElement firstDraggableItem;

    @FindBy(xpath = "//span[.='Draggable 2']")
    @CacheLookup
    private WebElement secondDraggableItem;

    @FindBy(id = "mydropzone")
    @CacheLookup
    private WebElement firstDropTarget;

    @FindBy(id = "droppedlist")
    @CacheLookup
    private WebElement firstDropList;

    @FindBy(xpath = "//div[@id='droppedlist']/span")
    private List<WebElement> droppedItems;

    @FindBy(id = "draggable")
    @CacheLookup
    private WebElement demoTwoDraggableItem;

    @FindBy(id = "droppable")
    @CacheLookup
    private WebElement demoTwoDropTarget;

    public DragAndDropDemoPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public List<WebElement> getFirstDraggableItemsList() {
        return new ArrayList<WebElement>(Arrays.asList(firstDraggableItem, secondDraggableItem));
    }

    public int getSizeOfDroppedItemsList() {
        return droppedItems.size(); // It will depend on the current dropped elements in the drop-zone
    }

    public WebElement getFirstDropTarget() {
        return firstDropTarget;
    }

    public WebElement getDemoTwoDraggableItem() {
        return demoTwoDraggableItem;
    }

    public WebElement getDemoTwoDropTarget() {
        return demoTwoDropTarget;
    }

    public void DropElementToTarget(WebElement droppableElement, WebElement targetElement) {
        actions = new Actions(driver);
        actions.dragAndDrop(droppableElement, targetElement).perform();
    }

    public List<String> getDroppedItemsText() {
        return droppedItems.stream().map(WebElement::getText).collect(Collectors.toList());
    }

    public boolean checkClassAttributeOfDropTargetContainsHighlighted() {
        return demoTwoDropTarget.getAttribute("class").contains("ui-state-highlight");
    }

    public String getDemoTwoDropTargetText() {
        return demoTwoDropTarget.getText();
    }

}
