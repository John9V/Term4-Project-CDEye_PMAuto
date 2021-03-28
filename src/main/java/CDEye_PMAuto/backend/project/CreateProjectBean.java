package CDEye_PMAuto.backend.project;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named("createProject")
@RequestScoped
public class CreateProjectBean extends Project implements Serializable {
    @Inject private ProjectManager projectManager;

    String errorMsg;
    boolean showError;

    public String add() {
        if (this.projectNumber.isEmpty()) {
            showError = true;
            errorMsg = "The project number cannot be empty";
        } else if (this.projectName.isEmpty()) {
            showError = true;
            errorMsg = "The project name cannot be empty";
        } else if (projectManager.isProjectNameExist(this.projectName)) {
            errorMsg = "The project name " + this.projectName + " already exists";
            showError = true;
        } else if (projectManager.isProjectNumberExist(this.projectNumber)) {
            errorMsg = "The project number " + this.projectNumber + " already exists";
            showError = true;
        } else {
            Project p = new Project(this);
            projectManager.persist(p);
            showError = false;
            errorMsg = "";
            return "VIEW_PROJECTS";
        }

        return "";
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public boolean isShowError() {
        return showError;
    }

    public void setShowError(boolean showError) {
        this.showError = showError;
    }
}
