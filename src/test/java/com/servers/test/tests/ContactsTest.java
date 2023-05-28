package com.servers.test.tests;

import com.servers.test.Contact;
import com.servers.test.Role;
import com.servers.test.TestBase;
import com.servers.test.pages.ContactEditorPage;
import com.servers.test.pages.ContactInfoPage;
import com.servers.test.pages.ContactsPage;
import com.servers.test.pages.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class ContactsTest extends TestBase {
  public static String contacts_url = main_url + "/profile/contacts";
  public static LoginPage loginPage;
  public static ContactsPage contactsPage;
  public static ContactEditorPage contactEditorPage;
  public static ContactInfoPage contactInfoPage;
  private static Contact contact1 = new Contact("Jon", Role.Abuse)
          .setLastName("Smith")
          .setMiddleName("Alexandrovich")
          .setEmail("test@test.ru")
          .setSecondaryEmail("test2@test.ru")
          .setPhone("+1 (234) 567-80-91980")
          .setCompany("First Company")
          .setJobTitle("Job title")
          .setJobRole("Job role")
          .setNickname("Agent Smith")
          .setComments("Test Comments\nTest Comments\nTest Comments\n1234567890-=!@#$%^*()_+")
          .setContactDetails(new Contact.ContactDetails("URL", "https://test"))
          .setContactDetails(new Contact.ContactDetails("Work phone", "+1 (234) 567-80"));

  @BeforeClass
  public void setUp() {
    super.setUp();
    loginPage = new LoginPage(driver, wait);
    loginPage.declineCookiesIfNeed();
    login(loginPage);
  }

  @BeforeMethod
  public void openContactsPage() {
    driver.get(contacts_url);
    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[contains(@href, '/profile/contacts/new')]")));
    contactsPage = new ContactsPage(driver, wait);
  }

  @AfterClass
  public void tearDown() {
    super.tearDown();
  }

  @Test(description = "Добавление контакта")
  public void addContactTest() {
    int beforeCount = contactsPage.getCountOfContacts();
    createGroup(contact1);

    contactInfoPage.checkInput("First name", contact1.getFirstName())
            .checkInput("Last name", contact1.getLastName())
            .checkInput("Middle name", contact1.getMiddleName())
            .checkInput("Nickname", contact1.getNickname())
            .checkInput("Email", contact1.getEmail())
            .checkInput("Secondary email", contact1.getSecondaryEmail())
            .checkInput("Phone number", contact1.getPhone())
            .checkInput("Role", contact1.getRole())
            .checkInput("Company", contact1.getCompany())
            .checkInput("Job title", contact1.getJobTitle())
            .checkInput("Comments", contact1.getComments());

    openContactsPage();
    int afterCount = contactsPage.getCountOfContacts();
    assertThat("Количество групп увеличилось на единицу", afterCount, is(beforeCount + 1));
  }

  @Test(description = "Удаление контакта")
  public void deleteContactTest() {
    String contactName = contact1.getFirstName() + " " + contact1.getLastName();
    if (!contactsPage.isContactExist(contactName)) {
      createGroup(contact1);
      openContactsPage();
    }
    int beforeCount = contactsPage.getCountOfContacts();

    contactsPage.initDeletingGroupByName(contactName)
            .deleteButtonClick()
            .refreshButtonClick();

    int afterCount = contactsPage.getCountOfContacts();
    assertThat("Количество групп уменьшилось на единицу", afterCount, is(beforeCount - 1));
  }

  private void createGroup(Contact contact) {
    contactEditorPage = contactsPage.createButtonClick();
    contactInfoPage = contactEditorPage.inputFirstName(contact.getFirstName())
            .inputLastName(contact.getLastName())
            .inputMiddleName(contact.getMiddleName())
            .inputEmail(contact.getEmail())
            .inputSecondaryEmail(contact.getSecondaryEmail())
            .inputPhone(contact.getPhone())
            .setRole(contact.getRole())
            .inputCompany(contact.getCompany())
            .inputJobTitle(contact.getJobTitle())
            .inputJobRole(contact.getJobRole())
            .inputNickname(contact.getNickname())
            .inputComments(contact.getComments())
            .addContactDetails(contact.getContactDetails().get(0), 0)
            .addContactDetails(contact.getContactDetails().get(1), 1)
            .createButtonClick();
  }
}
