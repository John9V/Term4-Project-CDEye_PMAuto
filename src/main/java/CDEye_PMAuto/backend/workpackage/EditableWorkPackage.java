package CDEye_PMAuto.backend.workpackage;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.UUID;

/**
 * Editable version of work package.
 */
public class EditableWorkPackage extends WorkPackage implements Serializable {
    private boolean editable = false;
    private boolean deletable = false;
    
    EditableWorkPackage() {
        super();
    }
    
    /**
     * Constructor that takes an existing work package.
     */
    EditableWorkPackage(WorkPackage wp) {
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
    
    /**
     * Constructor with all parameters.
     */
    public EditableWorkPackage(UUID id, String workPackageNumber, WorkPackage parentWp, BigDecimal unAllocatedBudget,
            BigDecimal allocatedBudget, BigDecimal allocatedPersonDays, BigDecimal respEngPersonDayEstimate,
            BigDecimal respEngBudgetEstimate, BigDecimal completedBudget, BigDecimal completedPersonDays,
            BigDecimal completedVarianceProjectPD, BigDecimal completedVarianceProjectBudget,
            BigDecimal respEngEstVarianceProjectPD, BigDecimal respEngEstVarianceProjectBudget, Date startDate,
            Date endDate, boolean isLeaf) {
        super(id, workPackageNumber, parentWp, unAllocatedBudget, allocatedBudget, allocatedPersonDays,
                respEngPersonDayEstimate, respEngBudgetEstimate, completedBudget, completedPersonDays,
                completedVarianceProjectPD, completedVarianceProjectBudget, respEngEstVarianceProjectPD,
                respEngEstVarianceProjectBudget, startDate, endDate, isLeaf);
    }

    /**
     * @return the editable
     */
    public boolean isEditable() {
        return editable;
    }

    /**
     * @param editable the editable to set
     */
    public void setEditable(boolean editable) {
        this.editable = editable;
    }

    /**
     * @return the deletable
     */
    public boolean isDeletable() {
        return deletable;
    }

    /**
     * @param deletable the deletable to set
     */
    public void setDeletable(boolean deletable) {
        this.deletable = deletable;
    }
    
}
