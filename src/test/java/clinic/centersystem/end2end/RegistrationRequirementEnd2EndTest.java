package clinic.centersystem.end2end;

import clinic.centersystem.page.ClinicCenterAdminPage;
import clinic.centersystem.page.LoginPage;
import clinic.centersystem.page.RegistrationRequirementPage;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@TestPropertySource("classpath:application-test.properties")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class RegistrationRequirementEnd2EndTest {

    private WebDriver browser;

    private LoginPage loginPage;

    private ClinicCenterAdminPage clinicCenterAdminPage;

    private RegistrationRequirementPage registrationRequirementPage;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedrivers/chromedriver");
        browser = new ChromeDriver();

        browser.manage().window().maximize();
        browser.navigate().to("http://localhost:3000/login");

        loginPage = PageFactory.initElements(browser, LoginPage.class);
        clinicCenterAdminPage = PageFactory.initElements(browser, ClinicCenterAdminPage.class);
        registrationRequirementPage = PageFactory.initElements(browser, RegistrationRequirementPage.class);


        loginPage.ensureIsDisplayedTxtEmail();
        loginPage.getTxtEmail().sendKeys("tomic.miroslav97@gmail.com");
        loginPage.ensureIsDisplayedTxtPass();
        loginPage.getTxtPass().sendKeys("12345");
        loginPage.ensureIsDisplayedBtnLogin();
        loginPage.getBtnLogin().click();

        clinicCenterAdminPage.ensureIsDisplayedBtnRegReqs();
        assertEquals("http://localhost:3000/ccadmin", browser.getCurrentUrl());
    }

    @Test
    @Order(1)
    public void approveRegistrationRequirement() throws InterruptedException {
        clinicCenterAdminPage.ensureIsDisplayedBtnRegReqs();
        clinicCenterAdminPage.getBtnRegReqs().click();

        registrationRequirementPage.ensureIsDisplayedRegReqPag();
        registrationRequirementPage.ensureIsDisplayedRegReqTable();
        int size = registrationRequirementPage.getRowsApprove().size();
        WebElement btn = registrationRequirementPage.getRowsApprove().get(0);
        btn.click();

        registrationRequirementPage.ensureIsDisplayedToastMsg();
        String msg = registrationRequirementPage.getToastMsg().getText();

        assertEquals(size - 1, registrationRequirementPage.getRowsApprove().size());
        assertEquals("Patient registration approved", msg);
        assertEquals("http://localhost:3000/ccadmin/reg-req", browser.getCurrentUrl());
    }

    @Test
    @Order(2)
    public void rejectRegistrationRequirement() throws InterruptedException {
        clinicCenterAdminPage.ensureIsDisplayedBtnRegReqs();
        clinicCenterAdminPage.getBtnRegReqs().click();

        registrationRequirementPage.ensureIsDisplayedRegReqPag();
        registrationRequirementPage.ensureIsDisplayedRegReqTable();
        int size = registrationRequirementPage.getRowsApprove().size();
        WebElement btnModal = registrationRequirementPage.getRowsRefuse().get(0);
        btnModal.click();

        registrationRequirementPage.ensureIsDisplayedRegReqModal();
        registrationRequirementPage.ensureIsDisplayedTxtReason();
        registrationRequirementPage.ensureIsDisplayedBtnReject();

        registrationRequirementPage.getTxtReason().sendKeys("Not enough information");
        registrationRequirementPage.getBtnReject().click();

        registrationRequirementPage.ensureIsDisplayedToastMsg();
        String msg = registrationRequirementPage.getToastMsg().getText();

        assertEquals(size - 1, registrationRequirementPage.getRowsRefuse().size());
        assertEquals("Patient registration rejected", msg);
        assertEquals("http://localhost:3000/ccadmin/reg-req", browser.getCurrentUrl());
    }

    @Test
    @Order(3)
    public void rejectRegistrationRequirementMissingMsg() throws InterruptedException {
        clinicCenterAdminPage.ensureIsDisplayedBtnRegReqs();
        clinicCenterAdminPage.getBtnRegReqs().click();

        registrationRequirementPage.ensureIsDisplayedRegReqPag();
        registrationRequirementPage.ensureIsDisplayedRegReqTable();
        WebElement btnModal = registrationRequirementPage.getRowsRefuse().get(0);
        btnModal.click();

        registrationRequirementPage.ensureIsDisplayedRegReqModal();
        registrationRequirementPage.ensureIsDisplayedTxtReason();
        registrationRequirementPage.ensureIsDisplayedBtnReject();

        registrationRequirementPage.getTxtReason().sendKeys("");
        registrationRequirementPage.getBtnReject().click();

        String msg = registrationRequirementPage.getTxtReason().getText();

        assertEquals("", msg);
        assertEquals("http://localhost:3000/ccadmin/reg-req", browser.getCurrentUrl());
    }

//    @Test
//    @Order(4)
//    public void rejectRegistrationRequirementWrongMail() throws InterruptedException {
//        clinicCenterAdminPage.ensureIsDisplayedBtnRegReqs();
//        clinicCenterAdminPage.getBtnRegReqs().click();
//
//        registrationRequirementPage.ensureIsDisplayedRegReqPag();
//        registrationRequirementPage.ensureIsDisplayedRegReqTable();
//        WebElement btnModal = registrationRequirementPage.getRowsRefuse().get(5);
//        btnModal.click();
//
//        registrationRequirementPage.ensureIsDisplayedRegReqModal();
//        registrationRequirementPage.ensureIsDisplayedTxtReason();
//        registrationRequirementPage.ensureIsDisplayedBtnReject();
//
//        registrationRequirementPage.getTxtReason().sendKeys("Not enough information");
//        registrationRequirementPage.getBtnReject().click();
//
//        registrationRequirementPage.ensureIsDisplayedToastMsg();
//        String msg = registrationRequirementPage.getToastMsg().getText();
//
//        assertEquals("Email address is invalid", msg);
//        assertEquals("http://localhost:3000/ccadmin/reg-req", browser.getCurrentUrl());
//    }

    @Test
    @Order(4)
    public void rejectRegistrationRequirementWhenUserExists() throws InterruptedException {
        clinicCenterAdminPage.ensureIsDisplayedBtnRegReqs();
        clinicCenterAdminPage.getBtnRegReqs().click();

        registrationRequirementPage.ensureIsDisplayedRegReqPag();
        registrationRequirementPage.ensureIsDisplayedRegReqTable();
        WebElement btnModal = registrationRequirementPage.getRowsRefuse().get(2);
        btnModal.click();

        registrationRequirementPage.ensureIsDisplayedRegReqModal();
        registrationRequirementPage.ensureIsDisplayedTxtReason();
        registrationRequirementPage.ensureIsDisplayedBtnReject();

        registrationRequirementPage.getTxtReason().sendKeys("Not enough information");
        registrationRequirementPage.getBtnReject().click();

        registrationRequirementPage.ensureIsDisplayedToastMsg();
        String msg = registrationRequirementPage.getToastMsg().getText();

        assertEquals("User with email tomic.miroslav97@gmail.com already exists", msg);
        assertEquals("http://localhost:3000/ccadmin/reg-req", browser.getCurrentUrl());
    }

    @Test
    @Order(5)
    public void approveRegistrationRequirementWhenUserExists() throws InterruptedException {
        clinicCenterAdminPage.ensureIsDisplayedBtnRegReqs();
        clinicCenterAdminPage.getBtnRegReqs().click();

        registrationRequirementPage.ensureIsDisplayedRegReqPag();
        registrationRequirementPage.ensureIsDisplayedRegReqTable();
        WebElement btnModal = registrationRequirementPage.getRowsApprove().get(6);
        btnModal.click();


        registrationRequirementPage.ensureIsDisplayedToastMsg();
        String msg = registrationRequirementPage.getToastMsg().getText();

        assertEquals("User with email tomic.miroslav97@gmail.com already exists", msg);
        assertEquals("http://localhost:3000/ccadmin/reg-req", browser.getCurrentUrl());
    }


    @After
    public void tearDown() {
        browser.close();
    }
}
