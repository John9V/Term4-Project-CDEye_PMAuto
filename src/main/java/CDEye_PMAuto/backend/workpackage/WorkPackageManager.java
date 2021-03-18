package CDEye_PMAuto.backend.workpackage;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import javax.ejb.Stateless;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import CDEye_PMAuto.backend.project.Project;
import CDEye_PMAuto.backend.recepackage.RECEManager;
import CDEye_PMAuto.backend.recepackage.RespEngCostEstimate;
import CDEye_PMAuto.backend.wpallocation.WorkPackageAllocManager;
import CDEye_PMAuto.backend.wpallocation.WorkPackageAllocation;

@Dependent
@Stateless
public class WorkPackageManager implements Serializable {
    
    @PersistenceContext(unitName="inventory-jpa") 
    EntityManager em;
    
    @Inject 
    @Dependent 
    private WorkPackageAllocManager workPackageAllocManager;
    
    @Inject 
    @Dependent 
    private RECEManager receManager;
    
    /**
     * Gets all the work packages in the DB.
     * @return an array of packages
     */
    public WorkPackage[] getAll() {
        TypedQuery<WorkPackage> query = em.createQuery("select wp from WorkPackage wp",
                WorkPackage.class); 
        List<WorkPackage> workPackages = query.getResultList();
        WorkPackage[] wpArr = new WorkPackage[workPackages.size()];
        for (int i = 0; i < wpArr.length; i++) {
            wpArr[i] = workPackages.get(i);
        }
        return wpArr;
    }
    
    /**
     * Gets a workPackage by UUID number.
     * 
     * @param uuid string id
     * @return workPackage workPackage
     */
    public WorkPackage getByUUID(String uuid) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<WorkPackage> criteriaQuery = criteriaBuilder.createQuery(WorkPackage.class);
        Root<WorkPackage> itemRoot = criteriaQuery.from(WorkPackage.class);
        
        UUID uuidAsString = UUID.fromString(uuid);
        
        Predicate predicateForName = criteriaBuilder.equal(itemRoot.get("id"), uuidAsString);
        
        criteriaQuery.where(predicateForName);
        
        List<WorkPackage> workPackage = em.createQuery(criteriaQuery).getResultList();

        return workPackage.get(0);
    }
    
    public WorkPackage[] getByPackageNumber(String packageNumber) {
        TypedQuery<WorkPackage> query = em.createQuery(
                "SELECT wp FROM WorkPackage wp WHERE workpackagenumber LIKE :packageNumber", WorkPackage.class)
                .setParameter("packageNumber", "%" + packageNumber + "%");
        List<WorkPackage> workPackages = query.getResultList();
        
        WorkPackage[] packageArr = new WorkPackage[workPackages.size()];
        for (int i = 0; i < packageArr.length; i++) {
            packageArr[i] = workPackages.get(i);
        }
        
        return packageArr;
    }
    
    public WorkPackage[] getByParentId(String parentId) {
        TypedQuery<WorkPackage> query = em.createQuery(
                "SELECT wp FROM WorkPackage wp WHERE parentworkpackage LIKE :parentId", WorkPackage.class)
                .setParameter("parentId", "%" + parentId + "%");
        List<WorkPackage> workPackages = query.getResultList();
        
        WorkPackage[] packageArr = new WorkPackage[workPackages.size()];
        for (int i = 0; i < packageArr.length; i++) {
            packageArr[i] = workPackages.get(i);
        }
        
        return packageArr;
    }
    
    public WorkPackage[] findWpsByProject(Project p) {
        TypedQuery<WorkPackage> query = em.createQuery(
                "SELECT wp FROM WorkPackage wp WHERE wp.project.id = :projectId", WorkPackage.class)
                .setParameter("projectId", p.getId());
        List<WorkPackage> workPackages = query.getResultList();
        
        WorkPackage[] packageArr = new WorkPackage[workPackages.size()];
        for (int i = 0; i < packageArr.length; i++) {
            packageArr[i] = workPackages.get(i);
        }
        
        return packageArr;
    }
    
    public WorkPackage[] findWpsByPkgNumAndProj(String workPackageNumber, Project p) {
    	System.out.println("the wp num is " + workPackageNumber);
    	System.out.println("the proj is " + p.getProjectName());
    	TypedQuery<WorkPackage> query = em.createQuery(
                "SELECT wp FROM WorkPackage wp WHERE wp.workPackageNumber = :workPackageNumber AND wp.project.id = :projectId", WorkPackage.class)
    			.setParameter("workPackageNumber", workPackageNumber)
    			.setParameter("projectId", p.getId());
    	List<WorkPackage> workPackages = query.getResultList();
    	WorkPackage[] packageArr = new WorkPackage[workPackages.size()];
        for (int i = 0; i < packageArr.length; i++) {
            packageArr[i] = workPackages.get(i);
        }
        return packageArr;
    }
    
    /**
     * Uses a modified workPackage to update a workPackage in the database.
     * 
     * @param modifiedWP the work package with modifications
     * @return the modified work package
     */
    public WorkPackage updateWorkPackage(WorkPackage modifiedWP) {
        return em.merge(modifiedWP);
    }
    
    /**
     * Adds a work package to the database.
     * @param wp the work package to add
     */
    public void addWorkPackage(WorkPackage wp) {
        em.persist(wp);
    }
    
    /**
     * Deletes a work package from the database.
     * @param wp the work package to delete
     */
    public void deleteWorkPackage(WorkPackage wp) {
        em.remove(em.contains(wp) ? wp : em.merge(wp));
    }
    
    /**
     * Looks to see if a work package is a leaf.
     * @param wp work package to determine leaf status of
     * @return boolean for whether the work package is a leaf or not
     */
    public boolean isLeaf(WorkPackage wp) {
        WorkPackage[] childrenWPs = getByParentId(wp.id.toString());
        return childrenWPs.length < 1;
    }
    
    /**
     * Sums all person day estimates from work package allocations for a specific
     * work package.
     * @param wp work package for which person day estimates are summed
     * @return calculated allocated person days
     */
    public BigDecimal calculateAllocatedPersonDays2(WorkPackage wp) {
        WorkPackageAllocation[] allocationsWP = workPackageAllocManager.getByWP(wp);
        BigDecimal personDays = new BigDecimal(0);
        
        for (WorkPackageAllocation allocation : allocationsWP) {
            personDays.add(allocation.getPersonDaysEstimate());
        }
        
        return personDays;
    }
    
    /**
     * If it is a branch, recursively calculates and sets the allocated budget for
     * a work package and its children by looking at its children. Otherwise, if it is
     * a leaf, the paygrade salary and person days are multiplied to get the allocated
     * budget.
     * @param wp work package for which to calculate allocated budget
     * @return allocated budget
     */
    public BigDecimal calculateAllocatedBudget(WorkPackage wp) {
        BigDecimal budget = new BigDecimal(0);
        
        if (isLeaf(wp)) {
            WorkPackageAllocation[] allocationsWP = workPackageAllocManager.getByWP(wp);
            
            for (WorkPackageAllocation allocation : allocationsWP) {
                BigDecimal calculatedBudget = allocation.getPaygrade().getSalary()
                        .multiply(allocation.getPersonDaysEstimate());
                budget.add(calculatedBudget);
            }
        } else { // for a branch work package
            WorkPackage[] childrenWPs = getByParentId(wp.id.toString());
            
            // sums all the children's allocated person days
            for (WorkPackage child : childrenWPs) {
                budget.add(calculateAllocatedBudget(child));
            }
        }
        
        return budget;
    }
    
    /**
     * Sums all responsible engineer person day estimates for a specific
     * work package. It is assumed this will be used only on leaf work
     * packages.
     * @param wp work package for which person day estimates are summed
     * @return calculated responsible engineer person day estimate
     */
    public BigDecimal calculateREPDEstimate(WorkPackage wp) {
        RespEngCostEstimate[] respEngPDEstimates = receManager.getByWP(wp);
        BigDecimal personDays = new BigDecimal(0);
        
        for (RespEngCostEstimate estimatePD : respEngPDEstimates) {
            personDays.add(estimatePD.getPersonDayEstimate());
        }
        
        return personDays;
    }
    
    /**
     * Sums all responsible engineer budget estimates for a specific
     * work package. It is assumed this will be used only on leaf work
     * packages.
     * @param wp work package for which budget estimates are summed
     * @return calculated responsible engineer budget estimate
     */
    public BigDecimal calculateREBudgetEstimate(WorkPackage wp) {
        RespEngCostEstimate[] respEngCostEstimates = receManager.getByWP(wp);
        BigDecimal budget = new BigDecimal(0);
        
        for (RespEngCostEstimate estimateCost : respEngCostEstimates) {
            BigDecimal calculatedCost = estimateCost.getPaygrade().getSalary()
                    .multiply(estimateCost.getPersonDayEstimate());
            budget.add(calculatedCost);
        }
        
        return budget;
    }
    
    /**
     * Calculate unallocated budget of the WorkPackage
     * 
     * @param wp WorkPackage
     * @return calculated unallocated budget
     */
    public BigDecimal calculateUnallocatedBudget(WorkPackage wp) {
		return wp.projectBudget.subtract(calculateAllocatedBudget(wp));
	}

    /**
     * INCORRECT
     */
	public String determineParentWPNum(WorkPackage wp) {
		return null;
	}

	/**
	 *INCORRECT
	 */
	public String determineWPNumFromParent(WorkPackage wp) {
		return null;
//		if (!wp.isLeaf) {
//			String WPNumFromParent = "";
//			WorkPackage[] workpackages = getByParentId(wp.workPackageNumber);
//			for (WorkPackage workpackage : workpackages) {
//				WPNumFromParent.concat(workpackage.workPackageNumber);
//				WPNumFromParent.concat("\n");
//			}
//			return WPNumFromParent;
//		} else {
//			return null;
//		}
	}

	/**
	 * Get WorkPackage(s) by StartDate
	 * 
	 * @param startDate Start Date
	 * @return WorkPackage(s) by StartDate
	 */
	public WorkPackage[] getByStartDate(LocalDate startDate) {
		TypedQuery<WorkPackage> query = em
				.createQuery("SELECT wp FROM WorkPackage wp WHERE startdate LIKE :startDate", WorkPackage.class)
				.setParameter("startDate", "%" + startDate + "%");
		List<WorkPackage> workPackages = query.getResultList();

		WorkPackage[] packageArr = new WorkPackage[workPackages.size()];
		for (int i = 0; i < packageArr.length; i++) {
			packageArr[i] = workPackages.get(i);
		}
		return packageArr;
	}

	/**
	 * Get WorkPackage(s) by EndDate
	 * 
	 * @param endDate End Date
	 * @return WorkPackage(s) by EndDate
	 */
	public WorkPackage[] getByEndDate(LocalDate endDate) {
		TypedQuery<WorkPackage> query = em
				.createQuery("SELECT wp FROM WorkPackage wp WHERE enddate LIKE :endDate", WorkPackage.class)
				.setParameter("endDate", "%" + endDate + "%");
		List<WorkPackage> workPackages = query.getResultList();

		WorkPackage[] packageArr = new WorkPackage[workPackages.size()];
		for (int i = 0; i < packageArr.length; i++) {
			packageArr[i] = workPackages.get(i);
		}
		return packageArr;
	}

	/**
	 * Get all leaves WorkPackage(s)
	 * 
	 * @param isLeaf isLeaf
	 * @return All leaves WorkPackage(s)
	 */
	public WorkPackage[] getAllLeaves(boolean isLeaf) {
		TypedQuery<WorkPackage> query = em
				.createQuery("SELECT wp FROM WorkPackage wp WHERE isleaf LIKE :isLeaf", WorkPackage.class)
				.setParameter("isLeaf", "%" + isLeaf + "%");
		List<WorkPackage> workPackages = query.getResultList();

		WorkPackage[] packageArr = new WorkPackage[workPackages.size()];
		for (int i = 0; i < packageArr.length; i++) {
			packageArr[i] = workPackages.get(i);
		}
		return packageArr;
	}

    /**
     * Updated selected Work Package Leaf details
     * @param w
     */
	public void updateWorkPackageLeaf(EditableWorkPackageLeaf w) {
        WorkPackage wp = getByUUID(String.valueOf(w.getId()));
        wp.workPackageNumber = w.workPackageNumber;
        wp.startDate = w.startDate;
        wp.endDate = w.endDate;
        wp.projectBudget = w.projectBudget;
        wp.completedBudget = w.completedBudget;
        wp.RECEs = w.RECEs;
        wp.wpAllocs = w.wpAllocs;
        wp.childPackages = w.childPackages;
        em.merge(wp);
        System.out.println("Update work package leaf" + wp.workPackageNumber);
    }
}
