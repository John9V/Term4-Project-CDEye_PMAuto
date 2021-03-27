package CDEye_PMAuto.backend.recepackage;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

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
        TypedQuery<RespEngCostEstimate> query = em.createQuery(
                "SELECT r FROM RespEngCostEstimate r WHERE r.workPackage.id = :wpId", RespEngCostEstimate.class)
                .setParameter("wpId", wp.getId());
        List<RespEngCostEstimate> respEngCostEstimates = query.getResultList();
        
        RespEngCostEstimate[] respEngCostEstimateArr = new RespEngCostEstimate[respEngCostEstimates.size()];
        for (int i = 0; i < respEngCostEstimateArr.length; i++) {
            respEngCostEstimateArr[i] = respEngCostEstimates.get(i);
        }
        
        return respEngCostEstimateArr;
    }
    
    public void persist(RespEngCostEstimate rece) {
    	em.persist(rece);
    }
    
    public void merge(RespEngCostEstimate rece) {
        em.merge(rece);
    }
    
    public RespEngCostEstimate find(UUID id) {
        return em.find(RespEngCostEstimate.class, id);
    }
    
    //TODO: Create Package
    //TODO: Edit Package
    //TODO: Remove Package
    
}
