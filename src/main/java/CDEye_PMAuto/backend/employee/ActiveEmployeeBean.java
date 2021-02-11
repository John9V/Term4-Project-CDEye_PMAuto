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

	private String newPassword = null;



	private String confirmPassword = null;

	/**
	 * Login method. Validates that credentials are correct, and, if so,
	 * loads that user's data from the employee list into this bean.
	 * @return a string directing the user to the right page based on the
	 * type of user that just logged in.
	 */
	public String login() {
		Credential c = new Credential();
		c.setUserName(userName);
		c.setPassword(password);
		credentialManager.validCredentials(c);
		if (credentialManager.validCredentials(c)) {
			Employee loggedInEmployee = employeeManager
			        .getEmployeeByUserName(userName);
			if (loggedInEmployee.active) {
				if (loggedInEmployee.hr) {
					return "HRHome";
				} else {
					return "Home";
				}
			} else {
				return "invalidCredentials";
			}
		} else {
			return "invalidCredentials";
		}
	}

	public String changePassword() {
		Credential[] crdAry = credentialManager.getAll();
		for (int i=0; i < crdAry.length; i++)
			if (crdAry[i].getUserName().contains(userName))
			{
				crdAry[i].setPassword(password);
				credentialManager.validCredentials(crdAry[i]);
				System.out.println(credentialManager.validCredentials(crdAry[i]));
				if (credentialManager.validCredentials(crdAry[i]))
				{
					if (newPassword.equals(confirmPassword))
					{
						setPassword(newPassword);
						crdAry[i].setPassword(newPassword);
						setNewPassword(null);
						setConfirmPassword(null);
						credentialManager.merge(crdAry[i]);
						return "Home";
					}
				}
			}
		return "changePw";
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


	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
}
