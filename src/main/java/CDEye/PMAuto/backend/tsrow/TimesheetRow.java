package CDEye.PMAuto.backend.tsrow;

import java.math.BigDecimal;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import CDEye.PMAuto.backend.timesheet.Timesheet;
import CDEye_PMAuto.backend.workpackage.WorkPackage;

@Entity
@Table(name="timesheetrow")
public class TimesheetRow {

	@Column(name="comments")
	protected String comments;
	
	@Id
	@Column(name="id")
	@Type(type = "uuid-char")
	protected UUID id;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="parent_sheet")
    protected Timesheet timesheet;
	
	@Column(name="mon")
	protected BigDecimal mon;
	@Column(name="tue")
	protected BigDecimal tue;
	@Column(name="wed")
	protected BigDecimal wed;
	@Column(name="thu")
	protected BigDecimal thu;
	@Column(name="fri")
	protected BigDecimal fri;
	@Column(name="sat")
	protected BigDecimal sat;
	@Column(name="sun")
	protected BigDecimal sun;
	
	
	
	
}
