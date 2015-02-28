package controllers;

import static play.data.Form.form;





import org.bson.types.ObjectId;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;

import org.mongodb.morphia.Datastore;


import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.employees.*;
import views.html.summary.*;

//import views.html.employees.;
public class Employee extends Controller {

	// final static Form<models.Employee>
	// createEmployeeForm=form(models.Employee.class);

	public static Result blank() {

		// models.Employee defaults= new models.Employee();
		Form<models.Employee> employeeForm = form(models.Employee.class);
		// Form<models.Employee> employeeCreateForm=form(models.Employee.class);
		return ok(employeeCreate.render(employeeForm));
		// return redirect(routes.Application.index());

	}

	public static Result submit() {
		Form<models.Employee> employeeForm = form(models.Employee.class)
				.bindFromRequest();
		Form<models.Employee> filledForm = employeeForm.bindFromRequest();
		// Form<models.Employee> filledForm =
		// createEmployeeForm.bindFromRequest();

		models.Employee created=null;
		try{
		created = filledForm.get();
		 System.out.println("created"+created);
		}catch(IllegalStateException e){
			
		}
		
		
		
		//System.out.println("Mongo connection   "+MorphiaObject.getDataStore());
		if(created!=null){

		if (filledForm.get().employeeName.equals("")) {
			filledForm.reject("employeeName", "This cannot be empty");
		}

		if (filledForm.get().salary == 0) {
			filledForm.reject("salary", "This cannot be empty");
		}

		if (filledForm.get().department.equals("")) {
			filledForm.reject("department", "This cannot be empty");
		}

		if (filledForm.hasErrors()) {
			return badRequest(employeeCreate.render(filledForm));
			// return redirect(routes.Application.index());
		} else {
			ObjectId id=new ObjectId();
			String empId=id.toString().substring(21, 24);
			created.employeeId=id;
			Long outputDecimal = Long.parseLong(empId,16);
			created.empId=outputDecimal.toString().trim();
			MorphiaObject.getDataStore().save(created);
			return ok(insert.render(created));
		}
		}
		
	else{
			System.out.println("Nullllllllllllllllllll");
			/*filledForm.reject("employeeName", "This cannot be empty");
			filledForm.reject("department", "This cannot be empty");
			filledForm.reject("salary", "This cannot be empty");*/
			return badRequest(employeeCreate.render(filledForm));
		}
	

	}

public static Result edit(String primaryKey) {
	
		System.out.println(primaryKey);
		Query< models.Employee> query=MorphiaObject.getDataStore().find(models.Employee.class,"empId", primaryKey);
		models.Employee employee2=(models.Employee) MorphiaObject.getDataStore().find(models.Employee.class,"empId", primaryKey).get();
		System.out.println(employee2);

		Form<models.Employee> employeeForm = form(models.Employee.class).fill(
				employee2);
		
		return ok(employeeEdit.render(primaryKey, employeeForm));
		
	//return TODO;
	}

	public static Result update(String primaryKey) {

		System.out.println("IN UPDATE");
		Form<models.Employee> employeeForm = form(models.Employee.class)
				.bindFromRequest();
		if (employeeForm.hasErrors()) {
			System.out.println("IN UPDATE rrr");
			return badRequest(employeeEdit.render(primaryKey, employeeForm));
		}
		
		
		
		Query<models.Employee> updateQuery = MorphiaObject.getDataStore().createQuery(models.Employee.class).field("empId").equal(employeeForm.get().empId);
		
		UpdateOperations<models.Employee> ops1=MorphiaObject.getDataStore().createUpdateOperations(models.Employee.class).set("employeeName", employeeForm.get().employeeName);
		UpdateOperations<models.Employee> ops2=MorphiaObject.getDataStore().createUpdateOperations(models.Employee.class).set("salary", employeeForm.get().salary);
		UpdateOperations<models.Employee> ops3=MorphiaObject.getDataStore().createUpdateOperations(models.Employee.class).set("department", employeeForm.get().department);
		MorphiaObject.getDataStore().update(updateQuery, ops1);
		MorphiaObject.getDataStore().update(updateQuery, ops2);
		MorphiaObject.getDataStore().update(updateQuery, ops3);
		
		
		return ok(update.render(employeeForm.get()));
		
		//return TODO;
	}

	public static Result delete(String primaryKey) {
		Form<models.Employee> employeeForm = form(models.Employee.class)
				.bindFromRequest();
		
		Query< models.Employee> query=MorphiaObject.getDataStore().find(models.Employee.class,"empId", primaryKey);
		models.Employee employee2=(models.Employee) MorphiaObject.getDataStore().find(models.Employee.class,"empId", primaryKey).get();
		
		models.Employee deleted=(models.Employee) MorphiaObject.getDataStore().find(models.Employee.class,"empId", primaryKey).get();
		
		System.out.println(employeeForm.get().employeeName);
		
		MorphiaObject.getDataStore().delete(query);
		return ok(deleteEmp.render(deleted));
		//return TODO;
	}
	

}
