package CDEye_PMAuto.backend.workpackage;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import CDEye_PMAuto.backend.paygrade.Paygrade;
import CDEye_PMAuto.backend.paygrade.PaygradeManager;
import CDEye_PMAuto.backend.project.ActiveProjectBean;
import CDEye_PMAuto.backend.project.Project;
import CDEye_PMAuto.backend.project.ProjectManager;
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
	@Inject
	PaygradeManager pgm;
	@Inject
	WorkPackageAllocManager wpam;
	@Inject
	RECEManager recem;
	@Inject
	ProjectManager pm;
	@Inject
	WorkPackageList wpl;

	String parentWpNumber = "";

	public Boolean validateWorkPackageNumber() {
		if (workPackageManager.getByPackageNumber(workPackageNumber).length != 0) {
			System.out.println("already existing workPackage.");
			return false;
		}
		return true;
	}

	public void validateWPNum(FacesContext context, UIComponent comp, Object value) {
		String workPackageNumber = (String) value;

		if (workPackageManager.getByPackageNumber(workPackageNumber).length != 0) {
			((UIInput) comp).setValid(false);
			FacesMessage message = new FacesMessage("already existing workPackage");
			context.addMessage(comp.getClientId(context), message);
		}
	}

	/**
	 * Used to persist the new work package.
	 */
	public String add() {
		// TODO - make the message show
		if (this.endDate.before(this.startDate)) {
			FacesContext facesContext = FacesContext.getCurrentInstance();
			FacesMessage facesMessage = new FacesMessage("Start Date is before End Date");
			facesContext.addMessage("beginDate", facesMessage);
			return null;
		}

		Project activeProj = new Project();
		// activeProj.setId(apb.getId());

		activeProj = pm.find(apb.getId());

		// if (validateWorkPackageNumber()) {
		WorkPackage[] parentWp = workPackageManager.findWpsByPkgNumAndProj(parentWpNumber, activeProj);
		if (parentWp.length == 0) {
			String testParentWPNum = workPackageManager.determineParentWPNum(workPackageNumber);
			checkAndCreateWP(testParentWPNum);
			// Parent should now be created, check for a parent again
			parentWp = workPackageManager.findWpsByPkgNumAndProj(testParentWPNum, activeProj);
		}

		WorkPackage wp = new WorkPackage(this);
		// wp.setProject(parentWp[0].project);
		wp.setProject(activeProj);
		wp.setId(UUID.randomUUID());
		wp.setLeaf(wp.isLeaf);
		System.out.println("before setting parent committed work package " + wp.getId().toString() + " wpnum "
				+ wp.getWorkPackageNumber());
		wp.setParentWp(parentWp[0]);

		workPackageManager.addWorkPackage(wp);

		WorkPackage addedWp = workPackageManager.getByUUID(wp.getId().toString());
		System.out.println("committed work package " + wp.getId().toString() + " wpnum " + wp.getWorkPackageNumber());
		createWpAllocs(addedWp);
		createRECEs(addedWp);

		wpl.refreshList();
		return "WPList";
		// } else {
		// return "CreateWorkPackage";
		// }
	}

	/**
	 * A RECURSIVE function that checks to see if a WorkPackage has a parent, if
	 * not, check parent to see if the parent has a parent. Once the function finds
	 * a WorkPackage with a parent, creates the desired WorkPackage
	 *
	 * @param wpToCreate, the WorkPackage number, as a String, of the WorkPackage to
	 *                    create
	 */
	public void checkAndCreateWP(String wpToCreate) {

		// TODO
		// check that wp does not exist in project

		Project activeProj = new Project();
		activeProj = pm.find(apb.getId());
		// Determines the wpNum of the WorkPackage that is SUPPOSED to exist
		String schrodingersWp = workPackageManager.determineParentWPNum(wpToCreate);
		System.out.println("shrodingers package num " + schrodingersWp);

		// Checks to see if the supposed to exist WP really does exist
		WorkPackage[] arrayOfParentWPs = workPackageManager.findWpsByPkgNumAndProj(schrodingersWp, activeProj);

		// Checks to see if parentless WorkPackages parent has a WP
		if (arrayOfParentWPs.length == 0 && !schrodingersWp.contentEquals("00000")) {
			checkAndCreateWP(schrodingersWp);
			// Parent should now be created, check for a parent again
			arrayOfParentWPs = workPackageManager.findWpsByPkgNumAndProj(schrodingersWp, activeProj);
		} else if (schrodingersWp.contentEquals("00000")) {

			WorkPackage[] existingWps = workPackageManager.findWpsByPkgNumAndProj(wpToCreate, activeProj);

			if (existingWps.length != 0) {
				return;
			}

			WorkPackage newWorkPackage = new WorkPackage(wpToCreate, null, BigDecimal.valueOf(0), BigDecimal.valueOf(0),
					new Date(), new Date(), false, BigDecimal.valueOf(0), activeProj, null, null, null);

			newWorkPackage.setId(UUID.randomUUID());
			newWorkPackage.setProject(activeProj);
			workPackageManager.addWorkPackage(newWorkPackage);

			WorkPackage addedWp = workPackageManager.getByUUID(newWorkPackage.getId().toString());
			System.out.println(
					"in conditional " + addedWp.getId().toString() + " number " + addedWp.getWorkPackageNumber());
			createWpAllocs(addedWp);
			createRECEs(addedWp);

			return;
		}

//        System.out.println("creating wp " + wpToCreate);
//        for (WorkPackage w : arrayOfParentWPs) {
//        	System.out.print("potential parent " + w.getWorkPackageNumber());
//        	System.out.print(" in proj " + w.getProject().getProjectNumber());
//        }
//        System.out.println("end of potential parent list " );
		WorkPackage newWorkPackage = new WorkPackage(wpToCreate, arrayOfParentWPs[0], BigDecimal.valueOf(0),
				BigDecimal.valueOf(0), new Date(), new Date(), false, BigDecimal.valueOf(0), activeProj, null, null,
				null);

		newWorkPackage.setId(UUID.randomUUID());
		newWorkPackage.setProject(activeProj);
		workPackageManager.addWorkPackage(newWorkPackage);

		WorkPackage addedWp = workPackageManager.getByUUID(newWorkPackage.getId().toString());
		System.out.println("committed workpackage outside conditional " + newWorkPackage.getWorkPackageNumber());
		createWpAllocs(addedWp);
		createRECEs(addedWp);
	}

	// create a wpalloc for each paygrade
	public void createWpAllocs(WorkPackage addedWp) {
		Paygrade[] paygradeArr = pgm.getAll();
		for (Paygrade p : paygradeArr) {
			WorkPackageAllocation wpa = new WorkPackageAllocation();
			wpa.setId(UUID.randomUUID());
			wpa.setWorkPackage(addedWp);
			wpa.setPaygrade(p);
			wpa.setPersonDaysEstimate(new BigDecimal(0));
			wpam.addWorkPackageAlloc(wpa);
		}
	}

	// create a RECE for each paygrade
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

	/**
	 * Gets the child WP number from a parent WP on keyup event from
	 * testCreateWP.xhtml
	 */
	public void ajaxEvent() {
		System.out.println("Called: " + parentWpNumber);
		workPackageNumber = workPackageManager.determineChildWPWithoutZeroes(parentWpNumber);
	}

	public String getParentWpNumber() {
		return parentWpNumber;
	}

	public void setParentWpNumber(String parentWpNumber) {
		this.parentWpNumber = parentWpNumber;
	}

}
