package by.grsu.yuzefovich.web.user;


import org.apache.wicket.markup.html.link.Link;


import by.grsu.yuzefovich.datamodel.Tenant;
import by.grsu.yuzefovich.datamodel.UserAccessData;
import by.grsu.yuzefovich.dataaccess.impl.TenantDao;
import by.grsu.yuzefovich.dataaccess.impl.UserAccessDataDao;
//import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import by.grsu.yuzefovich.web.authentication.BasePage;
import org.apache.wicket.markup.html.basic.Label;





//@AuthorizeInstantiation("USER")
public class UserPage extends BasePage {
	
	private String name;
	private String username;
	private String password;
	private Tenant tenant;
	
	public UserPage() {
		
	}
	
	
	public UserPage(String userName, String passWord) {
		
		this.username = userName;
		this.password = passWord;
		
		Long accessDataId = 0l;
		UserAccessDataDao userAccessDataDao = new UserAccessDataDao("testXmlFolder");
		for(UserAccessData userAccessData : userAccessDataDao.getAll())
			if(userAccessData.getLogin().equals(username) && userAccessData.getPassword().equals(password)) {
				accessDataId = userAccessData.getId();
				break;
			}				
		
		TenantDao tenantDao = new TenantDao("testXmlFolder");
		for(Tenant tenant : tenantDao.getAll())
			if (tenant.getUserAccessData().getId().equals(accessDataId)) {
				this.name = tenant.getName();
				this.tenant = tenant;
			}
				
		
		add(new Label("helloMessage", "Welcome " + name + "!"));
		
		add(new Link("addRequest") {

			@Override
			public void onClick() {
				setResponsePage(new AddingRequestPage(username, password, "UserPage"));
			}
		});
		
		add(new Link("showRequests") {
			
			@Override
			public void onClick() {
				setResponsePage(new ShowRequestsPage(username, password));
			}
		});
		
	}
	
}