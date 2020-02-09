package clinic.centersystem.page;

import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

@Getter
@Setter
public class LoginPage {

    private WebDriver driver;

    @FindBy(xpath = "//*[@id=\"txtEmail\"]")
    private WebElement txtEmail;

    @FindBy(xpath = "//*[@id=\"txtPass\"]")
    private WebElement txtPass;

    @FindBy(xpath = "//*[@id=\"btnLogin\"]")
    private WebElement btnLogin;

    public LoginPage() {
    }

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void ensureIsDisplayedBtnLogin() {
        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.elementToBeClickable(btnLogin));
    }

    public void ensureIsDisplayedTxtEmail() {
        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.elementToBeClickable(txtEmail));
    }

    public void ensureIsDisplayedTxtPass() {
        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.elementToBeClickable(txtPass));
    }
}
