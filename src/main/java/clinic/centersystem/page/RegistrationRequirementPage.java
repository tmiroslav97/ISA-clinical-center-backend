package clinic.centersystem.page;

import lombok.Getter;
import lombok.Setter;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

@Getter
@Setter
public class RegistrationRequirementPage {

    private WebDriver driver;

    @FindBy(xpath = "//*[@id=\"regReqPag\"]")
    private WebElement regReqPag;

    @FindBy(xpath = "//*[@id=\"regReqTable\"]")
    private WebElement regReqTable;

    @FindBy(xpath = "//*[@id=\"regReqTable\"]/tbody/tr/td[4]/button")
    private List<WebElement> rowsApprove;

    @FindBy(xpath = "//*[@id=\"regReqTable\"]/tbody/tr/td[5]/button")
    private List<WebElement> rowsRefuse;

    @FindBy(xpath = "/html/body/div[2]/div/div/div[2]")
    private WebElement toastMsg;

    @FindBy(xpath = "//*[@id=\"regReqModal\"]")
    private WebElement regReqModal;

    @FindBy(xpath = "//*[@id=\"txtReason\"]")
    private WebElement txtReason;

    @FindBy(xpath = "//*[@id=\"btnReject\"]")
    private WebElement btnReject;

    public RegistrationRequirementPage() {
    }

    public RegistrationRequirementPage(WebDriver driver) {
        this.driver = driver;
    }

    public void ensureIsDisplayedTxtReason() {
        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.visibilityOf(txtReason));
    }

    public void ensureIsDisplayedBtnReject() {
        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.visibilityOf(btnReject));
    }

    public void ensureIsDisplayedRegReqModal() {
        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.visibilityOf(regReqModal));
    }


    public void ensureIsDisplayedToastMsg() {
        (new WebDriverWait(driver, 20))
                .until(ExpectedConditions.visibilityOf(toastMsg));
    }

    public void ensureIsDisplayedRegReqPag() {
        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.visibilityOf(regReqPag));
    }

    public void ensureIsDisplayedRegReqTable() {
        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.visibilityOf(regReqTable));
    }

}
