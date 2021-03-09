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
		Project activeProj = new Project(apb);
		System.out.println(parentWpNumber);
		System.out.println(activeProj);
		WorkPackage[] parentWp = workPackageManager.findWpsByPkgNumAndProj(parentWpNumber, activeProj);

		if (parentWp[0] != null) {
			System.out.println(parentWp[0]);
		}

		WorkPackage wp = new WorkPackage(this);
//        wp.setParentWp(parentWp);
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
