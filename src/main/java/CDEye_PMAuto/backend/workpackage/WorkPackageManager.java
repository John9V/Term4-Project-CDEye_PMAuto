package CDEye_PMAuto.backend.workpackage;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import javax.ejb.Stateless;
import javax.enterprise.context.Dependent;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import CDEye_PMAuto.backend.employee.Employee;

@Dependent
@Stateless
public class WorkPackageManager implements Serializable {
    
    @PersistenceContext(unitName="inventory-jpa") 
    EntityManager em;
    
    /**
     * Gets all the work packages in the DB.
     * @return an array of packages
     */
    public WorkPackage[] getAll() {
        TypedQuery<WorkPackage> query = em.createQuery("select wp from workpackages wp",
                WorkPackage.class); 
        List<WorkPackage> workPackages = query.getResultList();
        WorkPackage[] wpArr = new WorkPackage[workPackages.size()];
        for (int i = 0; i < wpArr.length; i++) {
            wpArr[i] = workPackages.get(i);
        }
        return wpArr;
    }
    
    /**
     * Gets a workPackage by UUID number.
     * 
     * @param uuid string id
     * @return workPackage workPackage
     */
    public WorkPackage getByUUID(String uuid) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<WorkPackage> criteriaQuery = criteriaBuilder.createQuery(WorkPackage.class);
        Root<WorkPackage> itemRoot = criteriaQuery.from(WorkPackage.class);
        
        UUID uuidAsString = UUID.fromString(uuid);
        
        Predicate predicateForName = criteriaBuilder.equal(itemRoot.get("id"), uuidAsString);
        
        criteriaQuery.where(predicateForName);
        
        List<WorkPackage> workPackage = em.createQuery(criteriaQuery).getResultList();

        return workPackage.get(0);
    }
    
    /**
     * Uses a modified workPackage to update a workPackage in the database.
     * 
     * @param modifiedWP the work package with modifications
     * @return the modified work package
     */
    public WorkPackage editWorkPackage(WorkPackage modifiedWP) {
        return em.merge(modifiedWP);
    }
    
    /**
     * Adds a work package to the database.
     * @param wp the work package to add
     */
    public void addWorkPackage(WorkPackage wp) {
        em.persist(wp);
    }
    
    /**
     * Deletes a work package from the database.
     * @param wp the work package to delete
     */
    public void deleteWorkPackage(WorkPackage wp) {
        em.remove(wp);
    }
    
}
