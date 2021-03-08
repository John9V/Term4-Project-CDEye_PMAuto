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

	Project activeProj = new Project(apb);

	/**
	 * Used to persist the new work package.
	 */
	public String add() {
		WorkPackage wp = new WorkPackage(this);
		wp.setParentWp(workPackageManager.findWpsByPkgNumAndProj(this.getWorkPackageNumber(), activeProj)[0]);
		wp.setUnAllocatedBudget(workPackageManager.calculateUnallocatedBudget(this.parentWp));
		wp.setId(UUID.randomUUID());
		wp.setLeaf(false);
		workPackageManager.addWorkPackage(wp);
		return "testWPs";
	}

}
