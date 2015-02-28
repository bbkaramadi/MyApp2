package controllers;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.mongodb.morphia.query.Query;

import com.mongodb.util.JSON;

import models.Employee;

import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Results;
import views.html.*;
import views.html.employees.*;

public class ShowAll extends Controller {

	public static Result getEmployees() {

		Query<Employee> query = MorphiaObject.getDataStore().find(
				Employee.class);
		List<Employee> employees = MorphiaObject.getDataStore()
				.find(Employee.class).asList();
		System.out.println(employees);
		
		
		try {
			File file = new File("E:/Java Works/Play/workspace/sample2/app/views/employees/employees.json");
			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}
 
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(Json.toJson(employees).toString());
			bw.close();
 
			System.out.println("Done");
 
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
		return ok(showAllEmployee.render(employees));
		//return ok(Json.toJson(employees));
		//return ok(Json.toJson(employees));
		//return ok(Json.toJson(employees));
	}
}
