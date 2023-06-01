package com.servers.test.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

public class ContactsPage {
  protected WebDriver driver;
  protected WebDriverWait wait;

  public ContactsPage(WebDriver driver, WebDriverWait wait) {
    PageFactory.initElements(driver, this);
    this.driver = driver;
    this.wait = wait;
  }

  @FindBy(css = "colgroup")
  private WebElement table;

  @FindBy(xpath = "//a[contains(@href, '/profile/contacts/new')]")
  private WebElement createButton;

  @FindBy(xpath = "//td[contains(@data-label, 'Name')]/..")
  private List<WebElement> contacts;

  @FindBy(xpath = "//div[contains(@aria-label, 'Confirmation')]")
  private WebElement confirmationDialog;

  @FindBy(xpath = "//button[contains(@title, 'Delete')]")
  private WebElement deleteButton;

  @FindBy(xpath = "//button[contains(@title, 'Refresh')]")
  private WebElement refreshButton;

  @Step("Нажать кнопку Создать")
  public ContactEditorPage createButtonClick() {
    createButton.click();
    return new ContactEditorPage(driver, wait);
  }

  @Step("Получить число контактов")
  public int getCountOfContacts() {
    return contacts.size();
  }

  @Step("Проверка существует ли контакт: {contactName}")
  public boolean isContactExist(String contactName) {
    for (WebElement element: contacts) {
      if (getValue(element, "Name").equals(contactName)) {
        return true;
      }
    }
    return false;
  }

  @Step("Инициировать удаление контакта: {name}")
  public ContactsPage initDeletingGroupByName(String name) {
    WebElement deletingElement = null;
    for (WebElement element: contacts) {
      if (getValue(element, "Name").equals(name)) {
        deletingElement = element;
        break;
      }
    }
    deletingElement.findElement(By.xpath(".//td[6]")).click();
    wait.until(presenceOfElementLocated(By.xpath("//div[contains(@aria-label, 'Confirmation')]")));
    return this;
  }

  @Step("Инициировать редактирование контакта: {name}")
  public ContactInfoPage initEditingGroupByName(String name) {
    WebElement editingElement = null;
    for (WebElement element: contacts) {
      if (getValue(element, "Name").equals(name)) {
        editingElement = element;
        break;
      }
    }
    editingElement.findElement(By.xpath("./td[contains(@data-label, 'Name')]/a")).click();
    return new ContactInfoPage(driver, wait);
  }

  @Step("Нажать кнопку удаления")
  public ContactsPage deleteButtonClick() {
    deleteButton.click();
    driver.navigate().refresh ();
    return this;
  }

  public ContactsPage refreshButtonClick() {
    refreshButton.click();
    table.isDisplayed();
    return this;
  }

  private String getValue(WebElement element, String fieldName) {
    return element.findElement(By.xpath("./td[contains(@data-label, '" + fieldName + "')]/a")).getText();
  }
}
