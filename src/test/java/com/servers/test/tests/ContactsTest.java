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
  private static LoginPage loginPage;
  private static ContactsPage contactsPage;
  private static ContactEditorPage contactEditorPage;
  private static ContactInfoPage contactInfoPage;
  private static final String contacts_url = main_url + "/profile/contacts";
  private static final Contact contact1 = new Contact("Jon", Role.Abuse)
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
          .setContactDetails(new Contact.ContactDetails("Fax", "1234"))
          .setContactDetails(new Contact.ContactDetails("Work phone", "+1 (234) 567-80"));
  private static final Contact contact2 = new Contact("Tom", Role.Emergency)
          .setLastName("Hardy")
          .setMiddleName("Edvard")
          .setEmail("test1@test.ru")
          .setSecondaryEmail("test3@test.ru")
          .setPhone("786876876979879")
          .setCompany("Second Company")
          .setJobTitle("Edited Job title")
          .setJobRole("Edited Job role")
          .setNickname("Tom")
          .setComments("1234567890-=!@#$%^*()_+\nNew comment")
          .setContactDetails(new Contact.ContactDetails("Cellphone", "123"))
          .setContactDetails(new Contact.ContactDetails("Fax", "+12345"));
  private static String contactName1;
  private static String contactName2;

  @BeforeClass
  public void setUp() {
    super.setUp();
    loginPage = new LoginPage(driver, wait);
    loginPage.declineCookiesIfNeed();
    login(loginPage, TestBase.user);
    contactName1 = contact1.getFirstName() + " " + contact1.getLastName();
    contactName2 = contact2.getFirstName() + " " + contact2.getLastName();
  }

  @BeforeMethod
  public void openContactsPage() {
    driver.get(contacts_url);
    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[contains(@href, '/profile/contacts/new')]")));
    contactsPage = new ContactsPage(driver, wait);
  }

  @AfterClass
  public void tearDown() {
    if (contactsPage.isContactExist(contactName1)) {
      deleteContact(contactName1);
    }
    if (contactsPage.isContactExist(contactName2)) {
      deleteContact(contactName2);
    }
    super.tearDown();
  }

  @Test(description = "Добавление контакта")
  public void addContactTest() {
    String contactName = contact1.getFirstName() + " " + contact1.getLastName();
    int beforeCount = contactsPage.getCountOfContacts();
    createGroup(contact1);
    checkContactField(contact1);

    openContactsPage();
    int afterCount = contactsPage.getCountOfContacts();
    assertThat("Количество контактов увеличилось на единицу", afterCount, is(beforeCount + 1));
    assertThat("Присутствует контакт с указанным именем", contactsPage.isContactExist(contactName), is(true));
  }

  @Test(description = "Редактирование контакта")
  public void editContactTest() {
    if (!contactsPage.isContactExist(contactName1)) {
      createGroup(contact1);
      openContactsPage();
    }
    int beforeCount = contactsPage.getCountOfContacts();

    contactInfoPage = contactsPage.initEditingGroupByName(contactName1);
    ContactEditorPage contactEditorPage = contactInfoPage.editButtonClick();

    fillContactFields(contact2, contactEditorPage);
    contactInfoPage = contactEditorPage.submitButtonClick();
    checkContactField(contact2);

    openContactsPage();
    int afterCount = contactsPage.getCountOfContacts();
    assertThat("Количество контактов не изменилось", afterCount, is(beforeCount));
    assertThat("Присутствует контакт с указанным именем",
            contactsPage.isContactExist(contact2.getFirstName() + " " + contact2.getLastName()), is(true));
  }

  @Test(description = "Удаление контакта")
  public void deleteContactTest() {
    String contactName = contact1.getFirstName() + " " + contact1.getLastName();
    if (!contactsPage.isContactExist(contactName)) {
      createGroup(contact1);
      openContactsPage();
    }
    int beforeCount = contactsPage.getCountOfContacts();

    deleteContact(contactName);

    int afterCount = contactsPage.getCountOfContacts();
    assertThat("Количество контактов уменьшилось на единицу", afterCount, is(beforeCount - 1));
  }

  private void createGroup(Contact contact) {
    contactEditorPage = contactsPage.createButtonClick();
    fillContactFields(contact, contactEditorPage);
    contactInfoPage = contactEditorPage.submitButtonClick();
  }

  private void fillContactFields(Contact contact, ContactEditorPage contactEditorPage) {
    contactEditorPage.inputFirstName(contact.getFirstName())
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
            .addContactDetails(contact.getContactDetails().get(1), 1);
  }

  private void checkContactField(Contact contact) {
    contactInfoPage.checkInput("First name", contact.getFirstName())
            .checkInput("Last name", contact.getLastName())
            .checkInput("Middle name", contact.getMiddleName())
            .checkInput("Nickname", contact.getNickname())
            .checkInput("Email", contact.getEmail())
            .checkInput("Secondary email", contact.getSecondaryEmail())
            .checkInput("Phone number", contact.getPhone())
            .checkInput("Role", contact.getRole())
            .checkInput("Company", contact.getCompany())
            .checkInput("Job title", contact.getJobTitle())
            .checkInput("Comments", contact.getComments())
            .checkContactDetails(contact.getContactDetails());
  }

  private void deleteContact(String contactName) {
    openContactsPage();
    contactsPage.initDeletingGroupByName(contactName)
            .deleteButtonClick();
  }
}
