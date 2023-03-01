package jp.co.axa.apidemo.services;

import jp.co.axa.apidemo.entities.Employee;
import jp.co.axa.apidemo.vo.EmployeeRequestVO;

import java.util.List;

/**
 * The Interface EmployeeService.
 */
public interface EmployeeService {

    /**
     * Retrieve employees.
     *
     * @return the list
     */
    public List<Employee> retrieveEmployees();

    /**
     * Gets the employee.
     *
     * @param employeeId the employee id
     * @return the employee
     */
    public Employee getEmployee(Long employeeId);

    /**
     * Save employee.
     *
     * @param employee the employee
     */
    public void saveEmployee(EmployeeRequestVO employeeRequestVO);

    /**
     * Delete employee.
     *
     * @param employeeId the employee id
     */
    public void deleteEmployee(Long employeeId);

    /**
     * Update employee.
     *
     * @param employee the employee
     */
    public Employee updateEmployee(EmployeeRequestVO employeeRequestVO, Long employeeId);
}