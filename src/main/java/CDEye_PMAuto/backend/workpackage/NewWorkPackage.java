package CDEye_PMAuto.backend.workpackage;

import java.io.Serializable;
import javax.inject.Inject;

/**
 * For creating new work packages and temporarily storing their information.
 */
public class NewWorkPackage extends WorkPackage implements Serializable {

    @Inject private WorkPackageManager workPackageManager;
    
    /**
     * Used to persist the new work package.
     */
    public void add() {
        WorkPackage wp = new WorkPackage(this);
        workPackageManager.addWorkPackage(wp);
    }

}
