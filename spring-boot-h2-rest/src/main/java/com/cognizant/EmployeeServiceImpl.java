package com.cognizant;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeDao;
	
	@Override
	@Transactional
	public Employee storeEmployee(Employee employee) {
		return employeeDao.save(employee);
	}

	@Override
	public List<Employee> fetchAllEmployees() {
		return employeeDao.findAll();
	}

	@Override
	public Employee fetchEmployee(int id) throws EmployeeNotFoundException {
		Optional<Employee> optional = employeeDao.findById(id);
		if(optional.isPresent()) 
			return optional.get();
		else
			throw new EmployeeNotFoundException("employee with an id "+id+" not present");
	}
}
