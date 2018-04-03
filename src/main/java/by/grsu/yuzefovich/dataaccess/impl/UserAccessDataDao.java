package by.grsu.yuzefovich.dataaccess.impl;

import java.io.Serializable;
import java.util.List;

import by.grsu.yuzefovich.dataaccess.AbstractDao;
import by.grsu.yuzefovich.datamodel.UserAccessData;
import by.grsu.yuzefovich.table.UserAccessDataTable;

public class UserAccessDataDao extends AbstractDao<UserAccessDataTable, UserAccessData> {

	public UserAccessDataDao(final String rootFolderPath) {
		super(rootFolderPath);
	}

	@Override
	public void saveNew(UserAccessData newUserAccessData) {
		// set ID
		newUserAccessData.setId(getNextId());
		// get existing data
		final UserAccessDataTable userAccessDataTable = deserializeFromXml();
		// add new row
		userAccessDataTable.getRows().add(newUserAccessData);
		// save data
		serializeToXml(userAccessDataTable);
		//
	}

	@Override
	public void update(UserAccessData entity) {
		// get existing data
		final UserAccessDataTable userAccessDataTable = deserializeFromXml();
		// find by ID
		for (final UserAccessData row : userAccessDataTable.getRows()) {
			if (row.getId().equals(entity.getId())) {
				// found!!!
				// copy data
				//row.setNewRequests(entity.getNewRequests());
				//row.setAcceptedRequests(entity.getAcceptedRequests());
				break;
			}
		}
		// save updated table
		serializeToXml(userAccessDataTable);
	}

	@Override
	public UserAccessData get(Long id) {
		// get existing data
		final UserAccessDataTable userAccessDataTable = deserializeFromXml();
		// find by ID
		for (final UserAccessData row : userAccessDataTable.getRows()) {
			if (row.getId().equals(id)) {
				return row;
			}
		}
		return null;
	}

	@Override
	public List<UserAccessData> getAll() {
		// get existing data
		final UserAccessDataTable userAccessDataTable = deserializeFromXml();
		return userAccessDataTable.getRows();
	}

	@Override
	public void delete(Long id) {
		// get existing data
		final UserAccessDataTable userAccessDataTable = deserializeFromXml();
		// find by ID
		UserAccessData toBeDeleted = null;
		for (final UserAccessData row : userAccessDataTable.getRows()) {
			if (row.getId().equals(id)) {
				// found!!!
				toBeDeleted = row;
				break;
			}
		}
		// remove from list
		userAccessDataTable.getRows().remove(toBeDeleted);
		// save updated table
		serializeToXml(userAccessDataTable);
	}

	@Override
	protected Class<UserAccessDataTable> getTableClass() {
		return UserAccessDataTable.class;
	}

}
