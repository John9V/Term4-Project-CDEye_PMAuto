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
		super(e.id, e.empNum, e.firstName, e.lastName, e.active, e.payGrade);
	}

	public EditableEmployee(UUID id, String empNum, String firstName, String lastName, Boolean active, Paygrade payGrade) {
		super(id, empNum, firstName, empNum, active, payGrade);
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
