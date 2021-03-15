package CDEye.PMAuto.backend.timesheet;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import CDEye_PMAuto.backend.project.EditableProject;

@Named("activeTimesheetBean")
@SessionScoped
public class ActiveTimesheetBean extends Timesheet implements Serializable {

	@Inject TimesheetList tsl;
	
	public String setActiveTimesheetBean(EditableTimesheet ets) {
		this.id = ets.id;
		this.employee = ets.employee;
		this.endDate = ets.endDate;
		this.details = ets.details;
		this.sick = ets.sick;
		this.flex = ets.flex;
		tsl.refreshList();
		
		return "TimesheetDetails";
	}
	
}
