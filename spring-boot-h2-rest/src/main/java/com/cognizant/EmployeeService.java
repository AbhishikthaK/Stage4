package com.cognizant;

import java.util.List;

public interface EmployeeService {
	public Employee storeEmployee(Employee employee);
	public List<Employee> fetchAllEmployees();
	public Employee fetchEmployee(int id) throws EmployeeNotFoundException;
	// throw new EmployeeNotFoundException("Sorry id not found");
}
