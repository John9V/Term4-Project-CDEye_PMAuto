package CDEye_PMAuto.backend.recepackage;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.UUID;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.MapKey;
import javax.persistence.MapKeyColumn;
import javax.persistence.MapKeyJoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import CDEye_PMAuto.backend.paygrade.Paygrade;
import CDEye_PMAuto.backend.workpackage.WorkPackage;


@Entity
@Table(name="recepackage")
@Named("recePackage")
@RequestScoped
public class RECEPackage implements Serializable {

    @Id
    @Column(name="id")
    @Type(type = "uuid-char")
    protected UUID id;
    
    @Column(name="workpackagenumber")
    protected String workPackageNumber;
    
    @OneToOne
    @JoinColumn(name="parentwp")
    protected WorkPackage parentWp;
    
    @Column(name="budgetestimate")
    protected BigDecimal budgetEstimate;
    
    @Column(name="persondayestimate")
    protected BigDecimal personDayEstimate;
    

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "RECE_Breakdown_mapping", 
      joinColumns = {@JoinColumn(name = "RECE_id", referencedColumnName = "id")},
      inverseJoinColumns = {@JoinColumn(name = "Breakdown_id", referencedColumnName = "id")})
    @MapKey(name = "numberofdays")
    protected HashMap<Paygrade, Integer> paygradeDaysBreakdown;
    
    public RECEPackage() {};
    
    public RECEPackage(UUID id, String workpackagenumber, WorkPackage parentwp, 
            BigDecimal budgetEstimate, BigDecimal personDayEstimate, HashMap<Paygrade, Integer> paygradeDaysBreakdown) {
        this.id = id;
        this.workPackageNumber = workpackagenumber;
        this.parentWp = parentwp;
        this.budgetEstimate = budgetEstimate;
        this.personDayEstimate = personDayEstimate;
        this.paygradeDaysBreakdown = paygradeDaysBreakdown;
    }
    
    /**Ensures that associated package cost estimates sum to 
     * estimated budget - cannot save if false*/
    public boolean valid() { return true; }
    
    
    // Getters and Setters ====================================================
    
    public UUID getId() {
        return id;
    }
    
    public void setId(UUID id) {
        this.id = id;
    }
    
    public String getWorkPackageNumber() {
        return workPackageNumber;
    }
    
    public void setWorkPackageNumber(String workPackageNumber) {
        this.workPackageNumber = workPackageNumber;
    }
    
    public WorkPackage getParentWp () {
        return parentWp;
    }
    
    public void setParentWp(WorkPackage parentWp) {
        this.parentWp = parentWp;
    }
    
    public BigDecimal getBudgetEstimate() {
        return budgetEstimate;
    }
    
    public void setBudgetEstimate(BigDecimal budgetEstimate) {
        this.budgetEstimate = budgetEstimate;
    }
    
    public BigDecimal getPersonDayEstimate() {
        return personDayEstimate;
    }
    
    public void setPersonDayEstimate(BigDecimal personDayEstimate) {
        this.personDayEstimate = personDayEstimate;
    }
   
    public HashMap<Paygrade, Integer> getPaygradeDaysBreakdown() {
        return this.paygradeDaysBreakdown;
    }
    
    public void setPaygradeDaysBreakdown(HashMap<Paygrade, Integer> paygradeDaysBreakdown) {
        this.paygradeDaysBreakdown = paygradeDaysBreakdown;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}

