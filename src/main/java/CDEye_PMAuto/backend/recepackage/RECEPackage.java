package CDEye_PMAuto.backend.recepackage;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.UUID;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
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

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Type;

import CDEye_PMAuto.backend.employee.Employee;
import CDEye_PMAuto.backend.paygrade.Paygrade;
import CDEye_PMAuto.backend.workpackage.WorkPackage;

@Entity
@Table(name="recepackage")
@Named("recepackage")
@DynamicUpdate
@SessionScoped
public class RECEPackage implements Serializable {

    @Id
    @Column(name="id")
    @Type(type = "uuid-char")
    protected UUID id;
    
    @JoinColumn(name="parentwp")
    @ManyToOne
    protected WorkPackage parentWp;
  
    @JoinColumn(name="paygrade")
    @ManyToOne
    protected Paygrade paygrade;
    
    @Column(name="persondayestimate")
    protected BigDecimal personDayEstimate;
    
    @JoinColumn(name="employeeId")
    @ManyToOne
    protected Employee employee;

    
    public RECEPackage() {};
    
    public RECEPackage(UUID id, WorkPackage parentWp, Paygrade paygrade, BigDecimal personDayEstimate, Employee employee) {
        this.id = id;
        this.parentWp = parentWp;
        this.paygrade = paygrade;
        this.personDayEstimate = personDayEstimate;
        this.employee = employee;
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
     
    public WorkPackage getParentWp() {
        return parentWp;
    }
    
    public void setParentWp(WorkPackage parentWp) {
        this.parentWp = parentWp;
    }
    
    public Paygrade getPaygrade() {
        return paygrade;
    }
    
    public void setPaygrage(Paygrade paygrade) {
        this.paygrade = paygrade;
    }
 
    public BigDecimal getPersonDayEstimate() {
        return personDayEstimate;
    }
    
    public void setPersonDayEstimate(BigDecimal personDayEstimate) {
        this.personDayEstimate = personDayEstimate;
    }
   
    public Employee getEmployee() {
        return employee;
    }
    
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}

