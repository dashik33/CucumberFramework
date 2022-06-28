package APIPracticeSteps;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONArray;
import org.junit.Assert;

import java.util.concurrent.ThreadLocalRandom;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class APIPracticeSteps {
    RequestSpecification request;
    Response response;
    public static final String BASE_URI = RestAssured.baseURI = "https://api.thecatapi.com/v1";
    public static final String HEADER_NAME = "x-api-key";
    public static final String HEADER_KEY = "DEMO-API-KEY";
    public static JsonArray allVotes;


    @Given("a request is prepared to get all the votes")
    public void a_request_is_prepared_to_get_all_the_votes() {
        request = given().header(HEADER_NAME, HEADER_KEY).header("Content_Type", "/application.json");

    }

    @When("a GET call is made to get all the votes and object is created to store them")
    public void a_get_call_is_made_to_get_all_the_votes_and_object_is_created_to_store_them() {
        response = request.when().get("/votes");
     //   response.prettyPrint();
        allVotes= JsonParser.parseString(response.getBody().asString()).getAsJsonArray();
    }

    @Then("the status code is {int} and response's length more than {int}")
    public void the_status_code_is_and_response_s_length_more_than(Integer code, Integer num) {
        response.then().assertThat().statusCode(code);
        Assert.assertTrue(response.getBody().asString().length() > num);
    }

    @Given("a request is prepared to get a vote with random id")
    public void a_request_is_prepared_to_get_a_vote_with_random_id() {
        request = given().header(HEADER_NAME, HEADER_KEY).header("Content-Type", "application.json");
    }

    @When("a GET call is made to get a vote with random id")
    public void a_get_call_is_made_to_get_a_vote_with_random_id() {
        int randomIndex= ThreadLocalRandom.current().nextInt(0, allVotes.size());
        JsonObject randomJsonObject=allVotes.get(randomIndex).getAsJsonObject();
        int randomVoteID= randomJsonObject.get("id").getAsInt();
        response=request.given().when().get(BASE_URI+"/votes/"+randomVoteID);
        response.prettyPrint();
    }

    @Then("the status code is {int} and response is not empty")
    public void the_status_code_is_and_response_is_not_empty(Integer code) {
        response.then().assertThat().statusCode(code);
        Assert.assertTrue(response.getBody().asString().length() > 0);
    }


    }

