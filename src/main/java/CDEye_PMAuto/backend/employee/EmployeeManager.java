package CDEye_PMAuto.backend.employee;

import java.io.Serializable;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import javax.ejb.Stateless;
import javax.enterprise.context.Dependent;
import javax.persistence.Column;
import javax.persistence.EntityManager;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;


@Dependent
@Stateless
public class EmployeeManager implements Serializable {
	
    @PersistenceContext(unitName="inventory-jpa") 
	EntityManager em;
    
    /**
     * Gets all the employees in the DB
     * @return an array of employees, as an [Employee]
     */
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
    
    /**
     * Gets an employee by first name.
     * 
     * @param firstName of the employee as a String
     * @return employee, Employeee
     */
    public Employee getByFirstName(String firstName) {     
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Employee> criteriaQuery = criteriaBuilder.createQuery(Employee.class);
        Root<Employee> itemRoot = criteriaQuery.from(Employee.class);
        
        Predicate predicateForName = criteriaBuilder.equal(itemRoot.get("firstName"), firstName);
        
        criteriaQuery.where(predicateForName);
        
        List<Employee> employee = em.createQuery(criteriaQuery).getResultList();

        return employee.get(0);
    }
    
    /**
     * Gets an employee by last name.
     * 
     * @param lastName of the employee as a String
     * @return employee, Employee
     */
    public Employee getByLastName(String lastName) {     
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Employee> criteriaQuery = criteriaBuilder.createQuery(Employee.class);
        Root<Employee> itemRoot = criteriaQuery.from(Employee.class);
        
        Predicate predicateForName = criteriaBuilder.equal(itemRoot.get("lastName"), lastName);
        
        criteriaQuery.where(predicateForName);
        
        List<Employee> employee = em.createQuery(criteriaQuery).getResultList();

        return employee.get(0);
    }
    
    /**
     * Gets an employee by UUID number
     * 
     * @param uuid as a string
     * @return employee, Employee
     */
    public Employee getByUUID(String uuid) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Employee> criteriaQuery = criteriaBuilder.createQuery(Employee.class);
        Root<Employee> itemRoot = criteriaQuery.from(Employee.class);
        
        UUID uuidAsString = UUID.fromString(uuid);
        
        Predicate predicateForName = criteriaBuilder.equal(itemRoot.get("id"), uuidAsString);
        
        criteriaQuery.where(predicateForName);
        
        List<Employee> employee = em.createQuery(criteriaQuery).getResultList();

        return employee.get(0);
    }
    
    /**
     * Gets an employee by its id number and makes any changes differing from the original.
     * Nulls will be persisted to db.
     * 
     * @param changesToEmployee, the changes to make to an employee, as Employee
     * @return the changed employee, as an Employee
     */
    public Employee editEmployee(Employee changesToEmployee) {
        return em.merge(changesToEmployee);
    }

	public Employee getEmployeeByUserName(String userName) {
		TypedQuery<Employee> query = em.createQuery(
    			"SELECT e FROM Employee e WHERE e.userName LIKE :userName", Employee.class)
    			.setParameter("userName", "%" + userName + "%");
    	List<Employee> employees = query.getResultList();
    	
        return employees.get(0);
	}


}
