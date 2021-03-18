package CDEye.PMAuto.backend.timesheet;

import CDEye.PMAuto.backend.tsrow.TimesheetRow;
import CDEye_PMAuto.backend.employee.ActiveEmployeeBean;
import CDEye_PMAuto.backend.employee.Employee;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

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
		Timesheet t = new Timesheet();
		Employee e = new Employee();
		e.setId(aeb.getId());
		t.setEmployee(e);
		Date date = new Date();
		t.setEndDate(date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
		this.employee = e;
		this.endDate = t.getEndDate();
		return "CreateTimesheet";
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
		System.out.println("called in addrow");
		Timesheet t = new Timesheet();
//		t.setId(this.id);
//		t.setEndDate(this.endDate);
		this.getDetails().add(new TimesheetRow(this));
		System.out.println("getdetails: " + this.getDetails());
		for (TimesheetRow tsr : this.getDetails()) {
			System.out.println("tsr: " + tsr);
		}
//		return "";
	}

	public LocalDate getSheetDate() {
		return sheetDate;
	}

	public void setSheetDate(LocalDate sheetDate) {
		this.sheetDate = sheetDate;
	}
}
