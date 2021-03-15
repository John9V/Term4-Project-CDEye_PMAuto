package CDEye.PMAuto.backend.timesheet;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.enterprise.context.Dependent;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import CDEye_PMAuto.backend.employee.Employee;

@Dependent
@Stateless
public class TimesheetManager implements Serializable {

	@PersistenceContext(unitName="inventory-jpa") 
	EntityManager em;
	
	public Timesheet[] getAll() {
        TypedQuery<Timesheet> query = em.createQuery("select t from Timesheet t",
        		Timesheet.class); 
        List<Timesheet> timesheet = query.getResultList();
        Timesheet[] timesheetArr = new Timesheet[timesheet.size()];
        for (int i = 0; i < timesheetArr.length; i++) {
        	timesheetArr[i] = timesheet.get(i);
        }
        return timesheetArr;
    }
	
	
	
}
