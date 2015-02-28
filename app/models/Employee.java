package models;



import org.bson.types.ObjectId;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;


@Entity
public class Employee {

	@Id
	@JsonDeserialize(using = ObjectIdDeserializer.class)
    @JsonSerialize(using = ObjectIdSerializer.class)
	public ObjectId employeeId;
	public String empId;
	public String employeeName;
	public int salary;
	public String department;
	
	//private static JacksonDBCollection<Employee, String> coll = MongoDB.getCollection("tasks", Employee.class, String.class);

	public Employee() {

	}

	public Employee( String employeeName, int salary,
			String department) {
		this.employeeName = employeeName;
		this.salary = salary;
		this.department = department;
	}
	
	

	/*public static Finder<Long, Employee> find(){
		return new Finder<Long, Employee>(
				Long.class, Employee.class);
	}
	*/
	
	
	/*public static Finder<Long, Product> find() {
	    return new Finder<Long, Product>(Long.class, Product.class);
	  }*/

	
	
	@Override
	public String toString() {
		return " {\"empId\":" +"\""+ empId +"\"" +", \"employeeName\":"+"\"" + employeeName
				+"\"" + ", \"salary\":"+"\"" + salary+"\""  + ", \"department\":"+"\"" + department +"\"" + "}";
	}
	
	/*public static List<String> getNames() {
	    List<String> employeeNames = new ArrayList<String>();
	    for (Employee employee : find().all()) {
	      employeeNames.add(employee.employeeName);
	    }
	    return employeeNames;
	  }*/

}


