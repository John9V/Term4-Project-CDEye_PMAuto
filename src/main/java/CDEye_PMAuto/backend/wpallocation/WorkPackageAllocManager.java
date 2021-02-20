package CDEye_PMAuto.backend.wpallocation;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.enterprise.context.Dependent;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import CDEye_PMAuto.backend.workpackage.WorkPackage;

@Dependent
@Stateless
public class WorkPackageAllocManager implements Serializable {

	@PersistenceContext(unitName="inventory-jpa") 
    EntityManager em;
	
	public WorkPackageAllocation[] getAll() {
        TypedQuery<WorkPackageAllocation> query = em.createQuery("select wpa from WorkPackageAllocation wpa",
        		WorkPackageAllocation.class); 
        List<WorkPackageAllocation> workPackages = query.getResultList();
        WorkPackageAllocation[] wpaArr = new WorkPackageAllocation[workPackages.size()];
        for (int i = 0; i < wpaArr.length; i++) {
            wpaArr[i] = workPackages.get(i);
        }
        return wpaArr;
    }
	
	public WorkPackageAllocation[] getByWP(WorkPackage wp) {
	    // TODO
	    return null;
	}
	
	public void addWorkPackageAlloc(WorkPackageAllocation wpa) {
		em.persist(wpa);
	}
	
}
