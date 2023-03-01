package jp.co.axa.apidemo.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The Class Employee.
 */
@Entity
@Table(name="EMPLOYEE")

/**
 * Instantiates a new employee.
 *
 * @param id the id
 * @param name the name
 * @param salary the salary
 * @param department the department
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

    /** The id. */
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    /** The name. */
    @Column(name="EMPLOYEE_NAME")
    private String name;

    /** The salary. */
    @Column(name="EMPLOYEE_SALARY")
    private Integer salary;

    /** The department. */
    @Column(name="DEPARTMENT")
    private String department;

}
