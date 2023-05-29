package com.servers.test.pages;

import com.servers.test.Contact;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

import static com.servers.test.Role.Abuse;

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

  public ContactEditorPage createButtonClick() {
    createButton.click();
    return new ContactEditorPage(driver, wait);
  }

  public int getCountOfContacts() {
    return contacts.size();
  }

  public List<Contact> getContacts() {
    List<Contact> contactList = new ArrayList<Contact>();
    for (WebElement element: contacts) {
      Contact contact = new Contact(getValue(element, "Name"), Abuse)
              .setNickname(getValue(element, "Nickname"))
              .setEmail(getValue(element, "Emails"))
              .setSecondaryEmail(getValue(element, "Emails"))
              .setPhone(getValue(element, "Phone"));
      contactList.add(contact);
    }
    return contactList;
  }

  public boolean isContactExist(String contactName) {
    for (WebElement element: contacts) {
      if (getValue(element, "Name").equals(contactName)) {
        return true;
      }
    }
    return false;
  }

  public ContactsPage initDeletingGroupByName(String name) {
    WebElement deletingElement = null;
    for (WebElement element: contacts) {
      if (getValue(element, "Name").equals(name)) {
        deletingElement = element;
        break;
      }
    }
    deletingElement.findElement(By.xpath(".//td[6]")).click();
    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(@aria-label, 'Confirmation')]")));
    return this;
  }

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

  public ContactsPage deleteButtonClick() {
    deleteButton.click();
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
