package CDEye_PMAuto.backend.project;

import java.io.Serializable;
import java.util.UUID;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import CDEye_PMAuto.backend.credentials.Credential;
import CDEye_PMAuto.backend.credentials.CredentialManager;
import CDEye_PMAuto.backend.employee.Employee;

@Named("activeProjectBean")
@SessionScoped
public class ActiveProjectBean extends Project implements Serializable {

	@Inject ProjectManager projectManager;
	
	public String setActiveProjectBean(EditableProject ep) {
		UUID uid = UUID.fromString("12121212-e89b-12d3-a456-556642430000");  
		this.id = uid;
		Project activeProject = projectManager.find(this.id);
		this.id = activeProject.id;
		this.projectName = activeProject.projectName;
		this.projectNumber = activeProject.projectNumber;
		this.projManager = activeProject.projManager;
		this.startDate = activeProject.startDate;
		this.endDate = activeProject.endDate;
		this.estimateBudget = activeProject.estimateBudget;
		this.markUpRate = activeProject.markUpRate;
		this.projectBudget = activeProject.projectBudget;
		
		return "WPList";
	}
	
}
