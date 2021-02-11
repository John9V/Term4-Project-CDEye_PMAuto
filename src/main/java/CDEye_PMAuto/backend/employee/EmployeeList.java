package CDEye_PMAuto.backend.employee;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.inject.Named;


@Named("employeeList")
@ConversationScoped
public class EmployeeList implements Serializable {
    
	@Inject 
	@Dependent 
	private EmployeeManager employeeManager;
	
	private List<EditableEmployee> list;
	private Employee employee;
	
	@Inject 
	Conversation conversation;
	
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

    /**
     * Save selected employee info and update to db
     * @return on page refresh
     */
    public String saveSelectedEmployee(EditableEmployee e) {
        employeeManager.updateEmployee(e);
        e.setEditable(false);
        return "";
    }

    /**
     * Edit selected employe row, disable employee id.
     * Can edit other field
     * @return on page refresh
     */
    public String editSelectedEmployee(EditableEmployee e) {
        e.setEditable(true);
        return "";
    }

    /**
     * Delete selected employee
     * @return on page refresh
     */
    public String deleteSelectedEmployee(EditableEmployee e) {
        employeeManager.deleteEmployee(e);
        refreshList();
        return "";
    }

    public Employee getEmployee() {  
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

}
