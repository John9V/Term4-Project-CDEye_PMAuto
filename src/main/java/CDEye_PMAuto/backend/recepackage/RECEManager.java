package CDEye_PMAuto.backend.recepackage;

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
public class RECEManager implements Serializable {
    
    @PersistenceContext(unitName="inventory-jpa") 
    EntityManager em;
    
    /**
     * Gets all the RECEPackages in the DB
     * @return an array of RECEPackage, as an [RECEPackage]
     */
    public RespEngCostEstimate[] getAll() {
        TypedQuery<RespEngCostEstimate> query = em.createQuery("select p from RespEngCostEstimate p", RespEngCostEstimate.class); 
        List<RespEngCostEstimate> packages = query.getResultList();
        RespEngCostEstimate[] packageArr = new RespEngCostEstimate[packages.size()];
        for (int i = 0; i < packageArr.length; i++) {
            packageArr[i] = packages.get(i);
        }
        return packageArr;
    }
    
    public RespEngCostEstimate[] getByWP(WorkPackage wp) {
        // TODO
        return null;
    }
    
    public void persist(RespEngCostEstimate rece) {
    	em.persist(rece);
    }
    
    //TODO: Create Package
    //TODO: Edit Package
    //TODO: Remove Package
    
}
