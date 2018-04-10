package by.grsu.yuzefovich.dataaccess.impl;

import java.io.Serializable;
import java.util.List;

import by.grsu.yuzefovich.dataaccess.AbstractDao;
import by.grsu.yuzefovich.datamodel.Brigade;
import by.grsu.yuzefovich.datamodel.Request;
import by.grsu.yuzefovich.table.RequestTable;

public class RequestDao extends AbstractDao<RequestTable, Request> {

	public RequestDao(final String rootFolderPath) {
		super(rootFolderPath);
	}

	@Override
	public void saveNew(Request newRequest) {
		// set ID
		newRequest.setId(getNextId());
		// get existing data
		final RequestTable requestTable = deserializeFromXml();
		// add new row
		requestTable.getRows().add(newRequest);
		// save data
		serializeToXml(requestTable);
		//
	}

	@Override
	public void update(Request entity) {
		// get existing data
		final RequestTable requestTable = deserializeFromXml();
		// find by ID
		for (final Request row : requestTable.getRows()) {
			if (row.getId().equals(entity.getId())) {
				// found!!!
				// copy data
				row.setTypeOfWork(entity.getTypeOfWork());
				row.setScopeOfWork(entity.getScopeOfWork());
				row.setLeadTime(entity.getLeadTime());
				row.setBrigade(entity.getBrigade());
				row.setIsAccepted(entity.getIsAccepted());
				break;
			}
		}
		// save updated table
		serializeToXml(requestTable);
	}
	
	public void updateBrigade(Request entity, Long id) {
		// get existing data
		final RequestTable requestTable = deserializeFromXml();
		// find by ID
		for (final Request row : requestTable.getRows()) {
			if (row.getId().equals(entity.getId())) {
				// found!!!
				// copy data
				row.setBrigade(new Brigade(id));
				row.setIsAccepted(true);
				break;
			}
		}
		// save updated table
		serializeToXml(requestTable);
	}

	@Override
	public Request get(Long id) {
		// get existing data
		final RequestTable requestTable = deserializeFromXml();
		// find by ID
		for (final Request row : requestTable.getRows()) {
			if (row.getId().equals(id)) {
				return row;
			}
		}
		return null;
	}

	@Override
	public List<Request> getAll() {
		// get existing data
		final RequestTable requestTable = deserializeFromXml();
		return requestTable.getRows();
	}

	@Override
	public void delete(Long id) {
		// get existing data
		final RequestTable requestTable = deserializeFromXml();
		// find by ID
		Request toBeDeleted = null;
		for (final Request row : requestTable.getRows()) {
			if (row.getId().equals(id)) {
				// found!!!
				toBeDeleted = row;
				break;
			}
		}
		// remove from list
		requestTable.getRows().remove(toBeDeleted);
		// save updated table
		serializeToXml(requestTable);
	}

	@Override
	protected Class<RequestTable> getTableClass() {
		return RequestTable.class;
	}

}
