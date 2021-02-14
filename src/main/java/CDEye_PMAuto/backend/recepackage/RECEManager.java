package CDEye_PMAuto.backend.recepackage;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import javax.ejb.Stateless;
import javax.enterprise.context.Dependent;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import CDEye_PMAuto.backend.employee.Employee;
import CDEye_PMAuto.backend.paygrade.Paygrade;
import CDEye_PMAuto.backend.workpackage.WorkPackage;

@Dependent
@Stateless
public class RECEManager {
    
    @PersistenceContext(unitName="inventory-jpa") 
    EntityManager em;
    
    /**
     * Gets all the RECEPackages in the DB
     * @return an array of RECEPackage, as an [RECEPackage]
     */
    public RECEPackage[] getAll() {
        TypedQuery<RECEPackage> query = em.createQuery("select p from RECEPackage p", RECEPackage.class); 
        List<RECEPackage> packages = query.getResultList();
        RECEPackage[] packageArr = new RECEPackage[packages.size()];
        for (int i = 0; i < packageArr.length; i++) {
            packageArr[i] = packages.get(i);
        }
        return packageArr;
    }
    
    /**
     * Creates a RECE in the db
     * 
     * @param p, a RECEPackage
     */
    public void createRECE(RECEPackage p) {
        em.persist(p);
    }

    public void createReceFromIds(UUID id, UUID wpId, UUID paygradeId, BigDecimal workDays, UUID empId ) {
        WorkPackage wp = em.getReference(WorkPackage.class, wpId);
        Paygrade paygrade= em.getReference(Paygrade.class, paygradeId);
        Employee emp = em.getReference(Employee.class, empId);
        
        em.persist(
            new RECEPackage(id, wp, paygrade, workDays, emp)
        );

    }

    /**
     * Gets a RECE by its id number and makes any changes differing from the original.
     * Nulls will be persisted to db.
     * 
     * @param p, the changes to make to a RECE, as RECEPackage
     * @return the changed RECEPackage, as a RECEPackage
     */
    public RECEPackage editEmployee(RECEPackage p) {
        return em.merge(p);
    }
    
    /**
     * Removes a RECE from the db
     * 
     * @param p, the RECE to remove
     */
    public void deleteRECE(UUID id) {
        RECEPackage rece = em.find(RECEPackage.class, id);
        em.remove(rece);
    }

    
}
