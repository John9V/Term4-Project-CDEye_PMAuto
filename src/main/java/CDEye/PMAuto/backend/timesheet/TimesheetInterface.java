package CDEye.PMAuto.backend.timesheet;

import javax.ejb.Stateless;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import CDEye_PMAuto.backend.employee.ActiveEmployeeBean;

public interface TimesheetInterface {
	
	//Get timesheets for the currently logged in employee
	public Timesheet[] getTimesheetsByEmployee();
	
	//Get unapproved timesheets for all employees managed by the currently logged in employee
	public Timesheet[] getTimesheetsNeedingApproval();
	
	//find by primary key
	public Timesheet find();
	
	//add timesheet
	public void addTimesheet(Timesheet t);
	
	//edit timesheet
	public void merge(Timesheet t);
	
	//delete timesheet
	public void deleteTimesheet(Timesheet t);
	
}
