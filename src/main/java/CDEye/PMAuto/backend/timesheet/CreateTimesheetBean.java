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

	private String projNum;
	
	public CreateTimesheetBean() {


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
		
		
		Timesheet t = new Timesheet();
		Employee e = new Employee();
		e.setId(aeb.getId());
		t.setEmployee(e);
		t.setEndDate(sheetDate);
		manager.addTimesheet(t);
		
		//go through all the timesheet rows in t.details and add them one at a time
		//see new work package bean for logic to add details one at a time
		
		// add cascade?? to entity class
		return "timesheetlist";
	}

	public void addRow() {
		this.getDetails().add(new TimesheetRow(this));
		//look up and set (use crud method in projectManager class) project for each row based on creationprojnum (write CRUD method)
		//look up and set work package for each row based on creationWpNum (use findWpsByPkgNumAndProj)
	}

	public LocalDate getSheetDate() {
		return sheetDate;
	}

	public void setSheetDate(LocalDate sheetDate) {
		this.sheetDate = sheetDate;
	}
	public String getProjNum() {
		return projNum;
	}
	public void setProjNum(String projNum) {
		this.projNum = projNum;
	}
	
	
}
