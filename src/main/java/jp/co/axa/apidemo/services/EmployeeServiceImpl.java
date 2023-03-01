package jp.co.axa.apidemo.services;

import jp.co.axa.apidemo.constants.ApplicationConstants;
import jp.co.axa.apidemo.entities.Employee;
import jp.co.axa.apidemo.repositories.EmployeeRepository;
import jp.co.axa.apidemo.vo.EmployeeRequestVO;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Optional;

/**
 * The Class EmployeeServiceImpl.
 */
@Service
/** The Constant log. */

/** The Constant log. */
@Slf4j
public class EmployeeServiceImpl implements EmployeeService{

    /** The employee repository. */
    @Autowired
    private EmployeeRepository employeeRepository;

    /**
     * Retrieve employees.
     *
     * @return the list
     */
    @Cacheable(value = ApplicationConstants.CACHE_EMP_INFO)
    public List<Employee> retrieveEmployees() {
    	log.debug("Inside retrieveEmployees method of EmployeeServiceImpl");
        List<Employee> employees = employeeRepository.findAll();
        return employees;
    }

    /**
     * Gets the employee.
     *
     * @param employeeId the employee id
     * @return the employee
     */
    @Cacheable(value = ApplicationConstants.CACHE_EMP_INFO_BY_ID, key = "#employeeId")
    public Employee getEmployee(Long employeeId) {
    	log.debug("Inside getEmployee method of EmployeeServiceImpl");
        Optional<Employee> optEmp = employeeRepository.findById(employeeId);
        return optEmp.get();
    }

    /**
     * Save employee.
     *
     * @param employee the employee
     */
    @CacheEvict(value = ApplicationConstants.CACHE_EMP_INFO, allEntries = true)
    public void saveEmployee(EmployeeRequestVO employeeRequestVO){
    	log.debug("Inside saveEmployee method of EmployeeServiceImpl");
    	ObjectMapper mapper = new ObjectMapper();
    	Employee employee = mapper.convertValue(employeeRequestVO, Employee.class);
        employeeRepository.save(employee);
    }

    /**
     * Delete employee.
     *
     * @param employeeId the employee id
     */
    @Caching(evict = {
		    @CacheEvict(value = ApplicationConstants.CACHE_EMP_INFO, allEntries = true),
		    @CacheEvict(value = ApplicationConstants.CACHE_EMP_INFO_BY_ID, key = "#employeeId")
		})
    public void deleteEmployee(Long employeeId){
    	log.debug("Inside deleteEmployee method of EmployeeServiceImpl");
        employeeRepository.deleteById(employeeId);
    }

    /**
     * Update employee.
     *
     * @param employee the employee
     */
    @Caching(evict = {
		    @CacheEvict(value = ApplicationConstants.CACHE_EMP_INFO, allEntries = true),
		    @CacheEvict(value = ApplicationConstants.CACHE_EMP_INFO_BY_ID, key = "#employee?.id")
		})
    public Employee updateEmployee(EmployeeRequestVO employeeRequestVO, Long employeeId) {
    	log.debug("Inside updateEmployee method of EmployeeServiceImpl");
    	Optional<Employee> optEmp = employeeRepository.findById(employeeId);
        if(optEmp.isPresent()){
	    	ObjectMapper mapper = new ObjectMapper();
	    	Employee employee = mapper.convertValue(employeeRequestVO, Employee.class);
	    	employee.setId(employeeId);
	    	return employeeRepository.save(employee);
        } else {
        	return null;
        }
    }
}