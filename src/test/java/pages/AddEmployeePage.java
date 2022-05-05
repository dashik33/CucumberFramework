package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.CommonMethods;

public class AddEmployeePage  extends CommonMethods {


    @FindBy (id = "firstName")
    public WebElement firstname;

    @FindBy (id = "middleName")
    public WebElement middlename;

    @FindBy (id = "lastName")
    public WebElement lastname;

    @FindBy (id = "btnSave")
    public WebElement saveBtn;

    @FindBy (xpath="//div[@id='profile-pic']/h1")
    public WebElement addedEmployeeVerification;

    public AddEmployeePage(){
        PageFactory.initElements(driver, this);
    }



}
