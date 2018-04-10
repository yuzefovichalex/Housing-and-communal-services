package by.grsu.yuzefovich.table;

import java.util.ArrayList;
import java.util.List;

import by.grsu.yuzefovich.datamodel.Brigade;

public class BrigadeTable extends AbstractTable<Brigade> {
	
	private List<Brigade> rows;

	@Override
	public List<Brigade> getRows() {
		if (rows == null) {
			rows = new ArrayList<Brigade>();
		}
		return rows;
	}

	@Override
	public void setRows(List<Brigade> rows) {
		this.rows = rows;
	}
}
