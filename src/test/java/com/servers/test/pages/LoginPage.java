package com.servers.test.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
  public WebDriver driver;
  public WebDriverWait wait;

  public LoginPage(WebDriver driver, WebDriverWait wait) {
    PageFactory.initElements(driver, this);
    this.driver = driver;
    this.wait = wait;
  }

  @FindBy(id = "email")
  private WebElement loginField;

  @FindBy(id = "password")
  private WebElement passwordField;

  @FindBy(id = "CybotCookiebotDialogBodyButtonDecline")
  private WebElement declineCookiesButton;

  @FindBy(css = "[type=submit]")
  private WebElement submitButton;

  public LoginPage inputLogin(String login) {
    loginField.sendKeys(login);
    return this;
  }

  public LoginPage inputPassword(String password) {
    passwordField.sendKeys(password);
    return this;
  }

  public AccountInfoPage loginButtonClick() {
    submitButton.click();
   // wait.until(ExpectedConditions.invisibilityOfElementLocated((By)loginField));
    return new AccountInfoPage(driver, wait);
  }

  public LoginPage incorrectLoginButtonClick() {
    submitButton.click();
    return this;
  }

  public LoginPage declineCookiesIfNeed() {
    if (declineCookiesButton.isDisplayed())
      declineCookiesButton.click();
//    wait.until(ExpectedConditions.invisibilityOfElementLocated((By)declineCookiesButton));
    return this;
  }
}
