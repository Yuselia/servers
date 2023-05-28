package com.servers.test.pages;

import com.servers.test.Contact;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static com.codeborne.selenide.files.DownloadActions.click;

public class ContactEditorPage {
  public WebDriver driver;
  public WebDriverWait wait;

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

  @FindBy(xpath = "//button[contains(@title, 'Create')]")
  private WebElement createButton;

  public ContactEditorPage inputFirstName(String firstName) {
    fname.sendKeys(firstName);
    return this;
  }

  public ContactEditorPage inputLastName(String lastName) {
    lname.sendKeys(lastName);
    return this;
  }

  public ContactEditorPage inputMiddleName(String middlename) {
    this.middlename.sendKeys(middlename);
    return this;
  }

  public ContactEditorPage inputEmail(String email) {
    this.email.sendKeys(email);
    return this;
  }

  public ContactEditorPage inputSecondaryEmail(String email) {
    email2.sendKeys(email);
    return this;
  }

  public ContactEditorPage inputPhone(String phone) {
    this.phone.sendKeys(phone);
    return this;
  }

  public ContactEditorPage setRole(String role) {
    WebElement element = this.role.findElement(By.xpath(".//*[text() = '" + role + "']"));
    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    element.click();
    return this;
  }

  public ContactEditorPage inputCompany(String company) {
    this.company.sendKeys(company);
    return this;
  }

  public ContactEditorPage inputJobTitle(String title) {
    this.jobTitle.sendKeys(title);
    return this;
  }

  public ContactEditorPage inputJobRole(String jobRole) {
    this.jobRole.sendKeys(jobRole);
    return this;
  }

  public ContactEditorPage inputNickname(String nickname) {
    this.nickname.sendKeys(nickname);
    return this;
  }

  public ContactEditorPage inputComments(String comments) {
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
    driver.findElement(By.xpath("//div[contains(@id, 'contacts')]//input[contains(@name, 'contacts[" + index + "].value')]"))
            .sendKeys(contactDetails.getValue());
    return this;
  }

  public ContactInfoPage createButtonClick() {
    createButton.click();
    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("[name=fname]")));
    wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//*[text() = 'Contact info']")));
    return new ContactInfoPage(driver, wait);
  }
}
