package pages;

import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.BaseTest;

import static org.junit.Assert.assertEquals;


public class BrandNameFetchBrandName extends BaseTest {
    @Test
    public  void  xssTest() throws InterruptedException {
        // 1. Login
        login("admin", "admin");

        // Wait for login
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 2. Go to brand page and click on action button
        WebElement brandButton = driver.findElement(By.xpath("//a[contains(text(),'Brand')]"));
        brandButton.click();
        WebElement actionButton  = driver.findElement(By.xpath("//tr[1]//td[3]//div[1]//button[1]"));
        actionButton.click();
        WebElement dropDownEditButton = driver.findElement(By.xpath("//div[@class='btn-group open']//li[1]//a[1]"));
        dropDownEditButton.click();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 3. Change admin's username inserting a XSS attack vector
        WebElement editBrandName = driver.findElement(By.id("editBrandName"));
        editBrandName.clear();
        editBrandName.sendKeys("<h1>Apple</h1>");

        WebElement saveButton = driver.findElement(By.id("editBrandBtn"));
        WebElement closeButton = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[3]/div[1]/div[1]/form[1]/div[3]/button[1]"));
        saveButton.click();
        closeButton.click();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 4 Check the username in the brand page
        WebElement brandTable= driver.findElement(By.xpath("//tr[1]//td[1]"));
        String brandLabel = brandTable.getAttribute("h1 xpath");
        assertEquals("<h1>Apple</h1>", brandLabel);
        System.out.println("Passed");



        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        reset();


    }
    //=== Support methods ===
    private void login(String username, String password) {
        WebElement usernameTextBox = driver.findElement(By.id("username"));
        WebElement passwordTextBox = driver.findElement(By.id("password"));
        usernameTextBox.sendKeys(username);
        passwordTextBox.sendKeys(password);
        WebElement submitButton = driver.findElement(By.xpath("/html/body/div/div/div/div/div[2]/form/fieldset/div[3]/div/button"));
        submitButton.click();
    }

    @After
    public void reset(){
        // 2. Go to brand page and click on action button
        WebElement brandButton = driver.findElement(By.xpath("//a[contains(text(),'Brand')]"));
        brandButton.click();
        WebElement actionButton  = driver.findElement(By.xpath("//tr[1]//td[3]//div[1]//button[1]"));
        actionButton.click();
        WebElement dropDownEditButton = driver.findElement(By.xpath("//div[@class='btn-group open']//li[1]//a[1]"));
        dropDownEditButton.click();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 3. Change admin's username inserting a XSS attack vector
        WebElement editBrandName = driver.findElement(By.id("editBrandName"));
        editBrandName.clear();
        editBrandName.sendKeys("Apple");

        WebElement saveButton = driver.findElement(By.id("editBrandBtn"));
        WebElement closeButton = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[3]/div[1]/div[1]/form[1]/div[3]/button[1]"));
        saveButton.click();
        closeButton.click();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

}
