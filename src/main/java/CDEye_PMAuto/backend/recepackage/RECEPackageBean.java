package CDEye_PMAuto.backend.recepackage;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named("recePackageBean")
@RequestScoped
public class RECEPackageBean extends RECEPackage implements Serializable {

    @Inject 
    private RECEManager receManager;
    
    public void test() {
        System.out.println("Test Function Called");
    }
    
    public void add() {
        System.out.println("Adding RECE package to DB");
        RECEPackage p = new RECEPackage(this);
        receManager.createRECE(p);
    }
    


    
    
    
}
