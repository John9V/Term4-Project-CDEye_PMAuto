package CDEye_PMAuto.backend.recepackage;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.UUID;

import CDEye_PMAuto.backend.paygrade.Paygrade;
import CDEye_PMAuto.backend.workpackage.WorkPackage;

public class EditableRECEPackage extends RECEPackage {

    private boolean editable = false;
    private boolean deletable = false;
    
    public EditableRECEPackage() {
        super();
    }
    
    public EditableRECEPackage(RECEPackage r) {
        super(r.id, r.workPackageNumber, r.parentWp, r.budgetEstimate, r.personDayEstimate, r.paygradeDaysBreakdown);
    };
    
    public EditableRECEPackage(UUID id, String workpackagenumber, WorkPackage parentwp, 
            BigDecimal budgetEstimate, BigDecimal personDayEstimate, HashMap<Paygrade, Integer> paygradeDaysBreakdown) {
        super(id, workpackagenumber, parentwp, budgetEstimate, personDayEstimate, paygradeDaysBreakdown);
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


