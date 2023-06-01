package com.servers.test.pages;

import com.servers.test.Contact;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class ContactInfoPage {
  protected WebDriver driver;
  protected WebDriverWait wait;

  public ContactInfoPage(WebDriver driver, WebDriverWait wait) {
    PageFactory.initElements(driver, this);
    this.driver = driver;
    this.wait = wait;
  }

  @FindBy(xpath = "//button[contains(@title, 'Refresh')]")
  private WebElement refreshButton;

  @FindBy(xpath = "//button[contains(@title, 'Edit')]")
  private WebElement editButton;

  @Step("Нажать кнопку редактирования")
  public ContactEditorPage editButtonClick() {
    editButton.click();
    return new ContactEditorPage(driver, wait);
  }

  @Step("Проверка поля: {name}")
  public ContactInfoPage checkInput(String name, String value) {
    WebElement element = driver.findElement(By.xpath("//*[text() = '" + name + "']/following-sibling::div"));
    if (value!=null)
      assertThat("Поле: " + name + " корректно", element.getText(), is(value));
    else
      assertThat("Поле: " + name + " корректно", element.getText(), is(""));
    return this;
  }

  @Step("Проверка доп.информации контакта")
  public ContactInfoPage checkContactDetails(List<Contact.ContactDetails> contactDetails) {
    for (Contact.ContactDetails cdetails:
            contactDetails) {
      WebElement element = driver
              .findElement(By.xpath("//*[text() = 'Contact details']/" +
                      "following-sibling::div//span[text() = '" + cdetails.getName() + "']/../.."));
        assertThat("Поле: " + cdetails.getName() + " корректно", element.getText(),
                is(cdetails.getName() + ": " + cdetails.getValue()));
    }
    return this;
  }
}
