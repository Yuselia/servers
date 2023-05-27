package com.servers.test.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
  public WebDriver driver;

  public LoginPage(WebDriver driver) {
    PageFactory.initElements(driver, this);
    this.driver = driver;
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
    return new AccountInfoPage(driver);
  }

  public LoginPage declineCookiesIfNeed() {
    if (declineCookiesButton.isDisplayed())
      declineCookiesButton.click();
    return this;
  }
}
