package CDEye_PMAuto.backend.project;

import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named("projectDashboard")
@ConversationScoped
public class ProjectDashboard implements Serializable {
	@Inject @Dependent private ProjectManager projectManager;
	private List<EditableProject> list;
	@Inject Conversation conversation;
	@Inject ActiveProjectBean apb;
	@Inject
	EditProjectWrapper epb;

	public List<EditableProject> getList() {
		if (!conversation.isTransient()) {
			conversation.end();
		}
		conversation.begin();
		if (list == null) {
			refreshList();
		}
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
		conversation.end();
		return apb.setActiveProjectBean(p);
	}

	public String editProject(EditableProject p) {
		conversation.end();
		return epb.setActiveProjectBean(p);
	}

}
