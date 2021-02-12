package CDEye_PMAuto.backend.workpackage;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.inject.Named;

@Named("workPackageList")
@ConversationScoped
public class WorkPackageList implements Serializable {
    
    @Inject 
    @Dependent 
    private WorkPackageManager workPackageManager;
    
    private List<EditableWorkPackage> list;
    private WorkPackage workPackage;
    
    @Inject 
    Conversation conversation;
    
    public List<EditableWorkPackage> getList() {
        if(!conversation.isTransient()) {
            conversation.end();
        }
        conversation.begin();
        if (list == null) {
            refreshList();
        }
        return list;
    }

    public List<EditableWorkPackage> refreshList() {
        WorkPackage[] workPackages = workPackageManager.getAll();
        list = new ArrayList<EditableWorkPackage>();
        for (int i = 0; i < workPackages.length; i++) {
            list.add(new EditableWorkPackage(workPackages[i]));
        }
        System.out.println("running");
        return list;
    }

    public WorkPackage getWorkPackage() {  
        return workPackage;
    }

    public void setWorkPackage(WorkPackage workPackage) {
        this.workPackage = workPackage;
    }
    
}
