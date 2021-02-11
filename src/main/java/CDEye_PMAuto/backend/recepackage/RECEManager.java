package CDEye_PMAuto.backend.recepackage;

import java.util.List;

import javax.ejb.Stateless;
import javax.enterprise.context.Dependent;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

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
    
    //TODO: Create Package
    //TODO: Edit Package
    //TODO: Remove Package
    
}
