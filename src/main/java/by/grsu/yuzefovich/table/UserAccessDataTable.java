package by.grsu.yuzefovich.table;

import java.util.ArrayList;
import java.util.List;

import by.grsu.yuzefovich.datamodel.UserAccessData;

public class UserAccessDataTable extends AbstractTable<UserAccessData> {
	
	private List<UserAccessData> rows;

	@Override
	public List<UserAccessData> getRows() {
		if (rows == null) {
			rows = new ArrayList<UserAccessData>();
		}
		return rows;
	}

	@Override
	public void setRows(List<UserAccessData> rows) {
		this.rows = rows;
	}

}
