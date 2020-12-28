package com.jcabujat.springboot.cruddemo.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jcabujat.springboot.cruddemo.entity.Employee;

@Repository
public class EmployeeDAOJPAImpl implements EmployeeDAO {
	
	// define entity manager
	@Autowired
	private EntityManager entityManager;

	@Override
	public List<Employee> findAll() {
		
		//create a query
		Query theQuery = entityManager.createQuery("from Employee");
		
		// execute the query and get result list
		List<Employee> employees = theQuery.getResultList();
		
		// return the list
		return employees;
	}

	@Override
	public Employee findById(int theId) {
		
		// get the employee
		Employee employee = entityManager.find(Employee.class, theId);
		
		// return the employee
		return employee;
	}

	@Override
	public void save(Employee theEmployee) {


		// save or update the employee
		Employee dbEmployee = entityManager.merge(theEmployee);
		
		// update the id from db ... so we can get generated id for save/insert
		theEmployee.setId(dbEmployee.getId());

	}

	@Override
	public void deleteById(int theId) {
		
		// create query for deleting by id
		Query theQuery = entityManager.createQuery("delete from Employee where id=:employeeId");
		
		// set the parameter
		theQuery.setParameter("employeeId", theId);
		
		// execute the query
		theQuery.executeUpdate();

	}

}
