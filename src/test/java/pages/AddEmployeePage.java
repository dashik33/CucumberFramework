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

    @FindBy (id="employeeId")
    public WebElement employeeID;

    @FindBy (id="photofile")
    public WebElement uploadPhoto;

    @FindBy (id="chkLogin")
    public WebElement checkbox;

    @FindBy (id="user_name")
    public WebElement createUsername;

    @FindBy (id="user_password")
    public WebElement createPassword;

    @FindBy (id="re_password")
    public WebElement confirmPassword;


    public AddEmployeePage(){
        PageFactory.initElements(driver, this);
    }



}
