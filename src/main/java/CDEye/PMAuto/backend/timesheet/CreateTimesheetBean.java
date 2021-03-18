package CDEye.PMAuto.backend.timesheet;

import CDEye.PMAuto.backend.tsrow.TimesheetRow;
import CDEye.PMAuto.backend.tsrow.TimesheetRowManager;
import CDEye_PMAuto.backend.employee.ActiveEmployeeBean;
import CDEye_PMAuto.backend.employee.Employee;
import CDEye_PMAuto.backend.project.Project;
import CDEye_PMAuto.backend.project.ProjectManager;
import CDEye_PMAuto.backend.workpackage.WorkPackage;
import CDEye_PMAuto.backend.workpackage.WorkPackageManager;

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
	@Inject TimesheetRowManager rowManager;
	@Inject
	ProjectManager projectManager;
	@Inject
	WorkPackageManager wpManager;

	private String projNum;
	
	public CreateTimesheetBean() {}
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
		manager.addTimesheet(t);
		return "CreateTimesheet";
	}

	public String add() {
//		Timesheet t = new Timesheet();
//		Employee e = new Employee();
//		e.setId(aeb.getId());
//		t.setEmployee(e);
//		t.setEndDate(sheetDate);
//
//		t.setDetails(this.getDetails());

		for (TimesheetRow r : this.getDetails()) {
			Project p = projectManager.findProjectByNum(r.getCreationProjNum());
			WorkPackage[] wp = wpManager.findWpsByPkgNumAndProj(r.getCreationWpNum(), p);
			if (wp.length > 0) {
				r.setProject(p);
				r.setWorkPackage(wp[0]);
			} else System.out.println("Not found");
			rowManager.addRow(r);
		}
		//manager.addTimesheet(t);


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
