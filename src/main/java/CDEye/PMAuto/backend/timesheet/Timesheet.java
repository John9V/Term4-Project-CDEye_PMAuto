package CDEye.PMAuto.backend.timesheet;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Type;

import CDEye.PMAuto.backend.tsrow.TimesheetRow;
import CDEye_PMAuto.backend.employee.Employee;

@Entity
public class Timesheet {
	
	//DUE March 19
	
	//TODO make sure timesheet manager has all CRUD methods and also implements TimesheetInterface
	//TODO Add XML file for creating a timesheet (do not style it) - check createemployee.xml for example - you will need to create NewTimesheetBean which extends Timesheet, see EditableEmployee for example
	//TODO complete ediatable timesheet class - extends timesheet, check editable employee for example
	//xml doesn't have to be perfect, doesn't need to do validation, and doesn't need to handle all error cases (yet) - just get it to work
	
	//TODO Add xml file for listing timesheets for currently logged in employee (active employee bean) 
	//(do not style it) - check editemployees for example 
	//To show timesheets for currently logged in employee, see how editableprojects.xhtml calls a method in the activeprojectbean
	//This means you'll have to base activetimesheetbean off of activeproject bean
	
	
	//no business logic needed, just get the screens working so we can create new timesheets and see/edit/delete the timesheets for the logged in employee
	
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
    private Employee employee;
    
    /** The date of Friday for the week of the timesheet. */
    private LocalDate endDate;
    
    @Fetch(value = FetchMode.SUBSELECT)
	@OneToMany(mappedBy="timesheet", fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    /** The List of all details (i.e. rows) that the form contains. */
    private List<TimesheetRow> details;
    
    /** The total number of overtime hours on the timesheet. Decihours. 
     *  Must be >= 0 */
    private int overtime;
    
    /** The total number of flextime hours on the timesheet. Decihours.
     *  Must be >= 0  */
    private int flextime;

    //check work package class for examples of constructors
    //TODO constructor with all parameters
    //TODO constructor that accepts new timesheet (auto-generates id)
    //TODO constructor with all parameters, accepts editable timesheet
    //TODO constructor with all parameters
	
}
