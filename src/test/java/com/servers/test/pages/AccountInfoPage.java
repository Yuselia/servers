package com.servers.test.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountInfoPage {
  public WebDriver driver;

  public AccountInfoPage(WebDriver driver) {
    PageFactory.initElements(driver, this);
    this.driver = driver;
  }

  @FindBy(name = "business_type")
  private WebElement business_type;

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
}
