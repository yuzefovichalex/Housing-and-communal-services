package by.grsu.yuzefovich.web.admin;

import org.apache.wicket.markup.html.link.Link;

//import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import by.grsu.yuzefovich.web.authentication.BasePage;
import by.grsu.yuzefovich.web.user.AddingRequestPage;
import by.grsu.yuzefovich.web.user.ShowRequestsPage;

//@AuthorizeInstantiation("ADMIN")
public class AdminPage extends BasePage {
	
	public AdminPage() {
		
		
		add(new Link("addTenant") {

			@Override
			public void onClick() {
				setResponsePage(new AddingTenantPage());
			}
		});
		
		add(new Link("showRequests") {
			
			@Override
			public void onClick() {
				setResponsePage(new ShowAllRequestsPage());
			}
		});
		
	}
	
}