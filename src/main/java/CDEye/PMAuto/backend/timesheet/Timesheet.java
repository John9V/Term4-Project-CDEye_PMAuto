package CDEye.PMAuto.backend.timesheet;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Type;

import CDEye.PMAuto.backend.tsrow.TimesheetRow;
import CDEye_PMAuto.backend.employee.Employee;

@Entity
@Table(name="timesheets")
public class Timesheet {
	
	@Id
	@Column(name="id")
	@Type(type = "uuid-char")
	protected UUID id;

	/** Number of days in a week. */
    public static final int DAYS_IN_WEEK = 7;
    
    /** Number of hours in a day as double. */
    public static final double HOURS_IN_DAY = 24.0;
    
    /** Number of decihours in a day. */
    public static final int DECIHOURS_IN_DAY = 240;
    
    /** Number of work hours in week as double. */
    public static final double FULL_WORK_WEEK_HOURS = 40.0;

    /** Number of work hours in week as double. */
    public static final int FULL_WORK_WEEK_DECIHOURS = 400;

    /** Week fields of week ending on Friday. */
    public static final WeekFields FRIDAY_END 
            = WeekFields.of(DayOfWeek.SATURDAY, 1);

    /** Serial version number. */
    private static final long serialVersionUID = 4L;

    /** The user associated with this timesheet. */
    @ManyToOne
    @JoinColumn(name="employee")
    protected Employee employee;
    
    /** The date of Friday for the week of the timesheet. */
    @Column(name="end_date")
    protected LocalDate endDate;
    
    @Fetch(value = FetchMode.SUBSELECT)
	@OneToMany(mappedBy="timesheet", fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    /** The List of all details (i.e. rows) that the form contains. */
    protected List<TimesheetRow> details;
    
    @Column(name="sick")
	protected BigDecimal sick;
	@Column(name="flex")
	protected BigDecimal flex;

    public Timesheet() {
		this.id = UUID.randomUUID();
		this.details = new ArrayList<TimesheetRow>();
		this.details.add(new TimesheetRow(this));
	}
    
    public Timesheet(CreateTimesheetBean nts) {
    	this.id = UUID.randomUUID();
		this.employee = nts.employee;
		this.endDate = nts.endDate;
		this.details = nts.details;
		this.sick = nts.sick;
		this.flex = nts.flex;
    }
    
    public Timesheet(EditableTimesheet ets) {
    	this.id = UUID.randomUUID();
		this.employee = ets.employee;
		this.endDate = ets.endDate;
		this.details = ets.details;
		this.sick = ets.sick;
		this.flex = ets.flex;
    }

	public Timesheet(UUID id, Employee employee, LocalDate endDate, List<TimesheetRow> details,
			BigDecimal flex, BigDecimal sick) {
		super();
		this.id = id;
		this.employee = employee;
		this.endDate = endDate;
		this.details = details;
		this.sick = sick;
		this.flex = flex;
	}
	
	public Timesheet(Timesheet t) {
		super();
		this.id = t.id;
		this.employee = t.employee;
		this.endDate = t.endDate;
		this.details = t.details;
		this.sick = t.sick;
		this.flex = t.flex;
	}

	public UUID getId() {
		return id;
	}
	
	public void setId(UUID id) {
		this.id = id;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public List<TimesheetRow> getDetails() {
		return details;
	}

	public void setDetails(List<TimesheetRow> details) {
		this.details = details;
	}
}
