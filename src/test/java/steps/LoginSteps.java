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

        @When("user enters invalid credentials")
        public void user_enters_valid_invalid_credentials() {
            sendText(login.usernameBox, "tts12345678");
            sendText(login.passwordBox, "Hum@nhrm");
        }

        @Then("user sees Invalid credentials error message")
        public void user_sees_an_error_message() {
            Assert.assertTrue(login.invalidCredentialsErrMsg.isDisplayed());
        }

    @When("user enters password and leave username field empty")
    public void user_enters_password_and_leave_username_field_empty() {
        sendText(login.usernameBox, "");
        sendText(login.passwordBox, "Hum@nhrm");
    }

    @Then("user sees Username cannot be empty error message")
    public void user_sees_username_cannot_be_empty_error_message() {
        Assert.assertTrue(login.userNameCantBeEmptyErrMessage.isDisplayed());
    }


    @When("user enters username and leave password field empty")
    public void user_enters_username_and_leave_password_field_empty() {
        sendText(login.usernameBox, "Admin");
        sendText(login.passwordBox, "");
    }

    @Then("user sees Password cannot be empty error message")
    public void user_sees_password_cannot_be_empty_error_message() {
       Assert.assertTrue(login.passwordCantBeEmptyErrMsg.isDisplayed());
    }
    }

