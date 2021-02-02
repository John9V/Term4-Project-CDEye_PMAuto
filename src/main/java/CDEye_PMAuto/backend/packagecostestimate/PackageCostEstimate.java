package CDEye_PMAuto.backend.packagecostestimate;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import CDEye_PMAuto.backend.employee.Employee;
import CDEye_PMAuto.backend.paygrade.Paygrade;
import CDEye_PMAuto.backend.workpackage.WorkPackage;

@Entity
@Table(name="packagecostestimate")
@Named("packageCostEstimate")
@RequestScoped
public class PackageCostEstimate implements Serializable {

	@Id
	@Column(name="id")
	@Type(type = "uuid-char")
	protected UUID id;
	
	@ManyToOne
	@JoinColumn(name="workpackage")
	protected WorkPackage workPackage;
	
	//Might be null
	@ManyToOne
	@JoinColumn(name="employee")
	protected Employee employee;
	
	@ManyToOne
	@JoinColumn(name="paygrade")
	protected Paygrade paygrade;
	
	@Column(name="persondaysestimate")
	protected BigDecimal personDaysEstimate;
	
	@Column(name="dollarcostestimate")
	protected BigDecimal dollarCostEstimate;
	
	/**
	 * Calculates dollar cost estimate
	 */
	public BigDecimal calculateCost() { return null; }
	
}
