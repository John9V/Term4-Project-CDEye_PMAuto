package CDEye_PMAuto.backend.employee;

import java.io.Serializable;

import java.math.BigDecimal;
import java.util.UUID;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;

/**
 * Contains employee information.
 * 
 * @author travisblack and wilsonzhu
 */
@Entity
@Table(name="employees")
@Named("employee")
@SessionScoped
public class Employee implements Serializable {

	@Transient
	@Inject private EmployeeManager employeeManager;
	
	@Id
	@Type(type = "uuid-char")
	@Column(name="id")
	protected UUID id;
	
    /** The employee's employee number. Assigned by HR, indicates num and department */
	@Column(name="empnumber")
    protected String empNum;
    
    /** The employee's name. */
	@Column(name="firstname")
    protected String firstName;
    
    /** The employee's name. */
	@Column(name="lastname")
    protected String lastName;
	
    /** The employee's salary **/
	@Column(name="salary")
	protected BigDecimal salary;
	
	/** The employee's benefits remaining **/
	@Column(name="benefits")
	protected BigDecimal benefits;
	
	/**The employee's hourly net rate**/
	@Column(name="netrate")
	protected Short netRate;
	
	/** Indicates whether the employee is active or not within the company**/
	@Column(name="active")
	protected Boolean active;
	
	/** Employees Paygrade (Position/Salary) **/
	@ManyToOne
	@JoinColumn(name="paygrades")
	protected Paygrade payGrade;
	
	public Employee() {}


	public Employee(UUID id, String empNum, String firstName, String lastName, 
	        BigDecimal salary, BigDecimal benefits, Short netRate, Boolean active, Paygrade paygrades) {
		super();
		this.id = id;
		this.empNum = empNum;
		this.firstName = firstName;
		this.lastName = lastName;
		this.salary = salary;
		this.benefits = benefits;
		this.netRate = netRate;
		this.active = active;
		this.payGrade = paygrades;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}
	
	public String getEmpNum() {
	    return empNum;
	}
	
	public void setEmpNum(String empNum) {
	    this.empNum = empNum;
	}
	
	public String getFirstName() {
	    return firstName;
	}
	
	public void setFirstName(String firstName) {
	    this.firstName = firstName;
	}
	
	public String getLastName() {
	    return lastName;
	}
	
	public void setLastName(String lastName) {
	    this.lastName = lastName;
	}

	public BigDecimal getSalary() {
		return salary;
	}

	public void setSalary(BigDecimal salary) {
		this.salary = salary;
	}

	public BigDecimal getBenefits() {
		return benefits;
	}

	public void setBenefits(BigDecimal benefits) {
		this.benefits = benefits;
	}

	public Short getNetRate() {
		return netRate;
	}

	public void setNetRate(Short netRate) {
		this.netRate = netRate;
	}
	
	public Boolean getActive() {
	    return active;
	}
	
	public void setActive(Boolean active) {
	    this.active = active;
	}

	public Paygrade getPayGrade() {
	    return payGrade;
	}
	
	public void setPayGrade(Paygrade paygrade) {
	    this.payGrade = paygrade;
	}
	
	
	
}
