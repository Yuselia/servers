package com.servers.test.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CloudServers {
  protected WebDriver driver;
  protected WebDriverWait wait;

  public CloudServers(WebDriver driver, WebDriverWait wait) {
    PageFactory.initElements(driver, this);
    this.driver = driver;
    this.wait = wait;
  }

  @FindBy(xpath = "//a[contains(@title, 'Create server')]")
  private WebElement createButton;

  @Step("Нажать кнопку Создать")
  public CreateCloudServerPage createButtonClick() {
    createButton.click();
    wait.until(ExpectedConditions.presenceOfElementLocated(By.id("location_id")));
    return new CreateCloudServerPage(driver, wait);
  }
}
