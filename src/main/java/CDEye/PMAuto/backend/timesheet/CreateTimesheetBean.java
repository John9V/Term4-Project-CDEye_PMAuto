package CDEye.PMAuto.backend.timesheet;

import CDEye.PMAuto.backend.tsrow.EditableTimesheetRow;
import CDEye.PMAuto.backend.tsrow.TimesheetRow;
import CDEye.PMAuto.backend.tsrow.TimesheetRowManager;
import CDEye_PMAuto.backend.employee.Employee;
import CDEye_PMAuto.backend.employee.EmployeeManager;
import CDEye_PMAuto.backend.project.Project;
import CDEye_PMAuto.backend.project.ProjectManager;
import CDEye_PMAuto.backend.workpackage.WorkPackage;
import CDEye_PMAuto.backend.workpackage.WorkPackageManager;

import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Named("createTimesheet")
@ConversationScoped
public class CreateTimesheetBean extends Timesheet implements Serializable {

	@Inject TimesheetManager timesheetManager;
	@Inject ProjectManager projectManager;
	@Inject WorkPackageManager workPackageManager;
	@Inject EmployeeManager employeeManager;
	@Inject Conversation conversation;
	@Inject
	TimesheetRowManager rowManager;

	private LocalDate sheetDate;
	private List<EditableTimesheetRow> editableRows;

	public CreateTimesheetBean(){
		System.out.println("CreateTimeSheetBean");
	}

	public String initialize(String activeUserName) {
		if (!conversation.isTransient()) {
			conversation.end();
		}
		conversation.begin();

		Employee activeEmployee = employeeManager.getEmployeeByUserName(activeUserName);
		this.setEmployee(activeEmployee);
		this.editableRows = new ArrayList<>();
		System.out.println("initialized");
		return "CreateTimesheet";
	}

	public String add() {
		timesheetManager.addTimesheet(new Timesheet(this));
		saveTimesheetRow();
		if (!conversation.isTransient()) {
			conversation.end();
		}
		return "TimesheetList";
	}

	private void saveTimesheetRow() {
		//System.out.println(this.getId());
		//Timesheet t = timesheetManager.find(this.getId());
		//System.out.println(t.getId());

		for (EditableTimesheetRow er : getEditableRows()) {
			Project p = projectManager.findProjectByNum(er.getProjectNumber());
			WorkPackage[] wp = workPackageManager.findWpsByPkgNumAndProj(er.getWorkPackageNumber(), p);
			if (wp.length > 0) {
				er.setProject(p);
				er.setWorkPackage(wp[0]);
				er.setTimesheet(this);
				TimesheetRow tsr = new TimesheetRow(er);
				this.getDetails().add(tsr);
				rowManager.addRow(tsr);
			} else System.out.println("Not found");
		}
	}

	//ADD button edit page
	//Create an approval

	public void addRow() {
		this.editableRows.add(new EditableTimesheetRow());
	}

	public LocalDate getSheetDate() {
		return sheetDate;
	}
	public void setSheetDate(LocalDate sheetDate) {
		this.sheetDate = sheetDate;
	}

	public List<EditableTimesheetRow> getEditableRows() {
		return editableRows;
	}

	public void setEditableRows(List<EditableTimesheetRow> editableRows) {
		this.editableRows = editableRows;
	}
}
