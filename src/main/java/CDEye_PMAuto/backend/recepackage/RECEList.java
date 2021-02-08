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
    
    @Inject 
    @Dependent 
    private RECEManager receManager;
    
    private List<EditableRECEPackage> list;
    
    @Inject 
    Conversation conversation;
    
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

    public List<EditableRECEPackage> refreshList() {
        RECEPackage[] packages = receManager.getAll();
        list = new ArrayList<EditableRECEPackage>();
        for (int i = 0; i < packages.length; i++) {
            list.add(new EditableRECEPackage(packages[i]));
        }
        return list;
    }


}
