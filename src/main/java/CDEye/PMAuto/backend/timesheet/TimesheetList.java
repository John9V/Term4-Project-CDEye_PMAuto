package CDEye.PMAuto.backend.timesheet;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.inject.Named;

import CDEye_PMAuto.backend.employee.EditableEmployee;
import CDEye_PMAuto.backend.employee.Employee;
import CDEye_PMAuto.backend.employee.EmployeeManager;
import CDEye_PMAuto.backend.project.Project;

@Named("timesheetList")
@ConversationScoped
public class TimesheetList implements Serializable {

	@Inject 
	@Dependent 
	private TimesheetManager timesheetManager;
	
	@Inject 
	Conversation conversation;
	
	private List<EditableTimesheet> list;
	
	public List<EditableTimesheet> getList() {
		if (!conversation.isTransient()) {
			conversation.end();
		}
		conversation.begin();
        if (list == null) {
            refreshList();
        }
        return list;
    }
	
	public List<EditableTimesheet> refreshList() {
		Timesheet[] timesheets = timesheetManager.getAllForCurrentEmployee();
        list = new ArrayList<EditableTimesheet>();
        for (int i = 0; i < timesheets.length; i++) {
            list.add(new EditableTimesheet(timesheets[i]));
        }
        return list;
    }
	
	public String save() {
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).isEditable()) {
				Timesheet t = new Timesheet(list.get(i));
				timesheetManager.updateTimesheet(t);
				list.get(i).setEditable(false);
			}
			if (list.get(i).isDeletable()) {
				Timesheet t = new Timesheet(list.get(i));
				timesheetManager.delete(t);
			}
		}
		refreshList();
		return "";
	}
	
	
	
}
