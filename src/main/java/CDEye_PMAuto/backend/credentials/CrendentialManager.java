package ca.bcit.infosys.inventory.access;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import javax.ejb.Stateless;
import javax.enterprise.context.Dependent;

import ca.bcit.infosys.inventory.model.Credential;

/**
 * Handle CRUD actions for Credential class.
 * 
 * @author gwen
 * @version 1.0
 * 
 */
@Dependent
@Stateless
public class CrendentialManager implements Serializable {
    private static final long serialVersionUID = 1L;
    @PersistenceContext(unitName="inventory-jpa") EntityManager em;

    /**
     * Find Credential record from database.
     * 
     * @param id
     *            primary key for record.
     * @return the Credential record with key = id, null if not found.
     */
    public Credential find(int id) {
        return em.find(Credential.class, id);
        }

    /**
     * Persist Credential record into database. id must be unique.
     * 
     * @param credential
     *            the record to be persisted.
     */
    public void persist(Credential credential) {
        em.persist(credential);
    }

    /**
     * merge Credential record fields into existing database record.
     * 
     * @param credential
     *            the record to be merged.
     */
    public void merge(Credential credential) {
        em.merge(credential);
    }

    /**
     * Remove credential from database.
     * 
     * @param credential
     *            record to be removed from database
     */
    public void remove(Credential credential) {
        //attach credential
        credential = find(credential.getId());
        em.remove(credential);
    }

    /**
     * Return Categories table as array of Credential.
     * 
     * @return Credential[] of all records in Categories table
     */
    public Credential[] getAll() {
        TypedQuery<Credential> query = em.createQuery("select c from Credential c", Credential.class); 
        java.util.List<Credential> credential = query.getResultList();
        Credential[] credential = new Credential[credential.size()];
        for (int i=0; i < credential.length; i++) {
            crearray[i] = credential.get(i);
        }
        return crearray;
    }
}
