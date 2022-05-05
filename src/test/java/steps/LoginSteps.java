package steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import utils.CommonMethods;

public class LoginSteps extends CommonMethods {

        @Then("admin user is successfully logged in")
        public void admin_user_is_successfully_logged_in() {
            Assert.assertTrue(dashboard.welcomeMessage.isDisplayed());
        }

        @When("user enters valid ess credentials")
        public void user_enters_valid_ess_credentials() {
            sendText(login.usernameBox, "tts12345");
            sendText(login.passwordBox, "Hum@nhrm123");
        }

        @Then("ESS user is successfully logged in")
        public void ess_user_is_successfully_logged_in() {
            Assert.assertTrue(dashboard.welcomeMessage.isDisplayed());
        }

        @When("user enters valid invalid credentials")
        public void user_enters_valid_invalid_credentials() {
            sendText(login.usernameBox, "tts12345678");
            sendText(login.passwordBox, "Hum@nhrm");
        }

        @Then("user sees an error message")
        public void user_sees_an_error_message() {
            System.out.println(errMessage(login.errMsg));
            getMsgText(login.errMsg);

        }
    }

