package CDEye.PMAuto.backend.tsrow;

import CDEye.PMAuto.backend.timesheet.Timesheet;

import javax.ejb.Stateless;
import javax.enterprise.context.Dependent;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.io.Serializable;
import java.util.List;

@Dependent
@Stateless
public class TimesheetRowManager implements Serializable {

    @PersistenceContext(unitName="inventory-jpa")
    EntityManager em;

    public TimesheetRow[] getAll() {
        TypedQuery<TimesheetRow> query = em.createQuery("select tsr from TimesheetRow tsr",
                TimesheetRow.class);
        return getRows(query);
    }

    private TimesheetRow[] getRows(TypedQuery<TimesheetRow> query) {
        List<TimesheetRow> rows = query.getResultList();
        TimesheetRow[] rowArr = new TimesheetRow[rows.size()];
        for (int i = 0; i < rowArr.length; i++) {
            rowArr[i] = rows.get(i);
        }
        return rowArr;
    }

    public void updateRow(TimesheetRow r) {em.merge(r);}
    public void deleteRow(TimesheetRow r) {em.remove(r);}
    public void addRow(TimesheetRow r) {em.persist(r);}
}
