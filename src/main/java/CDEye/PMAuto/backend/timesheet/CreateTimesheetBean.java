package CDEye.PMAuto.backend.timesheet;

import CDEye.PMAuto.backend.tsrow.TimesheetRow;
import CDEye_PMAuto.backend.employee.ActiveEmployeeBean;
import CDEye_PMAuto.backend.employee.Employee;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.time.LocalDate;

@Named("createTimesheet")
@SessionScoped
public class CreateTimesheetBean extends Timesheet implements Serializable {

	@Inject ActiveEmployeeBean aeb;
	@Inject TimesheetManager manager;

	public CreateTimesheetBean() {
		System.out.println("create");

	}
	public CreateTimesheetBean(Timesheet nts) {
		super(nts);
	}

	private LocalDate sheetDate;

	public String createTimesheet() {
		return "";
	}

	public String add() {
		System.out.println("adding");

		Timesheet t = new Timesheet();
		Employee e = new Employee();
		e.setId(aeb.getId());
		t.setEmployee(e);
		t.setEndDate(sheetDate);
		System.out.println("clicked");
		manager.addTimesheet(t);
		return "timesheetlist";
	}

	public void addRow() {
		this.getDetails().add(new TimesheetRow());
	}

	public LocalDate getSheetDate() {
		return sheetDate;
	}

	public void setSheetDate(LocalDate sheetDate) {
		this.sheetDate = sheetDate;
	}
}
