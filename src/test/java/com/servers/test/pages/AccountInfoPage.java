package com.servers.test.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class AccountInfoPage {
  public WebDriver driver;
  public WebDriverWait wait;

  public AccountInfoPage(WebDriver driver, WebDriverWait wait) {
    PageFactory.initElements(driver, this);
    this.driver = driver;
    this.wait = wait;
  }

  @FindBy(xpath = "//a[contains(@href, '/cart')]/following::div/button")
  private WebElement account;

  @FindBy(xpath = "//span[text() = 'Logout']")
  private WebElement logoutButton;

  @FindBy(name = "business_type")
  private WebElement business_type;

  @FindBy(xpath = "//div[contains(@aria-label, 'Please fill your account info')]/div[1]/header/button")
  private WebElement closeModalWindowButton;

  @FindBy(name = "currency")
  private WebElement currency;

  @FindBy(name = "newsletters_subscription")
  private WebElement newsletters_subscription;

  @FindBy(name = "fname")
  private WebElement fname;

  public AccountInfoPage inputFirstName(String firstName) {
    fname.sendKeys(firstName);
    return this;
  }

  public AccountInfoPage checkAccountName(String accountName) {
    assertThat("Отображается аккаунт пользователя", this.account.getText(), is(accountName));
    return this;
  }

  public AccountInfoPage accountButtonClick() {
    account.click();
    return this;
  }

  public AccountInfoPage closeModalWindowButton() {
    closeModalWindowButton.click();
    return this;
  }

  public LoginPage logoutButtonClick() {
    logoutButton.click();
    return new LoginPage(driver, wait);
  }
}
