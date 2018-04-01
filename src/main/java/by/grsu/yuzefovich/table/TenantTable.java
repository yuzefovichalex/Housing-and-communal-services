package by.grsu.yuzefovich.table;

import java.util.ArrayList;
import java.util.List;

import by.grsu.yuzefovich.datamodel.Tenant;

public class TenantTable extends AbstractTable<Tenant> {
	
	private List<Tenant> rows;

	@Override
	public List<Tenant> getRows() {
		if (rows == null) {
			rows = new ArrayList<Tenant>();
		}
		return rows;
	}

	@Override
	public void setRows(List<Tenant> rows) {
		this.rows = rows;
	}
}
