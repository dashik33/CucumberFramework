package steps;

import pages.AddEmployeePage;
import pages.DashboardPage;
import pages.EmployeeSearchPage;
import pages.LoginPage;

public class PageInitializers {
//we create all the objects in one class in order to use them in Common methods just by calling the "initializePageObjects"
 //method one time (in this case we don't need to create the object every time).
// this method calls the constructors from Page classes and these constructors will initialize all the Web elements
    public static LoginPage login;
    public static EmployeeSearchPage employeeSearch;
    public static AddEmployeePage addEmployee;
    public static DashboardPage dashboard;

    public static void initializePageObjects(){
        login=new LoginPage();
        employeeSearch=new EmployeeSearchPage();
        addEmployee=new AddEmployeePage();
        dashboard=new DashboardPage();
    }
}
