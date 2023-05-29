package com.servers.test.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class MainPage {
  public WebDriver driver;
  public WebDriverWait wait;

  public MainPage(WebDriver driver, WebDriverWait wait) {
    PageFactory.initElements(driver, this);
    this.driver = driver;
    this.wait = wait;
  }

  @FindBy(xpath = "//a[contains(@href, '/cart')]/following::div/button")
  private WebElement account;

  @FindBy(xpath = "//span[text() = 'Logout']")
  private WebElement logoutButton;

  @FindBy(xpath = "//i[contains(@title, 'Cloud Servers')]")
  private WebElement cloudServersMenu;

  @FindBy(xpath = "//span[text() = 'Create & Manage']")
  private WebElement cloudServerCreateAndManageMenu;

  @FindBy(xpath = "//div[contains(@aria-label, 'Please fill your account info')]/div[1]/header/button")
  private WebElement closeModalWindowButton;

  public MainPage checkAccountName(String accountName) {
    assertThat("Отображается аккаунт пользователя", this.account.getText(), is(accountName));
    return this;
  }

  public MainPage accountButtonClick() {
    account.click();
    return this;
  }

  public LoginPage logoutButtonClick() {
    logoutButton.click();
    return new LoginPage(driver, wait);
  }

  public MainPage cloudServersMenuClick() {
    cloudServersMenu.click();
    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text() = 'Create & Manage']")));
    return this;
  }

  public CloudServers cloudServerCreateAndManageMenuClick() {
    cloudServerCreateAndManageMenu.click();
    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h3/span[text() = 'Cloud Servers']")));
    return new CloudServers(driver, wait);
  }

  public MainPage closeModalWindowButton() {
    closeModalWindowButton.click();
    wait.until(ExpectedConditions.stalenessOf(closeModalWindowButton));
    return this;
  }
}
