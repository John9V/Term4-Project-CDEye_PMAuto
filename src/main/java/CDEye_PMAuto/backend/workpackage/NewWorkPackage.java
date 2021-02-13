package CDEye_PMAuto.backend.workpackage;

import java.io.Serializable;
import java.util.UUID;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * For creating new work packages and temporarily storing their information.
 */
@Named("newWorkPackage")
@RequestScoped
public class NewWorkPackage extends WorkPackage implements Serializable {

    @Inject private WorkPackageManager workPackageManager;
    
    /**
     * Used to persist the new work package.
     */
    public String add() {
        WorkPackage wp = new WorkPackage(this);
        wp.setId(UUID.randomUUID());
        wp.setLeaf(false);
        workPackageManager.addWorkPackage(wp);
        return "testWPs";
    }

}
