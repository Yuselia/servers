package com.servers.test.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class ContactInfoPage {
  public WebDriver driver;
  public WebDriverWait wait;

  public ContactInfoPage(WebDriver driver, WebDriverWait wait) {
    PageFactory.initElements(driver, this);
    this.driver = driver;
    this.wait = wait;
  }

  public ContactInfoPage checkInput(String name, String value) {
    WebElement element = driver.findElement(By.xpath("//*[text() = '" + name + "']/following-sibling::div"));
    if (value!=null)
      assertThat("Поле: " + name + " корректно", element.getText(), is(value));
    else
      assertThat("Поле: " + name + " корректно", element.getText(), is(""));
    return this;
  }
}
