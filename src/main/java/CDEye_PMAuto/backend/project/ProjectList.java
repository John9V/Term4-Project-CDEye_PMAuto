package CDEye_PMAuto.backend.project;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.inject.Named;

@Named("projectList")
@ConversationScoped
public class ProjectList implements Serializable {
	@Inject @Dependent private ProjectManager projectManager;
	private List<EditableProject> list;
	@Inject Conversation conversation;
	@Inject ActiveProjectBean apb;
	
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
		System.out.println("calling set active project bean for: " + p.getProjectName());
		conversation.end();
		return apb.setActiveProjectBean(p);
	}
	
	public String save() {
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).isEditable()) {
				Project p = new Project(list.get(i));
				projectManager.updateProject(p);
				list.get(i).setEditable(false);
			}
			if (list.get(i).isDeletable()) {
				Project p = new Project(list.get(i));
				projectManager.delete(p);
			}
		}
		refreshList();
		return "";
	}
	
}
