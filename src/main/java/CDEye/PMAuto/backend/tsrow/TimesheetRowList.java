package CDEye.PMAuto.backend.tsrow;

import CDEye.PMAuto.backend.timesheet.Timesheet;
import CDEye_PMAuto.backend.project.Project;
import CDEye_PMAuto.backend.project.ProjectManager;
import CDEye_PMAuto.backend.workpackage.WorkPackage;
import CDEye_PMAuto.backend.workpackage.WorkPackageManager;

import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.enterprise.context.Dependent;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@ConversationScoped
public class TimesheetRowList implements Serializable {

    @Inject @Dependent TimesheetRowManager rowManager;
    @Inject ProjectManager projectManager;
    @Inject WorkPackageManager workPackageManager;
    @Inject Conversation conversation;

    private Timesheet parentSheet;
    private List<EditableTimesheetRow> rowList;

    public List<EditableTimesheetRow> getRowList(Timesheet parent) {
        if (!conversation.isTransient()) {
            conversation.end();
        }
        conversation.begin();
        if (rowList == null) refreshRowList(parent);
        return rowList;
    }

    public void refreshRowList(Timesheet parent) {
        this.parentSheet = parent;
        TimesheetRow[] rows = rowManager.getRowsForTimesheet(parent);
        rowList = new ArrayList<>();
        for (TimesheetRow row : rows) {
            EditableTimesheetRow etr = new EditableTimesheetRow(row);
            etr.setProjects(projectManager.getAll());
            rowList.add(etr);
        }
    }

    public void addRow() {
        EditableTimesheetRow etr = new EditableTimesheetRow();
        etr.setEditable(true);
        etr.setTimesheet(parentSheet);
        etr.setProjects(projectManager.getAll());
        rowList.add(etr);
    }

    public String save() {
        for (EditableTimesheetRow edited : rowList) {
            if (edited.isDeletable()) {
                rowManager.deleteRow(new TimesheetRow(edited));
                continue;
            }
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

    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        String pNum = value.toString();
        UIInput uiInputWorkPackageNumber = (UIInput) component.getAttributes().get("workPackageNumber");
        String wNum = uiInputWorkPackageNumber.getSubmittedValue().toString();

        Project p = projectManager.findProjectByNum(pNum);
        WorkPackage[] wp = workPackageManager.findWpsByPkgNumAndProj(wNum, p);
        if (wp.length == 0) {
            FacesMessage msg = new FacesMessage();
            msg.setDetail("The work package doesn't exists within given project");
            msg.setSummary("Work package not found");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }
    }

    public String back() {
        if (!conversation.isTransient()) {
            conversation.end();
        }
        return "TimesheetList";
    }
}
