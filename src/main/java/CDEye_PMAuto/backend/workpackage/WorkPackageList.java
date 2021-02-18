package CDEye_PMAuto.backend.workpackage;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import CDEye_PMAuto.backend.employee.EditableEmployee;
import CDEye_PMAuto.backend.employee.Employee;
import CDEye_PMAuto.backend.project.ActiveProjectBean;
import CDEye_PMAuto.backend.project.Project;

@Named("workPackageList")
@SessionScoped
public class WorkPackageList implements Serializable {
    
    @Inject 
    @Dependent 
    private WorkPackageManager workPackageManager;
    
    @Inject ActiveProjectBean apb;
    
    private List<EditableWorkPackage> list;
    
    private String searchId; // = "123e4567-e89b-12d3-a456-599342400003";
    private String searchParentPackageId; // = "123e4567-e89b-12d3-a456-599342400001";
    private String searchPackageNumber; // = "111"; 
    
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
    
    public List<EditableWorkPackage> getWpsByProj() {
    	WorkPackage[] wps = workPackageManager.findWpsByProjectNumber(apb.getProjectNumber());
        list = new ArrayList<EditableWorkPackage>();
        for (int i = 0; i < wps.length; i++) {
            list.add(new EditableWorkPackage(wps[i]));
        }
        System.out.println("running");
        return list;
    }

    public List<EditableWorkPackage> refreshList() {
        // Checks if there are search criteria. Gets the searched ones if the was
        // search, otherwise gets all
        WorkPackage[] workPackages;
        if (searchId != null && searchId.length() >= 1) {
            workPackages = new WorkPackage[1];
            workPackages[0] = workPackageManager.getByUUID(searchId);
            System.out.println("get WP by uuid");
        } else if (searchParentPackageId != null
                && searchParentPackageId.length() >= 1) {
            System.out.println("get WP by parent uuid");
            workPackages = workPackageManager.getByParentId(searchParentPackageId);
        } else if (searchPackageNumber != null
                && searchPackageNumber.length() >= 1) {
            System.out.println("get WP by package num");
            workPackages = workPackageManager.getByPackageNumber(searchPackageNumber);
        } else {
            System.out.println("get all WP");
            workPackages = workPackageManager.getAll();
        }
        
        list = new ArrayList<EditableWorkPackage>();
        for (int i = 0; i < workPackages.length; i++) {
            list.add(new EditableWorkPackage(workPackages[i]));
        }
        System.out.println("running after refreshList()");
        return list;
    }
    
    public void search() {
        System.out.println("custom search method start");
        refreshList();
        System.out.println("refreshing list");
        System.out.println("custom search method finish");
    }
    
    public void clearSearch() {
        searchId = null;
        searchParentPackageId = null;
        searchPackageNumber = null;
        refreshList();
    }

    /**
     * @return the searchId
     */
    public String getSearchId() {
        return searchId;
    }

    /**
     * @param searchId the searchId to set
     */
    public void setSearchId(String searchId) {
        this.searchId = searchId;
    }

    /**
     * @return the searchParentPackageId
     */
    public String getSearchParentPackageId() {
        return searchParentPackageId;
    }

    /**
     * @param searchParentPackageId the searchParentPackageId to set
     */
    public void setSearchParentPackageId(String searchParentPackageId) {
        this.searchParentPackageId = searchParentPackageId;
    }

    /**
     * @return the searchPackageNumber
     */
    public String getSearchPackageNumber() {
        return searchPackageNumber;
    }

    /**
     * @param searchPackageNumber the searchPackageNumber to set
     */
    public void setSearchPackageNumber(String searchPackageNumber) {
        this.searchPackageNumber = searchPackageNumber;
    }
    
}
