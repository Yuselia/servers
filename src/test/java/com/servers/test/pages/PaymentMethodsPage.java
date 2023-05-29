package com.servers.test.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PaymentMethodsPage {
  protected WebDriver driver;
  protected WebDriverWait wait;

  public PaymentMethodsPage(WebDriver driver, WebDriverWait wait) {
    PageFactory.initElements(driver, this);
    this.driver = driver;
    this.wait = wait;
  }

  @FindBy(xpath = "//h3/span[text() = 'Payment methods']")
  private WebElement paymentMethods;

  @FindBy(xpath = "//button[contains(@title, 'Add new card')]")
  private WebElement addNewCardButton;

  public MainPage createButtonClick() {
    addNewCardButton.click();
    wait.until(ExpectedConditions.stalenessOf(paymentMethods));
    return new MainPage(driver, wait);
  }
}
