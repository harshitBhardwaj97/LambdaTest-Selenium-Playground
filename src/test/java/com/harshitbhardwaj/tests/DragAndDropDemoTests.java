package com.harshitbhardwaj.tests;

import com.harshitbhardwaj.base.Base;
import com.harshitbhardwaj.pages.DragAndDropDemoPage;
import com.harshitbhardwaj.pages.HomePage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class DragAndDropDemoTests extends Base {

    WebDriver driver;
    DragAndDropDemoPage dragAndDropDemoPage;
    HomePage homePage;

    @BeforeMethod
    void setup() {
        driver = initializeDriver();
        homePage = new HomePage(driver);
        dragAndDropDemoPage = new DragAndDropDemoPage(driver);
        homePage.navigateToDragAndDropDemoPage();
    }

    @Test(priority = 1)
    public void verifyDragAndDropFunctionality_forDemoOneSection() {

        var demoOneDraggableItems = dragAndDropDemoPage.getFirstDraggableItemsList();

        // First of all without dropping, verify that size of dropped items list is 0.
        Assert.assertEquals(dragAndDropDemoPage.getSizeOfDroppedItemsList(), 0);

        /*
         * Now drag and drop first item, and check the size is changed to 1, and also
         * the dropped list contains the Draggable 1 text or not.
         *
         */

        dragAndDropDemoPage.DropElementToTarget(demoOneDraggableItems.get(0), dragAndDropDemoPage.getFirstDropTarget());

        // Checking the presence of text of the dropped item in the list.
        Assert.assertTrue(dragAndDropDemoPage.getDroppedItemsText().contains("Draggable 1"));

        // Checking that length of dropped items list has changed to 1.
        Assert.assertEquals(dragAndDropDemoPage.getSizeOfDroppedItemsList(), 1);

        /*
         * Now repeat the same procedure with second item.
         */

        dragAndDropDemoPage.DropElementToTarget(demoOneDraggableItems.get(1), dragAndDropDemoPage.getFirstDropTarget());

        // Checking the presence of text of the dropped item in the list.
        Assert.assertTrue(dragAndDropDemoPage.getDroppedItemsText().contains("Draggable 2"));

        // Checking that length of dropped items list has changed to 2.
        Assert.assertEquals(dragAndDropDemoPage.getSizeOfDroppedItemsList(), 2);

    }

    @Test(priority = 2)
    public void verifyDragAndDropFunctionality_forDemoTwoSection() {

        var demoTwoDraggableItem = dragAndDropDemoPage.getDemoTwoDraggableItem();
        var demoTwoDropTarget = dragAndDropDemoPage.getDemoTwoDropTarget();

        String actualDropTargetText;
        String expectedDropTargetText = "Drop here"; // Later it will be updated it to Dropped!

        /*
         * First of all without dropping, verify that drop target text is Drop here and
         * "ui-state-highlight" class is not present on dropTarget
         */
        actualDropTargetText = dragAndDropDemoPage.getDemoTwoDropTargetText();
        Assert.assertEquals(actualDropTargetText, expectedDropTargetText);
        Assert.assertFalse(dragAndDropDemoPage.checkClassAttributeOfDropTargetContainsHighlighted());

        /*
         * Now drop the element, and verify that drop target text is changed to
         * Dropped!, and "ui-state-highlight" class has been added on dropTarget
         */
        dragAndDropDemoPage.DropElementToTarget(demoTwoDraggableItem, demoTwoDropTarget);
        actualDropTargetText = dragAndDropDemoPage.getDemoTwoDropTargetText();
        expectedDropTargetText = "Dropped!";
        Assert.assertEquals(actualDropTargetText, expectedDropTargetText);
        Assert.assertTrue(dragAndDropDemoPage.checkClassAttributeOfDropTargetContainsHighlighted());

    }

    @AfterMethod
    void closeWindow() {
        driver.quit();
    }

}
