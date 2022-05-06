package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(

        //path to all feature files
        features = "src/test/resources/features/", //add the name of feature file if you need to execute just one
        //we provide the path of package to get all the step definitions
        glue = "steps",

        //dryRun - to get the step definitions of undefined steps.
        //if we set it to true, it will quickly scan all the gherkin and get only unimplemented steps,
        // and it means we stop actual execution.
        // we set it to false to get all the steps, and it means we execute scripts in real time
        dryRun = false,

        //it means the console's output is having irrelevant info
        //when we set it to true it removes all this info
        monochrome = true,

        //executing scenarios with the tags provided, we can add multiple tags, like @sprint1,2,3. we use OR/AND between tags
        tags = "@screenshot"
)

public class RunnerClass {

}
