package CDEye_PMAuto.backend.paygrade;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.enterprise.context.Dependent;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Dependent
@Stateless
public class PaygradeManager implements Serializable {

	@PersistenceContext(unitName="inventory-jpa") EntityManager em;
	
	public Paygrade[] getAll() {
		TypedQuery<Paygrade> query = em.createQuery("select p from Paygrade p",
        		Paygrade.class);
		List<Paygrade> paygrades = query.getResultList();
		Paygrade[] paygradeArr = new Paygrade[paygrades.size()];
        for (int i = 0; i < paygradeArr.length; i++) {
        	paygradeArr[i] = paygrades.get(i);
        }
        System.out.println("Found paygrades: " + paygrades);
        return paygradeArr;
	}

	public void updatePaygrade(Paygrade p) {
		em.merge(p);
	}

}
