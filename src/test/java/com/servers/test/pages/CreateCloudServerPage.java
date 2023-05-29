package com.servers.test.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


public class CreateCloudServerPage {
  public WebDriver driver;
  public WebDriverWait wait;

  public CreateCloudServerPage(WebDriver driver, WebDriverWait wait) {
    PageFactory.initElements(driver, this);
    this.driver = driver;
    this.wait = wait;
  }

  @FindBy(id = "location_id")
  private WebElement locationsList;

  @FindBy(xpath = "//*[contains(@id, 'image_id')]//input[contains(@name, 'image_id')]")
  private List<WebElement> images;

  @FindBy(xpath = "//*[contains(@id, 'flavor_id')]//h4")
  private List<WebElement> configurations;

  @FindBy(xpath = "//*[contains(@id, 'backup_enabled')]")
  private WebElement backup;

  @FindBy(xpath = "//*[contains(@id, 'auth_methods')]")
  private WebElement sshMethod;

  @FindBy(xpath = "//input[contains(@value, 'password')]")
  private WebElement password;

  @FindBy(xpath = "//*[contains(@id, 'ssh_key_fingerprint')]")
  private WebElement sshKeys;

  @FindBy(xpath = "//*[contains(@name, 'backup_copies')]")
  private WebElement backupCopies;

  @FindBy(xpath = "//h4[text() = 'Add IPv6 address")
  private WebElement addIpAddressCheckbox;

  @FindBy(name = "name")
  private WebElement name;

  @FindBy(xpath = "//button[contains(@title, 'Create Cloud Server')]")
  private WebElement createButton;

  public CreateCloudServerPage chooseLocation(String location) {
    locationsList.findElement(By.xpath(".//span[text() = '" + location + "']")).click();
    wait.until(ExpectedConditions.presenceOfElementLocated(By.id("image_id")));
    wait.until(ExpectedConditions.presenceOfElementLocated(By.id("flavor_id")));
    return this;
  }

  public String chooseRandomImage() {
    WebElement image = images.get((int)(Math.random()*images.size()));
    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", image);
    image.click();
    String text = image.findElement(By.xpath("./../following-sibling::div")).getText();
    if (!text.contains("Windows")) {
      assertThat(isSSHMethodsDisplayed(), is(true));
      assertThat(isSSHKeysDisplayed(), is(true));
      //wait.until(ExpectedConditions.presenceOfElementLocated(By.id("ssh_key_fingerprint")));
    }
    else {
      assertThat(isSSHMethodsDisplayed(), is(false));
      assertThat(isSSHKeysDisplayed(), is(false));
    }

    return text;
  }

  public String chooseRandomConfigurationAndGetPrice() {
    WebElement configuration = configurations.get((int)(Math.random()*configurations.size()));
    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", configuration);
    configuration.click();
    String price = configuration.findElement(By.xpath("./../../../div[2]//b[contains(@data-role, ':price')]")).getText();
    return price;
  }

  public CreateCloudServerPage checkPrice(String price) {
    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text() = 'Total price: " + price + " per month']")));
    return this;
  }

  public CreateCloudServerPage chooseSSHMethod() {
    sshMethod.click();
    return this;
  }

  public CreateCloudServerPage choosePassword() {
    password.click();
    return this;
  }

  public CreateCloudServerPage chooseSSHKey() {
    sshKeys.findElement(By.xpath(".//h4[1]")).click();
    return this;
  }

  public CreateCloudServerPage setBackupCopies(int count) {
    backupCopies.clear();
    backupCopies.sendKeys(String.valueOf(count));
    return this;
  }

  public CreateCloudServerPage setAddIpAddressCheckbox() {
    addIpAddressCheckbox.click();
    return this;
  }

  public CreateCloudServerPage setName(String name) {
    this.name.clear();
    this.name.sendKeys(name);
    return this;
  }

  public CreateCloudServerPage createButtonClick() {
    createButton.click();
    wait.until(ExpectedConditions.urlToBe("https://portal.servers.com/payment/methods"));
    return this;
  }

  public boolean isSSHMethodsDisplayed() {
    return sshMethod.isDisplayed();
  }

  public boolean isSSHKeysDisplayed() {
    return sshKeys.isDisplayed();
  }
}
