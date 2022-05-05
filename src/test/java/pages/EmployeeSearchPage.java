package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.CommonMethods;

public class EmployeeSearchPage extends CommonMethods {

//object repository
    @FindBy (id = "menu_pim_viewPimModule")
    public WebElement pimOption;

    @FindBy (id = "menu_pim_addEmployee")
    public WebElement addEmployeeOption;

    @FindBy (id = "menu_pim_viewEmployeeList")
    public WebElement empListOption;

    @FindBy (id = "empsearch_id")
    public WebElement searchID;

    @FindBy (id = "empsearch_employee_name_empName")
    public WebElement searchName;

    @FindBy (id = "searchBtn")
    public WebElement searchBtn;

    public EmployeeSearchPage(){
        PageFactory.initElements(driver, this);
    }
}
