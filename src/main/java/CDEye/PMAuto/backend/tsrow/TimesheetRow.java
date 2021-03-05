package CDEye.PMAuto.backend.tsrow;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Type;

import CDEye.PMAuto.backend.timesheet.Timesheet;
import CDEye_PMAuto.backend.workpackage.WorkPackage;

@Entity
public class TimesheetRow {

	@Id
	@Column(name="id")
	@Type(type = "uuid-char")
	protected UUID id ;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="sheet_id")
    protected Timesheet timesheet;
	
	
	
}
