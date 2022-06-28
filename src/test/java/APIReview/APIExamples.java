package APIReview;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

public class APIExamples {
    String baseURI = RestAssured.baseURI = "http://hrm.syntaxtechs.net/syntaxapi/api";
    String token = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2NTYxNzI2NDYsImlzcyI6ImxvY2FsaG9zdCIsImV4cCI6MTY1NjIxNTg0NiwidXNlcklkIjoiMzg3OSJ9.9Wch5rwuvVgKJDzS7QDnpdgJF-Tsr9LzcFG2U-T-NNU";


    //create an employee
    @Test
    public void createEmployee() {
        RequestSpecification request = given().header("Content-Type", "application/json").header("Authorization", token).
                body("{\n" +
                        "    \"emp_firstname\": \"Mary\",\n" +
                        "    \"emp_lastname\": \"Russell\",\n" +
                        "    \"emp_middle_name\": \"B\",\n" +
                        "    \"emp_gender\": \"M\",\n" +
                        "    \"emp_birthday\": \"2006-06-06\",\n" +
                        "    \"emp_status\": \"Active\",\n" +
                        "    \"emp_job_title\": \"Developer\"\n" +
                        "}");

        Response response = request.when().post("/createEmployee.php");
        response.prettyPrint();

        //understanding Gson

        //converting our response (that is json object) to json element
        JsonElement jsonElement = JsonParser.parseString(response.asString());
        System.out.println(jsonElement);

        //converting back to json object in order to work with it.
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        System.out.println(jsonObject);

        //extracting key from json object
        JsonElement keyMessage = jsonObject.get("Message");
        System.out.println(keyMessage);

        JsonElement keyEmployee = jsonObject.get("Employee");
        System.out.println(keyEmployee);

        JsonObject employeeDetails = keyEmployee.getAsJsonObject();
        System.out.println(employeeDetails.get("emp_firstname"));
        System.out.println(employeeDetails.get("emp_lastname"));

        Assert.assertEquals(keyEmployee, employeeDetails);
    }
}
