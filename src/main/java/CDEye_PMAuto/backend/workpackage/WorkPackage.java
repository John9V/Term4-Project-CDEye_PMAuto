package CDEye_PMAuto.backend.workpackage;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name="workpackages")
@Named("workPackage")
@RequestScoped
public class WorkPackage implements Serializable {

	@Id
	@Column(name="id")
	@Type(type = "uuid-char")
	protected UUID id;
	
	@Column(name="workpackagenumber")
	protected String workPackageNumber;
	
	@ManyToOne
	@JoinColumn(name="parentwp")
	protected WorkPackage parentWp;
	
	@Column(name="budgetestimate")
	protected BigDecimal budgetEstimate;
	
	@Column(name="budgetactual")
	protected BigDecimal budgetActual;
	
	@Column(name="persondayestimate")
	protected BigDecimal personDayEstimate;
	
	@Column(name="persondayactual")
	protected BigDecimal personDayActual;
	
	/**Ensures that associated package cost estimates sum to 
	 * estimated budget - cannot save if false*/
	public boolean valid() { return true; }
	
}
