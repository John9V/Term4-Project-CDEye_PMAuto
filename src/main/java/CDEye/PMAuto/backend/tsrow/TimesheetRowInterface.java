package CDEye.PMAuto.backend.tsrow;

import java.util.UUID;

import CDEye.PMAuto.backend.timesheet.Timesheet;

public interface TimesheetRowInterface {

	//Find approved timesheet rows for the currently active workpackage
	public TimesheetRow[] findChargesForWorkPackage(UUID workPackage);
	
	//Get the timesheet rows for the currently active timesheet
	public TimesheetRow[] findTimesheetRowsForTimesheet();
	
	//find by primary key
	public Timesheet find();
	
	//add timesheetrow
	public void addTimesheet(TimesheetRow tsr);
	
	//edit timesheetrow
	public void merge(TimesheetRow tsr);
	
	//delete timesheetrow
	public void deleteTimesheet(TimesheetRow tsr);
	
}
