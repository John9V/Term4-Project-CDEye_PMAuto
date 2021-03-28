package CDEye_PMAuto.backend.workpackage;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;
import javax.enterprise.context.RequestScoped;
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
		
		//make sure it found a parent lol
        if (parentWp.length == 0) {
            String testParentWPNum = workPackageManager.determineParentWPNum(workPackageNumber);
            checkAndCreateWP(testParentWPNum);
            // Parent should now be created, check for a parent again
            parentWp = workPackageManager.getByPackageNumber(testParentWPNum);
        }
        
        WorkPackage wp = new WorkPackage(this);
        wp.setProject(parentWp[0].project);
        wp.setParentWp(parentWp[0]);
		wp.setId(UUID.randomUUID());
		wp.setLeaf(false);
		
		workPackageManager.addWorkPackage(wp);
		
		WorkPackage addedWp = workPackageManager.getByUUID(wp.getId().toString());
		System.out.println("committed work package");
		createWpAllocs(addedWp);
		createRECEs(addedWp);
		return "WPList";
	}
	
    /**
     * A RECURSIVE function that checks to see if a WorkPackage has a parent, if not, 
     * check parent to see if the parent has a parent. Once the function finds a WorkPackage 
     * with a parent, creates the desired WorkPackage
     *
     * @param wpToCreate, the WorkPackage number, as a String, of the WorkPackage to create
     */
    public void checkAndCreateWP(String wpToCreate) {
        // Determines the wpNum of the WorkPackage that is SUPPOSED to exist
        String schrodingersWp = workPackageManager.determineParentWPNum(wpToCreate);
        
        // Checks to see if the supposed to exist WP really does exist
        WorkPackage[] arrayOfParentWPs = workPackageManager.getByPackageNumber(schrodingersWp);
        
        // Checks to see if parentless WorkPackages parent has a WP
        if (arrayOfParentWPs.length == 0) { 
            checkAndCreateWP(schrodingersWp); 
            // Parent should now be created, check for a parent again
            arrayOfParentWPs = workPackageManager.getByPackageNumber(schrodingersWp);
        }
        
        WorkPackage newWorkPackage = new WorkPackage(wpToCreate, arrayOfParentWPs[0], BigDecimal.valueOf(0), BigDecimal.valueOf(0), 
              new Date(), new Date(), false, BigDecimal.valueOf(0), arrayOfParentWPs[0].getProject(), null, null, null);
        
        newWorkPackage.setId(UUID.randomUUID());
        workPackageManager.addWorkPackage(newWorkPackage);
        
        WorkPackage addedWp = workPackageManager.getByUUID(newWorkPackage.getId().toString());
        
        createWpAllocs(addedWp);
        createRECEs(addedWp);
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
	
	/**
	 * Gets the child WP number from a parent WP on keyup event from testCreateWP.xhtml
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
