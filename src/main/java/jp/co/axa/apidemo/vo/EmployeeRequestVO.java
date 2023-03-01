package jp.co.axa.apidemo.vo;


import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * To string.
 *
 * @return the java.lang. string
 */
@Data

/**
 * Instantiates a new employee request VO.
 *
 * @param name the name
 * @param salary the salary
 * @param department the department
 */
@AllArgsConstructor

/**
 * Instantiates a new employee request VO.
 */
@NoArgsConstructor
public class EmployeeRequestVO {
	
	/** The name. */
	private String name;

    /** The salary. */
	@Valid
	@NotNull
	@Range(min = 6500000, max = 8000000, message = "Please check the salary amount")
    private Integer salary;

    /** The department. */
    private String department;
}
