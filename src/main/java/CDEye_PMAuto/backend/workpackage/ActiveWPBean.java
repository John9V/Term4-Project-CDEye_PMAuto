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
	@Inject EditableWorkPackageLeaf ewpl;
	
//	private String newparentwpnum;
	
	
    public String setActiveWorkPackage(EditableWorkPackage wp) {
        this.id = wp.id;
        this.workPackageNumber = wp.workPackageNumber;
        this.parentWp = wp.parentWp;
        this.unAllocatedBudget = wp.unAllocatedBudget;
        this.allocatedBudget = wp.allocatedBudget;
        this.allocatedPersonDays = wp.allocatedPersonDays;
        this.respEngPersonDayEstimate = wp.respEngPersonDayEstimate;
        this.respEngBudgetEstimate = wp.respEngBudgetEstimate;
        this.completedBudget = wp.completedBudget;
        this.completedPersonDays = wp.completedPersonDays;
        this.completedVarianceProjectPD = wp.completedVarianceProjectPD;
        this.completedVarianceProjectBudget = wp.completedVarianceProjectBudget;
        this.respEngEstVarianceProjectPD = wp.respEngEstVarianceProjectPD;
        this.respEngEstVarianceProjectBudget = wp.respEngEstVarianceProjectBudget;
        this.startDate = wp.startDate;
        this.endDate = wp.endDate;
        this.isLeaf = wp.isLeaf;
        this.projectBudget = wp.projectBudget;
        this.project = wp.project;
        this.RECEs = wp.RECEs;
        this.wpAllocs = wp.wpAllocs;
        
		if (wp.isLeaf) {
			ewpl.editSelectedWP(this);
			return "EditWorkPackageLeaf";
		} else {
			return "EditWorkPackageBranch";
		}
	}
	
	public String mergeActive() {
	    WorkPackage newWp = new WorkPackage();
	    newWp.id = this.id;
	    newWp.workPackageNumber = this.workPackageNumber;
	    newWp.parentWp = this.parentWp;
	    newWp.unAllocatedBudget = this.unAllocatedBudget;
	    newWp.allocatedBudget = this.allocatedBudget;
	    newWp.allocatedPersonDays = this.allocatedPersonDays;
	    newWp.respEngPersonDayEstimate = this.respEngPersonDayEstimate;
	    newWp.respEngBudgetEstimate = this.respEngBudgetEstimate;
	    newWp.completedBudget = this.completedBudget;
	    newWp.completedPersonDays = this.completedPersonDays;
	    newWp.completedVarianceProjectPD = this.completedVarianceProjectPD;
	    newWp.completedVarianceProjectBudget = this.completedVarianceProjectBudget;
	    newWp.respEngEstVarianceProjectPD = this.respEngEstVarianceProjectPD;
	    newWp.respEngEstVarianceProjectBudget = this.respEngEstVarianceProjectBudget;
	    newWp.startDate = this.startDate;
	    newWp.endDate = this.endDate;
	    newWp.isLeaf = this.isLeaf;
	    newWp.projectBudget = this.projectBudget;
	    newWp.project = this.project;
	    newWp.RECEs = this.RECEs;
	    newWp.wpAllocs = this.wpAllocs;
	    wpm.updateWorkPackage(newWp);
	        return "WPList";
	    
	}



	
	
}
