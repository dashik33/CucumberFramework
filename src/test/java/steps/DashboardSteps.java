package steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import utils.CommonMethods;

import java.util.ArrayList;
import java.util.List;

public class DashboardSteps extends CommonMethods {

    @Then("user verifies all the dashboard tabs")
    public void user_verifies_all_the_dashboard_tabs(DataTable dataTable) {
        //data coming from feature file
        List<String> expectedTabs = dataTable.asList();

        //data coming from website
        List<String> actualTabs = new ArrayList<>();

        for (WebElement element : dashboard.dashboardTabs) {
            actualTabs.add(element.getText());
        }
        System.out.println(actualTabs);
        System.out.println(expectedTabs);

        //if assertion is passed it won't give you any info and will execute a code
        //if assertion is failed it'll give you an error with comparison

        Assert.assertEquals(actualTabs, expectedTabs);
    }
}
