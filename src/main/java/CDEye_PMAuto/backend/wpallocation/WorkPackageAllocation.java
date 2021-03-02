package CDEye_PMAuto.backend.wpallocation;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@Table(name="workpackageallocation")
@Named("workPackageAllocation")
public class WorkPackageAllocation implements Serializable {

	@Id
	@Column(name="id")
	@Type(type = "uuid-char")
	protected UUID id;
	
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="workpackage")
    protected WorkPackage workPackage;
	
	@ManyToOne
	@JoinColumn(name="paygrade")
	protected Paygrade paygrade;
	
	@Column(name="persondaysestimate")
	protected BigDecimal personDaysEstimate;
	
	//constructor without id
	public WorkPackageAllocation(WorkPackage workPackage, Paygrade paygrade, BigDecimal personDaysEstimate) {
		super();
		this.id = UUID.randomUUID();
		this.workPackage = workPackage;
		this.paygrade = paygrade;
		this.personDaysEstimate = personDaysEstimate;
	}
	
	
	//constructor for new wpa
	
	public WorkPackageAllocation(CreateWorkPackageAlloc cwpa) {
		super();
		this.id = UUID.randomUUID();
		this.workPackage = cwpa.workPackage;
		this.paygrade = cwpa.paygrade;
		this.personDaysEstimate = cwpa.personDaysEstimate;
	}
	
	//constructor for editable wp
	
	public WorkPackageAllocation(EditableWorkPackageAlloc ewpa) {
		super();
		this.id = ewpa.id;
		this.workPackage = ewpa.workPackage;
		this.paygrade = ewpa.paygrade;
		this.personDaysEstimate = ewpa.personDaysEstimate;
	}
	
	//constructor for other wp
	
	public WorkPackageAllocation(WorkPackageAllocation wpa) {
		super();
		this.id = wpa.id;
		this.workPackage = wpa.workPackage;
		this.paygrade = wpa.paygrade;
		this.personDaysEstimate = wpa.personDaysEstimate;
	}
	
	WorkPackageAllocation() {}
	
	public WorkPackageAllocation(UUID id, WorkPackage workPackage, Paygrade paygrade, BigDecimal personDaysEstimate) {
		super();
		this.id = id;
		this.workPackage = workPackage;
		this.paygrade = paygrade;
		this.personDaysEstimate = personDaysEstimate;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public WorkPackage getWorkPackage() {
		return workPackage;
	}

	public void setWorkPackage(WorkPackage workPackage) {
		this.workPackage = workPackage;
	}

	public Paygrade getPaygrade() {
		return paygrade;
	}

	public void setPaygrade(Paygrade paygrade) {
		this.paygrade = paygrade;
	}

	public BigDecimal getPersonDaysEstimate() {
		return personDaysEstimate;
	}

	public void setPersonDaysEstimate(BigDecimal personDaysEstimate) {
		this.personDaysEstimate = personDaysEstimate;
	}

	/**
	 * Calculates dollar cost estimate
	 */
	public BigDecimal calculateCost() { return null; }
	
}
