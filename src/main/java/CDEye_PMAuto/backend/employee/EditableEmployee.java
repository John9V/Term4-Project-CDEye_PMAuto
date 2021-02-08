package CDEye_PMAuto.backend.employee;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

import CDEye_PMAuto.backend.paygrade.Paygrade;

public class EditableEmployee extends Employee implements Serializable {
	private boolean editable = false;
	private boolean deletable = false;
	
	public EditableEmployee() {
		super();
	}

	public EditableEmployee(Employee e) {
		super(e.id, e.empNum, e.firstName, e.lastName, e.userName, e.active, e.hr, e.payGrade);
	}

	public EditableEmployee(UUID id, String empNum, String firstName, String lastName, String userName, Boolean active, Boolean hr, Paygrade payGrade) {
		super(id, empNum, firstName, lastName, userName, active, hr, payGrade);
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
