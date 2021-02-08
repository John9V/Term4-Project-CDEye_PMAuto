package CDEye_PMAuto.backend.employee;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;

import org.hibernate.annotations.DynamicUpdate;

import CDEye_PMAuto.backend.credentials.Credential;
import CDEye_PMAuto.backend.credentials.CredentialManager;
import CDEye_PMAuto.backend.paygrade.PaygradeManager;

@Named("createEmployee")
@RequestScoped
public class CreateEmployeeBean extends Employee implements Serializable {
	@Inject private PaygradeManager paygradeManager;
	@Inject private EmployeeManager employeeManager;
	@Inject private CredentialManager credentialManager;
	//========================  PLEASE REVIEW CODE AND COMMENT BELOW FOR PAYGRADE DROPDOWN LIST FEATURE ==========================
	/**
	 * The dropdown list <h:selectOneMenu>need a string set the paygrades that the user select
	 * The paygrades above is an object with the details of the Paygrade
	 * So I create a String paygrade for set and get paygrade that user select from dropdown list
	 * To not mess up with backend-team and database models, the paygrade member and getter setters
	 * are commented out because it need to be added in employee table
	 *
	 * Also, please add isHR memeber and getter and setters to Employee model and database as well.
	 */

	protected String paygradeName;
	
	protected String password;

	/** Get selected paygrade from dropdown list. */

	public String getPaygradeName() {
		return paygradeName;
	}

	public void setPaygradeName(String paygradeName) {
		System.out.println("running: " + paygradeName);
		this.payGrade = paygradeManager.findPaygrade(paygradeName);
		this.paygradeName = paygradeName;
	}

	/** Paygrade Enum with 9 paygrdes
	 * Unsure for the last one, so just randomly give a name
	 */
	public enum PAYGRADE {
		P1,
		P2,
		P3,
		P4,
		P5,
		P6,
		DS,
		JS,
		XS,
	}

	/** Paygrade list to populate each select item. */
	private static Collection<SelectItem> paygradeList;
	static {
		paygradeList = new ArrayList<SelectItem>();

		for (PAYGRADE p : PAYGRADE.values()) {
			paygradeList.add(new SelectItem((p)));
		}
	}

	/** Return paygrade list. */
	public Collection<SelectItem> getPaygradeItems() {
		return paygradeList;
	}
	//======================== END ===================================================================================

	
	
	public void add() {
		Employee e = new Employee(this);
		Credential c = new Credential(this.userName, this.password);
		employeeManager.addEmployee(e);
		credentialManager.persist(c);
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
