package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import utils.CommonMethods;

public class Hooks extends CommonMethods {
    @Before
    public void start(){
        openBrowserAndLaunchApplication();
    }

    @After
    public void end(Scenario scenario){
        byte[] pic;
        //scenario class holds the complete info of our execution
        if(scenario.isFailed()){
            pic=  takeScreenShot("failed/"+scenario.getName());
        }else{
            pic=takeScreenShot("passed/"+scenario.getName());
        }

        //attaching the pic to the report
        scenario.attach(pic, "image/png", scenario.getName());
        tearDown();
    }

}
