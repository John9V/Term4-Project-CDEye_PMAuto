package CDEye_PMAuto.backend.workpackage;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
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

import CDEye_PMAuto.backend.project.Project;

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
	@JoinColumn(name="parentworkpackage")
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
    protected LocalDate startDate;
    
	/** End date of the package. */
    @Column(name="enddate")
    protected LocalDate endDate;
    
	
    /** Boolean for identifying if the package has children or not. */
	@Column(name="isleaf")
	protected boolean isLeaf;

	@Column(name="projectbudget")
	protected BigDecimal projectBudget;
	
	@ManyToOne
	@JoinColumn(name="project")
	protected Project project;
	
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
            BigDecimal respEngEstVarianceProjectPD, BigDecimal respEngEstVarianceProjectBudget, LocalDate startDate,
            LocalDate endDate, boolean isLeaf, BigDecimal projectBudget, Project project) {
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
        this.projectBudget = projectBudget;
        this.project = project;
        
    }

    /**
     * Constructor without the id parameter.
     */
    public WorkPackage(String workPackageNumber, WorkPackage parentWp, BigDecimal unAllocatedBudget,
            BigDecimal allocatedBudget, BigDecimal allocatedPersonDays, BigDecimal respEngPersonDayEstimate,
            BigDecimal respEngBudgetEstimate, BigDecimal completedBudget, BigDecimal completedPersonDays,
            BigDecimal completedVarianceProjectPD, BigDecimal completedVarianceProjectBudget,
            BigDecimal respEngEstVarianceProjectPD, BigDecimal respEngEstVarianceProjectBudget, LocalDate startDate,
            LocalDate endDate, boolean isLeaf, BigDecimal projectBudget, Project project) {
        super();
        this.id = UUID.randomUUID();
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
        this.projectBudget = projectBudget;
        this.project = project;
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
        this.projectBudget = wp.projectBudget;
        this.project = wp.project;
    }
    
    /**
     * Constructor that accepts an edited work package.
     */
    public WorkPackage(EditableWorkPackage wp) {
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
        this.projectBudget = wp.projectBudget;
        this.project = wp.project;
    }
    
    /**
     * Sets all variables to the ones in the passed in work package.
     */
    public void usePackage(WorkPackage wp) {
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
        this.projectBudget = wp.projectBudget;
        this.project = wp.project;
    }

    /**
     * @return the id
     */
    public UUID getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(UUID id) {
        this.id = id;
    }

    /**
     * @return the workPackageNumber
     */
    public String getWorkPackageNumber() {
        return workPackageNumber;
    }

    /**
     * @param workPackageNumber the workPackageNumber to set
     */
    public void setWorkPackageNumber(String workPackageNumber) {
        this.workPackageNumber = workPackageNumber;
    }

    /**
     * @return the parentWp
     */
    public WorkPackage getParentWp() {
        return parentWp;
    }

    /**
     * @param parentWp the parentWp to set
     */
    public void setParentWp(WorkPackage parentWp) {
        this.parentWp = parentWp;
    }

    /**
     * @return the unAllocatedBudget
     */
    public BigDecimal getUnAllocatedBudget() {
        return unAllocatedBudget;
    }

    /**
     * @param unAllocatedBudget the unAllocatedBudget to set
     */
    public void setUnAllocatedBudget(BigDecimal unAllocatedBudget) {
        this.unAllocatedBudget = unAllocatedBudget;
    }

    /**
     * @return the allocatedBudget
     */
    public BigDecimal getAllocatedBudget() {
        return allocatedBudget;
    }

    /**
     * @param allocatedBudget the allocatedBudget to set
     */
    public void setAllocatedBudget(BigDecimal allocatedBudget) {
        this.allocatedBudget = allocatedBudget;
    }

    /**
     * @return the allocatedPersonDays
     */
    public BigDecimal getAllocatedPersonDays() {
        return allocatedPersonDays;
    }

    /**
     * @param allocatedPersonDays the allocatedPersonDays to set
     */
    public void setAllocatedPersonDays(BigDecimal allocatedPersonDays) {
        this.allocatedPersonDays = allocatedPersonDays;
    }

    /**
     * @return the respEngPersonDayEstimate
     */
    public BigDecimal getRespEngPersonDayEstimate() {
        return respEngPersonDayEstimate;
    }

    /**
     * @param respEngPersonDayEstimate the respEngPersonDayEstimate to set
     */
    public void setRespEngPersonDayEstimate(BigDecimal respEngPersonDayEstimate) {
        this.respEngPersonDayEstimate = respEngPersonDayEstimate;
    }

    /**
     * @return the respEngBudgetEstimate
     */
    public BigDecimal getRespEngBudgetEstimate() {
        return respEngBudgetEstimate;
    }

    /**
     * @param respEngBudgetEstimate the respEngBudgetEstimate to set
     */
    public void setRespEngBudgetEstimate(BigDecimal respEngBudgetEstimate) {
        this.respEngBudgetEstimate = respEngBudgetEstimate;
    }

    /**
     * @return the completedBudget
     */
    public BigDecimal getCompletedBudget() {
        return completedBudget;
    }

    /**
     * @param completedBudget the completedBudget to set
     */
    public void setCompletedBudget(BigDecimal completedBudget) {
        this.completedBudget = completedBudget;
    }

    /**
     * @return the completedPersonDays
     */
    public BigDecimal getCompletedPersonDays() {
        return completedPersonDays;
    }

    /**
     * @param completedPersonDays the completedPersonDays to set
     */
    public void setCompletedPersonDays(BigDecimal completedPersonDays) {
        this.completedPersonDays = completedPersonDays;
    }

    /**
     * @return the completedVarianceProjectPD
     */
    public BigDecimal getCompletedVarianceProjectPD() {
        return completedVarianceProjectPD;
    }

    /**
     * @param completedVarianceProjectPD the completedVarianceProjectPD to set
     */
    public void setCompletedVarianceProjectPD(BigDecimal completedVarianceProjectPD) {
        this.completedVarianceProjectPD = completedVarianceProjectPD;
    }

    /**
     * @return the completedVarianceProjectBudget
     */
    public BigDecimal getCompletedVarianceProjectBudget() {
        return completedVarianceProjectBudget;
    }

    /**
     * @param completedVarianceProjectBudget the completedVarianceProjectBudget to set
     */
    public void setCompletedVarianceProjectBudget(BigDecimal completedVarianceProjectBudget) {
        this.completedVarianceProjectBudget = completedVarianceProjectBudget;
    }

    /**
     * @return the respEngEstVarianceProjectPD
     */
    public BigDecimal getRespEngEstVarianceProjectPD() {
        return respEngEstVarianceProjectPD;
    }

    /**
     * @param respEngEstVarianceProjectPD the respEngEstVarianceProjectPD to set
     */
    public void setRespEngEstVarianceProjectPD(BigDecimal respEngEstVarianceProjectPD) {
        this.respEngEstVarianceProjectPD = respEngEstVarianceProjectPD;
    }

    /**
     * @return the respEngEstVarianceProjectBudget
     */
    public BigDecimal getRespEngEstVarianceProjectBudget() {
        return respEngEstVarianceProjectBudget;
    }

    /**
     * @param respEngEstVarianceProjectBudget the respEngEstVarianceProjectBudget to set
     */
    public void setRespEngEstVarianceProjectBudget(BigDecimal respEngEstVarianceProjectBudget) {
        this.respEngEstVarianceProjectBudget = respEngEstVarianceProjectBudget;
    }

    /**
     * @return the startDate
     */
    public LocalDate getStartDate() {
        return startDate;
    }

    /**
     * @param startDate the startDate to set
     */
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    /**
     * @return the endDate
     */
    public LocalDate getEndDate() {
        return endDate;
    }

    /**
     * @param endDate the endDate to set
     */
    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    /**
     * @return the isLeaf
     */
    public boolean isLeaf() {
        return isLeaf;
    }

    /**
     * @param isLeaf the isLeaf to set
     */
    public void setLeaf(boolean isLeaf) {
        this.isLeaf = isLeaf;
    }

	public BigDecimal isProjectBudget() {
		return projectBudget;
	}

	public void setProjectBudget(BigDecimal projectBudget) {
		this.projectBudget = projectBudget;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public BigDecimal getProjectBudget() {
		return projectBudget;
	}
	
	
    
}
