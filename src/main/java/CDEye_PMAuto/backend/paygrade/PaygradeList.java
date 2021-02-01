package CDEye_PMAuto.backend.paygrade;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.inject.Named;

@Named("paygradeList")
@ConversationScoped
public class PaygradeList implements Serializable {
	@Inject @Dependent private PaygradeManager paygradeManager;
	private List<EditablePaygrade> list;
	@Inject Conversation conversation;
	
	public List<EditablePaygrade> getList() {
		if (!conversation.isTransient()) {
			conversation.end();
		}
		conversation.begin();
        if (list == null) {
            refreshList();
        }
        return list;
    }
	
	public List<EditablePaygrade> refreshList() {
		Paygrade[] paygrades = paygradeManager.getAll();
        list = new ArrayList<EditablePaygrade>();
        for (int i = 0; i < paygrades.length; i++) {
            list.add(new EditablePaygrade(paygrades[i]));
        }
        return list;
    }
	
	public String submit() {
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).isEditable()) {
				Paygrade p = new Paygrade(list.get(i));
				paygradeManager.updatePaygrade(p);
				list.get(i).setEditable(false);
			}
		}
		return "";
	}
	
}
