package CDEye_PMAuto.backend.recepackage;

import java.math.BigDecimal;
import java.util.UUID;

import javax.persistence.Transient;

import CDEye_PMAuto.backend.employee.Employee;
import CDEye_PMAuto.backend.paygrade.Paygrade;
import CDEye_PMAuto.backend.workpackage.WorkPackage;

public class EditableRECEPackage extends RECEPackage {

    /** Determines if the RECE is editable, true = yes, false = no **/
    @Transient
    private boolean editable = false;
    
    /** Determines if the RECE is deletable, true = yes, false = no **/
    @Transient
    private boolean deletable = false;
    
    /** No-Param Constructor */
    public EditableRECEPackage() {
        super();
    }
    
    /**
     * Constructor
     * 
     * @param r, a RECE Package
     */
    public EditableRECEPackage(RECEPackage r) {
        super(r.id, r.parentWp, r.paygrade, r.personDayEstimate, r.employee);
    };
    
    /**
     * Constructor
     * 
     * @param id of the RECE as a UUID
     * @param parentwp of the RECE as a WorkPackage
     * @param paygrade of the RECE as a Paygrade
     * @param personDayEstimate, number of days anticipated at the current paygrade, as a BigDecimal
     * @param employee chosen to work on the RECE at this Paygrade
     */
    public EditableRECEPackage(UUID id, WorkPackage parentwp, Paygrade paygrade, BigDecimal personDayEstimate, Employee employee) {
        super(id, parentwp, paygrade, personDayEstimate, employee);
    }
    
    // Getters and Setters ====================================================
    
    public boolean isEditable() {
        return editable;
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
    }

    public boolean isDeletable() {
        return deletable;
    }

    public void setDeletable(boolean deletable) {
        this.deletable = deletable;
    }
}


