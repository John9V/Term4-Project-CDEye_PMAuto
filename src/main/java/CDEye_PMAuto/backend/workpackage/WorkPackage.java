package CDEye_PMAuto.backend.workpackage;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.UUID;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name="workpackages")
@Named("workPackage")
@RequestScoped
public class WorkPackage implements Serializable {

	@Id
	@Column(name="id")
	@Type(type = "uuid-char")
	protected UUID id;
	
	/** Number of the package ex: 12100. */
	@Column(name="workpackagenumber")
	protected String workPackageNumber;
	
	/** Parent WorkPackage. */
	@ManyToOne
	@JoinColumn(name="workpackages")
	protected WorkPackage parentWp;
	
	
	/** Budget that this WP has, but is not yet allocated further. */
	@Column(name="unallocatedbudget")
    protected BigDecimal unAllocatedBudget;
	
	
	/** Budget that this WP has and is allocated further. */
	@Column(name="allocatedbudget")
	protected BigDecimal allocatedBudget;
	
	/** Person days this WP has that are allocated further. */
	@Column(name="allocatedpersondays")
    protected BigDecimal allocatedPersonDays;
	
	
	/** Person days amount that the responsible engineer has estimated. */
	@Column(name="respengpersondayestimate")
    protected BigDecimal respEngPersonDayEstimate;
    
	/** Budget amount that the the responsible engineer has estimated. */
    @Column(name="respengbudgetestimate")
    protected BigDecimal respEngBudgetEstimate;
	
	
    /**
     * Budget sum from this and all children WP that has been used up / is payable to the
     * employees on the WP for their effort based on timesheets they submitted.
     */
	@Column(name="completedbudget")
    protected BigDecimal completedBudget; // sum
	
	/**
     * Person day sum from this and all children WP that has been used up / accumulated by the
     * employees on the WP for their effort based on timesheets they submitted.
     */
	@Column(name="completedpersondays")
	protected BigDecimal completedPersonDays; //sum
	
	
	/** Variance of allocated person days and completed person days. */
	@Column(name="completedvarianceprojectpd")
    protected BigDecimal completedVarianceProjectPD;
	
	/** Variance of allocated budget and completed budget. */
	@Column(name="completedvarianceprojectbudget")
    protected BigDecimal completedVarianceProjectBudget;
	
	
	/** Variance of person days relating to the responsible engineer estimate. */
	@Column(name="respengestvarianceprojectpd")
    protected BigDecimal respEngEstVarianceProjectPD;
    
	/** Variance of budget relating to the responsible engineer estimate. */
    @Column(name="respengestvarianceprojectbudget")
    protected BigDecimal respEngEstVarianceProjectBudget;
	
	
    /** Start date of the package. */
	@Column(name="startdate")
    protected Date startDate;
    
	/** End date of the package. */
    @Column(name="enddate")
    protected Date endDate;
    
	
    /** Boolean for identifying if the package has children or not. */
	@Column(name="isleaf")
	protected boolean isLeaf;


	/**
	 * Default no parameter constructor.
	 */
    public WorkPackage() {
        super();
    }

    /**
     * Constructor with all parameters.
     */
    public WorkPackage(UUID id, String workPackageNumber, WorkPackage parentWp, BigDecimal unAllocatedBudget,
            BigDecimal allocatedBudget, BigDecimal allocatedPersonDays, BigDecimal respEngPersonDayEstimate,
            BigDecimal respEngBudgetEstimate, BigDecimal completedBudget, BigDecimal completedPersonDays,
            BigDecimal completedVarianceProjectPD, BigDecimal completedVarianceProjectBudget,
            BigDecimal respEngEstVarianceProjectPD, BigDecimal respEngEstVarianceProjectBudget, Date startDate,
            Date endDate, boolean isLeaf) {
        super();
        this.id = id;
        this.workPackageNumber = workPackageNumber;
        this.parentWp = parentWp;
        this.unAllocatedBudget = unAllocatedBudget;
        this.allocatedBudget = allocatedBudget;
        this.allocatedPersonDays = allocatedPersonDays;
        this.respEngPersonDayEstimate = respEngPersonDayEstimate;
        this.respEngBudgetEstimate = respEngBudgetEstimate;
        this.completedBudget = completedBudget;
        this.completedPersonDays = completedPersonDays;
        this.completedVarianceProjectPD = completedVarianceProjectPD;
        this.completedVarianceProjectBudget = completedVarianceProjectBudget;
        this.respEngEstVarianceProjectPD = respEngEstVarianceProjectPD;
        this.respEngEstVarianceProjectBudget = respEngEstVarianceProjectBudget;
        this.startDate = startDate;
        this.endDate = endDate;
        this.isLeaf = isLeaf;
    }

    /**
     * Constructor without the id parameter.
     */
    public WorkPackage(String workPackageNumber, WorkPackage parentWp, BigDecimal unAllocatedBudget,
            BigDecimal allocatedBudget, BigDecimal allocatedPersonDays, BigDecimal respEngPersonDayEstimate,
            BigDecimal respEngBudgetEstimate, BigDecimal completedBudget, BigDecimal completedPersonDays,
            BigDecimal completedVarianceProjectPD, BigDecimal completedVarianceProjectBudget,
            BigDecimal respEngEstVarianceProjectPD, BigDecimal respEngEstVarianceProjectBudget, Date startDate,
            Date endDate, boolean isLeaf) {
        super();
        this.workPackageNumber = workPackageNumber;
        this.parentWp = parentWp;
        this.unAllocatedBudget = unAllocatedBudget;
        this.allocatedBudget = allocatedBudget;
        this.allocatedPersonDays = allocatedPersonDays;
        this.respEngPersonDayEstimate = respEngPersonDayEstimate;
        this.respEngBudgetEstimate = respEngBudgetEstimate;
        this.completedBudget = completedBudget;
        this.completedPersonDays = completedPersonDays;
        this.completedVarianceProjectPD = completedVarianceProjectPD;
        this.completedVarianceProjectBudget = completedVarianceProjectBudget;
        this.respEngEstVarianceProjectPD = respEngEstVarianceProjectPD;
        this.respEngEstVarianceProjectBudget = respEngEstVarianceProjectBudget;
        this.startDate = startDate;
        this.endDate = endDate;
        this.isLeaf = isLeaf;
    }
    
    /**
     * Constructor that accepts a new work package.
     */
    public WorkPackage(NewWorkPackage wp) {
        super();
        this.id = wp.id;
        this.workPackageNumber = wp.workPackageNumber;
        this.parentWp = wp.parentWp;
        this.unAllocatedBudget = wp.unAllocatedBudget;
        this.allocatedBudget = wp.allocatedBudget;
        this.allocatedPersonDays = wp.allocatedPersonDays;
        this.respEngPersonDayEstimate = wp.respEngPersonDayEstimate;
        this.respEngBudgetEstimate = wp.respEngBudgetEstimate;
        this.completedBudget = wp.completedBudget;
        this.completedPersonDays = wp.completedPersonDays;
        this.completedVarianceProjectPD = wp.completedVarianceProjectPD;
        this.completedVarianceProjectBudget = wp.completedVarianceProjectBudget;
        this.respEngEstVarianceProjectPD = wp.respEngEstVarianceProjectPD;
        this.respEngEstVarianceProjectBudget = wp.respEngEstVarianceProjectBudget;
        this.startDate = wp.startDate;
        this.endDate = wp.endDate;
        this.isLeaf = wp.isLeaf;
    }
	
}
