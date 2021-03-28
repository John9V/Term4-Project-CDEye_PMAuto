package CDEye_PMAuto.backend.project;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named("createProject")
@RequestScoped
public class CreateProjectBean extends Project implements Serializable {
    @Inject private ProjectManager projectManager;

    public String add() {
        Project p = new Project(this);
        projectManager.persist(p);
        return "ProjectDashboard";
    }
}
