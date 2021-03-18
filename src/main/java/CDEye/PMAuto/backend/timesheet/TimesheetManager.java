package CDEye.PMAuto.backend.timesheet;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import CDEye_PMAuto.backend.employee.ActiveEmployeeBean;
import CDEye_PMAuto.backend.employee.Employee;

@Dependent
@Stateless
public class TimesheetManager implements Serializable {

	@PersistenceContext(unitName="inventory-jpa") 
	EntityManager em;
	@Inject ActiveEmployeeBean aeb;
	
	public Timesheet[] getAll() {
        TypedQuery<Timesheet> query = em.createQuery("select t from Timesheet t",
        		Timesheet.class);
		return getTimesheets(query);
	}

	private Timesheet[] getTimesheets(TypedQuery<Timesheet> query) {
		List<Timesheet> timesheet = query.getResultList();
		Timesheet[] timesheetArr = new Timesheet[timesheet.size()];
		for (int i = 0; i < timesheetArr.length; i++) {
			timesheetArr[i] = timesheet.get(i);
		}
		return timesheetArr;
	}

	public void updateTimesheet(Timesheet t) {
		em.merge(t);
	}
	
	public void deleteTimesheet(Timesheet t) {
		em.remove(t);
	}
	
	public Timesheet[] getAllForCurrentEmployee() {
		Employee currentEmp = new Employee();
		currentEmp.setId(aeb.getId());
//		currentEmp.setEmpNum(aeb.getEmpNum());
//		currentEmp.setFirstName(aeb.getFirstName());
//		currentEmp.setLastName(aeb.getLastName());
//		currentEmp.setActive(aeb.getActive());
//		currentEmp.setHr(aeb.getHr());
//		currentEmp.setUserName(aeb.getUserName());
//		currentEmp.setPayGrade(aeb.getPayGrade());
//		currentEmp.setManager(aeb.getManager());
//		currentEmp.setPeons(aeb.getPeons());
//		currentEmp.setFlextime(aeb.getFlextime());
//		currentEmp.setVacationTime(aeb.getVacationTime());
		
		TypedQuery<Timesheet> query = em.createQuery("select t from Timesheet t where t.employee = :emp",
        		Timesheet.class).setParameter("emp", currentEmp);
		return getTimesheets(query);
	}

	public void addTimesheet(Timesheet t) { em.persist(t);}
}
