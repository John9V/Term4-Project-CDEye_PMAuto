package CDEye_PMAuto.backend.employee;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="employees")
@Named("employee")
@SessionScoped
public class Employee implements Serializable {

	@Transient
	@Inject private EmployeeManager employeeManager;
	
	@Id
	@Column(name="id")
	protected String id;
	
	@Column(name="salary")
	protected BigDecimal salary;
	
	@Column(name="benefits")
	protected BigDecimal benefits;
	
	@Column(name="netrate")
	protected Short netRate;
	
	public Employee() {}

	public Employee(String id, BigDecimal salary, BigDecimal benefits, Short netRate) {
		super();
		this.id = id;
		this.salary = salary;
		this.benefits = benefits;
		this.netRate = netRate;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	
	
}
