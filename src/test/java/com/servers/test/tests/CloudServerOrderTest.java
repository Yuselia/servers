package com.servers.test.tests;

import com.servers.test.TestBase;
import com.servers.test.pages.CreateCloudServerPage;
import com.servers.test.pages.LoginPage;
import com.servers.test.pages.MainPage;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CloudServerOrderTest extends TestBase {
  private static LoginPage loginPage;
  private static MainPage mainPage;
  private static final String[] locations = {"Amsterdam - az2", "Amsterdam - az3", "Amsterdam - az4",
          "Dallas", "Luxembourg", "San Jose", "Singapore", "Washington"};
  private String location;

  @BeforeClass
  public void setUp() {
    super.setUp();
    loginPage = new LoginPage(driver, wait);
    loginPage.declineCookiesIfNeed();
    mainPage = login(loginPage, TestBase.user);
  }

  @BeforeMethod
  public void init() {
    location = locations[(int)(Math.random()*locations.length)];
  }

  @AfterClass
  public void tearDown() {
    super.tearDown();
  }

  @Test(description = "Заказ облачного сервера")
  public void cloudServerOrderTest() {
    CreateCloudServerPage createCloudServerPage = mainPage.cloudServersMenuClick()
            .cloudServerCreateAndManageMenuClick()
            .createButtonClick()
            .chooseLocation(location);
    String image = createCloudServerPage.chooseRandomImage();
    String price = createCloudServerPage.chooseRandomConfigurationAndGetPrice();
    createCloudServerPage.checkPrice(price);
    if (!image.contains("Windows")) {
      createCloudServerPage
              .choosePassword()
              .chooseSSHKey();
    }
    createCloudServerPage.setBackupCopies(4)
            .setAddIpAddressCheckbox()
            .setName("test name")
            .createButtonClick();
  }
}
