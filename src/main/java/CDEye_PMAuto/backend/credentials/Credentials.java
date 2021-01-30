package CDEye_PMAuto.backend.credentials;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Login credentials.
 */
@Entity
@Table(name="credentials")
@Named("credentials")
@SessionScoped
public class Credentials implements Serializable {
    
    /** The login ID. */
    @Id
    @Column(name="userName")
    private String userName;
    
    /** The password. */
    @Column(name="password")
    private String password;
    
    /**
     * userName getter.
     * @return the loginID
     */
    public String getUserName() {
        return userName;
    }
    
    /**
     * userName setter.
     * @param id the loginID to set
     */
    public void setUserName(final String id) {
        userName = id;
    }
    /**
     * password getter.
     * @return the password
     */
    public String getPassword() {
        return password;
    }
    /**
     * password setter.
     * @param pw the password to set
     */
    public void setPassword(final String pw) {
        password = pw;
    }

}
