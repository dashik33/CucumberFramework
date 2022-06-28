package API;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.json.Json;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@FixMethodOrder(MethodSorters.NAME_ASCENDING) //executing methods in alphabetical order

public class HardCodedExamples {
    String baseURI = RestAssured.baseURI = "http://hrm.syntaxtechs.net/syntaxapi/api";
    String token = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2NTUzODU3MDIsImlzcyI6ImxvY2FsaG9zdCIsImV4cCI6MTY1NTQyODkwMiwidXNlcklkIjoiMzg3OSJ9.MtL1RQWMySllNymr6YiQLmeJriSuiTUVTEPlLknnKAE";
    static String employee_id;

    @Test
    public void aCreateEmployee() {
        //making a request
        RequestSpecification request = given().header("Content-type", "application/json").
                header("Authorization", token).body("{\n" +
                        "    \"emp_firstname\": \"Mary\",\n" +
                        "    \"emp_lastname\": \"Russell\",\n" +
                        "    \"emp_middle_name\": \"B\",\n" +
                        "    \"emp_gender\": \"M\",\n" +
                        "    \"emp_birthday\": \"2006-06-06\",\n" +
                        "    \"emp_status\": \"Active\",\n" +
                        "    \"emp_job_title\": \"Developer\"\n" +
                        "}");

        //sending a request
        Response response = request.when().post("/createEmployee.php");
        response.prettyPrint(); //printing the request

        //getting the response
        response.then().assertThat().statusCode(201);

        //hamcrest matchers
        response.then().assertThat().body("Message", equalTo("Employee Created"));
        response.then().assertThat().body("Employee.emp_firstname", equalTo("Mary"));

        //using jsonPath() - to specify the key in the body returns the value against it
        employee_id = response.jsonPath().getString("Employee.employee_id");
        System.out.println(employee_id);
    }

    @Test
    public void bGetCreatedEmployee() {
        RequestSpecification request = given().header("Content-type", "application/json").
                header("Authorization", token).queryParam("employee_id", employee_id);
        Response response = request.when().get("/getOneEmployee.php");
        response.prettyPrint();

        response.then().assertThat().statusCode(200);

        String expectedId = response.jsonPath().getString("employee.employee_id");
        System.out.println(expectedId);
        Assert.assertEquals(expectedId, employee_id);
    }

    @Test
    public void cUpdateEmployee() {
        RequestSpecification request = given().header("Content-type", "application/json").
                header("Authorization", token).body("{\n" +
                        "  \"employee_id\": \"" + employee_id + "\",\n" +
                        "  \"emp_firstname\": \"Billy\",\n" +
                        "  \"emp_lastname\": \"John\",\n" +
                        "  \"emp_middle_name\": \"G\",\n" +
                        "  \"emp_gender\": \"M\",\n" +
                        "  \"emp_birthday\": \"2010-06-12\",\n" +
                        "  \"emp_status\": \"Fired\",\n" +
                        "  \"emp_job_title\": \"CEO\"\n" +
                        "}");
        Response response = request.when().put("/updateEmployee.php");
        response.prettyPrint();

        response.then().assertThat().statusCode(200);
    }

    @Test
    public void dGetUpdatedEmployee() {
        RequestSpecification request = given().header("Content-type", "application/json").
                header("Authorization", token).queryParam("employee_id", employee_id);
        Response response = request.when().get("/getOneEmployee.php");
        response.then().assertThat().statusCode(200);
        response.prettyPrint();
    }

    @Test
    public void eGetAllEmployees(){
        RequestSpecification request = given().header("Content-type", "application/json").
                header("Authorization", token);
        Response response=request.when().get("/getAllEmployees.php");

        // returns string of response. printing all employees info
        String allEmployees=response.prettyPrint();

        //creating an object of jsonPath class
        JsonPath js=new JsonPath(allEmployees);

        //retrieving the total # of employees
        int count=js.getInt("Employees.size()");
        System.out.println(count);

        // getting only employee id of each employee
        for (int i=0; i<count; i++){
           String empId= js.getString("Employees["+i+"].employee_id");
            System.out.println(empId);
        }
    }
}
