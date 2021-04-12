package CDEye_PMAuto.backend.project;

import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import CDEye_PMAuto.backend.employee.ActiveEmployeeBean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named("projectDashboard")
@RequestScoped
public class ProjectDashboard implements Serializable {
	@Inject @Dependent private ProjectManager projectManager;
	private List<EditableProject> list;
//	@Inject Conversation conversation;
	@Inject ActiveProjectBean apb;
	@Inject ActiveEmployeeBean activeEmp;
	@Inject ProjectList projList;
	@Inject
	EditProjectWrapper epb;

	private EditableProject deletedProject = new EditableProject();
	
	public List<EditableProject> getList() {
//		if (!conversation.isTransient()) {
//			conversation.end();
//		}
//		conversation.begin();
		if (list == null) {
			refreshList();
		}
		return list;
	}

	public EditableProject getDeletedProject() {
		return deletedProject;
	}

	public void setDeletedProject(EditableProject deletedProject) {
		this.deletedProject = deletedProject;
	}

	public List<EditableProject> refreshList() {
		Project[] projects = projectManager.getAll();
		list = new ArrayList<EditableProject>();
		for (int i = 0; i < projects.length; i++) {
			list.add(new EditableProject(projects[i]));
		}
		return list;
	}

	public String viewProjectWPs(EditableProject p) {
		//conversation.end();
		return apb.setActiveProjectBean(p);
	}

	public String editProject(EditableProject p) {
//		conversation.end();
		return epb.setActiveProjectBean(p);
	}
	
    public String deleteProject(EditableProject p) {
    	projectManager.deleteProject(projectManager.findProject(p.projectName));
        projList.refreshList();
    	return "ProjectDashboard";
    }
    
    public String reportProject(EditableProject p) {
//        conversation.end();
        return apb.setReportProjectBean(p);
    }

}
