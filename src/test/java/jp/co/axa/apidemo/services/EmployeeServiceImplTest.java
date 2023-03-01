package jp.co.axa.apidemo.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import jp.co.axa.apidemo.ApiDemoApplicationTests;
import jp.co.axa.apidemo.entities.Employee;
import jp.co.axa.apidemo.repositories.EmployeeRepository;
import jp.co.axa.apidemo.vo.EmployeeRequestVO;

/**
 * The Class EmployeeServiceImplTest.
 */
public class EmployeeServiceImplTest extends ApiDemoApplicationTests {
	
	/** The employee service. */
	@InjectMocks
	EmployeeServiceImpl employeeService;
	
	/** The employee repository. */
	@Mock
	EmployeeRepository employeeRepository;
	

	/**
	 * Construct employee request VO.
	 *
	 * @return the employee request VO
	 */
	private EmployeeRequestVO constructEmployeeRequestVO() {
		return new EmployeeRequestVO("John", 10000, "Sales");
	}
  
	/**
	 * Construct employee.
	 *
	 * @param employeeId the employee id
	 * @return the employee
	 */
	private Employee constructEmployee(int employeeId) {
		return new Employee(Integer.toUnsignedLong(employeeId), "John", 10000, "Sales");
	}
	
	/**
	 * Test get employees.
	 */
	@Test
	public void testGetEmployees() {
		Employee employee1 = constructEmployee(1);
		Employee employee2 = constructEmployee(2);
		List<Employee> employees = Arrays.asList(employee1, employee1);
		when(employeeRepository.findAll()).thenReturn(employees);
		List<Employee> result = employeeService.retrieveEmployees();
		assertEquals(result.size(), 2);
		assertEquals(result.get(0).getName(), employee1.getName());
		assertEquals(result.get(1).getName(), employee2.getName());
	}
	
	/**
	 * Test get employee.
	 */
	@Test
	public void testGetEmployee() {
		Optional<Employee> employee = Optional.of(constructEmployee(1));
		when(employeeRepository.findById(Integer.toUnsignedLong(1))).thenReturn(employee);
		Employee result = employeeService.getEmployee(Integer.toUnsignedLong(1));
		assertEquals(result.getName(), employee.get().getName());
	}
		
	/**
	 * Test add employee.
	 */
	@Test
	public void testAddEmployee() {
		Employee employee = constructEmployee(1);
		EmployeeRequestVO employeeRequestVO = constructEmployeeRequestVO();
		doReturn(employee).when(employeeRepository).save(any(Employee.class));
		employeeService.saveEmployee(employeeRequestVO);
		assertEquals(employee.getName(), employeeRequestVO.getName());
	}

	
	/**
	 * Test delete employee.
	 */
	@Test
	public void testDeleteEmployee() {
		doNothing().when(employeeRepository).deleteById(any(Long.class));
		employeeService.deleteEmployee(Integer.toUnsignedLong(1));
	}
	
	/**
	 * Test update employee when employee exists.
	 */
	@Test
	public void testUpdateEmployeeWhenEmployeeExists() {
		Optional<Employee> employee = Optional.of(constructEmployee(1));
		Employee emp = constructEmployee(1);
		EmployeeRequestVO employeeRequestVO =  constructEmployeeRequestVO();
		doReturn(employee).when(employeeRepository).findById(any(Long.class));
		doReturn(emp).when(employeeRepository).save(any(Employee.class));
		employeeService.updateEmployee(employeeRequestVO , Integer.toUnsignedLong(1));
		assertThat(employee.get().getName()).isEqualTo(employeeRequestVO.getName());
	}
	
	/**
	 * Test update employee when employee doesnot exists.
	 */
	@Test
	public void testUpdateEmployeeWhenEmployeeDoesnotExists() {
		Optional<Employee> employee = Optional.empty();
		EmployeeRequestVO employeeRequestVO =  constructEmployeeRequestVO();
		doReturn(employee).when(employeeRepository).findById(any(Long.class));	
		employeeService.updateEmployee(employeeRequestVO , Integer.toUnsignedLong(1));
		assertFalse(employee.isPresent());
	}
}
