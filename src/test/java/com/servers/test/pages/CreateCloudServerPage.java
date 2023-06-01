package com.servers.test.pages;

import com.servers.test.TestBase;
import io.qameta.allure.Step;
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
  protected WebDriver driver;
  protected WebDriverWait wait;

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

  @FindBy(xpath = "//h4[text() = 'Add IPv6 address']")
  private WebElement addIpAddressCheckbox;

  @FindBy(name = "name")
  private WebElement name;

  @FindBy(xpath = "//button[contains(@title, 'Create Cloud Server')]")
  private WebElement createButton;

  @Step("Выбираем локацию {location}")
  public CreateCloudServerPage chooseLocation(String location) {
    locationsList.findElement(By.xpath(".//span[text() = '" + location + "']")).click();
    wait.until(ExpectedConditions.presenceOfElementLocated(By.id("image_id")));
    wait.until(ExpectedConditions.presenceOfElementLocated(By.id("flavor_id")));
    return this;
  }

  @Step("Выбираем любой image")
  public String chooseRandomImage() {
    WebElement image = images.get((int)(Math.random()*images.size()));
    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", image);
    image.click();
    String text = image.findElement(By.xpath("./../following-sibling::div")).getText();
    if (!text.contains("Windows")) {
      assertThat(isSSHMethodsDisplayed(), is(true));
      assertThat(isSSHKeysDisplayed(), is(true));
    }
    return text;
  }

  @Step("Выбираем любою конфигурацию и получаем цену")
  public String chooseRandomConfigurationAndGetPrice() {
    WebElement configuration = configurations.get((int)(Math.random()*configurations.size()));
    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", configuration);
    configuration.click();
    String price = configuration.findElement(By.xpath("./../../../div[2]//b[contains(@data-role, ':price')]")).getText();
    return price;
  }

  @Step("Проверка цены")
  public CreateCloudServerPage checkPrice(String price) {
    WebElement element = driver.findElement(By.xpath("//span[text() = 'Total price']"));
    WebElement priceElement = element.findElement(By.xpath("./..//b"));
    assertThat("Отображается верная итоговая цена", priceElement.getText(), is(price));
    return this;
  }

  @Step("Выбираем SSH метод")
  public CreateCloudServerPage chooseSSHMethod() {
    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", sshMethod);
    sshMethod.click();
    return this;
  }

  @Step("Выбираем проверку паролем")
  public CreateCloudServerPage choosePassword() {
    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", password);
    password.click();
    return this;
  }

  @Step("Выбор SSH ключа")
  public CreateCloudServerPage chooseSSHKey() {
    sshKeys.findElement(By.xpath(".//h4[1]")).click();
    return this;
  }

  @Step("Ставим галку бекапа")
  public CreateCloudServerPage setBackupCopies(int count) {
    backupCopies.clear();
    backupCopies.sendKeys(String.valueOf(count));
    return this;
  }

  @Step("Выбор чекбокса добавления IP адреса")
  public CreateCloudServerPage setAddIpAddressCheckbox() {
    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", addIpAddressCheckbox);
    addIpAddressCheckbox.click();
    return this;
  }

  @Step("Ввод имени")
  public CreateCloudServerPage setName(String name) {
    this.name.clear();
    this.name.sendKeys(name);
    return this;
  }

  @Step("Нажать кнопку Создать")
  public PaymentMethodsPage createButtonClick() {
    createButton.click();
    wait.until(ExpectedConditions.urlToBe(TestBase.main_url + "/payment/methods"));
    return new PaymentMethodsPage(driver, wait);
  }

  public boolean isSSHMethodsDisplayed() {
    return sshMethod.isDisplayed();
  }

  public boolean isSSHKeysDisplayed() {
    return sshKeys.isDisplayed();
  }
}
