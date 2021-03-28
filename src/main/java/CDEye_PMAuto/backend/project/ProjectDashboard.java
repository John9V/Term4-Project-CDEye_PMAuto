package CDEye_PMAuto.backend.project;

import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named("projectDashboard")
@SessionScoped
public class ProjectDashboard implements Serializable {
	@Inject @Dependent private ProjectManager projectManager;
	private List<EditableProject> list;
	@Inject ActiveProjectBean apb;
	@Inject
	EditProjectWrapper epb;

	public List<EditableProject> getList() {
		refreshList();
		return list;
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
		return apb.setActiveProjectBean(p);
	}

	public String editProject(EditableProject p) {
		return epb.setEditProjectBean(p);
	}

}
