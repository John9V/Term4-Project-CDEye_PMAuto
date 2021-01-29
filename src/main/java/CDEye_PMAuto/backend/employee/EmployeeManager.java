package CDEye_PMAuto.backend.employee;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.ejb.Stateless;
import javax.enterprise.context.Dependent;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

@Dependent
@Stateless
public class EmployeeManager implements Serializable {
	@PersistenceContext(unitName="inventory-jpa") EntityManager em;
    
    public Employee[] getAll() {
        TypedQuery<Employee> query = em.createQuery("select e from Employee e",
        		Employee.class); 
        List<Employee> employees = query.getResultList();
        Employee[] empArr = new Employee[employees.size()];
        for (int i = 0; i < empArr.length; i++) {
        	empArr[i] = employees.get(i);
        }
        return empArr;
    }
    
}
