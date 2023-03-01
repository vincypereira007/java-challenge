package jp.co.axa.apidemo.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import jp.co.axa.apidemo.ApiDemoApplicationTests;
import jp.co.axa.apidemo.entities.Employee;
import jp.co.axa.apidemo.services.EmployeeService;
import jp.co.axa.apidemo.vo.EmployeeRequestVO;

/**
 * The Class EmployeeServiceControllerTest.
 */
public class EmployeeServiceControllerTest extends ApiDemoApplicationTests {

	/** The employee controller. */
	@InjectMocks
	EmployeeController employeeController;

	/** The employee service. */
	@Mock
	EmployeeService employeeService;

	/**
	 * Testget employees.
	 */
	@Test
	public void testgetEmployees() {
		Employee employee1 = new Employee(Integer.toUnsignedLong(1), "John", 10000, "Sales");
		Employee employee2 = new Employee(Integer.toUnsignedLong(2), "John", 10000, "Sales");
		List<Employee> employees = Arrays.asList(employee1, employee1);
		when(employeeService.retrieveEmployees()).thenReturn(employees);
		ResponseEntity<List<Employee>> result = employeeController.getEmployees();
		assertEquals(result.getBody().size(), 2);
		assertEquals(result.getBody().get(0).getName(), employee1.getName());
		assertEquals(result.getBody().get(1).getName(), employee2.getName());
	}

	/**
	 * Testget employee.
	 */
	@Test
	public void testgetEmployee() {
		Employee employee = new Employee(Integer.toUnsignedLong(1), "John", 10000, "Sales");
		when(employeeService.getEmployee(Integer.toUnsignedLong(1))).thenReturn(employee);
		ResponseEntity<Employee> result = employeeController.getEmployee(Integer.toUnsignedLong(1));
		assertEquals(result.getBody().getName(), employee.getName());
	}

	/**
	 * Test add employee.
	 */
	@Test
	public void testAddEmployee() {
		MockHttpServletRequest request = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
		EmployeeRequestVO employeeRequestVO = new EmployeeRequestVO("John", 10000, "Sales");
		doNothing().when(employeeService).saveEmployee(any(EmployeeRequestVO.class));
		ResponseEntity<Void> responseEntity = employeeController.saveEmployee(employeeRequestVO);
		assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
	}

	/**
	 * Test delete employee.
	 */
	@Test
	public void testDeleteEmployee() {
		MockHttpServletRequest request = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
		doNothing().when(employeeService).deleteEmployee(any(Long.class));
		ResponseEntity<Void> responseEntity = employeeController.deleteEmployee(Integer.toUnsignedLong(1));
		assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
	}
	
	/**
	 * Test update employee when employee exists.
	 */
	@Test
	public void testUpdateEmployeeWhenEmployeeExists() {
		MockHttpServletRequest request = new MockHttpServletRequest();
		Employee employee = new Employee(Integer.toUnsignedLong(1), "John", 10000, "Sales");
		EmployeeRequestVO employeeRequestVO = new EmployeeRequestVO("John", 10000, "Sales");
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
		doReturn(employee).when(employeeService).updateEmployee(any(EmployeeRequestVO.class),any(Long.class));
		ResponseEntity<Void> responseEntity = employeeController.updateEmployee(employeeRequestVO , Integer.toUnsignedLong(1));
		assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
	}
	
	/**
	 * Test update employee when employee doesnot exists.
	 */
	@Test
	public void testUpdateEmployeeWhenEmployeeDoesnotExists() {
		MockHttpServletRequest request = new MockHttpServletRequest();
		EmployeeRequestVO employeeRequestVO = new EmployeeRequestVO("John", 10000, "Sales");
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
		doReturn(null).when(employeeService).updateEmployee(any(EmployeeRequestVO.class),any(Long.class));
		ResponseEntity<Void> responseEntity = employeeController.updateEmployee(employeeRequestVO , Integer.toUnsignedLong(1));
		assertThat(responseEntity.getStatusCodeValue()).isEqualTo(204);
	}
}
