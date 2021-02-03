package CDEye_PMAuto.backend.credentials;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ejb.Stateless;
import javax.enterprise.context.Dependent;

/**
 * Handle CRUD actions for Credentials.
 */
@Dependent
@Stateless
public class CredentialManager implements Serializable {
    private static final long serialVersionUID = 1L;
    @PersistenceContext(unitName = "inventory-jpa")
    EntityManager em;

    /**
     * Find a Credentials record from database.
     * 
     * @param username primary key for record.
     * @return the Credential record with key = username, null if not found.
     */
    public Credentials find(String username) {
        return em.find(Credentials.class, username);
    }

    /**
     * Persist Credential record into database. id must be unique.
     * 
     * @param credential the record to be persisted.
     */
    public void persist(Credentials credential) {
        em.persist(credential);
    }

    /**
     * merge Credential record fields into existing database record.
     * 
     * @param credential the record to be merged.
     */
    public void merge(Credentials credential) {
        em.merge(credential);
    }

    /**
     * Remove credential from database.
     * 
     * @param credential record to be removed from database
     */
    public void remove(Credentials credential) {
        // attach credential
        credential = find(credential.getUserName());
        em.remove(credential);
    }

    /**
     * Return Categories table as array of Credential.
     * 
     * @return Credential[] of all records in Categories table
     */
    public Credentials[] getAll() {
        // need to make sure the table name matches the one in the database
        TypedQuery<Credentials> query = em.createQuery("select c from Credentials c", Credentials.class);
        java.util.List<Credentials> credentialsList = query.getResultList();
        Credentials[] credentials = new Credentials[credentialsList.size()];
        for (int i = 0; i < credentials.length; i++) {
            credentials[i] = credentialsList.get(i);
        }
        return credentials;
    }

    /**
     * Validates credentials by ensuring that the password of the credentials
     * passed in matches the password of the credential looked up by username.
     * @param credentials credentials entered by the user.
     * @return boolean for whether the credentials are valid.
     */
    public boolean validCredentials(Credentials credentials) {
        Credentials cred;
        cred = em.find(Credentials.class, credentials.getUserName());
        // if credentials not in database
        if (cred == null) {
            return false;
        }
        return credentials.getPassword().equals(cred.getPassword());
    }
}
