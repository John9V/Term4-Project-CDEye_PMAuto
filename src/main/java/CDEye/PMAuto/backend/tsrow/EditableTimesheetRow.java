package CDEye.PMAuto.backend.tsrow;

import CDEye.PMAuto.backend.timesheet.Timesheet;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named
@RequestScoped
public class EditableTimesheetRow extends TimesheetRow implements Serializable {

    private boolean editable;
    private boolean deletable;

    public EditableTimesheetRow() {}
    public EditableTimesheetRow(Timesheet parent, TimesheetRow target) { super(parent, target); }

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
