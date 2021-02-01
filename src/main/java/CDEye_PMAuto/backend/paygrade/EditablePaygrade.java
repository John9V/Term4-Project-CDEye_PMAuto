package CDEye_PMAuto.backend.paygrade;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

public class EditablePaygrade extends Paygrade implements Serializable {
	private boolean editable = false;
	private boolean deletable = false;
	
	public EditablePaygrade() {
		super();
	}
	
	public EditablePaygrade(Paygrade p) {
		super(p.id, p.name, p.salary);
	}
	
	public EditablePaygrade(UUID id, String name, BigDecimal salary) {
		super(id, name, salary);
	}

	public boolean isEditable() {
		return editable;
	}

	public void setEditable(boolean editable) {
		this.editable = editable;
	}

	public boolean isDeletable() {
		return deletable;
	}

	public void setDeletable(boolean deletable) {
		this.deletable = deletable;
	}
	
	
	
}
