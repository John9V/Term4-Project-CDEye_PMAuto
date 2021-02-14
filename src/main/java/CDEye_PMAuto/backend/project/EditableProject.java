package CDEye_PMAuto.backend.project;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

import CDEye_PMAuto.backend.employee.Employee;

public class EditableProject extends Project implements Serializable {
	private boolean editable = false;
	private boolean deletable = false;

	public EditableProject() {
		super();
	}

	public EditableProject(Project p) {
		super(p.id, p.projectName, p.projectNumber, p.projManager, p.startDate, p.endDate, p.estimateBudget,
				p.markUpRate, p.projectBudget);
	}

	public EditableProject(UUID id, String projectName, String projectNumber, Employee projectManager, Date startDate,
			Date endDate, BigDecimal estimateBudget, BigDecimal markUpRate, BigDecimal projectBudget) {
		super(id, projectName, projectNumber, projectManager, startDate, endDate, estimateBudget, markUpRate, projectBudget);
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
