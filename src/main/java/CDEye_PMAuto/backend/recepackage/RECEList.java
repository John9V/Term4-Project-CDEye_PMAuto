package CDEye_PMAuto.backend.recepackage;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.inject.Named;

@Named("receList")
@ConversationScoped
public class RECEList implements Serializable {
    
    /** The RECEManage used to access the DB **/
    @Inject 
    @Dependent 
    private RECEManager receManager;
    
    /** A list of editable rece packages **/
    private List<EditableRECEPackage> list;
        
    @Inject 
    Conversation conversation;
    
    /**
     * Gets a list of RECEPackages. Will call refreshList() if the list is currently null.
     * @return a list of EditableRecePackage, List<EditableRECEPackage>
     */
    public List<EditableRECEPackage> getList() {
        if(!conversation.isTransient()) {
            conversation.end();
        }
        conversation.begin();
        if (list == null) {
            refreshList();
        }
        return list;
    }

    /**
     * Accesses the RECEManager Class to get all EditableRECEPackages. This function is used by 
     * getList().
     * 
     * @return a list of EditableREcePackage, List<EditableRECEPackage>
     */
    public List<EditableRECEPackage> refreshList() {
        RECEPackage[] packages = receManager.getAll();
        list = new ArrayList<EditableRECEPackage>();
        for (int i = 0; i < packages.length; i++) {
            list.add(new EditableRECEPackage(packages[i]));
        }
        return list;
    }
    

}
