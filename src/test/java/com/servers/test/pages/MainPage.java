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
  private WebElement volumesMenu;

  @FindBy(xpath = "//i[contains(@title, 'Cloud Storage')]")
  private WebElement cloudStorageMenu;

  @FindBy(xpath = "//i[contains(@title, 'DNS')]")
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

  @FindBy(xpath = "//i[contains(@title, 'Private Racks')]")
  private WebElement privateRacksMenu;

  @FindBy(xpath = "//i[contains(@title, 'Monitoring')]")
  private WebElement monitoringMenu;

  @FindBy(xpath = "//a[contains(@href, '/monitoring/healthchecks')]")
  private WebElement healthchecksMenu;

  @FindBy(xpath = "//a[contains(@href, '/monitoring/notifications')]")
  private WebElement mnotificationsMenu;

  @FindBy(xpath = "//i[contains(@title, 'Reports')]")
  private WebElement reportsMenu;

  @FindBy(xpath = "//a[contains(@href, '/cloud-storage/reports')]")
  private WebElement cloudStorageReportsMenu;

  @FindBy(xpath = "//a[contains(@href, '/cloud-computing/reports')]")
  private WebElement cloudComputingReportsMenu;

  @FindBy(xpath = "//i[contains(@title, 'Requests')]")
  private WebElement requestsMenu;

  @FindBy(xpath = "//i[contains(@title, 'SSL certificates')]")
  private WebElement sslMenu;

  @FindBy(xpath = "//i[contains(@title, 'Profile')]")
  private WebElement profileMenu;

  @FindBy(xpath = "//a[contains(@href, '/profile/info')]")
  private WebElement accountMenu;

  @FindBy(xpath = "//a[contains(@href, '/profile/contacts')]")
  private WebElement contactsMenu;

  @FindBy(xpath = "//a[contains(@href, '/profile/groups')]")
  private WebElement groupsMenu;

  @FindBy(xpath = "//a[contains(@href, '/profile/sshkeys')]")
  private WebElement sshkeysMenu;

  @FindBy(xpath = "//a[contains(@href, '/profile/vpn')]")
  private WebElement vpnMenu;

  @FindBy(xpath = "//a[contains(@href, '/profile/api-tokens')]")
  private WebElement apiTokentsMenu;

  @FindBy(xpath = "//i[contains(@title, 'Billing')]")
  private WebElement billingMenu;

  @FindBy(xpath = "//a[contains(@href, '/billing/orders')]")
  private WebElement ordersMenu;

  @FindBy(xpath = "//a[contains(@href, '/billing/invoices')]")
  private WebElement invoicesMenu;

  @FindBy(xpath = "//a[contains(@href, '/billing/group-invoices')]")
  private WebElement groupInvoicesMenu;

  @FindBy(xpath = "//a[contains(@href, '/billing/statement')]")
  private WebElement statementMenu;

  @FindBy(xpath = "//a[contains(@href, '/payment/methods')]")
  private WebElement paymentDetailsMenu;

  @FindBy(xpath = "//a[contains(@href, '/payment/pay')]")
  private WebElement paymentPayMenu;

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
    assertThat(volumesMenu.isDisplayed(), is(true));
    return this;
  }

  public CloudServers cloudServerCreateAndManageMenuClick() {
    cloudServerCreateAndManageMenu.click();
    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h3/span[text() = 'Cloud Servers']")));
    return new CloudServers(driver, wait);
  }

  public MainPage snapshotsMenuClick() {
    snapshotsMenu.click();
    wait.until(ExpectedConditions.urlToBe(TestBase.main_url + "/cloud-computing/snapshots"));
    return this;
  }

  public MainPage imagesMenuClick() {
    imagesMenu.click();
    wait.until(ExpectedConditions.urlToBe(TestBase.main_url + "/cloud-computing/images"));
    return this;
  }

  public MainPage volumesMenuClick() {
    volumesMenu.click();
    wait.until(ExpectedConditions.urlToBe(TestBase.main_url + "/cloud-computing/block-storage/info"));
    return this;
  }

  public MainPage cloudStorageMenuClick() {
    cloudStorageMenu.click();
    wait.until(ExpectedConditions.urlToBe(TestBase.main_url + "/cloud-storage/0/info"));
    return this;
  }

  public MainPage dnsMenuClick() {
    dnsMenu.click();
    wait.until(ExpectedConditions.urlToBe(TestBase.main_url + "/dns"));
    return this;
  }

  public MainPage loadBalancersMenuClick() {
    loadBalancersMenu.click();
    wait.until(ExpectedConditions.urlToBe(TestBase.main_url + "/lb"));
    return this;
  }

  public MainPage firewallsMenuClick() {
    firewallsMenu.click();
    wait.until(ExpectedConditions.urlToBe(TestBase.main_url + "/firewalls"));
    return this;
  }

  public MainPage kubernetesMenuClick() {
    kubernetesMenu.click();
    wait.until(ExpectedConditions.urlToBe(TestBase.main_url + "/k8s"));
    return this;
  }

  public MainPage networksMenuClick() {
    networksMenu.click();
    assertThat(directConnectMenu.isDisplayed(), is(true));
    assertThat(segmentsMenu.isDisplayed(), is(true));
    return this;
  }

  public MainPage privateRacksClick() {
    privateRacksMenu.click();
    wait.until(ExpectedConditions.urlToBe(TestBase.main_url + "/private-racks"));
    return this;
  }

  public MainPage monitoringMenuClick() {
    monitoringMenu.click();
    assertThat(healthchecksMenu.isDisplayed(), is(true));
    assertThat(mnotificationsMenu.isDisplayed(), is(true));
    return this;
  }

  public MainPage reportsMenuClick() {
    reportsMenu.click();
    assertThat(cloudStorageReportsMenu.isDisplayed(), is(true));
    assertThat(cloudComputingReportsMenu.isDisplayed(), is(true));
    return this;
  }

  public MainPage requestsClick() {
    requestsMenu.click();
    wait.until(ExpectedConditions.urlToBe(TestBase.main_url + "/requests"));
    return this;
  }

  public MainPage sslCertificatesClick() {
    sslMenu.click();
    wait.until(ExpectedConditions.urlToBe(TestBase.main_url + "/ssl"));
    return this;
  }

  public MainPage profileMenuClick() {
    profileMenu.click();
    assertThat(accountMenu.isDisplayed(), is(true));
    assertThat(contactsMenu.isDisplayed(), is(true));
    assertThat(groupsMenu.isDisplayed(), is(true));
    assertThat(sshkeysMenu.isDisplayed(), is(true));
    assertThat(vpnMenu.isDisplayed(), is(true));
    assertThat(apiTokentsMenu.isDisplayed(), is(true));
    return this;
  }

  public MainPage billingClick() {
    billingMenu.click();
    assertThat(ordersMenu.isDisplayed(), is(true));
    assertThat(invoicesMenu.isDisplayed(), is(true));
    assertThat(groupInvoicesMenu.isDisplayed(), is(true));
    assertThat(statementMenu.isDisplayed(), is(true));
    assertThat(paymentDetailsMenu.isDisplayed(), is(true));
    assertThat(paymentPayMenu.isDisplayed(), is(true));
    return this;
  }

  public MainPage closeModalWindowButton() {
    closeModalWindowButton.click();
    wait.until(ExpectedConditions.stalenessOf(closeModalWindowButton));
    return this;
  }
}
