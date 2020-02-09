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
public class ClinicCenterAdminPage {

    private WebDriver driver;

    @FindBy(xpath = "//*[@id=\"btnRegReqs\"]")
    private WebElement btnRegReqs;

    public ClinicCenterAdminPage() {
    }

    public ClinicCenterAdminPage(WebDriver driver) {
        this.driver = driver;
    }

    public void ensureIsDisplayedBtnRegReqs() {
        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.elementToBeClickable(btnRegReqs));
    }


}
