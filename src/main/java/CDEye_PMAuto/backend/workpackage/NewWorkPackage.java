package CDEye_PMAuto.backend.workpackage;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import CDEye_PMAuto.backend.paygrade.Paygrade;
import CDEye_PMAuto.backend.paygrade.PaygradeManager;
import CDEye_PMAuto.backend.project.ActiveProjectBean;
import CDEye_PMAuto.backend.project.Project;
import CDEye_PMAuto.backend.recepackage.RECEManager;
import CDEye_PMAuto.backend.recepackage.RespEngCostEstimate;
import CDEye_PMAuto.backend.wpallocation.WorkPackageAllocManager;
import CDEye_PMAuto.backend.wpallocation.WorkPackageAllocation;

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
	@Inject PaygradeManager pgm;
	@Inject WorkPackageAllocManager wpam;
	@Inject RECEManager recem;
	

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
		wp.setLeaf(wp.isLeaf);
		
		System.out.println("Leaf value is" + wp.isLeaf);
		
		workPackageManager.addWorkPackage(wp);
		WorkPackage addedWp = workPackageManager.getByUUID(wp.id.toString());
		System.out.println("committed work package");
		createWpAllocs(addedWp);
		createRECEs(addedWp);
		return "WPList";
	}

	//create a wpalloc for each paygrade
	public void createWpAllocs(WorkPackage addedWp) {
		Paygrade[] paygradeArr = pgm.getAll();
		for (Paygrade p : paygradeArr) {
			WorkPackageAllocation wpa = new WorkPackageAllocation();
			wpa.setId(UUID.randomUUID());
			wpa.setWorkPackage(addedWp);
			wpa.setPaygrade(p);
			wpa.setPersonDaysEstimate(new BigDecimal(0));
			System.out.println("right before comitting wpa");
			wpam.addWorkPackageAlloc(wpa);
			System.out.println("committed wpa");
		}
	}
	
	//create a RECE for each paygrade
	public void createRECEs(WorkPackage addedWp) {
		Paygrade[] paygradeArr = pgm.getAll();
		for (Paygrade p : paygradeArr) {
			RespEngCostEstimate rece = new RespEngCostEstimate();
			rece.setId(UUID.randomUUID());
			rece.setWorkPackage(addedWp);
			rece.setPaygrade(p);
			rece.setPersonDayEstimate(new BigDecimal(0));
			recem.persist(rece);
		}
	}
	
	public String getParentWpNumber() {
		return parentWpNumber;
	}

	public void setParentWpNumber(String parentWpNumber) {
		this.parentWpNumber = parentWpNumber;
	}

}
