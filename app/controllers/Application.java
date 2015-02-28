package controllers;

//import java.util.List;


//import models.Employee;
import play.mvc.*;
import views.html.*;

//import views.html.showAll.*;

public class Application extends Controller {
  
    public static Result index() {
    	
    	return ok(index.render());
    }
  
    /*public static Result getEmployees() {
    	List<EmployeeCreate> employees=EmployeeCreate.find.all();
    	return ok(showAllEmployee.render(employees));
	}*/
}