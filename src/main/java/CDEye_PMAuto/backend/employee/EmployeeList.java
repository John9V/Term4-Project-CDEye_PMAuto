package CDEye_PMAuto.backend.employee;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.inject.Named;


@Named("employeeList")
@ConversationScoped
public class EmployeeList implements Serializable {
	@Inject @Dependent private EmployeeManager employeeManager;
	private List<EditableEmployee> list;
	@Inject Conversation conversation;
	
	public List<EditableEmployee> getList() {
		if(!conversation.isTransient()) {
			conversation.end();
		}
		conversation.begin();
        if (list == null) {
            refreshList();
        }
        return list;
    }

	public List<EditableEmployee> refreshList() {
		Employee[] employees = employeeManager.getAll();
        list = new ArrayList<EditableEmployee>();
        for (int i = 0; i < employees.length; i++) {
            list.add(new EditableEmployee(employees[i]));
        }
        System.out.println("running");
        return list;
    }

}
