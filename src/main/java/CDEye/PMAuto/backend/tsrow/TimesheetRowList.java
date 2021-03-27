package CDEye.PMAuto.backend.tsrow;

import CDEye.PMAuto.backend.timesheet.Timesheet;
import CDEye_PMAuto.backend.project.Project;
import CDEye_PMAuto.backend.project.ProjectManager;
import CDEye_PMAuto.backend.workpackage.WorkPackage;
import CDEye_PMAuto.backend.workpackage.WorkPackageManager;

import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@ConversationScoped
public class TimesheetRowList implements Serializable {

    @Inject @Dependent
    private TimesheetRowManager rowManager;
    @Inject
    ProjectManager projectManager;
    @Inject
    WorkPackageManager workPackageManager;

    @Inject
    Conversation conversation;

    private List<EditableTimesheetRow> rowList;

    public List<EditableTimesheetRow> getRowList(Timesheet parent) {
        if (!conversation.isTransient()) {
            conversation.end();
        }
        conversation.begin();
        refreshRowList(parent);
        return rowList;
    }

    public void refreshRowList(Timesheet parent) {
        TimesheetRow[] rows = rowManager.getRowsForTimesheet(parent);
        rowList = new ArrayList<>();
        for (TimesheetRow row : rows) {
            rowList.add(new EditableTimesheetRow(parent, row));
        }
    }

    public String save() {
        for (EditableTimesheetRow edited : rowList) {
            Project p = projectManager.findProjectByNum(edited.getProjectNumber());
            WorkPackage[] wp = workPackageManager.findWpsByPkgNumAndProj(edited.getWorkPackageNumber(), p);
            if (wp.length > 0) {
                edited.setProject(p);
                edited.setWorkPackage(wp[0]);
                TimesheetRow row = new TimesheetRow(edited);
                rowManager.updateRow(row);
                edited.setEditable(false);
            } else System.out.println("Not found");
        }
        if (!conversation.isTransient()) {
            conversation.end();
        }
        return "TimesheetList";
    }
}
