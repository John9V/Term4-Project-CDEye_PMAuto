package CDEye_PMAuto.backend.recepackage;

import java.math.BigDecimal;
import java.util.UUID;

import CDEye_PMAuto.backend.employee.Employee;
import CDEye_PMAuto.backend.paygrade.Paygrade;
import CDEye_PMAuto.backend.workpackage.WorkPackage;

public class EditableRECEPackage extends RECEPackage {

    private boolean editable = false;
    private boolean deletable = false;
    
    public EditableRECEPackage() {
        super();
    }
    
    public EditableRECEPackage(RECEPackage r) {
        super(r.id, r.parentWp, r.paygrade, r.personDayEstimate, r.employee);
    };
    
    public EditableRECEPackage(UUID id, WorkPackage parentwp, Paygrade paygrade, BigDecimal personDayEstimate, Employee employee) {
        super(id, parentwp, paygrade, personDayEstimate, employee);
    }
    
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


