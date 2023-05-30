package com.servers.test.tests;

import com.servers.test.TestBase;
import com.servers.test.pages.LoginPage;
import com.servers.test.pages.MainPage;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class MenuTest extends TestBase {
  private static LoginPage loginPage;
  private static MainPage mainPage;

  @BeforeClass
  public void setUp() {
    super.setUp();
    loginPage = new LoginPage(driver, wait);
    loginPage.declineCookiesIfNeed();
    mainPage = login(loginPage, TestBase.user);
  }

  @AfterClass
  public void tearDown() {
    super.tearDown();
  }

  @Test(description = "Проверка меню")
  public void menuTest() {
    mainPage.dedicatedServersMenuClick()
            .manageMenuClick()
            .orderMenuClick()
            .cloudServersMenuClick()
            .cloudServerCreateAndManageMenuClick();
    mainPage.snapshotsMenuClick()
            .imagesMenuClick()
            .volumesMenuClick()
            .cloudStorageMenuClick()
            .dnsMenuClick()
            .loadBalancersMenuClick()
            .firewallsMenuClick()
            .kubernetesMenuClick()
            .networksMenuClick()
            .privateRacksClick()
            .monitoringMenuClick()
            .reportsMenuClick()
            .requestsClick()
            .sslCertificatesClick()
            .profileMenuClick()
            .billingClick();
  }
}
