package CDEye_PMAuto.backend.recepackage;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.Transient;


@Named("recePackageBean")
@RequestScoped
public class RECEPackageBean extends RECEPackage implements Serializable {

    @Inject 
    private RECEManager receManager;
    
    /** Used to access a RECEPackage via UUID */
    @Transient 
    protected String idAsString;
    /** The RECEPackage UUID as a string */
    @Transient
    protected String parentWpId;
    @Transient
    /** The RECEPackage assigned employee as a ID */
    protected String empId;
    @Transient
    /** The RECEPackage paygrade ID **/
    protected String paygradeId;
    
    /**
     * Adds a the RECEPackageBean to the DB by using the string ids
     */
    public void add() {
        System.out.println("Adding RECE package to DB");
        
        setId(UUID.randomUUID());
        
        receManager.createReceFromIds(getId(), UUID.fromString(getParentWpId()), UUID.fromString(getPaygradeId()), getPersonDayEstimate(), UUID.fromString(getEmpId()));
    }
    
    /**
     * Deletes a RECEPackageBean from the DB by using the string ids in this class
     */
    public void delete() {
        System.out.println("Deleting RECE Package from DB");
       
        //RECEPackage rece = new RECEPackage(this);
        receManager.deleteRECE(UUID.fromString(idAsString));
    }
    
 // Getters and Setters ====================================================
    
    public String getParentWpId() {
        return parentWpId;
    }


    public void setParentWpId(String parentWpId) {
        this.parentWpId = parentWpId;
    }


    public String getEmpId() {
        return empId;
    }


    public void setEmpId(String empId) {
        this.empId = empId;
    }


    public String getPaygradeId() {
        return paygradeId;
    }


    public void setPaygradeId(String paygradeId) {
        this.paygradeId = paygradeId;
    }

    public String getIdAsString() {
        return idAsString;
    }

    public void setIdAsString(String idAsString) {
        this.idAsString = idAsString;
    }
    
    
}
