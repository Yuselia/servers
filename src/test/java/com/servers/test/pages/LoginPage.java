package com.servers.test.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
  protected WebDriver driver;
  protected WebDriverWait wait;

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

  @FindBy(xpath = "//span[contains(@data-alert-level, 'error')]")
  private WebElement allert;

  @Step("Ввести логин")
  public LoginPage inputLogin(String login) {
    loginField.sendKeys(login);
    return this;
  }

  @Step("Ввести пароль")
  public LoginPage inputPassword(String password) {
    passwordField.sendKeys(password);
    return this;
  }

  @Step("Нажать кнопку логина")
  public MainPage loginButtonClick() {
    submitButton.click();
    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("email")));
    return new MainPage(driver, wait);
  }

  @Step("Нажать кнопку логина (при некорректных данных)")
  public LoginPage incorrectLoginButtonClick() {
    submitButton.click();
    allert.click();
    return this;
  }

  public String getError() {
    return allert.getText();
  }

  @Step("Нажать кнопку отлонения Cookies")
  public LoginPage declineCookiesIfNeed() {
    if (declineCookiesButton.isDisplayed())
      declineCookiesButton.click();
    return this;
  }
}
