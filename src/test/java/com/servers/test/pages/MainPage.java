package com.servers.test.pages;

import com.servers.test.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class MainPage {
  protected WebDriver driver;
  protected WebDriverWait wait;

  public MainPage(WebDriver driver, WebDriverWait wait) {
    PageFactory.initElements(driver, this);
    this.driver = driver;
    this.wait = wait;
  }

  @FindBy(xpath = "//a[contains(@href, '/cart')]/following::div/button")
  private WebElement account;

  @FindBy(xpath = "//span[text() = 'Logout']")
  private WebElement logoutButton;

  @FindBy(xpath = "//i[contains(@title, 'Dedicated Servers')]")
  private WebElement dedicatedServersMenu;

  @FindBy(xpath = "//a[contains(@href, '/servers/my')]")
  private WebElement manageMenu;

  @FindBy(xpath = "//a[contains(@href, '/servers/order')]")
  private WebElement orderMenu;

  @FindBy(xpath = "//i[contains(@title, 'Cloud Servers')]")
  private WebElement cloudServersMenu;

  @FindBy(xpath = "//span[text() = 'Create & Manage']")
  private WebElement cloudServerCreateAndManageMenu;

  @FindBy(xpath = "//a[contains(@href, '/cloud-computing/snapshots')]")
  private WebElement snapshotsMenu;

  @FindBy(xpath = "//a[contains(@href, '/cloud-computing/images')]")
  private WebElement imagesMenu;

  @FindBy(xpath = "//a[contains(@href, '/cloud-computing/block-storage')]")
  private WebElement volumessMenu;

  @FindBy(xpath = "//i[contains(@title, 'Cloud Storage')]")
  private WebElement cloudStorageMenu;

  @FindBy(xpath = "//i[contains(@title, 'DNS'")
  private WebElement dnsMenu;

  @FindBy(xpath = "//i[contains(@title, 'Load Balancers')]")
  private WebElement loadBalancersMenu;

  @FindBy(xpath = "//i[contains(@title, 'Firewalls')]")
  private WebElement firewallsMenu;

  @FindBy(xpath = "//i[contains(@title, 'Kubernetes (Beta)')]")
  private WebElement kubernetesMenu;

  @FindBy(xpath = "//i[contains(@title, 'Networks')]")
  private WebElement networksMenu;

  @FindBy(xpath = "//a[contains(@href, '/networks/dc')]")
  private WebElement directConnectMenu;

  @FindBy(xpath = "//a[contains(@href, '/networks/l2')]")
  private WebElement segmentsMenu;

  @FindBy(xpath = "//i[contains(@title, 'Private Racks'")
  private WebElement privateRacksMenu;

  @FindBy(xpath = "//i[contains(@title, 'Monitoring')]")
  private WebElement monitoringMenu;

  @FindBy(xpath = "//i[contains(@title, 'Reports')]")
  private WebElement reportsMenu;

  @FindBy(xpath = "//i[contains(@title, 'Requests')]")
  private WebElement requestsMenu;

  @FindBy(xpath = "//i[contains(@title, 'SSL certificates')]")
  private WebElement sslMenu;

  @FindBy(xpath = "//i[contains(@title, 'Profile')]")
  private WebElement profileMenu;

  @FindBy(xpath = "//i[contains(@title, 'Billing')]")
  private WebElement billingMenu;

  @FindBy(xpath = "//div[contains(@aria-label, 'Please fill your account info')]/div[1]/header/button")
  private WebElement closeModalWindowButton;

  public MainPage checkAccountName(String accountName) {
    assertThat("Отображается аккаунт пользователя", this.account.getText(), is(accountName));
    return this;
  }

  public MainPage accountButtonClick() {
    account.click();
    return this;
  }

  public LoginPage logoutButtonClick() {
    logoutButton.click();
    return new LoginPage(driver, wait);
  }

  public MainPage dedicatedServersMenuClick() {
    dedicatedServersMenu.click();
    manageMenu.isDisplayed();
    orderMenu.isDisplayed();
    return this;
  }

  public MainPage manageMenuClick() {
    manageMenu.click();
    wait.until(ExpectedConditions.urlToBe(TestBase.main_url + "/servers/my"));
    return this;
  }

  public MainPage orderMenuClick() {
    orderMenu.click();
    wait.until(ExpectedConditions.urlToBe(TestBase.main_url + "/servers/order"));
    return this;
  }

  public MainPage cloudServersMenuClick() {
    cloudServersMenu.click();
    assertThat(cloudServerCreateAndManageMenu.isDisplayed(), is(true));
    assertThat(cloudServerCreateAndManageMenu.isDisplayed(), is(true));
    assertThat(snapshotsMenu.isDisplayed(), is(true));
    assertThat(imagesMenu.isDisplayed(), is(true));
    assertThat(volumessMenu.isDisplayed(), is(true));
    return this;
  }

  public CloudServers cloudServerCreateAndManageMenuClick() {
    cloudServerCreateAndManageMenu.click();
    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h3/span[text() = 'Cloud Servers']")));
    return new CloudServers(driver, wait);
  }

  public MainPage closeModalWindowButton() {
    closeModalWindowButton.click();
    wait.until(ExpectedConditions.stalenessOf(closeModalWindowButton));
    return this;
  }
}
