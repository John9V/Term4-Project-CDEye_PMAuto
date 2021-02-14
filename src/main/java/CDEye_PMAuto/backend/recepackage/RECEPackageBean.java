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
    
    @Transient 
    protected String idAsString;
    @Transient
    protected String parentWpId;
    @Transient
    protected String empId;
    @Transient
    protected String paygradeId;
    
    
    public void add() {
        System.out.println("Adding RECE package to DB");
        
        setId(UUID.randomUUID());
        
        receManager.createReceFromIds(getId(), UUID.fromString(getParentWpId()), UUID.fromString(getPaygradeId()), getPersonDayEstimate(), UUID.fromString(getEmpId()));
    }
    
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
