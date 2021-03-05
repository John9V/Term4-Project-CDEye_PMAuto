package CDEye_PMAuto.backend.workpackage;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import CDEye_PMAuto.backend.project.EditableProject;
import CDEye_PMAuto.backend.project.Project;
import CDEye_PMAuto.backend.project.ProjectManager;

@Named("activeWpBean")
@SessionScoped
public class ActiveWPBean extends WorkPackage implements Serializable {

	@Inject ProjectManager projectManager;
	@Inject WorkPackageList wpl;
	@Inject WorkPackageManager wpm;
	
	public String setActiveWorkPackage(EditableWorkPackage ewp) {
		this.id = ewp.id;
		System.out.println("Set active wp - number is " + ewp.workPackageNumber);
		if (ewp.isLeaf) {
			return "EditWorkPackageLeaf";
		} else {
			return "EditWorkPackageBranch";
		}
	}
	
}
