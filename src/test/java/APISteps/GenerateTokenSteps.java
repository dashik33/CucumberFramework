package APISteps;

import io.cucumber.java.en.Given;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utils.APIConstants;

import static io.restassured.RestAssured.given;

public class GenerateTokenSteps {

   // String baseURI = RestAssured.baseURI= "http://hrm.syntaxtechs.net/syntaxapi/api";
    public static String token;

    @Given("a JWT token is generated")
    public void a_jwt_token_is_generated() {
        RequestSpecification request = given().header(APIConstants.CONTENT_TYPE_HEADER, APIConstants.CONTENT_TYPE_VALUE).
                body("{\n" +
                "  \"email\": \"batch66666@test.com\",\n" +
                "  \"password\": \"test12345\"\n" +
                "}");
        Response response=request.when().post(APIConstants.GENERATE_TOKEN_URI);
        token= "Bearer " + response.jsonPath().getString("token");
        System.out.println(token);
    }
}
