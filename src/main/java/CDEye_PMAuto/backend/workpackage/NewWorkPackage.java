package CDEye_PMAuto.backend.workpackage;

import java.io.Serializable;
import java.util.UUID;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import CDEye_PMAuto.backend.project.ActiveProjectBean;
import CDEye_PMAuto.backend.project.Project;

/**
 * For creating new work packages and temporarily storing their information.
 */
@Named("newWorkPackage")
@RequestScoped
public class NewWorkPackage extends WorkPackage implements Serializable {

	@Inject
	private WorkPackageManager workPackageManager;
	@Inject
	ActiveProjectBean apb;

	String parentWpNumber = "";

	/**
	 * Used to persist the new work package.
	 */
	public String add() {
		Project activeProj = new Project();
		activeProj.setId(apb.getId());
		WorkPackage[] parentWp = workPackageManager.findWpsByPkgNumAndProj(parentWpNumber, activeProj);

		WorkPackage wp = new WorkPackage(this);
		wp.setProject(activeProj);
		//make sure it found a parent lol
        if (parentWp.length >= 1) {
        	wp.setParentWp(parentWp[0]);
        }
		wp.setId(UUID.randomUUID());
		wp.setLeaf(false);
		workPackageManager.addWorkPackage(wp);
		return "WPList";
	}

	public String getParentWpNumber() {
		return parentWpNumber;
	}

	public void setParentWpNumber(String parentWpNumber) {
		this.parentWpNumber = parentWpNumber;
	}

}
