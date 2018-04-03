package by.grsu.yuzefovich.test;

import java.io.File;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import by.grsu.yuzefovich.dataaccess.impl.UserAccessDataDao;
import by.grsu.yuzefovich.datamodel.UserAccessData;
import junit.framework.Assert;

public class UserAccessDataDaoTest {

	private static final String TEST_XML_FOLDER = "testXmlFolder";
	private static UserAccessDataDao userAccessDataDao;

	@BeforeClass
	public static void createDao() {
		userAccessDataDao = new UserAccessDataDao(TEST_XML_FOLDER);
	}

		
	@AfterClass
	public static void deleteTestXmlData() {
	//	 write code to clean up test results from FS
		
	}

	@Test
	public void testAdd() {
		System.out.println("Start 'save' test for UserAccessData");
		final UserAccessData newUserAccessData = saveNewUserAccessData();
		Assert.assertNotNull(userAccessDataDao.get(newUserAccessData.getId()));
	}

	 @Test
	 public void testDelete() {
	 System.out.println("Start 'delete' test for UserAccessData");
	 final UserAccessData newUserAccessData = saveNewUserAccessData();
	 userAccessDataDao.delete(newUserAccessData.getId());
	 Assert.assertNull(userAccessDataDao.get(newUserAccessData.getId()));
	 }
	
	 @Test
	 public void testGetAll() {
	 System.out.println("Start 'getAll' test for UserAccessData");
	 final int initialRowsCount = userAccessDataDao.getAll().size();
	 saveNewUserAccessData();
	 Assert.assertEquals(userAccessDataDao.getAll().size(), initialRowsCount + 1);
	 }

	private UserAccessData saveNewUserAccessData() {
		final UserAccessData newUserAccessData = new UserAccessData();
		newUserAccessData.setLogin("user");
		newUserAccessData.setPassword("user");
		userAccessDataDao.saveNew(newUserAccessData);
		return newUserAccessData;
	}
}

