package steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.CommonMethods;
import utils.Constants;
import utils.ExcelReader;

import java.util.Iterator;
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

    @When("user adds multiple employees from excel file using {string} sheet and verify they are added")
    public void user_adds_multiple_employees_from_excel_file_using_sheet_and_verify_they_are_added(String sheetName) throws InterruptedException {
       List<Map<String,String>> employeeNames=ExcelReader.excelIntoMap(Constants.TESTDATA_FILEPATH, sheetName);

        Iterator<Map<String,String>> itr=employeeNames.iterator();
        while(itr.hasNext()){
            //it returns a key and value for an employee
            Map<String,String> mapNewEmp=itr.next();

            //filling all the fields with data coming from excel file
            sendText(addEmployee.firstname, mapNewEmp.get("FirstName"));
            sendText(addEmployee.middlename, mapNewEmp.get("MiddleName"));
            sendText(addEmployee.lastname, mapNewEmp.get("LastName"));

            //it will fetch the employee id from attribute
            String empIDValue=addEmployee.employeeID.getAttribute("value");

            //uploading the photo
            sendText(addEmployee.uploadPhoto, mapNewEmp.get("Photograph"));

            //clicking on a checkbox
            if (!addEmployee.checkbox.isSelected()){
                click(addEmployee.checkbox);
            }

            //filling up other fields
            sendText(addEmployee.createUsername, mapNewEmp.get("Username"));
            sendText(addEmployee.createPassword, mapNewEmp.get("Password"));
            sendText(addEmployee.confirmPassword, mapNewEmp.get("Password"));

            click(addEmployee.saveBtn);

            Thread.sleep(3000);

            //verifying the employee
            click(employeeSearch.empListOption);
            sendText(employeeSearch.searchID, empIDValue);
            click(employeeSearch.searchBtn);

            //it returns the data from a row
            List<WebElement> rowData=driver.findElements(By.xpath("//table[@id='resultTable']/tbody/tr"));
            for (int i=0; i<rowData.size(); i++){
                String rowText=rowData.get(i).getText();
                //verifying that employee is added
                String expectedData=empIDValue+" "+mapNewEmp.get("FirstName")+" "+mapNewEmp.get("MiddleName")+" "+mapNewEmp.get("LastName");
                Assert.assertEquals(expectedData, rowText);
            }
            click(employeeSearch.addEmployeeOption);

        }
    }
}
