package jp.co.axa.apidemo.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jp.co.axa.apidemo.entities.Employee;
import jp.co.axa.apidemo.services.EmployeeService;
import jp.co.axa.apidemo.vo.EmployeeRequestVO;
import lombok.extern.slf4j.Slf4j;

/**
 * The Class EmployeeController.
 */
@RestController
@RequestMapping("/api/v1")

/** The Constant log. */
@Slf4j
@Validated
public class EmployeeController {

	/** The employee service. */
	@Autowired
	private EmployeeService employeeService;

	/**
	 * Gets the employees.
	 *
	 * @return the employees
	 */
	@GetMapping("/employees")
	public ResponseEntity<List<Employee>> getEmployees() {
		log.debug("Inside getEmployees method of EmployeeController");
		List<Employee> employees = employeeService.retrieveEmployees();
		return new ResponseEntity<List<Employee>>(employees, HttpStatus.OK);
	}

	/**
	 * Gets the employee.
	 *
	 * @param employeeId the employee id
	 * @return the employee
	 */
	@GetMapping("/employees/{employeeId}")
	public ResponseEntity<Employee> getEmployee(@PathVariable(name = "employeeId") Long employeeId) {
		log.debug("Inside getEmployee method of EmployeeController");
		Employee employee = employeeService.getEmployee(employeeId);
		return new ResponseEntity<Employee>(employee, HttpStatus.OK);
	}

	/**
	 * Save employee.
	 *
	 * @param employee the employee
	 */
	@PostMapping("/employees")
	public ResponseEntity<Void> saveEmployee(@Valid @RequestBody EmployeeRequestVO employeeRequestVO) {
		log.debug("Inside saveEmployee method of EmployeeController");
		employeeService.saveEmployee(employeeRequestVO);
		log.info("Employee Saved Successfully");
		return new ResponseEntity<Void>(HttpStatus.OK);

	}

	/**
	 * Delete employee.
	 *
	 * @param employeeId the employee id
	 */
	@DeleteMapping("/employees/{employeeId}")
	public ResponseEntity<Void> deleteEmployee(@PathVariable(name = "employeeId") Long employeeId) {
		log.debug("Inside deleteEmployee method of EmployeeController");
		employeeService.deleteEmployee(employeeId);
		log.info("Employee Deleted Successfully");
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	/**
	 * Update employee.
	 *
	 * @param employee   the employee
	 * @param employeeId the employee id
	 */
	@PutMapping("/employees/{employeeId}")
	public ResponseEntity<Void> updateEmployee(@Valid @RequestBody EmployeeRequestVO employeeRequestVO, @PathVariable(name = "employeeId") Long employeeId) {
		log.debug("Inside updateEmployee method of EmployeeController");
		Employee employee = employeeService.updateEmployee(employeeRequestVO, employeeId);
		if(employee != null) {
			return new ResponseEntity<Void>(HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
	}

}
