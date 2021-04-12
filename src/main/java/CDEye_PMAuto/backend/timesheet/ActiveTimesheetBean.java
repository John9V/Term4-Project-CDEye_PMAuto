package CDEye_PMAuto.backend.timesheet;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import CDEye_PMAuto.backend.tsrow.TimesheetRow;
import CDEye_PMAuto.backend.workpackage.WorkPackage;
import CDEye_PMAuto.backend.workpackage.WorkPackageManager;

@Named("activeTimesheet")
@SessionScoped
public class ActiveTimesheetBean extends Timesheet implements Serializable {

	@Inject TimesheetList editableTimesheetList;
	@Inject WorkPackageManager wpManager;
	@Inject TimesheetManager timesheetManager;
	

	public String setActiveTimesheetBean(EditableTimesheet ets) {
		this.id = ets.id;
		this.employee = ets.employee;
		this.endDate = ets.endDate;
		this.details = ets.details;
		this.sick = ets.sick;
		this.flex = ets.flex;
		this.approved = ets.approved;
		this.vacation = ets.vacation;
		editableTimesheetList.refreshList();
		return "TimesheetDetails";
	}
	
	public String setActiveTimesheetBeanManagerView(EditableTimesheet ets) {
        this.id = ets.id;
        this.employee = ets.employee;
        this.endDate = ets.endDate;
        this.details = ets.details;
        this.sick = ets.sick;
        this.flex = ets.flex;
        this.approved = ets.approved;
        this.vacation = ets.vacation;
        editableTimesheetList.filterForManagerViewUnapproved();
        return "TimesheetDetails";
    }
	
	public void approve() {
		BigDecimal sumOfTimesheetsPersonalDays;
		for (TimesheetRow tsr : this.details) {
			WorkPackage wp = wpManager.find(tsr.getWorkPackage().getId());
			System.out.println(tsr.getFri() 
					+ " " + tsr.getSat() 
					+ " " + tsr.getSun() 
					+ " " + tsr.getMon() 
					+ " " + tsr.getTue() 
					+ " " + tsr.getWed() 
					+ " " + tsr.getThu());
			BigDecimal sum = wp.getCompletedBudget();
			
			BigDecimal pdSum = wp.getCompletedPersonDays();
			if (wp.getCompletedPersonDays() == null) {
				pdSum = new BigDecimal(0);
			} else {
				pdSum = wp.getCompletedPersonDays();
			}
			
			if (wp.getCompletedBudget() == null) {
				sum = new BigDecimal(0);
			} else {
				sum = wp.getCompletedBudget();
			}
			
			//BigDecimal pdSum = wp.getCompletedPersonDays();
			
			sum = sum.add(tsr.getFri().multiply(this.employee.getPayGrade().getSalary()));
			pdSum = pdSum.add(tsr.getFri());
			
			sum = sum.add(tsr.getSat().multiply(this.employee.getPayGrade().getSalary()));
			pdSum = pdSum.add(tsr.getSat());
			
			sum = sum.add(tsr.getSun().multiply(this.employee.getPayGrade().getSalary()));
			pdSum = pdSum.add(tsr.getSun());
			
			sum = sum.add(tsr.getMon().multiply(this.employee.getPayGrade().getSalary()));
			pdSum = pdSum.add(tsr.getMon());
			
			sum = sum.add(tsr.getTue().multiply(this.employee.getPayGrade().getSalary()));
			pdSum = pdSum.add(tsr.getTue());
			
			sum = sum.add(tsr.getWed().multiply(this.employee.getPayGrade().getSalary()));
			pdSum = pdSum.add(tsr.getWed());
			
			sum = sum.add(tsr.getThu().multiply(this.employee.getPayGrade().getSalary()));
			pdSum = pdSum.add(tsr.getThu());
			
			this.setApproved(true);
			timesheetManager.updateTimesheet(new Timesheet(this));
			
			wp.setCompletedBudget(sum);
			wp.setCompletedPersonDays(pdSum);
			wpManager.updateWorkPackage(wp);


		}
	}
}
