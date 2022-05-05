package steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebElement;
import utils.CommonMethods;

import java.util.List;
import java.util.Map;

public class AddEmployeeSteps extends CommonMethods {

    @When("user clicks on add employee option")
    public void user_navigates_on_add_employee_option() {
        click(employeeSearch.addEmployeeOption);
    }

    @When("user enters firstname, middlename and lastname")
    public void user_enters_firstname_middlename_and_lastname() {
        sendText(addEmployee.firstname, "Elon");
        sendText(addEmployee.middlename, "Gavrilovich");
        sendText(addEmployee.lastname, "Musk");
    }

    @When("user clicks on save button")
    public void user_clicks_on_save_button() {
        click(addEmployee.saveBtn);
    }

    @Then("the new employee is successfully added")
    public void the_new_employee_is_successfully_added() {
        System.out.println("Employee added");
    }

    @Then("user enters {string}, {string} and {string}")
    public void user_enters_and(String firstNameValue, String middleNameValue, String lastNameValue) {
        sendText(addEmployee.firstname, firstNameValue);
        sendText(addEmployee.firstname, middleNameValue);
        sendText(addEmployee.firstname, lastNameValue);
    }

    @Then("user provides {string}, {string} and {string}")
    public void user_provides_and(String firstName, String middleName, String lastName) {
        sendText(addEmployee.firstname, firstName);
        sendText(addEmployee.firstname, middleName);
        sendText(addEmployee.firstname, lastName);
    }

    @When("user provides multiple employee data and verify they are added")
    public void user_provides_multiple_employee_data_and_verify_they_are_added(DataTable dataTable) throws InterruptedException {
        List <Map<String,String>> employeeNames= dataTable.asMaps(); //storing dataTable in a List
        for (Map<String,String> employeeName:employeeNames){
            String firstNameValue= employeeName.get("firstName");
            String middleNameValue= employeeName.get("middleName");
            String lastNameValue= employeeName.get("lastName");
            System.out.println(firstNameValue+ " " + middleNameValue+ " " +lastNameValue);

            sendText(addEmployee.firstname, firstNameValue);
            sendText(addEmployee.middlename, middleNameValue);
            sendText(addEmployee.lastname, lastNameValue);

            click(addEmployee.saveBtn);
            Thread.sleep(3000);

            String fullEmployeeName=firstNameValue+" "+middleNameValue+" "+lastNameValue;
            if (addEmployee.addedEmployeeVerification.getText().equals(fullEmployeeName)){
                System.out.println(fullEmployeeName+" is added successfully");
            }
            click(employeeSearch.addEmployeeOption);
        }
    }




}
