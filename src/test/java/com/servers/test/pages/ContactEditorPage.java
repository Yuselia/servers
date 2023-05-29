package com.servers.test.pages;

import com.servers.test.Contact;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class ContactEditorPage {
  protected WebDriver driver;
  protected WebDriverWait wait;

  public ContactEditorPage(WebDriver driver, WebDriverWait wait) {
    PageFactory.initElements(driver, this);
    this.driver = driver;
    this.wait = wait;
  }

  @FindBy(name = "fname")
  private WebElement fname;

  @FindBy(name = "lname")
  private WebElement lname;

  @FindBy(name = "tokens.middlename")
  private WebElement middlename;

  @FindBy(name = "email")
  private WebElement email;

  @FindBy(name = "email2")
  private WebElement email2;

  @FindBy(name = "phone_number")
  private WebElement phone;

  @FindBy(id = "role")
  private WebElement role;

  @FindBy(name = "tokens.company")
  private WebElement company;

  @FindBy(name = "tokens.title")
  private WebElement jobTitle;

  @FindBy(name = "tokens.role")
  private WebElement jobRole;

  @FindBy(name = "nickname")
  private WebElement nickname;

  @FindBy(name = "tokens.note")
  private WebElement comments;

  @FindBy(xpath = "//button[contains(@title, 'Add more details')]")
  private WebElement addDetailsButton;

  @FindBy(xpath = "//div[contains(@id, 'contacts')]//div[contains(@class, 'select__indicators')]")
  private List<WebElement> contactNameSelectButtons;

  @FindBy(xpath = "//button[contains(@type, 'submit')]")
  private WebElement submitButton;

  public ContactEditorPage inputFirstName(String firstName) {
    fname.clear();
    fname.sendKeys(firstName);
    return this;
  }

  public ContactEditorPage inputLastName(String lastName) {
    lname.clear();
    lname.sendKeys(lastName);
    return this;
  }

  public ContactEditorPage inputMiddleName(String middlename) {
    this.middlename.clear();
    this.middlename.sendKeys(middlename);
    return this;
  }

  public ContactEditorPage inputEmail(String email) {
    this.email.clear();
    this.email.sendKeys(email);
    return this;
  }

  public ContactEditorPage inputSecondaryEmail(String email) {
    email2.clear();
    email2.sendKeys(email);
    return this;
  }

  public ContactEditorPage inputPhone(String phone) {
    this.phone.clear();
    this.phone.sendKeys(phone);
    return this;
  }

  public ContactEditorPage setRole(String role) {
    List<WebElement> elements = this.role.findElements(By.xpath(".//child::input"));
    for (int i = 1; i < elements.size(); i ++) {
      if (elements.get(i).getAttribute("checked")!=null) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", elements.get(i));
        elements.get(i).click();
      }
    }
    WebElement element = this.role.findElement(By.xpath(".//*[text() = '" + role + "']"));
    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    element.click();
    return this;
  }

  public ContactEditorPage inputCompany(String company) {
    this.company.clear();
    this.company.sendKeys(company);
    return this;
  }

  public ContactEditorPage inputJobTitle(String title) {
    this.jobTitle.clear();
    this.jobTitle.sendKeys(title);
    return this;
  }

  public ContactEditorPage inputJobRole(String jobRole) {
    this.jobRole.clear();
    this.jobRole.sendKeys(jobRole);
    return this;
  }

  public ContactEditorPage inputNickname(String nickname) {
    this.nickname.clear();
    this.nickname.sendKeys(nickname);
    return this;
  }

  public ContactEditorPage inputComments(String comments) {
    this.comments.clear();
    this.comments.sendKeys(comments);
    return this;
  }

  public ContactEditorPage addDetailsButtonClick() {
    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", addDetailsButton);
    addDetailsButton.click();
    return this;
  }

  public ContactEditorPage addContactDetails(Contact.ContactDetails contactDetails, int index) {
    addDetailsButtonClick();
    WebElement contactNameSelectButton = contactNameSelectButtons.get(index);
    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", contactNameSelectButton);
    contactNameSelectButton.click();
    driver.findElement(By.xpath("//*[text() = '" + contactDetails.getName() + "']")).click();
    WebElement input = driver.findElement(By.xpath("//div[contains(@id, 'contacts')]" +
            "//input[contains(@name, 'contacts[" + index + "].value')]"));
    input.clear();
    input.sendKeys(contactDetails.getValue());
    return this;
  }

  public ContactInfoPage submitButtonClick() {
    submitButton.click();
    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("[name=fname]")));
    wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//*[text() = 'Contact info']")));
    return new ContactInfoPage(driver, wait);
  }
}
