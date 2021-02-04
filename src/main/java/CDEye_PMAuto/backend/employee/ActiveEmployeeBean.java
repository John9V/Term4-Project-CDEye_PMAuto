package CDEye_PMAuto.backend.employee;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import CDEye_PMAuto.backend.credentials.CredentialManager;
import CDEye_PMAuto.backend.credentials.Credential;

@Named("activeEmployeeBean")
@SessionScoped
public class ActiveEmployeeBean extends Employee implements Serializable {

	@Inject CredentialManager credentialManager;
	@Inject EmployeeManager employeeManager;

	/**
	 * The username of the currently logged in user.
	 */
	private String userName;
	
	/**
	 * The password of the currently logged in user.
	 */
	private String password;

	/**
	 * Login method. Validates that credentials are correct, and, if so,
	 * loads that user's data from the employee list into this bean.
	 * @return a string directing the user to the right page based on the
	 * type of user that just logged in.
	 */
	public String login() {
		System.out.println("login called");
		Credential c = new Credential();
		c.setUserName(userName);
		c.setPassword(password);
		credentialManager.validCredentials(c);
		if(credentialManager.validCredentials(c)) {
			System.out.println("These are valid credentials");
		} else {
			System.out.println("These are not valid credentials");
		}
		if (credentialManager.validCredentials(c)) {
			Employee loggedInEmployee = employeeManager
			        .getEmployeeByUserName(userName);
			this.setFirstName(loggedInEmployee.getFirstName());
			this.setLastName(loggedInEmployee.getLastName());
			this.setEmpNum(loggedInEmployee.getEmpNum());
//			isAdmin = employeeManager
//			        .isAdmin(loggedInEmployee);
//			if (isAdmin) {
//				return "adminWelcome";
//			} else {
//				return "userWelcome";
//			}
			return "welcome";
		} else {
			return "invalidCredentials";
		}
	}

	
	public String getUserName() {
		return userName;
	}

	/**
	 * Setter to set the username based on input from the login screen.
	 * @param userName user's username.
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * Getter for the typed in password on the login screen.
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Setter for the password typed in on the login screen.
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	
}
