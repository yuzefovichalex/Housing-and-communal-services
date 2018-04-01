package by.grsu.yuzefovich.test;

import java.io.File;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import by.grsu.yuzefovich.dataaccess.impl.TenantDao;
import by.grsu.yuzefovich.datamodel.Tenant;
import junit.framework.Assert;

public class TenantDaoTest {

	private static final String TEST_XML_FOLDER = "testXmlFolder";
	private static TenantDao tenantDao;

	@BeforeClass
	public static void createDao() {
		tenantDao = new TenantDao(TEST_XML_FOLDER);
	}

		
	@AfterClass
	public static void deleteTestXmlData() {
	//	 write code to clean up test results from FS
		
	}

	@Test
	public void testAdd() {
		System.out.println("Start 'save' test for Tenant");
		final Tenant newTenant = saveNewTenant();
		Assert.assertNotNull(tenantDao.get(newTenant.getId()));
	}

	 @Test
	 public void testDelete() {
	 System.out.println("Start 'delete' test for Tenant");
	 final Tenant newTenant = saveNewTenant();
	 tenantDao.delete(newTenant.getId());
	 Assert.assertNull(tenantDao.get(newTenant.getId()));
	 }
	
	 @Test
	 public void testGetAll() {
	 System.out.println("Start 'getAll' test for Tenant");
	 final int initialRowsCount = tenantDao.getAll().size();
	 saveNewTenant();
	 Assert.assertEquals(tenantDao.getAll().size(), initialRowsCount + 1);
	 }

	private Tenant saveNewTenant() {
		final Tenant newTenant = new Tenant();
		newTenant.setName("Alex");
		newTenant.addRequest("typeOfWork", 15, 10);
		tenantDao.saveNew(newTenant);
		return newTenant;
	}
}
